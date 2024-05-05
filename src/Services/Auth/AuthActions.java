package Services.Auth;

import Services.LoggingService;

import java.util.Scanner;

public class AuthActions {
    private final Scanner scanner;
    private final AuthService authService;

    public AuthActions(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public String authenticate() {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LoggingService.logAction();
        if (authService.authenticate(email, password)) {
            return email;
        }
        return null;
    }

    public String getLoggedInUser() {
        return authService.getLoggedInUser();
    }
}

