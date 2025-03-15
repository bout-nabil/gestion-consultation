package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IPatientDao {
    @Override
    public void create(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PATIENTS(NOM,PRENOM,TELE)"+ "VALUES(?,?,?)");
        preparedStatement.setString(1,patient.getNom());
        preparedStatement.setString(2,patient.getPrenom());
        preparedStatement.setString(3,patient.getTel());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PATIENTS WHERE ID_PATIENT = ?");
        preparedStatement.setLong(1,patient.getId_patient());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PATIENTS SET NOM = ? , PRENOM = ?, TELE = ? WHERE ID_PATIENTS = ?");
        preparedStatement.setString(1,patient.getNom());
        preparedStatement.setString(2, patient.getPrenom());
        preparedStatement.setString(3,patient.getTel());
        preparedStatement.setLong(4,patient.getId_patient());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PATIENTS");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Patient> patientList = new ArrayList<>();
        while (resultSet.next()){
            Patient patient = new Patient();
            patient.setId_patient(resultSet.getLong("ID_PATIENT"));
            patient.setNom(resultSet.getString("NOM"));
            patient.setPrenom(resultSet.getString("PRENOM"));
            patient.setTel(resultSet.getString("TELE"));
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Patient patient = new Patient();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PATIENTS WHERE ID_PATIENT = ?");
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            patient.setId_patient(resultSet.getLong("ID_PATIENT"));
            patient.setNom(resultSet.getString("NOM"));
            patient.setPrenom(resultSet.getString("PRENOM"));
            patient.setTel(resultSet.getString("TELE"));
        }
        return patient;
    }

    @Override
    public List<Patient> searchByQuery(String query) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PATIENTS WHERE NOM LIKE ? PRENOM LIKE ?");
        preparedStatement.setString(1,"%"+query+"%");
        preparedStatement.setString(2,"%"+query+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Patient> patientList = new ArrayList<>();
        while (resultSet.next()){
            Patient patient = new Patient();
            patient.setId_patient(resultSet.getLong("ID_PATIENT"));
            patient.setNom(resultSet.getString("NOM"));
            patient.setPrenom(resultSet.getString("PRENOM"));
            patient.setTel(resultSet.getString("TELE"));
            patientList.add(patient);
        }
        return patientList;
    }
}
