package ma.enset.gestionconsultation.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<E,U> {
    void create(E e) throws SQLException;
    void delete(E e) throws SQLException;
    void update(E e) throws SQLException;
    List<E> findAll() throws SQLException;
    E findById(U id) throws SQLException;
}
