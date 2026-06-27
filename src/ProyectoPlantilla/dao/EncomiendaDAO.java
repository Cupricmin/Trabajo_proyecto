package ProyectoPlantilla.dao;

import ProyectoPlantilla.cConnection;
import ProyectoPlantilla.modelo.Encomienda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * DAO (Data Access Object) para la tabla Encomienda.
 * Contiene los métodos para registrar, listar, buscar y actualizar
 * encomiendas en la base de datos BDEncomiendas.accdb
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class EncomiendaDAO {

    public boolean registrar(Encomienda e) {
        cConnection cn = new cConnection();
        String sql = "INSERT INTO Encomienda (codigo, nombreRemitente, dniRemitente, "
                + "nombreDestinatario, dniDestinatario, origen, destino, fechaEnvio, estado) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getCodigo());
            ps.setString(2, e.getNombreRemitente());
            ps.setString(3, e.getDniRemitente());
            ps.setString(4, e.getNombreDestinatario());
            ps.setString(5, e.getDniDestinatario());
            ps.setString(6, e.getOrigen());
            ps.setString(7, e.getDestino());
            ps.setDate(8, new java.sql.Date(e.getFechaEnvio().getTime()));
            ps.setString(9, e.getEstado());
            ps.executeUpdate();
            cn.closeConecction();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar encomienda: " + ex.getMessage());
            return false;
        }
    }
    public String generarCodigoAutomatico() {

    cConnection cn = new cConnection();

    String sql = "SELECT MAX(idEncomienda) AS ultimo FROM Encomienda";

    try {

        Connection con = cn.ObtenerConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int numero = 1;

        if (rs.next()) {
            numero = rs.getInt("ultimo") + 1;
        }

        cn.closeConecction();

        return String.format("ENC-%04d", numero);

    } catch (Exception ex) {
        return "ENC-0001";
    }

}

    public List<Encomienda> listarTodas() {
        List<Encomienda> lista = new ArrayList<>();
        cConnection cn = new cConnection();
        String sql = "SELECT * FROM Encomienda ORDER BY idEncomienda";
        try {
            Connection con = cn.ObtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Encomienda e = new Encomienda(
                        rs.getInt("idEncomienda"),
                        rs.getString("codigo"),
                        rs.getString("nombreRemitente"),
                        rs.getString("dniRemitente"),
                        rs.getString("nombreDestinatario"),
                        rs.getString("dniDestinatario"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fechaEnvio"),
                        rs.getString("estado")
                );
                lista.add(e);
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al listar encomiendas: " + ex.getMessage());
        }
        return lista;
    }

    public Encomienda buscarPorCodigo(String codigo) {
        cConnection cn = new cConnection();
        String sql = "SELECT * FROM Encomienda WHERE codigo = ?";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Encomienda e = new Encomienda(
                        rs.getInt("idEncomienda"),
                        rs.getString("codigo"),
                        rs.getString("nombreRemitente"),
                        rs.getString("dniRemitente"),
                        rs.getString("nombreDestinatario"),
                        rs.getString("dniDestinatario"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getDate("fechaEnvio"),
                        rs.getString("estado")
                );
                cn.closeConecction();
                return e;
            }
            cn.closeConecction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar encomienda: " + ex.getMessage());
        }
        return null;
    }

    public boolean actualizarEstado(int idEncomienda, String nuevoEstado) {
        cConnection cn = new cConnection();
        String sql = "UPDATE Encomienda SET estado = ? WHERE idEncomienda = ?";
        try {
            Connection con = cn.ObtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idEncomienda);
            ps.executeUpdate();
            cn.closeConecction();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado: " + ex.getMessage());
            return false;
        }
    }
}
