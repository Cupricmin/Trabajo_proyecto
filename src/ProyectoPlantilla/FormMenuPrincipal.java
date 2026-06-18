package ProyectoPlantilla;

import java.awt.*;
import javax.swing.*;

/**
 * Menú principal del sistema de Gestión de Incidencias
 * de Expreso Los Chankas S.A.C.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class FormMenuPrincipal extends JFrame {

    public FormMenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Gestión de Incidencias - Expreso Los Chankas S.A.C.");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        Color azul = new Color(41, 98, 168);
        Color azulClaro = new Color(214, 232, 255);
        Color blanco = Color.WHITE;

        getContentPane().setBackground(blanco);
        setLayout(new BorderLayout());
        setSize(450, 480);

        // --- Encabezado ---
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(azul);
        panelTitulo.setPreferredSize(new Dimension(450, 90));
        JLabel lblTitulo = new JLabel("EXPRESO LOS CHANKAS S.A.C.");
        lblTitulo.setForeground(blanco);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JLabel lblSubtitulo = new JLabel("Gestión de Incidencias en Encomiendas");
        lblSubtitulo.setForeground(blanco);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JPanel panelTextos = new JPanel();
        panelTextos.setOpaque(false);
        panelTextos.setLayout(new BoxLayout(panelTextos, BoxLayout.Y_AXIS));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTextos.add(Box.createVerticalGlue());
        panelTextos.add(lblTitulo);
        panelTextos.add(lblSubtitulo);
        panelTextos.add(Box.createVerticalGlue());
        panelTitulo.setLayout(new BorderLayout());
        panelTitulo.add(panelTextos, BorderLayout.CENTER);
        add(panelTitulo, BorderLayout.NORTH);

        // --- Bienvenida ---
        String nombreUsuario = FormIniciarSesion.NombUsuario != null ? FormIniciarSesion.NombUsuario : "Usuario";
        JLabel lblBienvenida = new JLabel("Bienvenido(a), " + nombreUsuario, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblBienvenida.setForeground(azul);
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        // --- Panel de botones ---
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(blanco);
        panelBotones.setLayout(new GridLayout(6, 1, 10, 12));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnRegistrarEncomienda = crearBoton("Registrar Encomienda", azul, blanco);
        JButton btnRegistrarIncidencia = crearBoton("Registrar Incidencia", azul, blanco);
        JButton btnGestionarIncidencias = crearBoton("Gestionar Incidencias", azul, blanco);
        JButton btnReportes = crearBoton("Reportes", azul, blanco);
        JButton btnSalir = crearBoton("Salir", new Color(178, 34, 34), blanco);

        panelBotones.add(btnRegistrarEncomienda);
        panelBotones.add(btnRegistrarIncidencia);
        panelBotones.add(btnGestionarIncidencias);
        panelBotones.add(btnReportes);
        panelBotones.add(btnSalir);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(blanco);
        panelCentral.add(lblBienvenida, BorderLayout.NORTH);
        panelCentral.add(panelBotones, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // --- Acciones ---
        btnRegistrarEncomienda.addActionListener(e -> new FrmEncomienda().setVisible(true));
        btnRegistrarIncidencia.addActionListener(e -> new FrmIncidencia().setVisible(true));
        btnGestionarIncidencias.addActionListener(e -> new FrmGestionIncidencias().setVisible(true));
        btnReportes.addActionListener(e -> mostrarReportes());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton crearBoton(String texto, Color fondo, Color letra) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(letra);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
