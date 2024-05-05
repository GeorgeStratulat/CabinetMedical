package Services.Auth;

import Services.Database.DatabaseService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.*;

public class AuthServiceImpl implements AuthService {
    public AuthServiceImpl() {
    }

    private String loggedInUser;

    @Override
    public boolean authenticate(String email, String password) {
        try {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = DatabaseService
                    .getInstance()
                    .createStatement(
                            query,
                            email,
                            hashPassword(password)
                    );

            ResultSet resultSet = statement.executeQuery();

            boolean authenticated = resultSet.next();

            if (authenticated) {
                loggedInUser = email;
            }

            return authenticated;
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : hashBytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
