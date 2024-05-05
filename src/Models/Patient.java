package Models;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Patient extends Person {
    // Constructor
    public Patient(Integer id, String firstName, String lastName, Date dateOfBirth) {
        super(id, firstName, lastName, dateOfBirth);
    }

    public Patient(String firstName, String lastName, String dateOfBirth) {
        super(null, firstName, lastName, null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateOfBirth);
            this.setDateOfBirth(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format for dateOfBirth: " + dateOfBirth, e);
        }
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " / " + this.getAge() + " years old";
    }

    private String getAge() {
        Calendar dob = Calendar.getInstance();
        dob.setTime(this.getDateOfBirth());

        Calendar now = Calendar.getInstance();

        int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return String.valueOf(age);
    }
}