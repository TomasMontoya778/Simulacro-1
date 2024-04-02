package Entity;

public class Especializacion {
    private int id_especializacion;
    private String nombreEsp;
    private String description;
    public Especializacion(){

    }
    public Especializacion(int id_especializacion, String nombreEsp, String description) {
        this.id_especializacion = id_especializacion;
        this.nombreEsp = nombreEsp;
        this.description = description;
    }

    public int getId_especializacion() {
        return id_especializacion;
    }

    public void setId_especializacion(int id_especializacion) {
        this.id_especializacion = id_especializacion;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\n---------------Especialidad---------------" +
                "\nIdentificador: " + id_especializacion +
                "\nNombre de la Especialidad: " + nombreEsp +
                "\nDescripción: " + description;
    }
}
