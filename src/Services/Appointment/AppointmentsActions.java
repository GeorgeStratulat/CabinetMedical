package Services.Appointment;

import Models.Appointment;
import Models.Patient;
import Services.Patients.PatientsService;
import Services.Patients.PatientsServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppointmentsActions {
    private final Scanner scanner;
    private final AppointmentsService appointmentsService;

    public AppointmentsActions(AppointmentsService appointmentsService) {
        this.scanner = new Scanner(System.in);
        this.appointmentsService = appointmentsService;
    }

    public void addAppointment() {
        System.out.println("--- Add appointment ---");
        System.out.println("Enter patient's first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter patient's last name:");
        String lastName = scanner.nextLine();

        // check for patient
        PatientsService patientsService = new PatientsServiceImpl();
        Patient patient = patientsService.findByName(firstName, lastName);

        if (patient != null) {
            System.out.println("Patient found!");
        } else {
            System.out.println("Patient not found. Adding new patient!");
            System.out.println("Enter patient's date of birth (dd/mm/yyyy):");
            String dateOfBirth = scanner.nextLine();
            patient = new Patient(firstName, lastName, dateOfBirth);
            patientsService.create(patient);
            System.out.println("Patient created!");
        }

        System.out.println("Enter doctor id:");
        int doctorId = scanner.nextInt();

        System.out.println("Enter date (dd/mm/yyyy):");
        String date = scanner.next();

        System.out.println("Enter time (HH:mm):");
        String time = scanner.next();

        Date appointmentDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            appointmentDate = dateFormat.parse(date + " " + time);
        } catch (ParseException e) {
            System.err.println("Error parsing date and time: " + e.getMessage());
        }

        System.out.println("Write observations:");
        String observations = scanner.next();

        Appointment appointment = new Appointment(patient.getId(), doctorId, appointmentDate, observations);
        appointmentsService.create(appointment);
        System.out.println("Appointment created!");
    }

    public void listAppointments() {
        List<Appointment> appointments = appointmentsService.getAll();
        System.out.println("--- List of appointments ---");
        for (Appointment appointment : appointments) {
            System.out.println(appointment.toString());
        }
    }

    public void changeAppointment() {
        System.out.println("--- Change appointment ---");
        System.out.println("Enter appointment ID:");
        int id = scanner.nextInt();

        Appointment appointment = appointmentsService.getById(id);
        if (appointment != null) {
            System.out.println("Appointment found!");
            System.out.println("You cannot change the patient ID.");

            System.out.println("Enter doctor id:");
            int doctorId = scanner.nextInt();
            appointment.setDoctorId(doctorId);

            System.out.println("Enter date (dd/mm/yyyy):");
            String date = scanner.next();

            System.out.println("Enter time (HH:mm):");
            String time = scanner.next();

            Date appointmentDate;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                appointmentDate = dateFormat.parse(date + " " + time);
                appointment.setDate(appointmentDate);
            } catch (ParseException e) {
                System.err.println("Error parsing date and time: " + e.getMessage());
            }

            System.out.println("Write observations:");
            String observations = scanner.next();
            appointment.setObservations(observations);

            appointmentsService.update(appointment);
        } else {
            System.out.println("Appointment not found!");
        }
    }

    public void cancelAppointment() {
        System.out.println("--- Change appointment ---");
        System.out.println("Enter appointment ID:");
        int id = scanner.nextInt();

        if (appointmentsService.delete(id)) {
            System.out.println("Appointment cancelled");
        } else {
            System.out.println("There was a problem cancelling the appointment");
        }
    }
}
