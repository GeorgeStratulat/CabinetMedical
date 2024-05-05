package Services.Auth;

public interface AuthService {
    boolean authenticate(String email, String password);

    String getLoggedInUser();
}
