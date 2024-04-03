package Controller;

import Entity.Especializacion;
import Entity.Paciente;
import Model.EspModel;
import Model.PacienteModel;

import javax.swing.*;

public class pacienteController {
    public static void insertPatience() {
        PacienteModel objPatienceModel = new PacienteModel();
        String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente.");
        String lastName = JOptionPane.showInputDialog(null, "Ingrese el apellido del paciente.");
        String dateOfBirth = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del paciente. (YYYY/MM/DD)");
        int DNI = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Número de identificación del paciente. (Recuerde que el máximo de números es 10 caracteres)"));
        if (objPatienceModel.checkDuplicateDNI(DNI)) {
            JOptionPane.showMessageDialog(null, "El DNI está duplicado.\nNo se agregó corrextamente.");
        }else {
            Paciente objPatience = new Paciente();
            objPatience.setNombrePaciente(name);
            objPatience.setApellidoPaciente(lastName);
            objPatience.setFechaNacimiento(dateOfBirth);
            objPatience.setDNI(DNI);
            objPatience = (Paciente) objPatienceModel.insert(objPatience);

            JOptionPane.showMessageDialog(null, objPatience.toString());
        }
    }
    public static void listPaciente() {
        PacienteModel objPacienteModel = new PacienteModel();
        String text = """
                -------Listado de los Pacientes-------\n
                """;
        for (Object temp : objPacienteModel.readAll()) {
            Paciente objPaciente = (Paciente) temp;
            text += objPaciente.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listPacienteString() {
        PacienteModel objPacienteModel = new PacienteModel();
        String text = """
                -------Listado de las Especialidades-------\n
                """;
        for (Object temp : objPacienteModel.readAll()) {
            Paciente objPaciente = (Paciente) temp;
            text += objPaciente.toString() + "\n";
        }
        return text;
    }
    public static void updatePaciente() {
        PacienteModel objPacienteModel = new PacienteModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del Paciente que quiere actualizar.\n" + listPacienteString()));
        Paciente objPaciente = objPacienteModel.findById(idToUpdate);
        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "El ID que ingresastes no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Nombre.
                        2. Actualizar Apellidos.
                        3. Actualizar Fecha de nacimiento.
                        4. Actualizar DNI.
                        5. Actualizar Todo.
                        6. Salir.
                        """);
                switch (option) {
                    case "1":
                        String name = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del paciente.");
                        objPaciente.setNombrePaciente(name);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objPaciente);
                        if (confirm == 0) {
                            objPacienteModel.update(objPaciente);
                        }
                        break;
                    case "2":
                        String lastname = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del paciente.");
                        objPaciente.setNombrePaciente(lastname);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objPaciente);
                        if (confirm2 == 0) {
                            objPacienteModel.update(objPaciente);
                        }
                        break;
                    case "3":
                        String fechaNac = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de nacimiento del paciente.");
                        objPaciente.setFechaNacimiento(fechaNac);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objPaciente);
                        if (confirm3 == 0) {
                            objPacienteModel.update(objPaciente);
                        }
                        break;
                    case "4":
                        int newDNI = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el nuevo DNI del paciente."));
                        objPaciente.setDNI(newDNI);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objPaciente);
                        if (confirm4 == 0) {
                            objPacienteModel.update(objPaciente);
                        }
                        break;
                    case "5":
                        String name2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del paciente.");
                        String lastname2 = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del paciente.");
                        String fechaNac2 = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de nacimiento del paciente.");
                        int newDNI2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el nuevo DNI del paciente."));
                        objPaciente.setNombrePaciente(name2);
                        objPaciente.setApellidoPaciente(lastname2);
                        objPaciente.setFechaNacimiento(fechaNac2);
                        objPaciente.setDNI(newDNI2);
                        int confirm5 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objPaciente);
                        if (confirm5 == 0) {
                            objPacienteModel.update(objPaciente);
                        }
                        break;
                }
            }while (!option.equals("6"));
        }
    }
    public static void findByDocument(){
        PacienteModel objPacienteModel = new PacienteModel();
        int DNI = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de documento del paciente que quiere buscar. (Recuerda que el máximo de caracteres es 10)"));
        Paciente objPaciente = objPacienteModel.findByDNI(DNI);
        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "No hay pacientes ingresados con ese número de identificación.");
        }else {
            JOptionPane.showMessageDialog(null,objPaciente.toString());
        }
    }
    public static void deletePaciente() {
        PacienteModel objPacienteModel = new PacienteModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del que quiere eliminar. (Recuerde que si elimina un paciente se eliminarán todas las citas reservadas de ese paciente.)\n" + listPacienteString()));
        Paciente objPaciente = objPacienteModel.findById(idToDelete);
        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar ese paciente?\n" + objPaciente);
            if (confirm == 0) {
                objPacienteModel.delete(objPaciente);
            }
        }
    }
    public static void listByName(){
        PacienteModel objPacienteModel = new PacienteModel();
        String name =JOptionPane.showInputDialog(null, "Ingresa el Nombre de los pacientes que quieres buscar.");
        String text = "";
        for (Paciente temp : objPacienteModel.listPacienteByName(name)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay Pacientes registrados con ese nombre.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Pacientes Encontrados----\n"+text);
    }
    public static void listByLastName(){
        PacienteModel objPacienteModel = new PacienteModel();
        String Lastname =JOptionPane.showInputDialog(null, "Ingresa el Nombre de los pacientes que quieres buscar.");
        String text = "";
        for (Paciente temp : objPacienteModel.listPacienteByLastName(Lastname)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay Pacientes registrados con esos apellidos.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Pacientes Encontrados----\n"+text);
    }
    public static void listByBirthDate(){
        PacienteModel objPacienteModel = new PacienteModel();
        String birth =JOptionPane.showInputDialog(null, "Ingresa el/los Apellidos/s de los pacientes que quieres buscar.  (YYYY/MM/DD)");
        String text = "";
        for (Paciente temp : objPacienteModel.listPacienteByBirthDate(birth)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay Pacientes registrados con esa fecha de nacimiento.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Pacientes Encontrados----\n"+text);
    }
}