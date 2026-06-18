package ProyectoPlantilla;

import ProyectoPlantilla.dao.IncidenciaDAO;
import ProyectoPlantilla.modelo.Incidencia;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Formulario de Gestión de Incidencias.
 * Muestra todas las incidencias registradas y permite cambiar su
 * estado (Pendiente, En proceso, Resuelta) junto con la solución aplicada.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class FrmGestionIncidencias extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JComboBox<String> cmbNuevoEstado;
    private JTextField txtSolucion;

    public FrmGestionIncidencias() {
        initComponents();
        cargarDatos();
        this.setLocationRelativeTo(null);
        this.setTitle("Gestión de Incidencias");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azul = new Color(41, 98, 168);
        Color blanco = Color.WHITE;

        getContentPane().setBackground(blanco);
        setSize(720, 500);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Gestión de Incidencias", SwingConstants.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(azul);
        lblTitulo.setForeground(blanco);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setPreferredSize(new Dimension(720, 45));
        add(lblTitulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Cod. Encomienda", "Tipo", "Descripción", "Estado", "Solución"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(blanco);
        panelInferior.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        cmbNuevoEstado = new JComboBox<>(new String[]{"Pendiente", "En proceso", "Resuelta"});
        txtSolucion = new JTextField(25);
        JButton btnActualizar = new JButton("Actualizar Estado");
        btnActualizar.setBackground(azul);
        btnActualizar.setForeground(blanco);
        JButton btnRefrescar = new JButton("Refrescar");

        panelInferior.add(new JLabel("Nuevo estado:"));
        panelInferior.add(cmbNuevoEstado);
        panelInferior.add(new JLabel("Solución:"));
        panelInferior.add(txtSolucion);
        panelInferior.add(btnActualizar);
        panelInferior.add(btnRefrescar);

        add(panelInferior, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> actualizarEstado());
        btnRefrescar.addActionListener(e -> cargarDatos());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        IncidenciaDAO dao = new IncidenciaDAO();
        List<Incidencia> lista = dao.listarTodas();
        for (Incidencia inc : lista) {
            modelo.addRow(new Object[]{
                inc.getIdIncidencia(),
                inc.getCodigoEncomienda(),
                inc.getTipo(),
                inc.getDescripcion(),
                inc.getEstado(),
                inc.getSolucion()
            });
        }
    }

    private void actualizarEstado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una incidencia de la tabla");
            return;
        }
        int idIncidencia = (int) modelo.getValueAt(fila, 0);
        String nuevoEstado = (String) cmbNuevoEstado.getSelectedItem();
        String solucion = txtSolucion.getText().trim();

        IncidenciaDAO dao = new IncidenciaDAO();
        if (dao.actualizarEstadoYSolucion(idIncidencia, nuevoEstado, solucion)) {
            JOptionPane.showMessageDialog(this, "Incidencia actualizada correctamente");
            txtSolucion.setText("");
            cargarDatos();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmGestionIncidencias().setVisible(true));
    }
}
