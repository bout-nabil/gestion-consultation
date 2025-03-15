package ma.enset.gestionconsultation.service;

import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ICabinetService {
    void addPatient(Patient patient);
    void deletePatient(Patient patient);
    void updatePatient(Patient patient);
    List<Patient> getPatients();
    Patient getPatientById(Long id);
    List<Patient> searchPatientByQuery(String query) throws SQLException;
    void addConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    List<Consultation> getConsultation();
    Consultation getConsultationById(Long id);
}
