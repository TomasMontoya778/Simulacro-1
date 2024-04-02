import Controller.especializacionController;
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

                                break;
                            case "3":
                                pacienteController.insertPatience();
                                break;
                            case "4":

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
                                2. Listar Médico.
                                3. Listar Paciente.
                                4. listar Cita.
                                5. Salir.
                                """);
                        switch (optionList) {
                            case "1":
                                especializacionController.listEsp();
                                break;
                            case "2":

                                break;
                            case "3":

                                break;
                            case "4":

                                break;
                        }
                    } while (!optionList.equals("5"));
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
                                break;
                            case "3":
                                break;
                            case "4":
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
                        }
                    }while (!optionDelete.equals("5"));
                    break;
            }
        } while (!option.equals("5"));
    }
}