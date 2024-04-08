package Controller;

import Entity.Cita;
import Entity.Especializacion;
import Entity.Medico;
import Entity.Paciente;
import Model.CitaModel;
import Model.EspModel;
import Model.MedicoModel;
import Model.PacienteModel;

import javax.swing.*;

public class citaController {
    public static void insertCita(){
        CitaModel objCitaModel = new CitaModel();
        MedicoModel objMedicoModel = new MedicoModel();
        PacienteModel objPacienteModel = new PacienteModel();
        String listPacienteString = pacienteController.listPacienteString();
        String listMedicoString = medicoController.listMedicoString();
        try {
            int idPacienteToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del paciente que quiere agendar la cita.\n"+listPacienteString));
            int idMedicoToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del médico que sea acorde a la cita requerida.\n"+listMedicoString));
            Medico objMedico = objMedicoModel.findById(idMedicoToAdd);
            Paciente objPaciente = objPacienteModel.findById(idPacienteToAdd);
            if (objMedico == null && objPaciente == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún médico y paciente.");
            } else if (objMedico == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún médico para agendar la cita.");
            } else if (objPaciente == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún paciente para agendar la cita.");
            } else {
                Cita objCita = new Cita();
                String fecha_cita = JOptionPane.showInputDialog("Ingrese la fecha de la cita que quiere agendar.");
                String hora_fecha = JOptionPane.showInputDialog("Ingrese la hora de la cita que quieres agendar.");
                String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita que quieres agendar. (Recuerda que el motivo debe ser menor a 255 caracteres.)");
                if (motivo.length() > 255){
                    JOptionPane.showMessageDialog(null, "El motivo tiene que ser menor o igual a 500 caracteres.");
                }else {
                    objCita.setFecha_cita(fecha_cita);
                    objCita.setHora_cita(hora_fecha);
                    objCita.setMotivo(motivo);
                    objCita.setMedico(objMedico);
                    objCita.setPaciente(objPaciente);
                    objCita = (Cita) objCitaModel.insert(objCita);
                }
                JOptionPane.showMessageDialog(null, objCita.toString());
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }catch (Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public static void listCita() {
        CitaModel objCitaModel = new CitaModel();
        String text = """
                -------Listado de las citas-------\n
                """;
        for (Object temp : objCitaModel.readAll()) {
            Cita objCita = (Cita) temp;
            text += objCita.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listCitaString() {
        CitaModel objCitaModel = new CitaModel();
        String text = """
                -------Listado de las citas-------\n
                """;
        for (Object temp : objCitaModel.readAll()) {
            Cita objCita = (Cita) temp;
            text += objCita.toString() + "\n";
        }
        return text;
    }
    public static void listByDate(){
        CitaModel objCitaModel = new CitaModel();
        String date =JOptionPane.showInputDialog(null, "Ingresa la fecha de la cita que quieres buscar.");
        String text = "";
        for (Cita temp : objCitaModel.listCitaByDate(date)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay citas registradas con esa fecha.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Citas Encontradas----\n"+text);
    }
    public static void updateCita() {
        CitaModel objCitaModel = new CitaModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la cita que quiere actualizar.\n" + listCitaString()));
        Cita objCita = objCitaModel.findById(idToUpdate);
        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Fecha
                        2. Actualizar Hora
                        3. Actualizar Motivo
                        4. Actualizar Todo.
                        5. Salir.
                        """);
                switch (option) {
                    case "1":
                        String fecha = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de la cita. (YYYY/MM/DD)");
                        objCita.setFecha_cita(fecha);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta cita?\n" + objCita);
                        if (confirm == 0) {
                            objCitaModel.update(objCita);
                        }
                        break;
                    case "2":
                        String hora = JOptionPane.showInputDialog(null, "Ingresa la nueva hora de la cita. (HH:MMpm o HH:MMam)");
                        objCita.setHora_cita(hora);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta cita?\n" + objCita);
                        if (confirm2 == 0) {
                            objCitaModel.update(objCita);
                        }
                        break;
                    case "3":
                        String motivo = JOptionPane.showInputDialog(null, "Ingresa el nuevo motivo de la cita.");
                        objCita.setMotivo(motivo);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta cita?\n" + objCita);
                        if (confirm3 == 0) {
                            objCitaModel.update(objCita);
                        }
                        break;
                    case "4":
                        String fecha2 = JOptionPane.showInputDialog(null, "Ingresa la nueva fecha de la cita. (YYYY/MM/DD)");
                        String hora2 = JOptionPane.showInputDialog(null, "Ingresa la nueva hora de la cita. (HH:MMpm o HH:MMam)");
                        String motivo2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo motivo de la cita.");
                        objCita.setFecha_cita(fecha2);
                        objCita.setHora_cita(hora2);
                        objCita.setMotivo(motivo2);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta cita?\n" + objCita);
                        if (confirm4 == 0) {
                            objCitaModel.update(objCita);
                        }
                        break;
                }
            }while (!option.equals("5"));
        }
    }
    public static void deleteCita(){
        CitaModel objCitaModel = new CitaModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la cta que quiere eliminar.\n" + listCitaString()));
        Cita objCita = objCitaModel.findById(idToDelete);
        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra disponible.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar esa cita?\n" + objCita);
            if (confirm == 0) {
                objCitaModel.delete(objCita);
            }
        }
    }
}
