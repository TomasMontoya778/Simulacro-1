package Entity;

public class Cita {
    private int id_cita;
    private Medico medico;
    private Paciente paciente;
    private String fecha_cita;
    private String hora_cita;
    private String motivo;
    public Cita(){

    }

    public Cita(int id_cita, Medico medico, Paciente paciente, String fecha_cita, String hora_cita, String motivo) {
        this.id_cita = id_cita;
        this.medico = medico;
        this.paciente = paciente;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "\n----------------Cita----------------" +
                "\nIdentificador de la cita: " + id_cita +
                "\nFecha de la cita: " + fecha_cita +
                "\nHora de la cita: " + hora_cita +
                "\nMotivo de la cita: " + motivo +
                medico +
                paciente;
    }
}
