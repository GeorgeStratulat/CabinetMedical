package Services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingService {
    public static void logAction() {
        String method = new Throwable().getStackTrace()[1].getMethodName();

        String file = "log.csv";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            out.println(method + "," + LocalDateTime.now().format(formatter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
