import Controller.citaController;
import Controller.especializacionController;
import Controller.medicoController;
import Controller.pacienteController;
import Database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    -------Menú Del Centro Médico-----
                    1. Menú para Insertar.
                    2. Menú para Listar.
                    3. Menú para Actualizar.
                    4. Menú para Eliminar.
                    5. Salir.
                    """);
            switch (option) {
                case "1":
                    String optionInsert;
                    do {
                        optionInsert = JOptionPane.showInputDialog("""
                                --------Menú Para insertar------
                                1. Insertar Especialidad.
                                2. Insertar Médico.
                                3. Insertar Paciente.
                                4. Insertar Cita.
                                5. Salir.
                                """);
                        switch (optionInsert) {
                            case "1":
                                especializacionController.insertEsp();
                                break;
                            case "2":
                                medicoController.insertMedico();
                                break;
                            case "3":
                                pacienteController.insertPatience();
                                break;
                            case "4":
                                citaController.insertCita();
                                break;
                        }
                    } while (!optionInsert.equals("5"));
                    break;
                case "2":
                    String optionList;
                    do {
                        optionList = JOptionPane.showInputDialog("""
                                --------Menú Para Listar------
                                1. Listar Especialidad.
                                2. Listar Médicos.
                                3. Listar Todos los Médicos de una especialidad.
                                4. Listar Paciente.
                                5. Listar Paciente por DNI.
                                6. Listar Paciente por Nombre.
                                7. Listar Paciente por Apellido.
                                8. Listar Paciente por Fecha de nacimiento.
                                9. Listar Cita.
                                10. Listar cita por fecha.
                                11. Salir.
                                """);
                        switch (optionList) {
                            case "1":
                                especializacionController.listEsp();
                                break;
                            case "2":
                                medicoController.listMedico();
                                break;
                            case "3":
                                medicoController.listEspMedicos();
                                break;
                            case "4":
                                pacienteController.listPaciente();
                                break;
                            case "5":
                                pacienteController.findByDocument();
                                break;
                            case "6":
                                pacienteController.listByName();
                                break;
                            case "7":
                                pacienteController.listByLastName();
                                break;
                            case "8":
                                pacienteController.listByBirthDate();
                                break;
                            case "9":
                                citaController.listCita();
                                break;
                            case "10":
                                citaController.listByDate();
                                break;
                        }
                    } while (!optionList.equals("11"));
                    break;
                case "3":
                    String optionUpdate;
                    do {
                        optionUpdate = JOptionPane.showInputDialog(null, """
                                --------Menú Para Actualizar------
                                1. Actualizar Especialidad.
                                2. Actualizar Médico.
                                3. Actualizar Paciente.
                                4. Actualizar Cita.
                                5. Salir.
                                """);
                        switch (optionUpdate) {
                            case "1":
                                especializacionController.updateEsp();
                                break;
                            case "2":
                                medicoController.updatePaciente();
                                break;
                            case "3":
                                pacienteController.updatePaciente();
                                break;
                            case "4":
                                citaController.updateCita();
                                break;
                        }
                    } while (!optionUpdate.equals("5"));
                    break;
                case "4":
                    String optionDelete = "";
                    do {
                        optionDelete = JOptionPane.showInputDialog(null, """
                                --------Menú Para Eliminar------
                                1. Eliminar Especialidad.
                                2. Eliminar Médico.
                                3. Eliminar Paciente.
                                4. Eliminar Cita.
                                5. Salir.
                                """);
                        switch (optionDelete){
                            case "1":
                                especializacionController.deleteEsp();
                                break;
                            case "2":
                                medicoController.deleteMedico();
                                break;
                            case "3":
                                pacienteController.deletePaciente();
                                break;
                            case "4":
                                citaController.deleteCita();
                                break;
                        }
                    }while (!optionDelete.equals("5"));
                    break;
            }
        } while (!option.equals("5"));
    }
}