package Controller;

import Entity.Especializacion;
import Model.EspModel;

import javax.swing.*;

public class especializacionController {
    public static void insertEsp() {
        EspModel objEspModel = new EspModel();
        String name = JOptionPane.showInputDialog(null, "Ingrese el nombre de la especialidad.");
        String description = JOptionPane.showInputDialog(null, "Ingrese la descripción de la especialidad. (Recuerde que el límite de caracteres de la descripción son 255)");
        Especializacion objEsp = new Especializacion();
        objEsp.setNombreEsp(name);
        objEsp.setDescription(description);
        objEsp = (Especializacion) objEspModel.insert(objEsp);

        JOptionPane.showMessageDialog(null, objEsp.toString());
    }

    public static void listEsp() {
        EspModel objEspModel = new EspModel();
        String text = """
                -------Listado de las Especialidades-------\n
                """;
        for (Object temp : objEspModel.readAll()) {
            Especializacion objEsp = (Especializacion) temp;
            text += objEsp.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listEspString() {
        EspModel objEspModel = new EspModel();
        String text = """
                -------Listado de las Especialidades-------\n
                """;
        for (Object temp : objEspModel.readAll()) {
            Especializacion objEsp = (Especializacion) temp;
            text += objEsp.toString() + "\n";
        }
        return text;
    }

    public static void updateEsp() {
        EspModel objEspModel = new EspModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la Especialidad que quiere actualizar.\n" + listEspString()));
        Especializacion objEsp = objEspModel.findById(idToUpdate);
        if (objEsp == null) {
            JOptionPane.showMessageDialog(null, "El ID que ingresastes no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Nombre.
                        2. Actualizar Descripción.
                        3. Actualizar todo.
                        4. Salir.
                        """);
                switch (option) {
                    case "1":
                        String name = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la especialidad.");
                        objEsp.setNombreEsp(name);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta especialidad?\n" + objEsp);
                        if (confirm == 0) {
                            objEspModel.update(objEsp);
                        }
                        break;
                    case "2":
                        String description = JOptionPane.showInputDialog(null, "Ingresa la nueva descripción de la especialidad.");
                        if (description.length() > 500) {
                            JOptionPane.showMessageDialog(null, "La descripción tiene que ser menor o igual a 500 caracteres.");
                        } else {
                            objEsp.setDescription(description);
                            int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta especialidad?\n" + objEsp);
                            if (confirm2 == 0) {
                                objEspModel.update(objEsp);
                            }
                        }
                        break;
                    case "3":
                        String name2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de la especialidad.");
                        String description2 = JOptionPane.showInputDialog(null, "Ingresa la nueva descripción de la especialidad.");
                        if (description2.length() > 500) {
                            JOptionPane.showMessageDialog(null, "La descripción tiene que ser menor o igual a 500 caracteres.");
                        } else {
                            objEsp.setNombreEsp(name2);
                            objEsp.setDescription(description2);
                            int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta especialidad?\n" + objEsp);
                            if (confirm3 == 0) {
                                objEspModel.update(objEsp);
                            }
                        }
                        break;
                }

            } while (!option.equals("4"));
        }
    }

    public static void deleteEsp() {
        EspModel objEspModel = new EspModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la especialidad que quiere eliminar. (Recuerde que si elimina una especialidad se eliminarán todos los médicos de esa especialidad.)\n" + listEspString()));
        Especializacion objEsp = objEspModel.findById(idToDelete);
        if (objEsp == null) {
            JOptionPane.showMessageDialog(null, "El ID que ingresastes no se encuentra.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar esa especialidad?\n" + objEsp);
            if (confirm == 0) {
                objEspModel.delete(objEsp);
            }
        }
    }
}
