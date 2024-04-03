package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Especializacion;
import Entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
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

    public Paciente findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = """
                    SELECT * FROM paciente WHERE id_paciente = ?;
                    """;
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("name"));
                objPaciente.setApellidoPaciente(resultSet.getString("lastName"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPaciente;
    }
    @Override
    public List<Object> readAll() {
        List<Object> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM paciente;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("name"));
                objPaciente.setApellidoPaciente(resultSet.getString("lastName"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                listPaciente.add(objPaciente);
            }
        }catch (SQLException E){
            System.out.println(E.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return listPaciente;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = """
                    UPDATE paciente SET name = ?, lastName = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objPaciente.getNombrePaciente());
            preparedStatement.setString(2, objPaciente.getApellidoPaciente());
            preparedStatement.setString(3, objPaciente.getFechaNacimiento());
            preparedStatement.setInt(4, objPaciente.getDNI());
            preparedStatement.setInt(5, objPaciente.getId_paciente());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "El paciente fue actualizado correctamente\n" + objPaciente);
            }else {
                JOptionPane.showMessageDialog(null, "Algo ha salido mal...");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
