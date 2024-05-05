package Services.Appointment;

import Models.Appointment;
import Models.Patient;
import Services.Database.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsServiceImpl implements AppointmentsService {
    @Override
    public boolean create(Appointment entity) {
        // Perform the creation operation
        String query = "INSERT INTO appointments (patient_id, doctor_id, date, observations) VALUES (?, ?, ?, ?)";
        try {
            return DatabaseService.getInstance().createStatement(
                    query,
                    entity.getPatientId(),
                    entity.getDoctorId(),
                    entity.getDate(),
                    entity.getObservations()
            ).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAll() {
        // Perform the retrieval operation
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        try {
            ResultSet resultSet = DatabaseService.getInstance().createStatement(query).executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("observations")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public Appointment getById(int id) {
        String query = "SELECT * FROM appointments WHERE id = ?";
        try {
            ResultSet resultSet = DatabaseService.getInstance().createStatement(query, id).executeQuery();
            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("observations")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Appointment entity) {
        // Perform the update operation
        String query = "UPDATE appointments SET doctor_id = ?, date = ?, observations = ? WHERE id = ?";
        try {
            return DatabaseService.getInstance().createStatement(
                    query,
                    entity.getDoctorId(),
                    entity.getDate(),
                    entity.getObservations(),
                    entity.getId()
            ).executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        // Perform the delete operation
        String query = "DELETE FROM appointments WHERE id = ?";
        try {
            return DatabaseService
                    .getInstance()
                    .createStatement(query, id)
                    .executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
