package Controller;

import Entity.Paciente;
import Model.PacienteModel;

import javax.swing.*;

public class pacienteController {
    public static void insertPatience() {
        PacienteModel objPatienceModel = new PacienteModel();
        String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente.");
        String lastName = JOptionPane.showInputDialog(null, "Ingrese el apellido del paciente.");
        String dateOfBirth = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del paciente. (YYYY/MM/DD)");
        int DNI = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Número de identificación del paciente. (Recuerde que el máximo de números es 10 caracteres)"));
        Paciente objPatience = new Paciente();
        objPatience.setNombrePaciente(name);
        objPatience.setApellidoPaciente(lastName);
        objPatience.setFechaNacimiento(dateOfBirth);
        objPatience.setDNI(DNI);
        objPatience = (Paciente) objPatienceModel.insert(objPatience);

        JOptionPane.showMessageDialog(null, objPatience.toString());
    }
}
