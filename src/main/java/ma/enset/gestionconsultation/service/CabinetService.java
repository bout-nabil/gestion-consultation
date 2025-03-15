package ma.enset.gestionconsultation.service;

import ma.enset.gestionconsultation.dao.IConsultationDao;
import ma.enset.gestionconsultation.dao.IPatientDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetService implements ICabinetService{
    private IPatientDao iPatientDao;
    private IConsultationDao iConsultationDao;

    public CabinetService(IPatientDao iPatientDao, IConsultationDao iConsultationDao) {
        this.iPatientDao = iPatientDao;
        this.iConsultationDao = iConsultationDao;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            iPatientDao.create(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            iPatientDao.delete(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            iPatientDao.update(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = null;
        try {
            patients = iPatientDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = null;
        try {
            patient = iPatientDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<Patient> searchPatientByQuery(String query) throws SQLException {
        return iPatientDao.searchByQuery(query);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            iConsultationDao.create(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            iConsultationDao.delete(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            iConsultationDao.update(consultation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getConsultation() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            consultations = iConsultationDao.findAll();
            System.out.println("Consultations récupérées : " + consultations); // Vérifier si la liste est vide
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }


    @Override
    public Consultation getConsultationById(Long id) {
        Consultation consultation = null;
        try {
            consultation = iConsultationDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultation;
    }
}
