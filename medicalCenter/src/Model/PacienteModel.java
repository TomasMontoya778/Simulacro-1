package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class PacienteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = """
                    INSERT INTO paciente (name, lastName, fecha_nacimiento, documento_identidad) VALUES (?, ?, ?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objPaciente.getNombrePaciente());
            objPrepared.setString(2, objPaciente.getApellidoPaciente());
            objPrepared.setString(3, objPaciente.getFechaNacimiento());
            objPrepared.setInt(4, objPaciente.getDNI());

            objPrepared.execute();
            ResultSet resultSet = objPrepared.getGeneratedKeys();
            while (resultSet.next()){
                objPaciente.setId_paciente(resultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El paciente fue agregado correctamente.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objPaciente;
    }

    @Override
    public List<Object> readAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
