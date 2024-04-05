package Controller;

import Entity.Pasajero;
import Model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {
    public static void insertPajero() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del pasajero.");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del pasajero.");
        int documento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el documento de identidad del pasajero."));
        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombrePasajero(nombre);
        objPasajero.setApellidoPasajero(apellido);
        objPasajero.setDocumento_identidad(documento);
        objPasajero = (Pasajero) objPasajeroModel.insert(objPasajero);

        JOptionPane.showMessageDialog(null, objPasajero.toString());
    }

    public static void listPasajero() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String text = """
                -------Listado de los pasajeros-------\n
                """;
        for (Object temp : objPasajeroModel.readAll()) {
            Pasajero objPasajero = (Pasajero) temp;
            text += objPasajero.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listPasajeroString() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String text = """
                -------Listado de los pasajeros-------\n
                """;
        for (Object temp : objPasajeroModel.readAll()) {
            Pasajero objPasajero = (Pasajero) temp;
            text += objPasajero.toString() + "\n";
        }
        return text;
    }

    public static void listByName() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        String name = JOptionPane.showInputDialog(null, "Ingresa el Nombre de los pasajeros que quieres buscar.");
        String text = "";
        for (Pasajero temp : objPasajeroModel.listPasajeroByName(name)) {
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay pasajeros registrados con ese nombre.");
            } else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Pasajeros Encontrados----\n" + text);
    }

    public static void updateEsp() {
        PasajeroModel objPasajeroModel = new PasajeroModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del pasajero que quiere actualizar.\n" + listPasajeroString()));
        Pasajero objPasajero = objPasajeroModel.findById(idToUpdate);
        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "El ID que ingresastes no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Nombre.
                        2. Actualizar apellido.
                        3. Actualizar Documento.
                        4. Actualizar todo.
                        5. Salir.
                        """);
                switch (option) {
                    case "1":
                        String name = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del pasajero.");
                        objPasajero.setNombrePasajero(name);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar ese pasajero?\n" + objPasajero);
                        if (confirm == 0) {
                            objPasajeroModel.update(objPasajero);
                        }
                        break;
                    case "2":
                        String apellido = JOptionPane.showInputDialog(null, "Ingresa el nuevo apellido del Pasajero.");

                        JOptionPane.showMessageDialog(null, "La descripción tiene que ser menor o igual a 40 caracteres.");

                        objPasajero.setApellidoPasajero(apellido);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar ese Pasajero?\n" + objPasajero);
                        if (confirm2 == 0) {
                            objPasajeroModel.update(objPasajero);
                        }
                        break;
                    case "3":
                        int documento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad de pasajero."));
                        objPasajero.setDocumento_identidad(documento);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar ese pasajero?\n" + objPasajero);
                        if (confirm4 == 0) {
                            objPasajeroModel.update(objPasajero);
                        }
                        break;
                    case "4":
                        String name2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la Pasajero.");
                        String apellido2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo apellido del Pasajero.");
                        int documento2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad de pasajero."));
                        objPasajero.setNombrePasajero(name2);
                        objPasajero.setApellidoPasajero(apellido2);
                        objPasajero.setDocumento_identidad(documento2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar ese Pasajero?\n" + objPasajero);
                        if (confirm3 == 0) {
                            objPasajeroModel.update(objPasajero);
                        }
                        break;
                }

            } while (!option.equals("4"));
        }
    }
    public static void deletePasajero(){
        PasajeroModel objPasajeroModel = new PasajeroModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del pasajero que quiere eliminar.\n" + listPasajeroString()));
        Pasajero objPasajero = objPasajeroModel.findById(idToDelete);
        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar ese pasajero?\n" + objPasajero);
            if (confirm == 0) { objPasajeroModel.delete(objPasajero);
            }
        }
    }
}
