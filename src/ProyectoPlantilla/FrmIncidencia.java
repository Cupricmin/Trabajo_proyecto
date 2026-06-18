package ProyectoPlantilla;

import ProyectoPlantilla.dao.EncomiendaDAO;
import ProyectoPlantilla.dao.IncidenciaDAO;
import ProyectoPlantilla.modelo.Encomienda;
import ProyectoPlantilla.modelo.Incidencia;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

/**
 * Formulario de Registro de Incidencias.
 * Permite buscar la encomienda por código y registrar el problema
 * presentado (daño, extravío, retraso o error de datos).
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class FrmIncidencia extends JFrame {

    private JTextField txtCodigoEncomienda;
    private JLabel lblDatosEncomienda;
    private JComboBox<String> cmbTipo;
    private JTextArea txtDescripcion;
    private Encomienda encomiendaEncontrada;

    public FrmIncidencia() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Registrar Incidencia");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azul = new Color(41, 98, 168);
        Color blanco = Color.WHITE;

        getContentPane().setBackground(blanco);
        setSize(420, 450);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Registro de Incidencia", SwingConstants.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(azul);
        lblTitulo.setForeground(blanco);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setPreferredSize(new Dimension(420, 45));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(blanco);
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        // --- Búsqueda de encomienda ---
        JPanel panelBusqueda = new JPanel(new BorderLayout(5, 0));
        panelBusqueda.setBackground(blanco);
        txtCodigoEncomienda = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(azul);
        btnBuscar.setForeground(blanco);
        panelBusqueda.add(new JLabel("Código de Encomienda: "), BorderLayout.WEST);
        panelBusqueda.add(txtCodigoEncomienda, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        panelForm.add(panelBusqueda);

        lblDatosEncomienda = new JLabel(" ");
        lblDatosEncomienda.setForeground(new Color(0, 120, 0));
        lblDatosEncomienda.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblDatosEncomienda.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        panelForm.add(lblDatosEncomienda);

        // --- Tipo de incidencia ---
        JPanel panelTipo = new JPanel(new BorderLayout(5, 0));
        panelTipo.setBackground(blanco);
        cmbTipo = new JComboBox<>(new String[]{"Daño", "Extravío", "Retraso", "Error de datos"});
        panelTipo.add(new JLabel("Tipo de Incidencia: "), BorderLayout.WEST);
        panelTipo.add(cmbTipo, BorderLayout.CENTER);
        panelTipo.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panelForm.add(panelTipo);

        // --- Descripción ---
        JLabel lblDescripcion = new JLabel("Descripción:");
        panelForm.add(lblDescripcion);
        txtDescripcion = new JTextArea(6, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtDescripcion);
        panelForm.add(scroll);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(blanco);
        JButton btnRegistrar = new JButton("Registrar Incidencia");
        btnRegistrar.setBackground(azul);
        btnRegistrar.setForeground(blanco);
        panelBotones.add(btnRegistrar);
        add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarEncomienda());
        btnRegistrar.addActionListener(e -> registrarIncidencia());
    }

    private void buscarEncomienda() {
        String codigo = txtCodigoEncomienda.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código de la encomienda");
            return;
        }
        EncomiendaDAO dao = new EncomiendaDAO();
        encomiendaEncontrada = dao.buscarPorCodigo(codigo);
        if (encomiendaEncontrada == null) {
            lblDatosEncomienda.setForeground(Color.RED);
            lblDatosEncomienda.setText("No se encontró ninguna encomienda con ese código.");
        } else {
            lblDatosEncomienda.setForeground(new Color(0, 120, 0));
            lblDatosEncomienda.setText("Encontrada: " + encomiendaEncontrada.getNombreRemitente()
                    + " -> " + encomiendaEncontrada.getNombreDestinatario());
        }
    }

    private void registrarIncidencia() {
        if (encomiendaEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Primero busque una encomienda válida");
            return;
        }
        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Describa la incidencia");
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
            // También actualizamos el estado de la encomienda para reflejar el problema
            new EncomiendaDAO().actualizarEstado(encomiendaEncontrada.getIdEncomienda(), "Con incidencia");
            JOptionPane.showMessageDialog(this, "Incidencia registrada correctamente");
            this.dispose();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmIncidencia().setVisible(true));
    }
}
