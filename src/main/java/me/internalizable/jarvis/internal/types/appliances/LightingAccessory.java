package me.internalizable.jarvis.internal.types.appliances;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IControllable;
import me.internalizable.jarvis.internal.IMonitor;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.utils.JarvisLists;
import org.simpleframework.xml.Element;

@Getter @Setter
public class LightingAccessory extends Accessory implements IMonitor, IControllable {

    @Element(name = "intensity")
    private double intensity;

    @Element(name = "maxIntensity")
    private double maxIntensity;

    public LightingAccessory(int id) {
        super(id);
        super.setType(AccessoryType.LIGHTING);
    }

    public LightingAccessory() {
        super.setType(AccessoryType.LIGHTING);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data for the Light system");
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on the light accessory");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the light accessory");
    }

    @Override
    public String toString() {
        return super.toString() + "\nIntensity: " + intensity + "\nMax Intensity: " + maxIntensity;
    }
}
