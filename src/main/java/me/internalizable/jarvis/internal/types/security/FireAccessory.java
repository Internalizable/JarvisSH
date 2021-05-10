package me.internalizable.jarvis.internal.types.security;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IMonitor;
import org.simpleframework.xml.Element;

@Getter @Setter
public class FireAccessory extends Accessory implements IMonitor {

    @Element(name = "smokeLimit")
    private double smokeLimit;

    @Element(name = "co2TankCapacity")
    private double co2TankCapacity;

    @Element(name = "isLoaded")
    private boolean isLoaded;

    public FireAccessory(int id) {
        super(id);
        super.setType(AccessoryType.FIRE);
    }

    public FireAccessory() {
        super.setType(AccessoryType.FIRE);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nSmoke Limit: " + smokeLimit + "\nTank Capacity: " + co2TankCapacity + "\nFully Loaded: " + isLoaded;
    }
}
