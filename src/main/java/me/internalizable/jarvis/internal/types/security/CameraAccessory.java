package me.internalizable.jarvis.internal.types.security;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IMonitor;
import org.simpleframework.xml.Element;

@Getter
@Setter
public class CameraAccessory extends Accessory implements IMonitor {

    @Element(name = "resolution")
    private double resolution;

    @Element(name = "range")
    private double range;

    @Element(name = "angle")
    private CameraAngle angle;

    public CameraAccessory(int id) {
        super(id);
        super.setType(AccessoryType.CAMERA);
    }

    public CameraAccessory() {
        super.setType(AccessoryType.CAMERA);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data");
    }

    @Override
    public String toString() {
        return super.toString() + "\nResolution: " + resolution + "\nRange: " + range + "\nAngle: " + angle.name();
    }

    public enum CameraAngle {
        MACRO, TRUE_DEPTH, WIDE, ULTRA_WIDE
    }
}
