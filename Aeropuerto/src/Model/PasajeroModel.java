package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Pasajero;
import Entity.Pasajero;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        try {
            String sql = """
                    INSERT INTO pasajero (nombre, apellido, documento) VALUES (?, ?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objPasajero.getNombrePasajero());
            objPrepared.setString(2, objPasajero.getApellidoPasajero());
            objPrepared.setInt(3, objPasajero.getDocumento_identidad());
            objPrepared.execute();
            ResultSet resultSet = objPrepared.getGeneratedKeys();
            while (resultSet.next()) {
                objPasajero.setIdPasajero(resultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El Pasajero fue agregado correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objPasajero;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listPasajero = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM pasajero;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Pasajero objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
                listPasajero.add(objPasajero);
            }
        } catch (SQLException E) {
            System.out.println(E.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listPasajero;
    }

    public ArrayList<Pasajero> listPasajeroByName(String name) {
        ArrayList<Pasajero> listPasajero = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = """
                    SELECT * FROM pasajero WHERE nombre LIKE ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%" + name + "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
                listPasajero.add(objPasajero);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listPasajero;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        try {
            String sql = """
                    UPDATE pasajero SET nombre = ?, apellido = ?, documento = ? WHERE id_pasajero = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1, objPasajero.getNombrePasajero());
            preparedStatement.setString(2, objPasajero.getApellidoPasajero());
            preparedStatement.setInt(3, objPasajero.getDocumento_identidad());
            preparedStatement.setInt(4, objPasajero.getIdPasajero());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = false;
                JOptionPane.showMessageDialog(null, "El pasajero fue actualizado correctamente\n" + objPasajero);
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

    public Pasajero findById(int id) {
        Connection objConnetion = ConfigDB.openConnection();
        Pasajero objPasajero = null;
        try {
            String sql = """
                    SELECT * FROM pasajero WHERE id_pasajero = ?;
                    """;
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objPasajero;
    }

    @Override
    public boolean delete(Object obj) {

        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Pasajero objPasajero = (Pasajero) obj;
        try {
            String slqDelete = """
                    DELETE FROM pasajero WHERE id_pasajero = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objPasajero.getIdPasajero());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "El Pasajero fue eliminado correctamente");
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
