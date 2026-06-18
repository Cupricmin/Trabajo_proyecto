package ProyectoPlantilla.modelo;

import java.util.Date;

/**
 * Clase modelo Incidencia.
 * Representa un problema (daño, extravío, retraso, error de datos)
 * ocurrido durante el proceso de envío de una encomienda.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class Incidencia {

    private int idIncidencia;
    private int idEncomienda;
    private String codigoEncomienda;
    private String tipo;        // Daño, Extravío, Retraso, Error de datos
    private String descripcion;
    private Date fechaRegistro;
    private String estado;      // Pendiente, En proceso, Resuelta
    private String solucion;

    public Incidencia() {
    }

    public Incidencia(int idEncomienda, String codigoEncomienda, String tipo,
            String descripcion, Date fechaRegistro, String estado, String solucion) {
        this.idEncomienda = idEncomienda;
        this.codigoEncomienda = codigoEncomienda;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.solucion = solucion;
    }

    public Incidencia(int idIncidencia, int idEncomienda, String codigoEncomienda, String tipo,
            String descripcion, Date fechaRegistro, String estado, String solucion) {
        this.idIncidencia = idIncidencia;
        this.idEncomienda = idEncomienda;
        this.codigoEncomienda = codigoEncomienda;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.solucion = solucion;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getIdEncomienda() {
        return idEncomienda;
    }

    public void setIdEncomienda(int idEncomienda) {
        this.idEncomienda = idEncomienda;
    }

    public String getCodigoEncomienda() {
        return codigoEncomienda;
    }

    public void setCodigoEncomienda(String codigoEncomienda) {
        this.codigoEncomienda = codigoEncomienda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return "Incidencia #" + idIncidencia + " [" + tipo + "] - " + estado;
    }
}
