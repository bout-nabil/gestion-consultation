package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends IDao<Patient,Long> {
    List<Patient> searchByQuery(String query) throws SQLException;
}
