package ProyectoPlantilla.dao;

import ProyectoPlantilla.cConnection;
import ProyectoPlantilla.modelo.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * DAO para la tabla Usuario, usado en el FormIniciarSesion (Login).
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class UsuarioDAO {

    public Usuario validarLogin(String usuario, String password) {
        cConnection cn = new cConnection();
        String sql = "SELECT * FROM Usuario WHERE usuario = ? AND password = ?";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
                cn.closeConecction();
                return u;
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al validar usuario: " + ex.getMessage());
        }
        return null;
    }
}
