package Entity;

public class Medico extends Especializacion{
    private int id_medico;
    private String nombreMedico;
    private String apellidoMedico;
    public Medico(){

    }
    public Medico(int id_especializacion, String nombreEsp, String description, int id_medico, String nombreMedico, String apellidoMedico) {
        super(id_especializacion, nombreEsp, description);
        this.id_medico = id_medico;
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    @Override
    public String toString() {
        return "\n------------Medico-----------" +
                "\nidMedico: " + id_medico +
                "\nNombre del Médico: " + nombreMedico +
                "\nApellidos del Médico: " + apellidoMedico;
    }
}
