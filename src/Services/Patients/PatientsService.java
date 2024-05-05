package Services.Patients;

import Models.Patient;
import Services.BaseService;

import java.util.List;

public interface PatientsService extends BaseService<Patient> {
    public Patient findByName(String firstName, String lastName);
}