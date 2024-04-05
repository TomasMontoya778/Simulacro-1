package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigDB {
    public static Connection status = null;

    public static Connection openConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/aeropuertodb";
            String user = "root";
            String passWord = "";

            connection = DriverManager.getConnection(url, user, passWord);
            System.out.println("Conexión establecida correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException error) {
            System.out.println("Error al cargar el controlador JDBC: " + error.getMessage());
        }
        return connection;
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
