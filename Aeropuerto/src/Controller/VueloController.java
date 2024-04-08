package Controller;

import Entity.Avion;
import Entity.Vuelo;
import Model.AvionModel;
import Model.VueloModel;

import javax.swing.*;

public class VueloController {
    public static void insertVuelo() {
        VueloModel objVueloModel = new VueloModel();
        AvionModel objAvionModel = new AvionModel();
        String listAvionString = AvionController.listAvionString();
        try {
            int idVueloToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del avión que quiere agendar el vuelo.\n" + listAvionString));
            Avion objAvion = objAvionModel.findById(idVueloToAdd);
            if (objAvion == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún Avión.");
            } else {
                Vuelo objVuelo = new Vuelo();
                String destino = JOptionPane.showInputDialog("Ingrese el destino que quiere agendar.");
                String fecha_salida = JOptionPane.showInputDialog("Ingrese la Fecha de salida que quieres agendar. (dd/MM/yyyy)");
                String hora = JOptionPane.showInputDialog("Ingrese la hora de salida que quieres agendar. (HH:MMpm o HH:MMam)");
                objVuelo.setDestino(destino);
                objVuelo.setFecha_salida(fecha_salida);
                objVuelo.setHora(hora);
                objVuelo.setIdAvion(objAvion);
                objVuelo = (Vuelo) objVueloModel.insert(objVuelo);
                JOptionPane.showMessageDialog(null, objVuelo.toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public static void listVuelo() {
        VueloModel objVueloModel = new VueloModel();
        String text = """
                -------Listado de los vuelos-------\n
                """;
        for (Object temp : objVueloModel.readAll()) {
            Vuelo objVuelo = (Vuelo) temp;
            text += objVuelo.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listVueloString() {
        VueloModel objVueloModel = new VueloModel();
        String text = """
                -------Listado de los vuelos-------\n
                """;
        for (Object temp : objVueloModel.readAll()) {
            Vuelo objVuelo = (Vuelo) temp;
            text += objVuelo.toString() + "\n";
        }
        return text;
    }
    public static void listByDestine(){
        VueloModel objVueloModel = new VueloModel();
        String destino =JOptionPane.showInputDialog(null, "Ingresa el destino del vuelo que quieres buscar.");
        String text = "";
        for (Vuelo temp : objVueloModel.listCitaByDestine(destino)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay vuelos registrados con ese destino.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Citas Encontradas----\n"+text);
    }
    public static void updateVuelo() {
        VueloModel objVueloModel = new VueloModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del vuelo que quiere actualizar.\n" + listVueloString()));
        Vuelo objVuelo = objVueloModel.findById(idToUpdate);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar fecha de salida.
                        2. Actualizar Hora.
                        3. Actualizar destino.
                        4. Actualizar Todo.
                        5. Salir.
                        """);
                switch (option) {
                    case "1":
                        String fecha = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de salida. (dd/MM/yyyy)");
                        objVuelo.setFecha_salida(fecha);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este vuelo?\n" + objVuelo);
                        if (confirm == 0) {
                            objVueloModel.update(objVuelo);
                        }
                        break;
                    case "2":
                        String hora = JOptionPane.showInputDialog(null, "Ingresa la nueva hora de salida. (HH:MMpm o HH:MMam)");
                        objVuelo.setHora(hora);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este vuelo?\n" + objVuelo);
                        if (confirm2 == 0) {
                            objVueloModel.update(objVuelo);
                        }
                        break;
                    case "3":
                        String destino = JOptionPane.showInputDialog(null, "Ingresa el nuevo destino del vuelo.");
                        objVuelo.setDestino(destino);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este vuelo?\n" + objVuelo);
                        if (confirm3 == 0) {
                            objVueloModel.update(objVuelo);
                        }
                        break;
                    case "4":
                        String fecha2 = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de salida del vuelo. (dd/MM/yyyy)");
                        String hora2 = JOptionPane.showInputDialog(null, "Ingresa la nueva hora de salida del vuelo. (HH:MMpm o HH:MMam)");
                        String destino2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo destino del vuelo.");
                        objVuelo.setFecha_salida(fecha2);
                        objVuelo.setHora(hora2);
                        objVuelo.setDestino(destino2);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este vuelo?\n" + objVuelo);
                        if (confirm4 == 0) {
                            objVueloModel.update(objVuelo);
                        }
                        break;
                }
            }while (!option.equals("5"));
        }
    }
    public static void deleteVuelo(){
        VueloModel objVueloModel = new VueloModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del vuelo que quiere eliminar.\n" + listVueloString()));
        Vuelo objVuelo = objVueloModel.findById(idToDelete);
        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar ese vuelo?\n" + objVuelo);
            if (confirm == 0) {
                objVueloModel.delete(objVuelo);
            }
        }
    }
}
