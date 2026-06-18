package ProyectoPlantilla.dao;

import ProyectoPlantilla.cConnection;
import ProyectoPlantilla.modelo.Incidencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * DAO (Data Access Object) para la tabla Incidencia.
 * Contiene los métodos para registrar, listar, resolver y actualizar
 * incidencias relacionadas a una encomienda.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class IncidenciaDAO {

    public boolean registrar(Incidencia inc) {
        cConnection cn = new cConnection();
        String sql = "INSERT INTO Incidencia (idEncomienda, codigoEncomienda, tipo, "
                + "descripcion, fechaRegistro, estado, solucion) VALUES (?,?,?,?,?,?,?)";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, inc.getIdEncomienda());
            ps.setString(2, inc.getCodigoEncomienda());
            ps.setString(3, inc.getTipo());
            ps.setString(4, inc.getDescripcion());
            ps.setDate(5, new java.sql.Date(inc.getFechaRegistro().getTime()));
            ps.setString(6, inc.getEstado());
            ps.setString(7, inc.getSolucion() == null ? "" : inc.getSolucion());
            ps.executeUpdate();
            cn.closeConecction();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar incidencia: " + ex.getMessage());
            return false;
        }
    }

    public List<Incidencia> listarTodas() {
        List<Incidencia> lista = new ArrayList<>();
        cConnection cn = new cConnection();
        String sql = "SELECT * FROM Incidencia ORDER BY idIncidencia";
        try {
            Connection con = cn.ObtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Incidencia inc = new Incidencia(
                        rs.getInt("idIncidencia"),
                        rs.getInt("idEncomienda"),
                        rs.getString("codigoEncomienda"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaRegistro"),
                        rs.getString("estado"),
                        rs.getString("solucion")
                );
                lista.add(inc);
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al listar incidencias: " + ex.getMessage());
        }
        return lista;
    }

    public List<Incidencia> listarPorEstado(String estado) {
        List<Incidencia> lista = new ArrayList<>();
        cConnection cn = new cConnection();
        String sql = "SELECT * FROM Incidencia WHERE estado = ? ORDER BY idIncidencia";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Incidencia inc = new Incidencia(
                        rs.getInt("idIncidencia"),
                        rs.getInt("idEncomienda"),
                        rs.getString("codigoEncomienda"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaRegistro"),
                        rs.getString("estado"),
                        rs.getString("solucion")
                );
                lista.add(inc);
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al filtrar incidencias: " + ex.getMessage());
        }
        return lista;
    }

    public boolean actualizarEstadoYSolucion(int idIncidencia, String nuevoEstado, String solucion) {
        cConnection cn = new cConnection();
        String sql = "UPDATE Incidencia SET estado = ?, solucion = ? WHERE idIncidencia = ?";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setString(2, solucion);
            ps.setInt(3, idIncidencia);
            ps.executeUpdate();
            cn.closeConecction();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar incidencia: " + ex.getMessage());
            return false;
        }
    }

    public int contarPorEstado(String estado) {
        cConnection cn = new cConnection();
        String sql = "SELECT COUNT(*) AS total FROM Incidencia WHERE estado = ?";
        int total = 0;
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al contar incidencias: " + ex.getMessage());
        }
        return total;
    }

    public int contarTotal() {
        cConnection cn = new cConnection();
        String sql = "SELECT COUNT(*) AS total FROM Incidencia";
        int total = 0;
        try {
            Connection con = cn.ObtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt("total");
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al contar incidencias: " + ex.getMessage());
        }
        return total;
    }
}
