package Models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseCredentials {
    private static final String PROPERTIES_FILE = "src/Utils/db.properties";
    private String url;
    private String user;
    private String password;

    public DatabaseCredentials() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
