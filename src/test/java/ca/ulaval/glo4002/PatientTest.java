package ca.ulaval.glo4002;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private final UUID ANY_UUID = UUID.fromString("4b18fa32-5688-11ee-9dd2-5f1d256023f6");
    private final String ANY_NAME = "asdasd";
    private final int ANY_GRAVITY = 1023;
    private final VisibleSymptom ANY_VISIBLE_SYMPTOM = VisibleSymptom.SPRAIN;
    private final Patient ANY_PATIENT = new Patient(ANY_UUID, ANY_NAME, ANY_GRAVITY, ANY_VISIBLE_SYMPTOM);

    @Test
    public void givenPatients_whenPatientsHaveSameUuid_thenReturnTrue() {
        UUID uuid = UUID.fromString("4b18fa32-5688-11ee-9dd2-5f1d256023f6"); // changer uuid
        Patient patient1 = new Patient(uuid, ANY_NAME, ANY_GRAVITY, ANY_VISIBLE_SYMPTOM);
        Patient patient2 = new Patient(uuid, ANY_NAME, ANY_GRAVITY, ANY_VISIBLE_SYMPTOM);

        Assertions.assertEquals(patient1, patient2);
    }

    @Test
    public void givenPatient_whenPatientIsNotEqual_thenReturnFalse() {
        Patient otherPatient = new Patient(UUID.fromString("1c752a3e-5688-11ee-a987-2fa9a7cbe380"), ANY_NAME, ANY_GRAVITY, ANY_VISIBLE_SYMPTOM);

        Assertions.assertNotEquals(ANY_PATIENT, otherPatient);
    }

    @Test
    public void givenPatient_whenPatientIsNull_thenReturnFalse() {
        Assertions.assertNotEquals(ANY_PATIENT, null);
    }

    @Test
    public void givenPatient_whenComparedToANonObject_thenReturnFalse(){
        String nonObject = "Hello";
        Assertions.assertNotEquals(ANY_PATIENT, nonObject);


    }
}
