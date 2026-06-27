package ProyectoPlantilla;

import ProyectoPlantilla.dao.EncomiendaDAO;
import ProyectoPlantilla.dao.IncidenciaDAO;
import ProyectoPlantilla.modelo.Encomienda;
import ProyectoPlantilla.modelo.Incidencia;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class FrmIncidencia extends JFrame {

    private JTextField txtCodigoEncomienda;
    private JLabel lblDatosEncomienda;
    private JComboBox<String> cmbTipo;
    private JTextArea txtDescripcion;
    private Encomienda encomiendaEncontrada;

    public FrmIncidencia() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Registrar Incidencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulMedio = new Color(0, 94, 180);
        Color amarillo = new Color(245, 180, 0);
        Color fondo = new Color(245, 248, 252);
        Color blanco = Color.WHITE;

        setSize(560, 580);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 560, 95);
        add(header);

        JLabel lblTitulo = new JLabel("REGISTRO DE INCIDENCIA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(blanco);
        lblTitulo.setBounds(0, 22, 560, 30);
        header.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Busque una encomienda y registre el problema", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(220, 235, 255));
        lblSubtitulo.setBounds(0, 52, 560, 25);
        header.add(lblSubtitulo);

        JPanel card = new JPanel(null);
        card.setBackground(blanco);
        card.setBounds(45, 120, 470, 360);
        card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        add(card);

        JLabel lblCodigo = crearLabel("Código de encomienda:");
        lblCodigo.setBounds(35, 30, 170, 32);
        card.add(lblCodigo);

        txtCodigoEncomienda = crearCampo();
        txtCodigoEncomienda.setBounds(205, 30, 150, 34);
        card.add(txtCodigoEncomienda);

        JButton btnBuscar = crearBoton("BUSCAR", azulMedio, blanco);
        btnBuscar.setBounds(365, 30, 75, 34);
        card.add(btnBuscar);

        lblDatosEncomienda = new JLabel("Ingrese un código y presione BUSCAR.");
        lblDatosEncomienda.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblDatosEncomienda.setForeground(new Color(90, 90, 90));
        lblDatosEncomienda.setBounds(35, 72, 405, 45);
        card.add(lblDatosEncomienda);

        JLabel lblTipo = crearLabel("Tipo de incidencia:");
        lblTipo.setBounds(35, 130, 170, 32);
        card.add(lblTipo);

        cmbTipo = new JComboBox<>(new String[]{
            "Retraso en entrega",
            "Paquete dañado",
            "Extravío de encomienda",
            "Error en datos del destinatario",
            "Problema de atención",
            "Otro"
        });
        cmbTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbTipo.setBounds(205, 130, 235, 34);
        card.add(cmbTipo);

        JLabel lblDescripcion = crearLabel("Descripción:");
        lblDescripcion.setBounds(35, 178, 170, 28);
        card.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JScrollPane scroll = new JScrollPane(txtDescripcion);
        scroll.setBounds(35, 210, 405, 115);
        card.add(scroll);

        JButton btnRegistrar = crearBoton("REGISTRAR INCIDENCIA", amarillo, azulOscuro);
        JButton btnLimpiar = crearBoton("LIMPIAR", azulMedio, blanco);
        JButton btnRegresar = crearBoton("REGRESAR", new Color(178, 34, 34), blanco);

        btnRegistrar.setBounds(45, 500, 185, 38);
        btnLimpiar.setBounds(245, 500, 120, 38);
        btnRegresar.setBounds(380, 500, 135, 38);

        add(btnRegistrar);
        add(btnLimpiar);
        add(btnRegresar);

        btnBuscar.addActionListener(e -> buscarEncomienda());
        btnRegistrar.addActionListener(e -> registrarIncidencia());
        btnLimpiar.addActionListener(e -> limpiar());
        btnRegresar.addActionListener(e -> dispose());
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(0, 31, 84));
        return label;
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 200, 215)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        return campo;
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

    private void buscarEncomienda() {
        String codigo = txtCodigoEncomienda.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código de la encomienda.");
            txtCodigoEncomienda.requestFocus();
            return;
        }

        EncomiendaDAO dao = new EncomiendaDAO();
        encomiendaEncontrada = dao.buscarPorCodigo(codigo);

        if (encomiendaEncontrada == null) {
            lblDatosEncomienda.setForeground(Color.RED);
            lblDatosEncomienda.setText("No se encontró ninguna encomienda con ese código.");
            return;
        }

        lblDatosEncomienda.setForeground(new Color(0, 120, 0));
        lblDatosEncomienda.setText("<html>Encontrada: <b>"
                + encomiendaEncontrada.getNombreRemitente()
                + "</b> → <b>"
                + encomiendaEncontrada.getNombreDestinatario()
                + "</b><br>Estado actual: "
                + encomiendaEncontrada.getEstado()
                + "</html>");
    }

    private void registrarIncidencia() {
        if (encomiendaEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Primero busque una encomienda válida.");
            return;
        }

        String estadoActual = encomiendaEncontrada.getEstado();

        if (estadoActual.equalsIgnoreCase("Con incidencia")) {
            JOptionPane.showMessageDialog(this,
                    "Esta encomienda ya tiene una incidencia registrada.\n"
                    + "No puede registrar otra incidencia sobre el mismo envío.");
            return;
        }

        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Describa la incidencia.");
            txtDescripcion.requestFocus();
            return;
        }

        Incidencia inc = new Incidencia(
                encomiendaEncontrada.getIdEncomienda(),
                encomiendaEncontrada.getCodigo(),
                (String) cmbTipo.getSelectedItem(),
                txtDescripcion.getText().trim(),
                new Date(),
                "Pendiente",
                ""
        );

        IncidenciaDAO dao = new IncidenciaDAO();

        if (dao.registrar(inc)) {
            new EncomiendaDAO().actualizarEstado(
                    encomiendaEncontrada.getIdEncomienda(),
                    "Con incidencia"
            );

            JOptionPane.showMessageDialog(this,
                    "✅ Incidencia registrada correctamente.\n\n"
                    + "Código: " + encomiendaEncontrada.getCodigo()
                    + "\nEstado de incidencia: Pendiente");

            limpiar();
        }
    }

    private void limpiar() {
        txtCodigoEncomienda.setText("");
        txtDescripcion.setText("");
        cmbTipo.setSelectedIndex(0);
        encomiendaEncontrada = null;

        lblDatosEncomienda.setForeground(new Color(90, 90, 90));
        lblDatosEncomienda.setText("Ingrese un código y presione BUSCAR.");

        txtCodigoEncomienda.requestFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmIncidencia().setVisible(true));
    }
}