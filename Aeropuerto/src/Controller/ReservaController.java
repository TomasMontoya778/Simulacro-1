package Controller;

import Entity.Pasajero;
import Entity.Reserva;
import Entity.Vuelo;
import Entity.Vuelo;
import Model.PasajeroModel;
import Model.VueloModel;
import Model.ReservaModel;

import javax.swing.*;

public class ReservaController {
    public static void insertReserva() {
        ReservaModel objReservaModel = new ReservaModel();
        VueloModel objVueloModel = new VueloModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String listVueloString = VueloController.listVueloString();
        String listPasajeroString = PasajeroController.listPasajeroString();
        try {
            int idVueloToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del vuelo que quiere agendar para la reserva.\n" + listVueloString));
            Vuelo objVuelo = objVueloModel.findById(idVueloToAdd);
            int idPasajeroAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del pasajero que quiere agendar para la reserva.\n" + listPasajeroString));
            Pasajero  objPasajero = objPasajeroModel.findById(idPasajeroAdd);
            if (objVuelo == null && objPasajero == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún Vuelo y pasajero.");
            } else {
                Reserva objReserva = new Reserva();
                String fecha_reserva = JOptionPane.showInputDialog("Ingrese la fecha de reservación que quieres agendar.");
                String asiento = JOptionPane.showInputDialog("Ingrese el asiento que quieres agendar.");
                objReserva.setFecha_reserva(fecha_reserva);
                objReserva.setAsiento(asiento);
                objReserva.setIdPasajero(objPasajero);
                objReserva.setIdVuelo(objVuelo);
                objReserva = (Reserva) objReservaModel.insert(objReserva);
                JOptionPane.showMessageDialog(null, objReserva.toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
