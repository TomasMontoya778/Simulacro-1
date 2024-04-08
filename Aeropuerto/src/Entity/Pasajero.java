package Entity;

public class Pasajero {
    private int idPasajero;
    private String nombrePasajero;
    private String apellidoPasajero;
    private int documento_identidad;
    public Pasajero(){

    }

    public Pasajero(int idPasajero, String nombrePasajero, String apellidoPasajero, int documento_identidad) {
        this.idPasajero = idPasajero;
        this.nombrePasajero = nombrePasajero;
        this.apellidoPasajero = apellidoPasajero;
        this.documento_identidad = documento_identidad;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getApellidoPasajero() {
        return apellidoPasajero;
    }

    public void setApellidoPasajero(String apellidoPasajero) {
        this.apellidoPasajero = apellidoPasajero;
    }

    public int getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(int documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return "\n----Pasajero----" +
                "\nIdentificador del pasajero: " + idPasajero +
                "\nNombre del Pasajero: " + nombrePasajero +
                "\nApellidos del Pasajero: " + apellidoPasajero +
                "\nDocumento de Identidad: " + documento_identidad;
    }
}
