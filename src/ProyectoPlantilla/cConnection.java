package ProyectoPlantilla;

import java.sql.*;

/**
 * Clase de conexión a la base de datos BDEncomiendas.accdb
 * IMPORTANTE: Cambia la ruta del archivo "url" según donde
 * tengas guardado BDEncomiendas.accdb en tu computadora.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class cConnection {

    // *** CAMBIA ESTA RUTA a donde tengas tu archivo BDEncomiendas.accdb ***
    // Ruta relativa: toma la carpeta donde se está ejecutando el proyecto
    // (en NetBeans esto es la raíz del proyecto) y busca ahí BDEncomiendas.accdb
    private String url = "jdbc:ucanaccess://" + System.getProperty("user.dir") + "/BDEncomiendas.accdb";
    private String usr = "";
    private String pswd = "";
    private Connection con = null;

    public cConnection() {  /*Constructor, carga el driver UCanAccess*/
        loadDriver();
    }

    private void loadDriver() { // Carga el driver de la conexión a la base de datos
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al crear el puente JDBC-Access");
        }
    }

    public Connection ObtenerConexion() { // Obtiene una conexión con el nombre del driver especificado
        System.out.println("Estableciendo conexión con " + url);
        try { //Obtiene la conexión
            con = DriverManager.getConnection(url, usr, pswd);
        } catch (SQLException sqle) {
            System.out.println("No se pudo establecer la conexión: " + sqle.getMessage());
            return null;
        }
        System.out.println("Conexión establecida con: " + url);
        return con; //Regresa la conexión
    }

    public boolean closeConecction() {// Cerrar la conexión.
        try {
            con.close();
        } catch (SQLException sqle) {
            System.out.println("No se cerro la conexión");
            return false;
        }
        System.out.println("Conexión cerrada con éxito ");
        return true;
    }
}
