package ca.ulaval.glo4002;

import java.util.Objects;
import java.util.UUID;

public class Patient {

    private final UUID uuid;
    private final String name;
    private final int gravity;
    private final VisibleSymptom visibleSymptom;

    public Patient(UUID uuid, String name, int gravity, VisibleSymptom visibleSymptom) {
        this.uuid = uuid;
        this.name = name;
        this.gravity = gravity;
        this.visibleSymptom = visibleSymptom;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public VisibleSymptom getVisibleSymptom() {
        return visibleSymptom;
    }

    public int getGravity() {
        return gravity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Patient)) {
            return false;
        }

        return this.uuid == ((Patient) obj).getUuid();
    }
}
