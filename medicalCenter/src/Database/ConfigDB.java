package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDB {
    public static Connection status = null;

    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/medicalcenterdb";
            String user = "root";
            String passWord = "";

            status = (Connection) DriverManager.getConnection(url, user, passWord);
            Statement sentencia = status.createStatement();
            System.out.println("Me conecté");
        }catch (SQLException e){
            System.out.println("Errorrrr " + e.getMessage());
        }catch (ClassNotFoundException error){
            System.out.println("Errorcito " + error.getMessage());
        }
        return status;
    }
    public static void closeConnection(){
        try {
            if (status !=null){
                status.close();
                System.out.println("Cerré conexión");
            }else{
                System.out.println("No hay conexión activas en estos momentos...");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
