package Services.Patients;

import Models.Patient;
import Services.LoggingService;

import java.util.List;
import java.util.Scanner;

public class PatientsActions {
    private final Scanner scanner;
    private final PatientsService patientsService;

    public PatientsActions(PatientsService patientsService) {
        this.scanner = new Scanner(System.in);
        this.patientsService = patientsService;
    }

    public void addPatient() {
        System.out.println("--- Patient's details ---");
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter date of birth (dd/mm/yyyy): ");
        String dateOfBirth = scanner.nextLine();

        Patient patient = new Patient(firstName, lastName, dateOfBirth);
        patientsService.create(patient);
        System.out.println("Patient created!");
    }

    public void listPatients() {
        List<Patient> patients = patientsService.getAll();
        System.out.println("--- List of patients ---");
        for (Patient patient : patients) {
            System.out.println(patient.toString());
        }
        LoggingService.logAction();
    }
}
