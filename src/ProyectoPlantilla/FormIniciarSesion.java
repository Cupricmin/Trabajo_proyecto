package ProyectoPlantilla;

import java.awt.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.*;

public class FormIniciarSesion extends javax.swing.JFrame {
   
   public static String NombUsuario; // Global, para usarlo en otros formularios
   public static String RolUsuario;  // Global, rol del usuario logueado (Administrador, Coordinador, etc.)
   
   public FormIniciarSesion() {
    initComponents();
    setLocationRelativeTo(null);
    aplicarDiseno();
}
   private ImageIcon cargarImagenArchivo(String ruta, int ancho, int alto) {
    ImageIcon icono = new ImageIcon(ruta);
    Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    return new ImageIcon(img);
}
   private void aplicarDiseno() {
    setTitle("Acceso Administrador - Expreso Los Chankas S.A.C.");
    setSize(1000, 680);
    setLocationRelativeTo(null);
    setResizable(false);

    jPanel1.setLayout(null);
    jPanel1.setBackground(new Color(225, 242, 255));

    JLabel fondo = new JLabel(cargarImagenArchivo("src/ProyectoPlantilla/imagenes/fondo.png", 1000, 680));
    fondo.setBounds(0, 0, 1000, 680);

    JLabel logo = new JLabel(cargarImagenArchivo("src/ProyectoPlantilla/imagenes/logo.jpeg", 300, 110));
    logo.setBounds(350, 25, 300, 110);
    jPanel1.add(logo);

    JPanel card = new JPanel(null);
    card.setBackground(new Color(255, 255, 255, 235));
    card.setBounds(210, 160, 580, 360);
    card.setBorder(BorderFactory.createLineBorder(new Color(220, 230, 240)));
    jPanel1.add(card);

    JLabel titulo = new JLabel("ACCESO ADMINISTRADOR", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 32));
    titulo.setForeground(new Color(0, 31, 84));
    titulo.setBounds(60, 35, 470, 45);
    card.add(titulo);

    jLabel1.setText("Usuario:");
    jLabel1.setFont(new Font("Arial", Font.BOLD, 17));
    jLabel1.setForeground(new Color(0, 31, 84));
    jLabel1.setBounds(90, 120, 130, 30);
    card.add(jLabel1);

    txtUser.setBounds(230, 118, 280, 38);
    txtUser.setFont(new Font("Arial", Font.PLAIN, 15));
    card.add(txtUser);

    jLabel2.setText("Contraseña:");
    jLabel2.setFont(new Font("Arial", Font.BOLD, 17));
    jLabel2.setForeground(new Color(0, 31, 84));
    jLabel2.setBounds(90, 180, 130, 30);
    card.add(jLabel2);

    txtPassw.setBounds(230, 178, 280, 38);
    txtPassw.setFont(new Font("Arial", Font.PLAIN, 15));
    card.add(txtPassw);

    btnIniciarSesion.setText("INGRESAR");
    btnIniciarSesion.setBounds(130, 260, 160, 45);
    btnIniciarSesion.setBackground(new Color(245, 180, 0));
    btnIniciarSesion.setForeground(Color.BLACK);
    btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 16));
    btnIniciarSesion.setFocusPainted(false);
    card.add(btnIniciarSesion);

    btnCancelarInicio.setText("VOLVER");
    btnCancelarInicio.setBounds(320, 260, 160, 45);
    btnCancelarInicio.setBackground(new Color(0, 94, 180));
    btnCancelarInicio.setForeground(Color.WHITE);
    btnCancelarInicio.setFont(new Font("Arial", Font.BOLD, 16));
    btnCancelarInicio.setFocusPainted(false);
    card.add(btnCancelarInicio);

    JLabel pie = new JLabel("Acceso seguro y confidencial", SwingConstants.CENTER);
    pie.setFont(new Font("Arial", Font.PLAIN, 15));
    pie.setForeground(new Color(0, 70, 150));
    pie.setBounds(160, 320, 280, 25);
    card.add(pie);

    JPanel footer = new JPanel(null);
    footer.setBackground(new Color(0, 31, 84));
    footer.setBounds(0, 560, 1000, 120);
    jPanel1.add(footer);

    JLabel empresa = new JLabel("<html><b>EXPRESO</b><br>LOS CHANKAS S.A.C.</html>");
    empresa.setForeground(Color.WHITE);
    empresa.setFont(new Font("Arial", Font.BOLD, 24));
    empresa.setBounds(60, 25, 300, 70);
    footer.add(empresa);

    JLabel info = new JLabel("Seguridad y confianza     |     Soporte técnico");
    info.setForeground(Color.WHITE);
    info.setFont(new Font("Arial", Font.BOLD, 17));
    info.setBounds(430, 35, 450, 30);
    footer.add(info);

    JLabel info2 = new JLabel("Tu información está protegida con nosotros");
    info2.setForeground(new Color(220, 235, 255));
    info2.setFont(new Font("Arial", Font.PLAIN, 14));
    info2.setBounds(430, 65, 400, 25);
    footer.add(info2);

    jPanel1.add(fondo);
    fondo.setVisible(true);
}
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassw = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        btnCancelarInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INICIAR SESION");
        setMinimumSize(new java.awt.Dimension(200, 150));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setText("Nombre User:");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        jLabel2.setText("Password USer:");

        txtPassw.setToolTipText("");
        txtPassw.setMinimumSize(new java.awt.Dimension(150, 20));
        txtPassw.setName(""); // NOI18N

        btnIniciarSesion.setText("ACEPTAR");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnCancelarInicio.setText("CANCELAR");
        btnCancelarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnIniciarSesion)
                        .addGap(34, 34, 34)
                        .addComponent(btnCancelarInicio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtPassw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciarSesion)
                    .addComponent(btnCancelarInicio))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
}//GEN-LAST:event_txtUserActionPerformed
private void btnCancelarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarInicioActionPerformed
    new FormInicio().setVisible(true);
dispose();
}//GEN-LAST:event_btnCancelarInicioActionPerformed

private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

    cConnection cn = new cConnection(); // invoca a la clase que conecta
    
    Statement  st = null; 
    ResultSet  rs = null;
    String query;
    
    int band = 0; //bandera para verificación
     
    String user = txtUser.getText();
    String passw = txtPassw.getText();
    
    if("".equals(txtUser.getText()))
        JOptionPane.showMessageDialog(null, "Ingrese nombre de usuario");
    else 
        if("".equals(txtPassw.getText()))
            JOptionPane.showMessageDialog(null, "Ingrese contraseña");
        else
        {   query = "SELECT * FROM Usuario WHERE usuario = '"+user+"' AND password = '"+passw+"'";

            try{
                  st = cn.ObtenerConexion().createStatement();  
                  rs = st.executeQuery(query);

                  while(rs.next()){
                        band = 1; // encontró coincidencia de usuario y contraseña
                        RolUsuario = rs.getString("rol"); // guarda el rol del usuario logueado
                    }
            }
            catch (SQLException ex){
                    JOptionPane.showConfirmDialog(null, "ERROR DE ACCESO"+ex);
            }
     
            if (band == 1)
            {
                NombUsuario = txtUser.getText();
                        
                this.setVisible(false);
                
                try {
                        final FormSplash sp = new FormSplash(); 
             
                        sp.setVisible(true);

                        final FormMenuPrincipal vp = new FormMenuPrincipal();

                        Thread hilos = new Thread()
                        {   @Override
                            public void run(){
                                                                           
                                for(int i = 0; i<=100; i++)
                                { 
                                    sp.lblPorcentaje.setText(i+"%");  // En modo diseño, lblPorcentaje cambiar Propiedades Code a Publico

                                    if(i==10)
                                        sp.lblEstado.setText("Cargando Modulos...");  // En modo diseño, lblEstado cambiar Propiedades Code a Publico
                                    else 
                                        if(i==50)
                                            sp.lblEstado.setText("Conectando con la Base de datos...");
                                        else 
                                            if(i==90)
                                                sp.lblEstado.setText("Iniciando Aplicación...");
                                            else 
                                                if(i==100)
                                                {   sp.setVisible(false);
                                                    vp.setVisible(true);
                                                }

                                    sp.pgBarraCarga.setValue(i);  //Coloca porcentaje en la barra // En modo diseño, pgBarraCarga cambiar Propiedades Code a Publico
                                    
                                    try {
                                        Thread.sleep(30);  //Velocidad de Carga 
                                    } 
                                    catch (InterruptedException ex) { 
                                    }
                                }
                            }
                        };
            
                        hilos.start();
                }
                catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Fatal ERROR, Carga erronea de los modulos: " + e.getMessage());
                }
            }
            else
            {   JOptionPane.showMessageDialog(null,"ERROR. Vuelva a Ingresar");
                this.setVisible(rootPaneCheckingEnabled);   // muestra la ventana actual
                this.txtUser.setText("");
                this.txtPassw.setText("");
                this.txtUser.requestFocus();
            }
        }
}//GEN-LAST:event_btnIniciarSesionActionPerformed

public static void main(String args[]) {
  
     java.awt.EventQueue.invokeLater(new Runnable() {

     public void run() {
                new FormIniciarSesion().setVisible(true);
            }
     });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarInicio;
    public javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassw;
    public javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}


