package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IConsultationDao{
    private PatientDao patientDao;

    public ConsultationDao(PatientDao patientDao) {
        //this.connection = DatabaseConnection.getConnection();
        this.patientDao = patientDao;  // Initialisation
    }

    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CONSULTATIONS(DATE_CONSULTATION,DESCRIPTION,ID_PATIENT)"+ "VALUES(?,?,?)");
        preparedStatement.setDate(1, consultation.getDateConsultation());
        preparedStatement.setString(2,consultation.getDescription());
        preparedStatement.setLong(3,consultation.getPatient().getId_patient());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CONSULTATIONS WHERE ID_CONSULTATION = ?");
        preparedStatement.setLong(1,consultation.getId_consultation());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CONSULTATIONS SET DATE_CONSULTATION = ? , DESCRIPTION = ?, WHERE ID = ?");
        preparedStatement.setDate(1, consultation.getDateConsultation());
        preparedStatement.setString(2, consultation.getDescription());
        preparedStatement.setLong(3, consultation.getId_consultation());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        List<Consultation> consultations = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CONSULTATIONS");
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Consultation consultation = new Consultation();
                consultation.setId_consultation(resultSet.getLong("id"));
                consultation.setDateConsultation(resultSet.getDate("date_consultation"));
                consultation.setDescription(resultSet.getString("description"));
                // Vérifier si patientDao est null et l'initialiser
//                if (patientDao == null) {
//                    patientDao = new PatientDao();  // Initialisation directe ici
//                }
                // Récupérer le patient associé
                long patientId = resultSet.getLong("id_patient");
                Patient patient = patientDao.findById(patientId);
                consultation.setPatient(patient);

                consultations.add(consultation);
            }
        return consultations;
    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        Consultation consultation = new Consultation();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM CONSULTATIONS WHERE ID_CONSULTATION = ?"
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            consultation.setId_consultation(resultSet.getLong("ID_CONSULTATION"));
            consultation.setDateConsultation(resultSet.getDate("DATE_CONSULTATION"));
            consultation.setDescription(resultSet.getString("DESCRIPTION"));

            // Création d'un patient avec son ID uniquement
            Patient patient = new Patient();
            patient.setId_patient(resultSet.getLong("ID_PATIENT"));

            consultation.setPatient(patient);
        }

        return consultation;
    }

}
