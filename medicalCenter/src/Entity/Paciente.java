package Entity;

public class Paciente {
    private int id_paciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String fechaNacimiento;
    private int DNI;
    public Paciente(){

    }
    public Paciente(int id_paciente, String nombrePaciente, String apellidoPaciente, String fechaNacimiento, int DNI) {
        this.id_paciente = id_paciente;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.fechaNacimiento = fechaNacimiento;
        this.DNI = DNI;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return "\n--------------Paciente---------------" +
                "\nid del Paciente: " + id_paciente +
                "\nNombre del Paciente: " + nombrePaciente +
                "\nApellidos del Paciente: " + apellidoPaciente +
                "\nFecha de Nacimiento: " + fechaNacimiento +
                "\nDNI: " + DNI;
    }
}
