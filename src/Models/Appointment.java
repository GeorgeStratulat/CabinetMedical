package Models;

import java.util.Date;

public class Appointment {
    private Integer id;
    private int patientId;
    private int doctorId;
    private Date date;
    private String observations;

    // Constructor
    public Appointment(Integer id, int patientId, int doctorId, Date date, String observations) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.observations = observations;
    }

    public Appointment(int patientId, int doctorId, Date date, String observations) {
        this(null, patientId, doctorId, date, observations);
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return this.getPatientId() + " " + this.getDoctorId() + " " + this.getDate().toString() + " " + this.getObservations();
    }
}

