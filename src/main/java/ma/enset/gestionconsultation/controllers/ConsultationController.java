package ma.enset.gestionconsultation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;
import ma.enset.gestionconsultation.service.CabinetService;
import ma.enset.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private TextArea textDescription;
    @FXML private ICabinetService iCabinetService;

    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation, Date> columnDateConsultation;
    @FXML private TableColumn<Consultation, String> columnDescription;
    @FXML private TableColumn<Consultation, String> columnPatient;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    private Consultation selectedConsultation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientDao patientDao = new PatientDao();
        iCabinetService = new CabinetService(new PatientDao(), new ConsultationDao(patientDao));
        comboPatient.setItems(patients);
        patients.setAll(iCabinetService.getPatients());

        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tableConsultation.setItems(consultations);
        loadConsultations();
        tableConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldConsultation, newConsultation) -> {
            if (newConsultation != null) {
                dateConsultation.setValue(selectedConsultation.getDateConsultation().toLocalDate());
                textDescription.setText(selectedConsultation.getDescription());
                comboPatient.setValue(selectedConsultation.getPatient());
                selectedConsultation = newConsultation;
            }
        });
    }

    public void addConsultation(){
        Consultation consultation = new Consultation();
        consultation.setDescription(textDescription.getText());
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        iCabinetService.addConsultation(consultation);
        loadConsultations();
    }

    public void updateConsultation(){
        if (selectedConsultation != null) {
            selectedConsultation.setDescription(textDescription.getText());
            selectedConsultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
            selectedConsultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
            iCabinetService.updateConsultation(selectedConsultation);
            loadConsultations();
        }
    }

    public void deleteConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        if (consultation != null) {
            iCabinetService.deleteConsultation(consultation);
            loadConsultations();
        }
    }

    private void loadConsultations(){
        consultations.setAll(iCabinetService.getConsultation());
    }
}
