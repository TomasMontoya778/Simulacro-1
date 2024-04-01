package Entity;

public class Cita {
    private int id_cita;
    private int id_medico;
    private int id_paciente;
    private String fecha_cita;
    private String hora_cita;
    private String motivo;
    public Cita(){

    }

    public Cita(int id_cita, int id_medico, int id_paciente, String fecha_cita, String hora_cita, String motivo) {
        this.id_cita = id_cita;
        this.id_medico = id_medico;
        this.id_paciente = id_paciente;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "\n------------Cita-----------" +
                "\nid Cita: " + id_cita +
                "\nid del Médico: " + id_medico +
                "\nid del Paciente: " + id_paciente +
                "\nFecha de la cita: " + fecha_cita +
                "\nHora de la cita: " + hora_cita +
                "\nMotivo de la cita: " + motivo;
    }
}
