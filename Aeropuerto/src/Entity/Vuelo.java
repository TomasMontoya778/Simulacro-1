package Entity;

import java.util.Date;

public class Vuelo {
    private int id;
    private String destino;
    private String fecha_salida;
    private String hora;
    private Avion idAvion;
    public Vuelo(){

    }

    public Vuelo(int id, String destino, String fecha_salida, String hora, Avion idAvion) {
        this.id = id;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora = hora;
        this.idAvion = idAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Avion getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Avion idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public String toString() {
        return "\n----Vuelo----" +
                "\nIndentidicador del vuelo: " + id +
                "\nDestino: " + destino +
                "\nFecha de Salida: " + fecha_salida +
                "\nHora de salida: " + hora +
                idAvion;
    }
}
