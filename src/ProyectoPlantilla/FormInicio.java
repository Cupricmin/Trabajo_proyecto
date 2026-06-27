package ProyectoPlantilla;

import javax.swing.*;
import java.awt.*;

public class FormInicio extends JFrame {

    private JLabel lblBus;
    private int busX = 610;

    public FormInicio() {
        setTitle("Expreso Los Chankas S.A.C.");
        setSize(1050, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(null) {
            ImageIcon imgFondo = cargarImagen("/ProyectoPlantilla/imagenes/fondo.png", 1050, 720);

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgFondo.getImage(), 0, 90, 1050, 590, this);
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(0, 90, 1050, 590);
            }
        };
        setContentPane(fondo);

        JPanel header = new JPanel(null);
        header.setBackground(new Color(0, 28, 80));
        header.setBounds(0, 0, 1050, 90);
        fondo.add(header);

        JLabel logo = new JLabel(cargarImagen("/ProyectoPlantilla/imagenes/logo.jpeg", 240, 70));
        logo.setBounds(35, 10, 260, 70);
        header.add(logo);

        JButton btnAdmin = new JButton("ACCESO ADMINISTRADOR");
        btnAdmin.setBounds(760, 25, 240, 42);
        btnAdmin.setBackground(new Color(0, 94, 180));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 13));
        btnAdmin.setFocusPainted(false);
        header.add(btnAdmin);

        JLabel titulo = new JLabel("<html>Rastrea tu envío<br>de forma <font color='#F5B400'>rápida y segura</font></html>");
        titulo.setFont(new Font("Arial", Font.BOLD, 42));
        titulo.setForeground(new Color(0, 31, 84));
        titulo.setBounds(60, 125, 620, 115);
        fondo.add(titulo);

        JLabel subtitulo = new JLabel("<html>Consulta el estado de tu encomienda en tiempo real<br>con Expreso Los Chankas S.A.C.</html>");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitulo.setForeground(new Color(20, 40, 80));
        subtitulo.setBounds(60, 250, 560, 55);
        fondo.add(subtitulo);

        lblBus = new JLabel(cargarImagen("/ProyectoPlantilla/imagenes/bus.jpeg", 390, 230));
        lblBus.setBounds(busX, 125, 390, 230);
        fondo.add(lblBus);

        RoundedPanel card = new RoundedPanel(25);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(60, 335, 500, 220);
        fondo.add(card);

        JLabel lblTitulo = new JLabel("BUSCA TU ENCOMIENDA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 31, 84));
        lblTitulo.setBounds(35, 25, 350, 30);
        card.add(lblTitulo);

        JLabel lblInfo = new JLabel("Ingresa tu código para rastrear tu envío");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 13));
        lblInfo.setBounds(35, 58, 350, 20);
        card.add(lblInfo);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(35, 105, 430, 38);
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
        card.add(txtCodigo);

        JButton btnRastrear = new JButton("RASTREAR ENVÍO");
        btnRastrear.setBounds(35, 160, 430, 40);
        btnRastrear.setBackground(new Color(245, 180, 0));
        btnRastrear.setForeground(Color.BLACK);
        btnRastrear.setFont(new Font("Arial", Font.BOLD, 14));
        btnRastrear.setFocusPainted(false);
        card.add(btnRastrear);

        crearTarjeta(fondo, "🛡", "Seguro", "Tu encomienda protegida", 610, 360);
        crearTarjeta(fondo, "⏰", "Puntual", "Cumplimos tus tiempos", 745, 360);
        crearTarjeta(fondo, "📍", "Cobertura", "Más destinos", 880, 360);

        RoundedPanel banner = new RoundedPanel(25);
        banner.setLayout(null);
        banner.setBackground(new Color(0, 31, 84));
        banner.setBounds(60, 590, 925, 70);
        fondo.add(banner);

        JLabel frase = new JLabel("En movimiento contigo");
        frase.setForeground(Color.WHITE);
        frase.setFont(new Font("Arial", Font.BOLD, 22));
        frase.setBounds(35, 10, 350, 25);
        banner.add(frase);

        JLabel contacto = new JLabel("083-123456   |   informes@loschankas.com.pe");
        contacto.setForeground(Color.WHITE);
        contacto.setFont(new Font("Arial", Font.BOLD, 13));
        contacto.setBounds(560, 25, 370, 20);
        banner.add(contacto);

        btnAdmin.addActionListener(e -> {
            new FormIniciarSesion().setVisible(true);
            dispose();
        });

        btnRastrear.addActionListener(e -> {
    String codigo = txtCodigo.getText().trim();

    if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el código de seguimiento.");
        txtCodigo.requestFocus();
        return;
    }

    new FrmRastreoCliente(codigo).setVisible(true);
dispose();
});
        animarBus();
    }

    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        java.net.URL url = getClass().getResource(ruta);

        if (url == null) {
            ruta = ruta.replace("/ProyectoPlantilla/imagenes/", "/ProyectoPlantilla/imagenes/");
            url = getClass().getResource(ruta);
        }

        if (url == null) {
            String rutaArchivo = ruta
                    .replace("/ProyectoPlantilla/imagenes/", "src/ProyectoPlantilla/imagenes/")
                    .replace("/", "\\");

            ImageIcon iconoArchivo = new ImageIcon(rutaArchivo);
            Image imagenArchivo = iconoArchivo.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenArchivo);
        }

        ImageIcon icono = new ImageIcon(url);
        Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
    }

    private void crearTarjeta(JPanel fondo, String icono, String titulo, String texto, int x, int y) {
        RoundedPanel tarjeta = new RoundedPanel(25);
        tarjeta.setLayout(null);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBounds(x, y, 115, 150);
        fondo.add(tarjeta);

        JLabel lblIcono = new JLabel(icono, SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        lblIcono.setBounds(0, 15, 115, 40);
        tarjeta.add(lblIcono);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(0, 31, 84));
        lblTitulo.setBounds(8, 65, 100, 30);
        tarjeta.add(lblTitulo);

        JLabel lblTexto = new JLabel("<html><center>" + texto + "</center></html>", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTexto.setForeground(Color.DARK_GRAY);
        lblTexto.setBounds(8, 100, 100, 40);
        tarjeta.add(lblTexto);
    }

    private void animarBus() {
        Timer timer = new Timer(15, e -> {
            if (busX > 580) {
                busX -= 2;
                lblBus.setLocation(busX, 125);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(0, 0, 0, 35));
            g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, radius, radius);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, radius, radius);

            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        new FormInicio().setVisible(true);
    }
}