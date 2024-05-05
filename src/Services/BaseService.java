package Services;

import Models.Patient;

import java.util.List;

public interface BaseService<T> {
    boolean create(T entity);

    List<T> getAll();

    T getById(int id);

    boolean update(T entity);

    boolean delete(int id);
}
