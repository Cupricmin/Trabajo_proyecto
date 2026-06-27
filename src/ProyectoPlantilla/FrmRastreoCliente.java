package ProyectoPlantilla;

import ProyectoPlantilla.dao.EncomiendaDAO;
import ProyectoPlantilla.modelo.Encomienda;
import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class FrmRastreoCliente extends JFrame {

    private JPanel panelResultado;
    private JLabel lblTituloEstado, lblDatos, lblLinea;

    public FrmRastreoCliente(String codigo) {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Rastreo de Encomienda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buscarEncomienda(codigo);
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulMedio = new Color(0, 94, 180);
        Color fondo = new Color(245, 248, 252);
        Color blanco = Color.WHITE;

        setSize(650, 500);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 650, 95);
        add(header);

        JLabel titulo = new JLabel("RASTREO DE ENCOMIENDA", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(blanco);
        titulo.setBounds(0, 20, 650, 35);
        header.add(titulo);

        JLabel subtitulo = new JLabel("Resultado de seguimiento del envío", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(220, 235, 255));
        subtitulo.setBounds(0, 55, 650, 25);
        header.add(subtitulo);

        panelResultado = new JPanel(null);
        panelResultado.setBackground(blanco);
        panelResultado.setBounds(45, 125, 560, 260);
        panelResultado.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));
        add(panelResultado);

        lblTituloEstado = new JLabel("Consultando encomienda...", SwingConstants.CENTER);
        lblTituloEstado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTituloEstado.setForeground(azulOscuro);
        lblTituloEstado.setBounds(20, 25, 520, 32);
        panelResultado.add(lblTituloEstado);

        lblDatos = new JLabel("", SwingConstants.CENTER);
        lblDatos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDatos.setForeground(new Color(40, 40, 40));
        lblDatos.setBounds(30, 70, 500, 100);
        panelResultado.add(lblDatos);

        lblLinea = new JLabel("", SwingConstants.CENTER);
        lblLinea.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblLinea.setForeground(azulMedio);
        lblLinea.setBounds(25, 180, 510, 55);
        panelResultado.add(lblLinea);

        JButton btnOtra = crearBoton("CONSULTAR OTRA", azulMedio, blanco);
        JButton btnCerrar = crearBoton("CERRAR", new Color(178, 34, 34), blanco);

        btnOtra.setBounds(165, 410, 145, 38);
        btnCerrar.setBounds(340, 410, 145, 38);

        add(btnOtra);
        add(btnCerrar);

        btnOtra.addActionListener(e -> {
            dispose();
            new FormInicio().setVisible(true);
        });

        btnCerrar.addActionListener(e -> dispose());
    }

    private JButton crearBoton(String texto, Color fondo, Color letra) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(letra);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void buscarEncomienda(String codigo) {
        codigo = codigo.trim();

        EncomiendaDAO dao = new EncomiendaDAO();
        Encomienda enc = dao.buscarPorCodigo(codigo);

        if (enc == null) {
            lblTituloEstado.setText("❌ Encomienda no encontrada");
            lblTituloEstado.setForeground(new Color(178, 34, 34));

            lblDatos.setText("<html><center>"
                    + "No existe una encomienda registrada con el código:<br>"
                    + "<b>" + codigo + "</b><br><br>"
                    + "Verifique el código ingresado."
                    + "</center></html>");

            lblLinea.setText("");
            return;
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = enc.getFechaEnvio() != null
                ? formato.format(enc.getFechaEnvio())
                : "Sin fecha";

        lblTituloEstado.setText("✅ Encomienda encontrada");
        lblTituloEstado.setForeground(new Color(0, 120, 0));

        lblDatos.setText("<html><center>"
                + "<b>Código:</b> " + enc.getCodigo() + "<br>"
                + "<b>Remitente:</b> " + enc.getNombreRemitente()
                + " &nbsp;&nbsp; | &nbsp;&nbsp; "
                + "<b>Destinatario:</b> " + enc.getNombreDestinatario() + "<br>"
                + "<b>Ruta:</b> " + enc.getOrigen() + " → " + enc.getDestino() + "<br>"
                + "<b>Fecha de envío:</b> " + fecha + "<br>"
                + "<b>Estado actual:</b> " + enc.getEstado()
                + "</center></html>");

        lblLinea.setText(obtenerLineaEstado(enc.getEstado()));
    }

    private String obtenerLineaEstado(String estado) {
        String[] estados = {
            "Recepcionada",
            "En almacén",
            "Despachada",
            "En tránsito",
            "En agencia destino",
            "Lista para recoger",
            "Entregada"
        };

        int posicion = 0;

        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equalsIgnoreCase(estado)) {
                posicion = i;
                break;
            }
        }

        StringBuilder linea = new StringBuilder("<html><center>");

        for (int i = 0; i < estados.length; i++) {
            linea.append(i <= posicion ? "● " : "○ ");
            linea.append(estados[i]);

            if (i < estados.length - 1) {
                linea.append(" → ");
            }
        }

        linea.append("</center></html>");
        return linea.toString();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmRastreoCliente("ENC-0001").setVisible(true));
    }
}