module ma.enset.gestionconsultation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ma.enset.gestionconsultation.controllers to javafx.fxml;

    opens ma.enset.gestionconsultation.entities to javafx.base;
    exports ma.enset.gestionconsultation;
}