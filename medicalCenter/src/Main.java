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
            switch (option){
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
                        switch (optionInsert){
                            case "1":

                                break;
                            case "2":

                                break;
                            case "3":
                                pacienteController.insertPatience();
                                break;
                            case "4":

                                break;
                        }
                    }while (!optionInsert.equals("5"));
                    break;
            }
        }while (!option.equals("5"));
    }
}