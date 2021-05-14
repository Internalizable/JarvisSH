package me.internalizable.jarvis.internal.types.security;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IMonitor;
import org.simpleframework.xml.Element;

@Getter
@Setter
public class MotionSensor extends Accessory implements IMonitor {

    @Element(name = "range")
    private double range;

    @Element(name = "height")
    private double height;

    public MotionSensor(int id) {
        super(id);
        super.setType(AccessoryType.MOTION);
    }

    public MotionSensor() {
        super.setType(AccessoryType.MOTION);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data");
    }

    @Override
    public String toString() {
        return super.toString() + "\nRange: " + range + "\nHeight: " + height;
    }

}