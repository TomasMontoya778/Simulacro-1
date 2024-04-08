package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Avion;
import Entity.Pasajero;
import Entity.Reserva;
import Entity.Vuelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Reserva objReserva = (Reserva) obj;
        try {
            String sqlInsert = """
                   INSERT INTO reserva (fecha_reservacion, asiento, id_pasajeroFK, id_vueloFK) VALUES (?, ?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objReserva.getFecha_reserva());
            prepared.setString(2, objReserva.getAsiento());
            prepared.setInt(3, objReserva.getIdPasajero().getIdPasajero());
            prepared.setInt(4, objReserva.getIdVuelo().getId());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objReserva.setIdReserva(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "La reserva fue agregada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objReserva;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listReserva = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT reserva.id_reserva, reserva.fecha_reservacion, reserva.asiento, vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, pasajero.id_pasajero, pasajero.nombre, pasajero.apellido, pasajero.documento, avion.id as id_avion, avion.modelo, avion.capacidad FROM reserva" +
                    " INNER JOIN vuelo ON reserva.id_vueloFK = vuelo.id_vuelo" +
                    " INNER JOIN pasajero ON reserva.id_pasajeroFK = pasajero.id_pasajero" +
                    " INNER JOIN avion ON vuelo.id_avion = avion.id";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Reserva objReserva = new Reserva();
                objReserva.setIdReserva(resultSet.getInt("id_reserva"));
                objReserva.setFecha_reserva(resultSet.getString("fecha_reservacion"));
                objReserva.setAsiento(resultSet.getString("asiento"));
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
                Pasajero objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
                objReserva.setIdPasajero(objPasajero);
                objReserva.setIdVuelo(objVuelo);
                listReserva.add(objReserva);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listReserva;
    }
    public List<Reserva> listReservacionesDeVueloById(int vueloId) {
        List<Reserva> listReservaFly = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT reserva.id_reserva, reserva.fecha_reservacion, reserva.asiento,  vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, pasajero.id_pasajero, pasajero.nombre, pasajero.apellido, pasajero.documento, avion.id as id_avion, avion.modelo, avion.capacidad "+
                    "FROM reserva" +
                    " INNER JOIN vuelo ON reserva.id_vueloFK = vuelo.id_vuelo" +
                    " INNER JOIN pasajero ON reserva.id_pasajeroFK = pasajero.id_pasajero" +
                    " INNER JOIN avion ON vuelo.id_avion = avion.id" +
                    " WHERE reserva.id_vueloFK = ?";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            prepared.setInt(1, vueloId);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Reserva objReserva = new Reserva();
                objReserva.setIdReserva(resultSet.getInt("id_reserva"));
                objReserva.setFecha_reserva(resultSet.getString("fecha_reservacion"));
                objReserva.setAsiento(resultSet.getString("asiento"));
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
                Pasajero objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
                objReserva.setIdPasajero(objPasajero);
                objReserva.setIdVuelo(objVuelo);
                listReservaFly.add(objReserva);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listReservaFly;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Reserva objReserva = (Reserva) obj;
        try {
            String sql = """
                    UPDATE reserva SET fecha_reservacion = ?, asiento = ? WHERE id_reserva = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objReserva.getFecha_reserva());
            preparedStatement.setString(2, objReserva.getAsiento());
            preparedStatement.setInt(3, objReserva.getIdReserva());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "La reserva fue actualizada correctamente\n" + objReserva);
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
    public Reserva findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Reserva objReserva = null;
        try {
            String sql = "SELECT reserva.id_reserva, reserva.fecha_reservacion, reserva.asiento, vuelo.id_vuelo, vuelo.destino, vuelo.fecha_salida, vuelo.hora, pasajero.id_pasajero, pasajero.nombre, pasajero.apellido, pasajero.documento, avion.id as id_avion, avion.modelo, avion.capacidad FROM reserva" +
                    " INNER JOIN vuelo ON reserva.id_vueloFK = vuelo.id_vuelo" +
                    " INNER JOIN pasajero ON reserva.id_pasajeroFK = pasajero.id_pasajero" +
                    " INNER JOIN avion ON vuelo.id_avion = avion.id" +
                    " WHERE id_reserva = ?";
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objReserva = new Reserva();
                objReserva.setIdReserva(resultSet.getInt("id_reserva"));
                objReserva.setFecha_reserva(resultSet.getString("fecha_reservacion"));
                objReserva.setAsiento(resultSet.getString("asiento"));
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
                Pasajero objPasajero = new Pasajero();
                objPasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                objPasajero.setNombrePasajero(resultSet.getString("nombre"));
                objPasajero.setApellidoPasajero(resultSet.getString("apellido"));
                objPasajero.setDocumento_identidad(resultSet.getInt("documento"));
                objReserva.setIdPasajero(objPasajero);
                objReserva.setIdVuelo(objVuelo);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objReserva;
    }

    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Reserva objReserva = (Reserva) obj;
        try {
            String slqDelete = """
                    DELETE FROM reserva WHERE id_reserva = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objReserva.getIdReserva());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "La reserva fue eliminada correctamente");
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
    public boolean checkDuplicateAsiento(String asiento){
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlQuery = "SELECT COUNT(*) FROM reserva WHERE asiento = ?";
            PreparedStatement preparedStatement = objConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, asiento);
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
