package ProyectoPlantilla;

import ProyectoPlantilla.dao.EncomiendaDAO;
import ProyectoPlantilla.modelo.Encomienda;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.*;

public class FrmEncomienda extends JFrame {

    private JTextField txtCodigo, txtNombreRemitente, txtDniRemitente,
            txtNombreDestinatario, txtDniDestinatario;

    private JComboBox<String> cmbEstado, cmbOrigen, cmbDestino;

    public FrmEncomienda() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Registrar Encomienda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        generarCodigo();
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulMedio = new Color(0, 94, 180);
        Color amarillo = new Color(245, 180, 0);
        Color fondo = new Color(245, 248, 252);
        Color blanco = Color.WHITE;

        setSize(520, 640);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 520, 95);
        add(header);

        JLabel lblTitulo = new JLabel("REGISTRO DE ENCOMIENDA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(blanco);
        lblTitulo.setBounds(0, 22, 520, 30);
        header.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Complete los datos del envío", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(220, 235, 255));
        lblSubtitulo.setBounds(0, 52, 520, 25);
        header.add(lblSubtitulo);

        JPanel card = new JPanel(null);
        card.setBackground(blanco);
        card.setBounds(45, 120, 430, 395);
        card.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        add(card);

        txtCodigo = crearCampo();
        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new Color(235, 235, 235));
        txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtNombreRemitente = crearCampo();
        txtDniRemitente = crearCampo();
        txtNombreDestinatario = crearCampo();
        txtDniDestinatario = crearCampo();

        aplicarValidacionTexto(txtNombreRemitente);
        aplicarValidacionTexto(txtNombreDestinatario);
        aplicarValidacionDNI(txtDniRemitente);
        aplicarValidacionDNI(txtDniDestinatario);

        String[] ciudades = {"Apurímac", "Ayacucho", "Cusco", "Lima"};
        cmbOrigen = crearCombo(ciudades);
        cmbDestino = crearCombo(ciudades);

        cmbEstado = crearCombo(new String[]{
            "Recepcionada",
            "En almacén",
            "Despachada",
            "En tránsito",
            "En agencia destino",
            "Lista para recoger",
            "Entregada"
        });
        cmbEstado.setSelectedItem("Recepcionada");
        cmbEstado.setEnabled(false);

        int xLabel = 35;
        int xCampo = 185;
        int y = 25;
        int alto = 34;
        int espacio = 44;

        agregarFila(card, "Código:", txtCodigo, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "Nombre Remitente:", txtNombreRemitente, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "DNI Remitente:", txtDniRemitente, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "Nombre Destinatario:", txtNombreDestinatario, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "DNI Destinatario:", txtDniDestinatario, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "Origen:", cmbOrigen, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "Destino:", cmbDestino, xLabel, xCampo, y, alto);
        y += espacio;
        agregarFila(card, "Estado:", cmbEstado, xLabel, xCampo, y, alto);

        JButton btnGuardar = crearBoton("GUARDAR", amarillo, azulOscuro);
        JButton btnLimpiar = crearBoton("LIMPIAR", azulMedio, blanco);
        JButton btnCerrar = crearBoton("CERRAR", new Color(178, 34, 34), blanco);

        btnGuardar.setBounds(55, 535, 125, 38);
        btnLimpiar.setBounds(195, 535, 125, 38);
        btnCerrar.setBounds(335, 535, 125, 38);

        add(btnGuardar);
        add(btnLimpiar);
        add(btnCerrar);

        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnCerrar.addActionListener(e -> dispose());
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 200, 215)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        return campo;
    }

    private JComboBox<String> crearCombo(String[] datos) {
        JComboBox<String> combo = new JComboBox<>(datos);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        return combo;
    }

    private void agregarFila(JPanel panel, String texto, JComponent campo,
            int xLabel, int xCampo, int y, int alto) {

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(0, 31, 84));
        label.setBounds(xLabel, y, 145, alto);

        campo.setBounds(xCampo, y, 205, alto);

        panel.add(label);
        panel.add(campo);
    }

    private JButton crearBoton(String texto, Color fondo, Color letra) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(letra);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void aplicarValidacionDNI(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c) || campo.getText().length() >= 8) {
                    e.consume();
                }
            }
        });
    }

    private void aplicarValidacionTexto(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isLetter(c)
                        && c != ' '
                        && c != KeyEvent.VK_BACK_SPACE
                        && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
    }

    private void guardar() {
        String codigo = txtCodigo.getText().trim();
        String nombreRemitente = txtNombreRemitente.getText().trim();
        String dniRemitente = txtDniRemitente.getText().trim();
        String nombreDestinatario = txtNombreDestinatario.getText().trim();
        String dniDestinatario = txtDniDestinatario.getText().trim();
        String origen = (String) cmbOrigen.getSelectedItem();
        String destino = (String) cmbDestino.getSelectedItem();
        String estado = "Recepcionada";

        if (nombreRemitente.isEmpty() || dniRemitente.isEmpty()
                || nombreDestinatario.isEmpty() || dniDestinatario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.");
            return;
        }

        if (!dniRemitente.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El DNI del remitente debe tener exactamente 8 números.");
            txtDniRemitente.requestFocus();
            return;
        }

        if (!dniDestinatario.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El DNI del destinatario debe tener exactamente 8 números.");
            txtDniDestinatario.requestFocus();
            return;
        }

        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(this, "El origen y el destino no pueden ser iguales.");
            cmbDestino.requestFocus();
            return;
        }

        Encomienda enc = new Encomienda(
                codigo,
                nombreRemitente,
                dniRemitente,
                nombreDestinatario,
                dniDestinatario,
                origen,
                destino,
                new Date(),
                estado
        );

        EncomiendaDAO dao = new EncomiendaDAO();

        if (dao.registrar(enc)) {
            JOptionPane.showMessageDialog(this,
                    "✅ Encomienda registrada correctamente.\n\n"
                    + "Código: " + codigo + "\n"
                    + "Estado: " + estado);

            limpiar();
            generarCodigo();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la encomienda.");
        }
    }

    private void generarCodigo() {
        EncomiendaDAO dao = new EncomiendaDAO();
        txtCodigo.setText(dao.generarCodigoAutomatico());
    }

    private void limpiar() {
        txtNombreRemitente.setText("");
        txtDniRemitente.setText("");
        txtNombreDestinatario.setText("");
        txtDniDestinatario.setText("");
        cmbOrigen.setSelectedIndex(0);
        cmbDestino.setSelectedIndex(0);
        cmbEstado.setSelectedItem("Recepcionada");
        txtNombreRemitente.requestFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmEncomienda().setVisible(true));
    }
}