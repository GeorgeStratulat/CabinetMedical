package Services.Patients;

import Models.Patient;
import Services.Database.DatabaseService;

import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientsServiceImpl implements PatientsService {

    public PatientsServiceImpl() {
    }

    @Override
    public boolean create(Patient patient) {
        // Perform the creation operation
        String query = "INSERT INTO patients (first_name, last_name, date_of_birth) VALUES (?, ?, ?)";
        try {
            return DatabaseService.getInstance().createStatement(
                    query,
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getDateOfBirth()
            ).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Patient> getAll() {
        // Perform the retrieval operation
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try {
            ResultSet resultSet = DatabaseService.getInstance().createStatement(query).executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient getById(int patientId) {
        // Perform the retrieval operation
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            ResultSet resultSet = DatabaseService.getInstance().createStatement(query, patientId).executeQuery();
            if (resultSet.next()) {
                return new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Patient patient) {
        // Perform the update operation
        String query = "UPDATE patients SET first_name = ?, last_name = ?, date_of_birth = ? WHERE id = ?";
        try {
            return DatabaseService.getInstance().createStatement(
                    query,
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getDateOfBirth(),
                    patient.getId()
            ).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int patientId) {
        // Perform the delete operation
        String query = "DELETE FROM patients WHERE id = ?";
        try {
            return DatabaseService
                    .getInstance()
                    .createStatement(query, patientId)
                    .executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Patient findByName(String firstName, String lastName) {
        // Perform the retrieval operation
        String query = "SELECT * FROM patients WHERE first_name = ? AND last_name = ?";
        try {
            ResultSet resultSet = DatabaseService.getInstance().createStatement(query, firstName, lastName).executeQuery();
            if (resultSet.next()) {
                return new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
