package ca.ulaval.glo4002;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Random;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @ValueSource(ints = {5, 15, Integer.MIN_VALUE, Integer.MAX_VALUE})
    public void triagePatient_adds_patient_to_front_of_doctor_queue_when_patient_has_gravity_greater_than_5(int gravity) {
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient(
                getRandomName(),
                1,
                VisibleSymptom.FLU
        );
        Patient patient2 = new Patient(
                getRandomName(),
                gravity,
                VisibleSymptom.FLU
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.doctorQueue.element());
    }

    @ParameterizedTest
    @ArgumentsSource(GravityPriorityValuesProvider.class)
    public void triagePatient_adds_patient_to_front_of_doctor_queue_when_new_patient_has_gravity_greater_than_5_whatever_other_patients(
            int gravityPatient1,
            int gravityPatient2
    ) {
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient(
                getRandomName(),
                gravityPatient1,
                VisibleSymptom.FLU
        );
        Patient patient2 = new Patient(
                getRandomName(),
                gravityPatient2,
                VisibleSymptom.FLU
        );

        clinic.triagePatient(patient1);
        clinic.triagePatient(patient2);

        assertEquals(patient2, clinic.doctorQueue.element());
    }

    static class GravityPriorityValuesProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(1, 6),
                    Arguments.of(2, 6),
                    Arguments.of(3, 6),
                    Arguments.of(4, 6),
                    Arguments.of(5, 6),
                    Arguments.of(6, 6),
                    Arguments.of(7, 6)
            );
        }
    }

//    @Test
//    public void triagePatient_adds_patient_to_end_of_radiology_queue_when_patient_has_gravity_7_and_Broken_Bone(int gravity) {
//        Clinic clinic = new Clinic();
//        Patient patient1 = new Patient(
//                getRandomName(),
//                1,
//                VisibleSymptom.SPRAIN
//        );
//        Patient patient2 = new Patient(
//                getRandomName(),
//                7,
//                VisibleSymptom.BROKEN_BONE
//        );
//
//        clinic.triagePatient(patient1);
//        clinic.triagePatient(patient2);
//
//        assertEquals(patient2, clinic.doctorQueue.stream().toList().get(1));
//    }

}
