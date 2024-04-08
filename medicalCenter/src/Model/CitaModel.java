package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.*;
import Entity.Cita;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        try {
            String sqlInsert = """
                   INSERT INTO cita (fecha_cita, hora_cita, motivo, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objCita.getFecha_cita());
            prepared.setString(2, objCita.getHora_cita());
            prepared.setString(3, objCita.getMotivo());
            prepared.setInt(4, objCita.getPaciente().getId_paciente());
            prepared.setInt(5, objCita.getMedico().getId_medico());

            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objCita.setId_cita(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "La cita fue agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objCita;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT cita.id, cita.fecha_cita, cita.hora_cita, cita.motivo, medico.id as id_medico ,medico.name, medico.lastName, paciente.id_paciente ,paciente.name as nombre_paciente, paciente.lastName as apellido_paciente, paciente.fecha_nacimiento, paciente.documento_identidad, especializacion.id as esp_id, especializacion.name as esp_name, especializacion.description as esp_descrip FROM cita" +
                    " INNER JOIN medico ON cita.id_medico = medico.id" +
                    " INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente" +
                    " INNER JOIN especializacion ON medico.id_especializacion = especializacion.id";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Cita objCita = new Cita();
                objCita.setId_cita(resultSet.getInt("id"));
                objCita.setFecha_cita(resultSet.getString("fecha_cita"));
                objCita.setHora_cita(resultSet.getString("hora_cita"));
                objCita.setMotivo(resultSet.getString("motivo"));
                Medico objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id_medico"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("esp_id"));
                objEsp.setNombreEsp(resultSet.getString("esp_name"));
                objEsp.setDescription(resultSet.getString("esp_descrip"));

                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("nombre_paciente"));
                objPaciente.setApellidoPaciente(resultSet.getString("apellido_paciente"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                objCita.setMedico(objMedico);
                objMedico.setEspecializacion(objEsp);
                objCita.setPaciente(objPaciente);
                listCita.add(objCita);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listCita;
    }
    public ArrayList<Cita> listCitaByDate(String fecha){
        ArrayList<Cita> listCita = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT cita.id, cita.fecha_cita, cita.hora_cita, cita.motivo, medico.id as id_medico ,medico.name, medico.lastName, paciente.id_paciente ,paciente.name as nombre_paciente, paciente.lastName as apellido_paciente, paciente.fecha_nacimiento, paciente.documento_identidad, especializacion.id as esp_id, especializacion.name as esp_name, especializacion.description as esp_descrip FROM cita" +
                    " INNER JOIN medico ON cita.id_medico = medico.id" +
                    " INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente" +
                    " INNER JOIN especializacion ON medico.id_especializacion = especializacion.id" +
                    " WHERE fecha_cita LIKE ?";
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            prepared.setString(1, "%" + fecha + "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objCita = new Cita();
                objCita.setId_cita(resultSet.getInt("id"));
                objCita.setFecha_cita(resultSet.getString("fecha_cita"));
                objCita.setHora_cita(resultSet.getString("hora_cita"));
                objCita.setMotivo(resultSet.getString("motivo"));

                Medico objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id_medico"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                objCita.setMedico(objMedico);
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("esp_id"));
                objEsp.setNombreEsp(resultSet.getString("esp_name"));
                objEsp.setDescription(resultSet.getString("esp_descrip"));

                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("nombre_paciente"));
                objPaciente.setApellidoPaciente(resultSet.getString("apellido_paciente"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                objCita.setPaciente(objPaciente);
                objMedico.setEspecializacion(objEsp);
                listCita.add(objCita);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listCita;
    }
    public Cita findById (int id){
        Connection objConnetion = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT cita.id, cita.fecha_cita, cita.hora_cita, cita.motivo, medico.id as id_medico ,medico.name, medico.lastName, paciente.id_paciente ,paciente.name as nombre_paciente, paciente.lastName as apellido_paciente, paciente.fecha_nacimiento, paciente.documento_identidad, especializacion.id as esp_id, especializacion.name as esp_name, especializacion.description as esp_descrip FROM cita" +
                    " INNER JOIN medico ON cita.id_medico = medico.id" +
                    " INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente" +
                    " INNER JOIN especializacion ON medico.id_especializacion = especializacion.id" +
                    " WHERE cita.id = ?";
            PreparedStatement preparedStatement = objConnetion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                objCita = new Cita();
                objCita.setId_cita(resultSet.getInt("id"));
                objCita.setFecha_cita(resultSet.getString("fecha_cita"));
                objCita.setHora_cita(resultSet.getString("hora_cita"));
                objCita.setMotivo(resultSet.getString("motivo"));

                Medico objMedico = new Medico();
                objMedico.setId_medico(resultSet.getInt("id_medico"));
                objMedico.setNombreMedico(resultSet.getString("name"));
                objMedico.setApellidoMedico(resultSet.getString("lastName"));
                objCita.setMedico(objMedico);
                Especializacion objEsp = new Especializacion();
                objEsp.setId_especializacion(resultSet.getInt("esp_id"));
                objEsp.setNombreEsp(resultSet.getString("esp_name"));
                objEsp.setDescription(resultSet.getString("esp_descrip"));

                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(resultSet.getInt("id_paciente"));
                objPaciente.setNombrePaciente(resultSet.getString("nombre_paciente"));
                objPaciente.setApellidoPaciente(resultSet.getString("apellido_paciente"));
                objPaciente.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                objPaciente.setDNI(resultSet.getInt("documento_identidad"));
                objCita.setPaciente(objPaciente);
                objMedico.setEspecializacion(objEsp);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objCita;
    }
    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        try {
            String sql = """
                    UPDATE cita SET fecha_cita = ?, hora_cita = ?, motivo = ? WHERE id = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objCita.getFecha_cita());
            preparedStatement.setString(2, objCita.getHora_cita());
            preparedStatement.setString(3, objCita.getMotivo());
            preparedStatement.setInt(4, objCita.getId_cita());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "La cita fue actualizada correctamente\n" + objCita);
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
        Cita objCita = (Cita) obj;
        try {
            String slqDelete = """
                    DELETE FROM cita WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objCita.getId_cita());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "La Cita fue eliminada correctamente");
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
