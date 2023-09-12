package ca.ulaval.glo4002;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClinicTest {

    private Patient patient1;

    private Patient patient2;

    private Patient patientWithSprainSymptom;

    @BeforeEach
    public void setup() {

        patient1 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.MIGRAINE
        );

        patient2 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.FLU
        );


        patientWithSprainSymptom = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.SPRAIN
        );
    }


    @Test
    public void triagePatient_adds_patient_to_doctor_queue_when_symptom_is_migraine() {
        Clinic clinic = new Clinic();

        clinic.triagePatient(patient1);

        assertEquals(1, clinic.doctorQueue.size());
    }

    private String getRandomName() {
        String name = "Simon";
        int randomInt = new Random().nextInt();
        return name + randomInt;
    }

    @Test
    public void triagePatient_adds_patient_to_front_of_doctor_queue_when_new_patient_has_migraine() {
        Clinic clinic = new Clinic();
        patient1 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.MIGRAINE
        );
        patient2 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.MIGRAINE
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.doctorQueue.stream().toList().get(0));
    }

    @Test
    public void triagePatient_does_not_add_patient_to_radiology_queue_when_patient_has_migraine() {
        Clinic clinic = new Clinic();
        patient1 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.MIGRAINE
        );

        clinic.triagePatient(patient1);

        assertEquals(0, clinic.radiologyQueue.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MIN_VALUE, Integer.MAX_VALUE})
    public void triagePatient_adds_patient_to_end_of_doctor_queue_when_second_patient_has_flu(int gravity) {
        Clinic clinic = new Clinic();
        patient1 = new Patient(
                getRandomName(),
                0,
                VisibleSymptom.MIGRAINE
        );
        patient2 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.FLU
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.doctorQueue.stream().toList().get(1));
    }

    @Test
    public void triagePatient_adds_patient_to_doctor_queue_when_patient_has_entorse() {
        Clinic clinic = new Clinic();

        clinic.triagePatient(patientWithSprainSymptom);

        assertEquals(patientWithSprainSymptom, clinic.doctorQueue.element());
    }

    @Test
    public void triagePatient_adds_patient_to_radiology_queue_when_patient_has_entorse() {
        Clinic clinic = new Clinic();

        clinic.triagePatient(patientWithSprainSymptom);

        assertEquals(patientWithSprainSymptom, clinic.radiologyQueue.element());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MIN_VALUE, Integer.MAX_VALUE})
    public void triagePatient_adds_patient_to_front_of_radiology_queue_when_patient_has_entorse_regardless_of_gravity(int gravity) {
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.SPRAIN
        );
        Patient patient2 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.SPRAIN
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.radiologyQueue.element());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MIN_VALUE, Integer.MAX_VALUE})
    public void triagePatient_adds_patient_to_front_of_doctor_queue_when_patient_has_entorse_regardless_of_gravity(int gravity) {
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.SPRAIN
        );
        Patient patient2 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.SPRAIN
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.doctorQueue.element());
    }
}
