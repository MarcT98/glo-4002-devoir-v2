package ca.ulaval.glo4002;

import java.util.LinkedList;
import java.util.Queue;
import ca.ulaval.glo4002.Patient;

public class Clinic {

    private Queue<Patient> doctorQueue = new LinkedList<>();

    public Queue<Patient> radiologyQueue = new LinkedList<>();

    public TriageType triageTypeDoctor;

    public Clinic() {
    }

    public Clinic(TriageType triageTypeDoctor) {
            this.triageTypeDoctor = triageTypeDoctor;
    }

    public Patient getNextPatient() {
        return this.doctorQueue.peek();
    }

    public void triagePatient(Patient patient) {
        if (doesPatientHavePriority(patient)) {
            addToFrontOfDoctorQueue(patient);
            return;
        }
        fifoTriageAlgorithm(patient);
    }

    private void fifoTriageAlgorithm(Patient patient) {
        switch (patient.getVisibleSymptom()) {
            case MIGRAINE -> addToFrontOfDoctorQueue(patient);
            case SPRAIN -> {
                addToFrontOfDoctorQueue(patient);
                addToFrontOfRadiologyQueue(patient);
            }
            default -> doctorQueue.add(patient);
        }
    }

    private static boolean doesPatientHavePriority(Patient patient) {
        return patient.getGravity() > 5;
    }

    private void addToFrontOfDoctorQueue(Patient patient) {
        Queue<Patient> updatedDoctor = new LinkedList<>();
        updatedDoctor.add(patient);
        updatedDoctor.addAll(doctorQueue);
        doctorQueue = updatedDoctor;
    }

    private void addToFrontOfRadiologyQueue(Patient patient) {
        Queue<Patient> updatedRadiology = new LinkedList<>();
        updatedRadiology.add(patient);
        updatedRadiology.addAll(radiologyQueue);
        radiologyQueue = updatedRadiology;
    }
}
