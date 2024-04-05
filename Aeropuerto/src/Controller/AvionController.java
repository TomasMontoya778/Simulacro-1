package Controller;

import Entity.Avion;
import Entity.Avion;
import Model.AvionModel;
import Model.AvionModel;

import javax.swing.*;

public class AvionController {
    public static void insertAvion() {
        AvionModel objAvionModel = new AvionModel();
        String modelo = JOptionPane.showInputDialog(null, "Ingrese el modelo del avión.");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad del avión."));
        Avion objAvion = new Avion();
        objAvion.setModelo(modelo);
        objAvion.setCapacidad(capacidad);
        objAvion = (Avion) objAvionModel.insert(objAvion);

        JOptionPane.showMessageDialog(null, objAvion.toString());
    }
    public static void listAvion() {
        AvionModel objAvionModel = new AvionModel();
        String text = """
                -------Listado de los Aviones-------\n
                """;
        for (Object temp : objAvionModel.readAll()) {
            Avion objAvion = (Avion) temp;
            text += objAvion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listAvionString() {
        AvionModel objAvionModel = new AvionModel();
        String text = """
                -------Listado de los Aviones-------\n
                """;
        for (Object temp : objAvionModel.readAll()) {
            Avion objAvion = (Avion) temp;
            text += objAvion.toString() + "\n";
        }
        return text;
    }
    public static void updateAvion() {
        AvionModel objAvionModel = new AvionModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del avión que quiere actualizar.\n" + listAvionString()));
        Avion objAvion = objAvionModel.findById(idToUpdate);
        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "El ID del avión no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar modelo.
                        2. Actualizar capacidad.
                        3. Actualizar Todo.
                        4. Salir.
                        """);
                switch (option) {
                    case "1":
                        String Modelo = JOptionPane.showInputDialog(null, "Ingresa el nuevo Modelo del Avión.");
                        objAvion.setModelo(Modelo);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Avión?\n" + objAvion);
                        if (confirm == 0) {
                            objAvionModel.update(objAvion);
                        }
                        break;
                    case "2":
                        int Capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la nueva capacidad del Avión."));
                        objAvion.setCapacidad(Capacidad);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Avión?\n" + objAvion);
                        if (confirm2 == 0) {
                            objAvionModel.update(objAvion);
                        }
                        break;
                    case "3":
                        String Modelo2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del Avión.");
                        int Capacidad2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la nueva capacidad del Avión."));
                        objAvion.setModelo(Modelo2);
                        objAvion.setCapacidad(Capacidad2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Avión?\n" + objAvion);
                        if (confirm3 == 0) {
                            objAvionModel.update(objAvion);
                        }
                        break;
                }
            }while (!option.equals("4"));
        }
    }
    public static void deleteAvion(){
        AvionModel objAvionModel = new AvionModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del avión que quiere eliminar.\n" + listAvionString()));
        Avion objAvion = objAvionModel.findById(idToDelete);
        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar ese avión?\n" + objAvion);
            if (confirm == 0) {
                objAvionModel.delete(objAvion);
            }
        }
    }
}
