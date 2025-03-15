package ma.enset.gestionconsultation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Patient;
import ma.enset.gestionconsultation.service.CabinetService;
import ma.enset.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField textFieldNom;
    @FXML private TextField textFieldPrenom;
    @FXML private TextField textFieldTel;
    @FXML private TextField textFieldSearch;

    @FXML private TableView<Patient> tableViewPatients;
    @FXML private TableColumn<Patient,Long> columnId;
    @FXML private TableColumn<Patient,String> columnNom;
    @FXML private TableColumn<Patient,String> columnPrenom;
    @FXML private TableColumn<Patient,String> columnTel;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private Patient selectedPatient;
    private ICabinetService iCabinetService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PatientDao patientDao = new PatientDao();
        iCabinetService = new CabinetService(new PatientDao(), new ConsultationDao(patientDao));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tableViewPatients.setItems(patients);
        loadPatients();
        textFieldSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                patients.setAll(iCabinetService.searchPatientByQuery(newValue));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        tableViewPatients.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPatient, newPatient) -> {
            if (newPatient!=null){
                textFieldNom.setText(newPatient.getNom());
                textFieldPrenom.setText(newPatient.getPrenom());
                textFieldTel.setText(newPatient.getTel());
                selectedPatient = newPatient;
            }
        });
    }

    public void addPatient(){
        Patient patient = new Patient();
        patient.setNom(textFieldNom.getText());
        patient.setPrenom(textFieldPrenom.getText());
        patient.setTel(textFieldTel.getText());
        iCabinetService.addPatient(patient);
        loadPatients();
    }

    public void updatePatient() {
        selectedPatient.setNom(textFieldNom.getText());
        selectedPatient.setPrenom(textFieldPrenom.getText());
        selectedPatient.setTel(textFieldTel.getText());
        iCabinetService.updatePatient(selectedPatient);
        loadPatients();
    }

    public void deletePatient(){
        Patient patient = tableViewPatients.getSelectionModel().getSelectedItem();
        iCabinetService.deletePatient(patient);
        loadPatients();
    }

    private void loadPatients(){
        patients.setAll(iCabinetService.getPatients());
    }

}
