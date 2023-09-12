package ca.ulaval.glo4002.domain;

import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicTest {

    @Test
    public void givenPatientWithMigraine_whenTriagePatient_thenPatientNotInRadiologyList() {
        //Given
        TriageType triageType = TriageType.FIFO;
        Clinic clinic = new Clinic(triageType);
        int gravity = -1;
        String patientName = "Sara";
        VisibleSymptom visibleSymptom = VisibleSymptom.MIGRAINE;
        clinic.triagePatient(patientName, gravity, visibleSymptom);

        //When
        boolean isInRadiologyQueue = clinic.IsInRadiologyQueue(patientName);
        Triplet<String, Integer, VisibleSymptom> nextPatientDoctorQueue = clinic.getNextPatientDoctorQueue();

        //Then
        assertEquals(patientName, nextPatientDoctorQueue.getValue0());
        assertFalse(isInRadiologyQueue);
    }

    @Test
    public void givenTwoPatients_whenTriagePatient_thenFluPatientIsSecondInDoctorQueue() {
        //Given
        TriageType triageType = TriageType.FIFO;
        int gravity = -1;
        String patientName1 = "Sara";
        String patientName2 = "Louis";
        VisibleSymptom visibleSymptomPatient1 = VisibleSymptom.MIGRAINE;
        VisibleSymptom visibleSymptomPatient2 = VisibleSymptom.FLU;
        Clinic clinic = new Clinic(triageType);
        clinic.triagePatient(patientName1, gravity, visibleSymptomPatient1);
        clinic.triagePatient(patientName2, gravity, visibleSymptomPatient2);

        //When
        boolean isInRadiologyQueue = clinic.IsInRadiologyQueue(patientName2);
        clinic.getNextPatientDoctorQueue();
        Triplet<String, Integer, VisibleSymptom> secondPatientDoctorQueue = clinic.getNextPatientDoctorQueue();

        //Then
        assertEquals(patientName2, secondPatientDoctorQueue.getValue0());
        assertFalse(isInRadiologyQueue);
    }

    @Test
    public void givenPatientWithSprain_whenTriagePatient_isFirstInDoctorQueueAndFirstInRadiologyQueue() {
        // Given
        TriageType triageType = TriageType.FIFO;
        int gravity = -1;
        String patientName = "Louis";
        VisibleSymptom visibleSymptomPatient = VisibleSymptom.SPRAIN;
        Clinic clinic = new Clinic(triageType);

        clinic.triagePatient(patientName, gravity, visibleSymptomPatient);
        clinic.triagePatient(patientName, gravity, visibleSymptomPatient);

        // When
        Triplet<String, Integer, VisibleSymptom> patientInDoctorQueue = clinic.getNextPatientDoctorQueue();
        Triplet<String, Integer, VisibleSymptom> patientInRadiologyQueue = clinic.getNextPatientRadiologyQueue();

        // Then
        assertEquals("Louis", patientInDoctorQueue);
        assertEquals("Louis", patientInRadiologyQueue);
    }

}
