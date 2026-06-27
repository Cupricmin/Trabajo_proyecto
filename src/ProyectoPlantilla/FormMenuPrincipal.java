package ProyectoPlantilla;

import java.awt.*;
import javax.swing.*;

public class FormMenuPrincipal extends JFrame {

    private JButton btnRegistrarEncomienda;
    private JButton btnRegistrarIncidencia;
    private JButton btnGestionarIncidencias;
    private JButton btnReportes;
    private JButton btnSalir;

    public FormMenuPrincipal() {
        initComponents();
        aplicarPermisosPorRol();
        setLocationRelativeTo(null);
        setTitle("Menú Principal - Expreso Los Chankas S.A.C.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulBoton = new Color(0, 94, 180);
        Color amarillo = new Color(245, 180, 0);
        Color blanco = Color.WHITE;

        setSize(1000, 650);
        setResizable(false);
        setLayout(null);

        JPanel fondo = new JPanel(null);
        fondo.setBackground(new Color(245, 248, 252));
        fondo.setBounds(0, 0, 1000, 650);
        add(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 1000, 95);
        fondo.add(header);

        JLabel titulo = new JLabel("EXPRESO LOS CHANKAS S.A.C.");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(blanco);
        titulo.setBounds(60, 20, 500, 35);
        header.add(titulo);

        JLabel subtitulo = new JLabel("Panel administrativo del sistema");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitulo.setForeground(new Color(220, 235, 255));
        subtitulo.setBounds(60, 55, 400, 25);
        header.add(subtitulo);

        String nombre = FormIniciarSesion.NombUsuario != null ? FormIniciarSesion.NombUsuario : "Usuario";
        String rol = FormIniciarSesion.RolUsuario != null ? FormIniciarSesion.RolUsuario : "Sin rol";

        JLabel usuario = new JLabel("Bienvenido(a), " + nombre + "  |  Rol: " + rol, SwingConstants.RIGHT);
        usuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usuario.setForeground(blanco);
        usuario.setBounds(570, 35, 370, 30);
        header.add(usuario);

        JPanel card = new JPanel(null);
        card.setBackground(blanco);
        card.setBounds(210, 135, 580, 420);
        card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        fondo.add(card);

        JLabel lblMenu = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblMenu.setForeground(azulOscuro);
        lblMenu.setBounds(0, 35, 580, 45);
        card.add(lblMenu);

        JLabel lblDescripcion = new JLabel("Seleccione una opción para continuar", SwingConstants.CENTER);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblDescripcion.setForeground(new Color(80, 80, 80));
        lblDescripcion.setBounds(0, 80, 580, 30);
        card.add(lblDescripcion);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(blanco);
        panelBotones.setLayout(new GridLayout(5, 1, 0, 14));
        panelBotones.setBounds(115, 130, 350, 250);
        card.add(panelBotones);

        btnRegistrarEncomienda = crearBoton("REGISTRAR ENCOMIENDA", azulBoton, blanco);
        btnRegistrarIncidencia = crearBoton("REGISTRAR INCIDENCIA", azulBoton, blanco);
        btnGestionarIncidencias = crearBoton("GESTIONAR INCIDENCIAS", azulBoton, blanco);
        btnReportes = crearBoton("REPORTES", amarillo, azulOscuro);
        btnSalir = crearBoton("VOLVER AL INICIO", new Color(178, 34, 34), blanco);

        panelBotones.add(btnRegistrarEncomienda);
        panelBotones.add(btnRegistrarIncidencia);
        panelBotones.add(btnGestionarIncidencias);
        panelBotones.add(btnReportes);
        panelBotones.add(btnSalir);

        btnRegistrarEncomienda.addActionListener(e -> new FrmEncomienda().setVisible(true));
        btnRegistrarIncidencia.addActionListener(e -> new FrmIncidencia().setVisible(true));
        btnGestionarIncidencias.addActionListener(e -> new FrmGestionIncidencias().setVisible(true));
        btnReportes.addActionListener(e -> new FrmReportes().setVisible(true));

        btnSalir.addActionListener(e -> {
            new FormInicio().setVisible(true);
            dispose();
        });
    }

    private void aplicarPermisosPorRol() {
        String rol = FormIniciarSesion.RolUsuario;

        btnRegistrarEncomienda.setVisible(false);
        btnRegistrarIncidencia.setVisible(false);
        btnGestionarIncidencias.setVisible(false);
        btnReportes.setVisible(false);

        if (rol == null) {
            JOptionPane.showMessageDialog(this, "No se detectó el rol del usuario.");
            return;
        }

        switch (rol) {
            case "Administrador":
                btnRegistrarEncomienda.setVisible(true);
                btnRegistrarIncidencia.setVisible(true);
                btnGestionarIncidencias.setVisible(true);
                btnReportes.setVisible(true);
                break;

            case "Coordinador":
                btnRegistrarIncidencia.setVisible(true);
                btnGestionarIncidencias.setVisible(true);
                btnReportes.setVisible(true);
                break;

            case "Operador":
                btnRegistrarEncomienda.setVisible(true);
                btnRegistrarIncidencia.setVisible(true);
                break;

            case "Recepcionista":
                btnRegistrarEncomienda.setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Rol no reconocido: " + rol);
                break;
        }
    }

    private JButton crearBoton(String texto, Color fondo, Color letra) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(letra);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color colorNormal = fondo;
        Color colorHover = fondo.brighter();

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorNormal);
            }
        });

        return btn;
    }

    private void mostrarReportes() {
        ProyectoPlantilla.dao.IncidenciaDAO dao = new ProyectoPlantilla.dao.IncidenciaDAO();

        int total = dao.contarTotal();
        int pendientes = dao.contarPorEstado("Pendiente");
        int enProceso = dao.contarPorEstado("En proceso");
        int resueltas = dao.contarPorEstado("Resuelta");

        String mensaje = "REPORTE DE INCIDENCIAS\n\n"
                + "Total de incidencias: " + total + "\n"
                + "Pendientes: " + pendientes + "\n"
                + "En proceso: " + enProceso + "\n"
                + "Resueltas: " + resueltas;

        JOptionPane.showMessageDialog(this, mensaje, "Reportes", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FormMenuPrincipal().setVisible(true));
    }
}