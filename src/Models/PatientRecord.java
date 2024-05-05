package Models;

import java.util.Date;

public class PatientRecord {
    private int patientId;
    private Date recordDate;
    private int doctorId;
    private String[] currentMedications;
    private String[] allergies;
    private String observations;

    // Constructor
    public PatientRecord(int patientId, Date recordDate, int doctorId, String[] currentMedications, String[] allergies, String observations) {
        this.patientId = patientId;
        this.recordDate = recordDate;
        this.doctorId = doctorId;
        this.currentMedications = currentMedications;
        this.allergies = allergies;
        this.observations = observations;
    }

    // Getters and setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String[] getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String[] currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
