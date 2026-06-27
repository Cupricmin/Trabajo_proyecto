package ProyectoPlantilla;

import ProyectoPlantilla.dao.IncidenciaDAO;
import ProyectoPlantilla.modelo.Incidencia;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmGestionIncidencias extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JComboBox<String> cmbEstado;
    private JTextField txtSolucion;
    private JButton btnActualizar, btnRefrescar, btnLimpiar, btnRegresar;

    public FrmGestionIncidencias() {
        initComponents();
        cargarIncidencias();
        setLocationRelativeTo(null);
        setTitle("Gestión de Incidencias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulMedio = new Color(0, 94, 180);
        Color amarillo = new Color(245, 180, 0);
        Color fondo = new Color(245, 248, 252);
        Color blanco = Color.WHITE;

        setSize(850, 640);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 850, 90);
        add(header);

        JLabel titulo = new JLabel("GESTIÓN DE INCIDENCIAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(blanco);
        titulo.setBounds(0, 20, 850, 35);
        header.add(titulo);

        JLabel subtitulo = new JLabel("Actualice el estado y solución de las incidencias registradas", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(220, 235, 255));
        subtitulo.setBounds(0, 52, 850, 25);
        header.add(subtitulo);

        JPanel cardTabla = new JPanel(null);
        cardTabla.setBackground(blanco);
        cardTabla.setBounds(35, 115, 780, 300);
        cardTabla.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));
        add(cardTabla);

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Cod. Encomienda", "Tipo", "Descripción", "Estado", "Solución"}, 0
        );

        tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(azulMedio);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(230, 240, 255));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(15, 15, 750, 270);
        cardTabla.add(scroll);

        JPanel cardAcciones = new JPanel(null);
        cardAcciones.setBackground(blanco);
        cardAcciones.setBounds(35, 435, 780, 85);
        cardAcciones.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));
        add(cardAcciones);

        JLabel lblEstado = crearLabel("Nuevo estado:");
        lblEstado.setBounds(25, 25, 100, 30);
        cardAcciones.add(lblEstado);

        cmbEstado = new JComboBox<>(new String[]{
            "Pendiente",
            "En proceso",
            "Resuelta"
        });
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbEstado.setBounds(125, 25, 130, 32);
        cardAcciones.add(cmbEstado);

        JLabel lblSolucion = crearLabel("Solución:");
        lblSolucion.setBounds(275, 25, 70, 30);
        cardAcciones.add(lblSolucion);

        txtSolucion = new JTextField();
        txtSolucion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSolucion.setBounds(345, 25, 260, 32);
        cardAcciones.add(txtSolucion);

        btnActualizar = crearBoton("ACTUALIZAR", amarillo, azulOscuro);
        btnActualizar.setBounds(620, 25, 135, 32);
        cardAcciones.add(btnActualizar);

        btnRefrescar = crearBoton("REFRESCAR", azulMedio, blanco);
        btnLimpiar = crearBoton("LIMPIAR", azulMedio, blanco);
        btnRegresar = crearBoton("REGRESAR", new Color(178, 34, 34), blanco);

        btnRefrescar.setBounds(160, 535, 150, 36);
        btnLimpiar.setBounds(350, 535, 150, 36);
        btnRegresar.setBounds(540, 535, 150, 36);

        add(btnRefrescar);
        add(btnLimpiar);
        add(btnRegresar);

        btnActualizar.addActionListener(e -> actualizarIncidencia());
        btnRefrescar.addActionListener(e -> cargarIncidencias());
        btnLimpiar.addActionListener(e -> limpiar());
        btnRegresar.addActionListener(e -> dispose());
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(0, 31, 84));
        return label;
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

    private void cargarIncidencias() {
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

    private void actualizarIncidencia() {
        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una incidencia de la tabla.");
            return;
        }

        int idIncidencia = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        String nuevoEstado = cmbEstado.getSelectedItem().toString();
        String solucion = txtSolucion.getText().trim();

        if (nuevoEstado.equals("Resuelta") && solucion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe escribir una solución para marcar la incidencia como resuelta.");
            txtSolucion.requestFocus();
            return;
        }

        IncidenciaDAO dao = new IncidenciaDAO();

        if (dao.actualizarEstadoYSolucion(idIncidencia, nuevoEstado, solucion)) {
            JOptionPane.showMessageDialog(this, "Incidencia actualizada correctamente.");
            cargarIncidencias();
            limpiar();
        }
    }

    private void limpiar() {
        tabla.clearSelection();
        cmbEstado.setSelectedIndex(0);
        txtSolucion.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmGestionIncidencias().setVisible(true));
    }
}