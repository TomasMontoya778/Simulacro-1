package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;
import Entity.Avion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        try {
            String sql = """
                    INSERT INTO avion (modelo, capacidad) VALUES (?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objAvion.getModelo());
            objPrepared.setInt(2, objAvion.getCapacidad());
            objPrepared.execute();
            ResultSet resultSet = objPrepared.getGeneratedKeys();
            while (resultSet.next()){
                objAvion.setId(resultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El Avión fue agregado correctamente.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objAvion;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listAvion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM avion;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                Avion objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
                listAvion.add(objAvion);
            }
        }catch (SQLException E){
            System.out.println(E.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return listAvion;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        try {
            String sql = """
                    UPDATE avion SET modelo = ?, capacidad = ? WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objAvion.getModelo());
            preparedStatement.setInt(2, objAvion.getCapacidad());
            preparedStatement.setInt(3, objAvion.getId());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "El Avión fue actualizado correctamente\n" + objAvion);
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
    public Avion findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Avion objAvion = null;
        try {
            String sql = """
                    SELECT * FROM avion WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objAvion;
    }

    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) obj;
        try {
            String slqDelete = """
                    DELETE FROM avion WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objAvion.getId());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "El Avión fue eliminado correctamente");
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
