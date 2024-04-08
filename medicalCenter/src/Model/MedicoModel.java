package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Especializacion;
import Entity.Medico;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        try {
            String sqlInsert = """
                    INSERT INTO medico (name, lastName, id_especializacion) VALUES (?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objMedico.getNombreMedico());
            prepared.setString(2, objMedico.getApellidoMedico());
            prepared.setInt(3, objMedico.getEspecializacion().getId_especializacion());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objMedico.setId_medico(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "El médico fue agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objMedico;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listMedico = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT m.id, m.name, m.lastName,e.id as id_esp ,e.name as Especialidad, e.description FROM medico m" +
                    " INNER JOIN especializacion e ON m.id_especializacion = e.id";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Medico objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("id_esp"));
                objEsp.setNombreEsp(resultSet.getString("Especialidad"));
                objEsp.setDescription(resultSet.getString("description"));
                objMedico.setEspecializacion(objEsp);
                listMedico.add(objMedico);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listMedico;
    }

    public List<Medico> listMedicoEspsById(int espID) {
        List<Medico> listMedicoEsp = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT medico.id, medico.name, medico.lastName, especializacion.id as esp_id, especializacion.name as esp_name, especializacion.description as esp_descrip "+
                    "FROM medico " +
                    "INNER JOIN especializacion ON medico.id_especializacion = especializacion.id " + //
                    "WHERE medico.id_especializacion = ?";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            prepared.setInt(1, espID);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Medico objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("esp_id"));
                objEsp.setNombreEsp(resultSet.getString("esp_name"));
                objEsp.setDescription(resultSet.getString("esp_descrip"));

                objMedico.setEspecializacion(objEsp);
                listMedicoEsp.add(objMedico);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listMedicoEsp;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        try {
            String sql = """
                    UPDATE medico SET name = ?, lastName = ? WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objMedico.getNombreMedico());
            preparedStatement.setString(2, objMedico.getApellidoMedico());
            preparedStatement.setInt(3, objMedico.getId_medico());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "El Médico fue actualizado correctamente\n" + objMedico);
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
        Medico objMedico = (Medico) obj;
        try {
            String slqDelete = """
                    DELETE FROM medico WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objMedico.getId_medico());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "El Médico fue eliminado correctamente");
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
    public Medico findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Medico objMedico = null;
        try {
            String sql = "SELECT medico.id as id_medico ,medico.name, medico.lastName, especializacion.id as esp_id, especializacion.name as esp_name, especializacion.description as esp_descrip FROM medico" +
                    " INNER JOIN especializacion ON medico.id_especializacion = especializacion.id" +
                    " WHERE medico.id = ?";
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id_medico"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("esp_id"));
                objEsp.setNombreEsp(resultSet.getString("esp_name"));
                objEsp.setDescription(resultSet.getString("esp_descrip"));
                objMedico.setEspecializacion(objEsp);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objMedico;
    }

}
