package Entity;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private Pasajero idPasajero;
    private Vuelo idVuelo;
    private String fecha_reserva;
    private String asiento;
    public Reserva(){

    }

    public Reserva(int idReserva, Pasajero idPasajero, Vuelo idVuelo, String fecha_reserva, String asiento) {
        this.idReserva = idReserva;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.fecha_reserva = fecha_reserva;
        this.asiento = asiento;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Pasajero getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Pasajero idPasajero) {
        this.idPasajero = idPasajero;
    }

    public Vuelo getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Vuelo idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "\n----Reserva----" +
                "\nfecha de la reserva: " + fecha_reserva +
                "\nAsiento: " + asiento+"\n"
                + idPasajero+
                idVuelo;
    }
}
