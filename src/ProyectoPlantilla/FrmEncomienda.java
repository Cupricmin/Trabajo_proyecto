package ProyectoPlantilla;

import ProyectoPlantilla.dao.EncomiendaDAO;
import ProyectoPlantilla.modelo.Encomienda;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

/**
 * Formulario de Registro de Encomiendas.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class FrmEncomienda extends JFrame {

    private JTextField txtCodigo, txtNombreRemitente, txtDniRemitente,
            txtNombreDestinatario, txtDniDestinatario, txtOrigen, txtDestino;
    private JComboBox<String> cmbEstado;

    public FrmEncomienda() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Registrar Encomienda");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azul = new Color(41, 98, 168);
        Color blanco = Color.WHITE;

        getContentPane().setBackground(blanco);
        setSize(420, 480);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Registro de Encomienda", SwingConstants.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(azul);
        lblTitulo.setForeground(blanco);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setPreferredSize(new Dimension(420, 45));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(8, 2, 8, 10));
        panelForm.setBackground(blanco);
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 25, 10, 25));

        txtCodigo = new JTextField();
        txtNombreRemitente = new JTextField();
        txtDniRemitente = new JTextField();
        txtNombreDestinatario = new JTextField();
        txtDniDestinatario = new JTextField();
        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        cmbEstado = new JComboBox<>(new String[]{"Registrada", "En transito", "Entregada"});

        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Nombre Remitente:"));
        panelForm.add(txtNombreRemitente);
        panelForm.add(new JLabel("DNI Remitente:"));
        panelForm.add(txtDniRemitente);
        panelForm.add(new JLabel("Nombre Destinatario:"));
        panelForm.add(txtNombreDestinatario);
        panelForm.add(new JLabel("DNI Destinatario:"));
        panelForm.add(txtDniDestinatario);
        panelForm.add(new JLabel("Origen:"));
        panelForm.add(txtOrigen);
        panelForm.add(new JLabel("Destino:"));
        panelForm.add(txtDestino);
        panelForm.add(new JLabel("Estado:"));
        panelForm.add(cmbEstado);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(blanco);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");
        btnGuardar.setBackground(azul);
        btnGuardar.setForeground(blanco);
        btnLimpiar.setBackground(new Color(200, 200, 200));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private void guardar() {
        if (txtCodigo.getText().trim().isEmpty() || txtNombreRemitente.getText().trim().isEmpty()
                || txtNombreDestinatario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios (Código, Remitente, Destinatario)");
            return;
        }

        Encomienda enc = new Encomienda(
                txtCodigo.getText().trim(),
                txtNombreRemitente.getText().trim(),
                txtDniRemitente.getText().trim(),
                txtNombreDestinatario.getText().trim(),
                txtDniDestinatario.getText().trim(),
                txtOrigen.getText().trim(),
                txtDestino.getText().trim(),
                new Date(),
                (String) cmbEstado.getSelectedItem()
        );

        EncomiendaDAO dao = new EncomiendaDAO();
        if (dao.registrar(enc)) {
            JOptionPane.showMessageDialog(this, "Encomienda registrada correctamente");
            limpiar();
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombreRemitente.setText("");
        txtDniRemitente.setText("");
        txtNombreDestinatario.setText("");
        txtDniDestinatario.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        cmbEstado.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmEncomienda().setVisible(true));
    }
}
