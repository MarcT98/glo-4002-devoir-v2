package ca.ulaval.glo4002;

import java.util.LinkedList;
import java.util.Queue;

public class Clinic {

    public Queue<Patient> doctorQueue = new LinkedList<>();
    public Queue<Patient> radiologyQueue = new LinkedList<>();

    public Clinic() {
    }

    public void triagePatient(Patient patient) {
        if (patient.visibleSymptom == VisibleSymptom.MIGRAINE) {
            addToFrontOfDoctorQueue(patient);
        } else {
            doctorQueue.add(patient);
        }
        if (patient.visibleSymptom == VisibleSymptom.SPRAIN) {
            addToFrontOfDoctorQueue(patient);
            addToFrontOfRadiologyQueue(patient);
        }

    }

    private void addToFrontOfRadiologyQueue(Patient patient) {
        Queue<Patient> updatedRadiology = new LinkedList<>();
        updatedRadiology.add(patient);
        updatedRadiology.addAll(radiologyQueue);
        radiologyQueue = updatedRadiology;
    }

    private void addToFrontOfDoctorQueue(Patient patient) {
        Queue<Patient> updatedDoctor = new LinkedList<>();
        updatedDoctor.add(patient);
        updatedDoctor.addAll(doctorQueue);
        doctorQueue = updatedDoctor;
    }
}
