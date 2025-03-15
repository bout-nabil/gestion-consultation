package ma.enset.gestionconsultation.service;

import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Patient;

import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        PatientDao patientDao = new PatientDao();
        ICabinetService service = new CabinetService(new PatientDao(),new ConsultationDao(patientDao));
//        Patient patient = new Patient();
//        patient.setNom("Tazi");
//        patient.setPrenom("Ahmed");
//        patient.setTel("0612341234");
//        service.addPatient(patient);
//
//        List<Patient> patients = service.getPatients();
//        patients.forEach(patient -> {
//            System.out.println(patient);
//        });
//
//        Patient patient = service.getPatientById(4L);
//        System.out.println(patient);

    }
}
