package ca.ulaval.glo4002.domain;

import java.util.LinkedList;
import java.util.Queue;
import org.javatuples.Triplet;

public class Clinic {
    private TriageType triageType;
    private Queue<Triplet<String, Integer, VisibleSymptom>> doctorQueue;
    private Queue<Triplet<String, Integer, VisibleSymptom>> radiologyQueue;

    public Clinic(TriageType triageType) {
        this.triageType = triageType;
        this.doctorQueue = new LinkedList<>();
        this.radiologyQueue = new LinkedList<>();
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        Triplet<String, Integer, VisibleSymptom> patient = Triplet.with(name, gravity, visibleSymptom);
        switch (triageType) {
            case FIFO:
                doctorQueue.add(patient);
                if (visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == VisibleSymptom.SPRAIN)
                    radiologyQueue.add(patient);
                break;
            case GRAVITY:
                break;
        }
    }

    public Triplet<String, Integer, VisibleSymptom> getNextPatientDoctorQueue() {
        return doctorQueue.poll();
    }

    public Triplet<String, Integer, VisibleSymptom> getNextPatientRadiologyQueue() {
        return radiologyQueue.poll();
    }

    public boolean IsInRadiologyQueue(String name) {
        return this.radiologyQueue.contains(name);
    }
}