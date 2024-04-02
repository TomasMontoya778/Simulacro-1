package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Especializacion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especializacion objEsp = (Especializacion) obj;
        try {
            String sql = """
                    INSERT INTO especializacion (name, description) VALUES (?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objEsp.getNombreEsp());
            objPrepared.setString(2, objEsp.getDescription());
            objPrepared.execute();
            ResultSet resultSet = objPrepared.getGeneratedKeys();
            while (resultSet.next()){
                objEsp.setId_especializacion(resultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "La especialidad fue agregado correctamente.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objEsp;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listEsp = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM especializacion;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("id"));
                objEsp.setNombreEsp(resultSet.getString("name"));
                objEsp.setDescription(resultSet.getString("description"));
                listEsp.add(objEsp);
            }
        }catch (SQLException E){
            System.out.println(E.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return listEsp;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Especializacion objEsp = (Especializacion) obj;
        try {
            String sql = """
                    UPDATE especializacion SET name = ?, description = ? WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objEsp.getNombreEsp());
            preparedStatement.setString(2, objEsp.getDescription());
            preparedStatement.setInt(3, objEsp.getId_especializacion());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "La Especialidad fue actualizada correctamente\n" + objEsp);
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

    public Especializacion findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Especializacion objEsp = null;
        try {
            String sql = """
                    SELECT * FROM especializacion WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("id"));
                objEsp.setNombreEsp(resultSet.getString("name"));
                objEsp.setDescription(resultSet.getString("description"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objEsp;
    }

    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Especializacion objEsp = (Especializacion) obj;
        try {
            String slqDelete = """
                    DELETE FROM especializacion WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objEsp.getId_especializacion());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "La especialidad fue eliminada correctamente");
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
}
