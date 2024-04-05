package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Reserva;

import javax.swing.*;
import java.sql.*;
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
