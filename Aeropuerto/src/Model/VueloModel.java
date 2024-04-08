package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;
import Entity.Vuelo;
import Entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        try {
            String sqlInsert = """
                   INSERT INTO vuelo (destino, fecha_salida, hora, id_avion) VALUES (?, ?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objVuelo.getDestino());
            prepared.setString(2, objVuelo.getFecha_salida());
            prepared.setString(3, objVuelo.getHora());
            prepared.setInt(4, objVuelo.getIdAvion().getId());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objVuelo.setId(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "El vuelo fue agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objVuelo;
    }

    @Override
    public List<Object> readAll(){
        List<Object> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, avion.id as id_avion, avion.modelo, avion.capacidad FROM vuelo" +
                    " INNER JOIN avion ON vuelo.id_avion = avion.id";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Vuelo objVuelo = new Vuelo();
                objVuelo.setId(resultSet.getInt("id_vuelo"));
                objVuelo.setDestino(resultSet.getString("destino"));
                objVuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                objVuelo.setHora(resultSet.getString("hora"));
                Avion objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id_avion"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
                objVuelo.setIdAvion(objAvion);
                listVuelo.add(objVuelo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listVuelo;
    }

    public ArrayList<Vuelo> listCitaByDestine(String destine){
        ArrayList<Vuelo> listVuelo = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo;
        try {
            String sql = """
                    SELECT vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, avion.id as id_avion, avion.modelo, avion.capacidad FROM vuelo
                        INNER JOIN avion ON vuelo.id_avion = avion.id WHERE destino like ?
                """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%" + destine + "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objVuelo = new Vuelo();
                objVuelo.setId(resultSet.getInt("id_vuelo"));
                objVuelo.setDestino(resultSet.getString("destino"));
                objVuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                objVuelo.setHora(resultSet.getString("hora"));

                Avion objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id_avion"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
                objVuelo.setIdAvion(objAvion);
                listVuelo.add(objVuelo);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVuelo;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        try {
            String sql = """
                    UPDATE vuelo SET destino = ?, fecha_salida = ?, hora = ? WHERE id_vuelo = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objVuelo.getDestino());
            preparedStatement.setString(2, objVuelo.getFecha_salida());
            preparedStatement.setString(3, objVuelo.getHora());
            preparedStatement.setInt(4, objVuelo.getId());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "El vuelo fue actualizado correctamente\n" + objVuelo);
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
    public Vuelo findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Vuelo objVuelo = null;
        try {
            String sql = "SELECT vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, avion.id, avion.modelo, avion.capacidad FROM vuelo" +
                    " INNER JOIN avion on vuelo.id_avion = avion.id" +
                    " WHERE id_vuelo = ?";
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objVuelo = new Vuelo();
                objVuelo.setId(resultSet.getInt("id_vuelo"));
                objVuelo.setDestino(resultSet.getString("destino"));
                objVuelo.setFecha_salida(resultSet.getString("fecha_salida"));
                objVuelo.setHora(resultSet.getString("hora"));
                Avion objAvion = new Avion();
                objAvion.setId(resultSet.getInt("id"));
                objAvion.setModelo(resultSet.getString("modelo"));
                objAvion.setCapacidad(resultSet.getInt("capacidad"));
                objVuelo.setIdAvion(objAvion);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objVuelo;
    }

    @Override
    public boolean delete(Object obj) {

        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;
        try {
            String slqDelete = """
                    DELETE FROM vuelo WHERE id_vuelo = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objVuelo.getId());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "Le Vuelo fue eliminado correctamente");
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
