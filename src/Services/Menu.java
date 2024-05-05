package Services;

import java.util.Scanner;

import Services.Appointment.AppointmentsActions;
import Services.Appointment.AppointmentsService;
import Services.Appointment.AppointmentsServiceImpl;
import Services.Auth.AuthActions;
import Services.Auth.AuthService;
import Services.Auth.AuthServiceImpl;
import Services.Patients.PatientsActions;
import Services.Patients.PatientsService;
import Services.Patients.PatientsServiceImpl;

public class Menu {
    private final AuthActions authActions;
    private final PatientsActions patientsActions;
    private final AppointmentsActions appointmentsActions;
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);

        AuthService authService = new AuthServiceImpl();
        this.authActions = new AuthActions(authService);

        PatientsService patientsService = new PatientsServiceImpl();
        this.patientsActions = new PatientsActions(patientsService);

        AppointmentsService appointmentsService = new AppointmentsServiceImpl();
        this.appointmentsActions = new AppointmentsActions(appointmentsService);
    }

    public void start() {
        // Authenticate user
        String loggedInUser = authActions.authenticate();

        // If user logged in successfully, display menu
        if (loggedInUser != null) {
            displayMenu();
        } else {
            System.out.println("Login failed. Exiting...");
        }
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("Welcome, " + authActions.getLoggedInUser());
        System.out.println("1. List of patients");
        System.out.println("2. Add new patient");
        System.out.println("3. Add new appointment");
        System.out.println("4. List of appointments");
        System.out.println("5. Change appointment");
        System.out.println("6. Cancel appointment");
        System.out.println("7. Exit");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                patientsActions.listPatients();
                break;
            case 2:
                patientsActions.addPatient();
                break;
            case 3:
                appointmentsActions.addAppointment();
                break;
            case 4:
                appointmentsActions.listAppointments();
                break;
            case 5:
                appointmentsActions.changeAppointment();
                break;
            case 6:
                appointmentsActions.cancelAppointment();
                break;
            case 7:
                System.exit(1);
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        if (authActions.getLoggedInUser() != null) {
            System.out.println("\n");
            displayMenu(); // Show menu again if user is still logged in
        }
    }
}

