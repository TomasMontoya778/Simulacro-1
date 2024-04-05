package Controller;

import Entity.Especializacion;
import Entity.Medico;
import Model.EspModel;
import Model.MedicoModel;
import javax.swing.*;
import java.util.List;

public class medicoController {
    public static void insertMedico(){
        MedicoModel objMedicoModel = new MedicoModel();
        EspModel objEspModel = new EspModel();
        String listEspString = especializacionController.listEspString();
        try {
            int idEspToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la especialización del Médico\n"+listEspString));
            Especializacion objEspecializacion = objEspModel.findById(idEspToAdd);
            if (objEspecializacion == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar la especialización");
            }else {
                Medico objMedico = new Medico();
                String name = JOptionPane.showInputDialog("Ingrese el nombre del Médico");
                String lastName = JOptionPane.showInputDialog("Ingrese el apellido del Médico");
                objMedico.setNombreMedico(name);
                objMedico.setApellidoMedico(lastName);
                objMedico.setEspecializacion(objEspecializacion);
                objMedico = (Medico) objMedicoModel.insert(objMedico);
                JOptionPane.showMessageDialog(null, objMedico.toString());
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }
    public static void listMedico() {
        MedicoModel objMedicoModel = new MedicoModel();
        String text = """
                -------Listado de los Médicos-------\n
                """;
        for (Object temp : objMedicoModel.readAll()) {
            Medico objMedico = (Medico) temp;
            text += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listMedicoString() {
        MedicoModel objMedicoModel = new MedicoModel();
        String text = """
                -------Listado de los Médicos-------\n
                """;
        for (Object temp : objMedicoModel.readAll()) {
            Medico objMedico = (Medico) temp;
            text += objMedico.toString() + "\n";
        }
        return text;
    }
    public static void listEspMedicos(){
        MedicoModel objMedicoModel = new MedicoModel();
        EspModel objEspModel = new EspModel();
        String listEspString = especializacionController.listEspString();
        try {
            int espID = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la especialización.\n"+listEspString));
            Especializacion objEsp = objEspModel.findById(espID);
            if (objEsp == null) {
                JOptionPane.showMessageDialog(null, "No fue encontrado el ID de la especialización.");
            }else {
                String text = "----Médicos de la Especialización----\n";
                List<Medico> medicos = objMedicoModel.listMedicoEspsById(espID);
                for (Medico temp : medicos){
                    text += temp.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, text);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido.");
        }
    }
    public static void updatePaciente() {
        MedicoModel objMedicoModel = new MedicoModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del Médico que quiere actualizar.\n" + listMedicoString()));
        Medico objMedico = objMedicoModel.findById(idToUpdate);
        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "El ID del médico no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Nombre.
                        2. Actualizar Apellidos.
                        3. Actualizar Todo.
                        4. Salir.
                        """);
                switch (option) {
                    case "1":
                        String name = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del Médico.");
                        objMedico.setNombreMedico(name);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Médico?\n" + objMedico);
                        if (confirm == 0) {
                            objMedicoModel.update(objMedico);
                        }
                        break;
                    case "2":
                        String lastname = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del Médico.");
                        objMedico.setApellidoMedico(lastname);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Médico?\n" + objMedico);
                        if (confirm2 == 0) {
                            objMedicoModel.update(objMedico);
                        }
                        break;
                    case "3":
                        String name2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del Médico.");
                        String lastname2 = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del Médico.");
                        objMedico.setNombreMedico(name2);
                        objMedico.setApellidoMedico(lastname2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este Médico?\n" + objMedico);
                        if (confirm3 == 0) {
                            objMedicoModel.update(objMedico);
                        }
                        break;
                }
            }while (!option.equals("4"));
        }
    }
    public static void deleteMedico(){
        MedicoModel objMedicoModel = new MedicoModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del médico que quiere eliminar. (Recuerde que si elimina un médico se eliminarán todas las citas que tiene agendadas ese médico.)\n" + listMedicoString()));
        Medico objMedico = objMedicoModel.findById(idToDelete);
        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar ese médico?\n" + objMedico);
            if (confirm == 0) {
                objMedicoModel.delete(objMedico);
            }
        }
    }
}
