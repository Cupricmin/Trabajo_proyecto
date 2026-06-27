package ProyectoPlantilla;

import ProyectoPlantilla.dao.IncidenciaDAO;
import java.awt.*;
import javax.swing.*;

public class FrmReportes extends JFrame {

    private JLabel lblTotal, lblPendientes, lblProceso, lblResueltas;
    private JProgressBar barraPendientes, barraProceso, barraResueltas;

    public FrmReportes() {
        initComponents();
        cargarReportes();
        setLocationRelativeTo(null);
        setTitle("Reportes - Expreso Los Chankas S.A.C.");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Color azulOscuro = new Color(0, 31, 84);
        Color azulMedio = new Color(0, 94, 180);
        Color amarillo = new Color(245, 180, 0);
        Color fondo = new Color(245, 248, 252);
        Color blanco = Color.WHITE;

        setSize(620, 520);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(azulOscuro);
        header.setBounds(0, 0, 620, 95);
        add(header);

        JLabel titulo = new JLabel("REPORTE DE INCIDENCIAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(blanco);
        titulo.setBounds(0, 22, 620, 35);
        header.add(titulo);

        JLabel subtitulo = new JLabel("Resumen general del sistema", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(220, 235, 255));
        subtitulo.setBounds(0, 55, 620, 25);
        header.add(subtitulo);

        JPanel cardTotal = crearTarjeta(40, 120, 540, 75);
        add(cardTotal);

        JLabel txtTotal = new JLabel("TOTAL DE INCIDENCIAS");
        txtTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        txtTotal.setForeground(azulOscuro);
        txtTotal.setBounds(25, 15, 300, 25);
        cardTotal.add(txtTotal);

        lblTotal = new JLabel("0", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblTotal.setForeground(amarillo);
        lblTotal.setBounds(380, 10, 120, 45);
        cardTotal.add(lblTotal);

        JPanel cardDetalle = crearTarjeta(40, 215, 540, 185);
        add(cardDetalle);

        lblPendientes = crearLabelDato("Pendientes: 0");
        lblPendientes.setBounds(25, 20, 220, 25);
        cardDetalle.add(lblPendientes);

        barraPendientes = crearBarra();
        barraPendientes.setBounds(25, 50, 490, 22);
        cardDetalle.add(barraPendientes);

        lblProceso = crearLabelDato("En proceso: 0");
        lblProceso.setBounds(25, 80, 220, 25);
        cardDetalle.add(lblProceso);

        barraProceso = crearBarra();
        barraProceso.setBounds(25, 110, 490, 22);
        cardDetalle.add(barraProceso);

        lblResueltas = crearLabelDato("Resueltas: 0");
        lblResueltas.setBounds(25, 140, 220, 25);
        cardDetalle.add(lblResueltas);

        barraResueltas = crearBarra();
        barraResueltas.setBounds(25, 165, 490, 22);
        cardDetalle.add(barraResueltas);

        JButton btnActualizar = crearBoton("ACTUALIZAR", amarillo, azulOscuro);
        JButton btnRegresar = crearBoton("REGRESAR", new Color(178, 34, 34), blanco);

        btnActualizar.setBounds(165, 425, 135, 38);
        btnRegresar.setBounds(320, 425, 135, 38);

        add(btnActualizar);
        add(btnRegresar);

        btnActualizar.addActionListener(e -> cargarReportes());
        btnRegresar.addActionListener(e -> dispose());
    }

    private JPanel crearTarjeta(int x, int y, int ancho, int alto) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(x, y, ancho, alto);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 235)));
        return panel;
    }

    private JLabel crearLabelDato(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(0, 31, 84));
        return label;
    }

    private JProgressBar crearBarra() {
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        barra.setFont(new Font("Segoe UI", Font.BOLD, 11));
        return barra;
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

    private void cargarReportes() {
        IncidenciaDAO dao = new IncidenciaDAO();

        int total = dao.contarTotal();
        int pendientes = dao.contarPorEstado("Pendiente");
        int proceso = dao.contarPorEstado("En proceso");
        int resueltas = dao.contarPorEstado("Resuelta");

        lblTotal.setText(String.valueOf(total));
        lblPendientes.setText("Pendientes: " + pendientes);
        lblProceso.setText("En proceso: " + proceso);
        lblResueltas.setText("Resueltas: " + resueltas);

        int pPendientes = calcularPorcentaje(pendientes, total);
        int pProceso = calcularPorcentaje(proceso, total);
        int pResueltas = calcularPorcentaje(resueltas, total);

        barraPendientes.setValue(pPendientes);
        barraPendientes.setString(pPendientes + "%");

        barraProceso.setValue(pProceso);
        barraProceso.setString(pProceso + "%");

        barraResueltas.setValue(pResueltas);
        barraResueltas.setString(pResueltas + "%");
    }

    private int calcularPorcentaje(int cantidad, int total) {
        if (total == 0) {
            return 0;
        }
        return (cantidad * 100) / total;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FrmReportes().setVisible(true));
    }
}