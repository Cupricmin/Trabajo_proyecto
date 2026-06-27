package ProyectoPlantilla;

import java.sql.*;

/**
 * Clase de conexión a la base de datos BDEncomiendas.accdb
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class cConnection {

    private final String url = "jdbc:ucanaccess://" + System.getProperty("user.dir") + "/BDEncomiendas.accdb";
    private final String usr = "";
    private final String pswd = "";

    private Connection con;

    public cConnection() {
        cargarDriver();
    }

    /**
     * Carga el driver UCanAccess.
     */
    private void cargarDriver() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            System.out.println("Driver UCanAccess cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el Driver UCanAccess.");
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la conexión con la base de datos.
     */
    public Connection ObtenerConexion() {

        try {

            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, usr, pswd);
                System.out.println("Conexión establecida correctamente.");
            }

        } catch (SQLException ex) {
            System.err.println("Error al conectar con la base de datos:");
            ex.printStackTrace();
            return null;
        }

        return con;
    }

    /**
     * Cierra la conexión.
     */
    public boolean closeConecction() {

        try {

            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada correctamente.");
            }

            return true;

        } catch (SQLException ex) {
            System.err.println("Error al cerrar la conexión:");
            ex.printStackTrace();
            return false;
        }

    }

}