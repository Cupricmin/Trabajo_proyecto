package ProyectoPlantilla.modelo;

import java.util.Date;

/**
 * Clase modelo Encomienda.
 * Representa el envío que realiza Expreso Los Chankas S.A.C.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class Encomienda {

    private int idEncomienda;
    private String codigo;
    private String nombreRemitente;
    private String dniRemitente;
    private String nombreDestinatario;
    private String dniDestinatario;
    private String origen;
    private String destino;
    private Date fechaEnvio;
    private String estado; // Registrada, En transito, Entregada, Con incidencia

    public Encomienda() {
    }

    public Encomienda(String codigo, String nombreRemitente, String dniRemitente,
            String nombreDestinatario, String dniDestinatario, String origen,
            String destino, Date fechaEnvio, String estado) {
        this.codigo = codigo;
        this.nombreRemitente = nombreRemitente;
        this.dniRemitente = dniRemitente;
        this.nombreDestinatario = nombreDestinatario;
        this.dniDestinatario = dniDestinatario;
        this.origen = origen;
        this.destino = destino;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
    }

    public Encomienda(int idEncomienda, String codigo, String nombreRemitente, String dniRemitente,
            String nombreDestinatario, String dniDestinatario, String origen,
            String destino, Date fechaEnvio, String estado) {
        this.idEncomienda = idEncomienda;
        this.codigo = codigo;
        this.nombreRemitente = nombreRemitente;
        this.dniRemitente = dniRemitente;
        this.nombreDestinatario = nombreDestinatario;
        this.dniDestinatario = dniDestinatario;
        this.origen = origen;
        this.destino = destino;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
    }

    public int getIdEncomienda() {
        return idEncomienda;
    }

    public void setIdEncomienda(int idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getDniRemitente() {
        return dniRemitente;
    }

    public void setDniRemitente(String dniRemitente) {
        this.dniRemitente = dniRemitente;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getDniDestinatario() {
        return dniDestinatario;
    }

    public void setDniDestinatario(String dniDestinatario) {
        this.dniDestinatario = dniDestinatario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombreDestinatario;
    }
}
