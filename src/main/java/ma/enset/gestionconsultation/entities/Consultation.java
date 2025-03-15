package ma.enset.gestionconsultation.entities;

import java.sql.Date;

public class Consultation {
    private long id_consultation;
    private Date dateConsultation;
    private String description;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(Date dateConsultation, String description, Patient patient) {
        this.dateConsultation = dateConsultation;
        this.description = description;
        this.patient = patient;
    }

    public long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(long id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
