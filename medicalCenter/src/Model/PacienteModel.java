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
    public Paciente findByDNI(int DNI){
        Connection objConnetion = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = """
                    SELECT * FROM paciente WHERE documento_identidad = ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, DNI);
            ResultSet resultSet = prepared.executeQuery();
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
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String slqDelete = """
                    DELETE FROM paciente WHERE id_paciente = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objPaciente.getId_paciente());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "El paciente fue eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Algo ha salido mal...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }
    public ArrayList<Paciente> listPacienteByName(String name){
        ArrayList<Paciente> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = """
                    SELECT * FROM paciente WHERE name LIKE ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%"+ name+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("name"));
                objPaciente.setApellidoPaciente(resultSet.getString("lastName"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                listPaciente.add(objPaciente);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listPaciente;
    }
    public ArrayList<Paciente> listPacienteByLastName(String lastName){
        ArrayList<Paciente> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = """
                    SELECT * FROM paciente WHERE lastName LIKE ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%"+ lastName+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("name"));
                objPaciente.setApellidoPaciente(resultSet.getString("lastName"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                listPaciente.add(objPaciente);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listPaciente;
    }
    public ArrayList<Paciente> listPacienteByBirthDate(String birth){
        ArrayList<Paciente> listPaciente = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = """
                    SELECT * FROM paciente WHERE fecha_nacimiento LIKE ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%"+ birth+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("name"));
                objPaciente.setApellidoPaciente(resultSet.getString("lastName"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                listPaciente.add(objPaciente);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listPaciente;
    }
    public boolean checkDuplicateDNI(int dni){
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlQuery = "SELECT COUNT(*) FROM paciente WHERE DNI = ?";
            PreparedStatement preparedStatement = objConnection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                flag = count > 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
