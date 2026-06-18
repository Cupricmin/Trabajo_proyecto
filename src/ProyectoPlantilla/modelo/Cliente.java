package ProyectoPlantilla.modelo;

/**
 * Clase modelo Cliente.
 * Representa al remitente o destinatario de una encomienda.
 * AUTOR: Carmen Del Rosario Anco - Proyecto Grupal POO
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente() {
    }

    public Cliente(String nombre, String dni, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    public Cliente(int idCliente, String nombre, String dni, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}
