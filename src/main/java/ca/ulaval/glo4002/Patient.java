package ca.ulaval.glo4002;

public class Patient  {

    public final String name;
    public final int gravity;
    public final VisibleSymptom visibleSymptom;

    public Patient(String name, int gravity, VisibleSymptom visibleSymptom) {
        this.name = name;
        this.gravity = gravity;
        this.visibleSymptom = visibleSymptom;
    }
}
