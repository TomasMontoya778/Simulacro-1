package Controller;

import Entity.Pasajero;
import Entity.Reserva;
import Entity.Vuelo;
import Entity.Vuelo;
import Model.PasajeroModel;
import Model.VueloModel;
import Model.ReservaModel;

import javax.swing.*;
import java.util.List;

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
            Pasajero objPasajero = objPasajeroModel.findById(idPasajeroAdd);
            if (objVuelo == null && objPasajero == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún Vuelo y pasajero.");
            } else {
                Reserva objReserva = new Reserva();
                String fecha_reserva = JOptionPane.showInputDialog("Ingrese la fecha de reservación que quieres agendar.");
                String asiento = JOptionPane.showInputDialog("Ingrese el asiento que quieres agendar.");
                if (objReservaModel.checkDuplicateAsiento(asiento)){
                    JOptionPane.showMessageDialog(null, "El Asiento ya está reservado.\nNo se agregó corrextamente.");
                }else {
                    objReserva.setFecha_reserva(fecha_reserva);
                    objReserva.setAsiento(asiento);
                    objReserva.setIdPasajero(objPasajero);
                    objReserva.setIdVuelo(objVuelo);
                    objReserva = (Reserva) objReservaModel.insert(objReserva);
                    JOptionPane.showMessageDialog(null, objReserva.toString());
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void listReserva() {
        ReservaModel objReservaModel = new ReservaModel();
        String text = """
                -------Listado de las reservas-------\n
                """;
        for (Object temp : objReservaModel.readAll()) {
            Reserva objReserva = (Reserva) temp;
            text += objReserva.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String ListReservaString() {
        ReservaModel objReservaModel = new ReservaModel();
        String text = """
                -------Listado de las reservas-------\n
                """;
        for (Object temp : objReservaModel.readAll()) {
            Reserva objReserva = (Reserva) temp;
            text += objReserva.toString() + "\n";
        }
        return text;
    }

    public static void listReservaFlys() {
        ReservaModel objReservaModel = new ReservaModel();
        VueloModel objVueloModel = new VueloModel();
        String listVuelo = VueloController.listVueloString();
        try {
            int vueloId = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del vuelo.\n" + listVuelo));
            Vuelo objVuelo = objVueloModel.findById(vueloId);
            if (objVuelo == null) {
                JOptionPane.showMessageDialog(null, "No fue encontrado el ID del vuelo.");
            } else {
                String text = "----Reservaciones del Vuelo----\n";
                List<Reserva> reserva = objReservaModel.listReservacionesDeVueloById(vueloId);
                for (Reserva temp : reserva) {
                    text += temp.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, text);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido.");
        }
    }

    public static void updateReserva() {
        ReservaModel objReservaModel = new ReservaModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la reserva que quiere actualizar.\n" + ListReservaString()));
        Reserva objReserva = objReservaModel.findById(idToUpdate);
        if (objReserva == null) {
            JOptionPane.showMessageDialog(null, "El ID de la reserva no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Fecha de la reserva.
                        2. Actualizar Asiento.
                        3. Actualizar Todo.
                        4. Salir.
                        """);
                switch (option) {
                    case "1":
                        String newDate = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de la reserva.");
                        objReserva.setFecha_reserva(newDate);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esa Reserva?\n" + objReserva);
                        if (confirm == 0) {
                            objReservaModel.update(objReserva);
                        }
                        break;
                    case "2":
                        String Asiento = JOptionPane.showInputDialog(null, "Ingresa el nuevo Asiento de la reserva.");
                        objReserva.setAsiento(Asiento);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esa Reserva?\n" + objReserva);
                        if (confirm2 == 0) {
                            objReservaModel.update(objReserva);
                        }
                        break;
                    case "3":
                        String newDate2 = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de la reserva.");
                        String Asiento2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo Asiento de la reserva.");
                        objReserva.setFecha_reserva(newDate2);
                        objReserva.setAsiento(Asiento2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esa Reserva?\n" + objReserva);
                        if (confirm3 == 0) {
                            objReservaModel.update(objReserva);
                        }
                        break;
                }
            } while (!option.equals("4"));
        }
    }
    public static void deleteReserva(){
        ReservaModel objReservaModel = new ReservaModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la reserva que quiere eliminar.\n" + ListReservaString()));
        Reserva objReserva = objReservaModel.findById(idToDelete);
        if (objReserva == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar esa Reserva?\n" + objReserva);
            if (confirm == 0) {
                objReservaModel.delete(objReserva);
            }
        }
    }
}
