package me.internalizable.jarvis.internal.types.appliances;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IControllable;
import me.internalizable.jarvis.internal.IMonitor;
import org.simpleframework.xml.Element;

@Getter
@Setter
public class TVAccessory extends Accessory implements IMonitor, IControllable {

    @Element(name = "resolution")
    private String resolution;

    @Element(name = "width")
    private double width;

    @Element(name = "height")
    private double height;

    @Element(name = "is4K")
    private boolean is4K;

    public TVAccessory(int id) {
        super(id);
        super.setType(AccessoryType.TV);
    }

    public TVAccessory() {
        super.setType(AccessoryType.TV);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data for the TV system");
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on the TV accessory");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the TV accessory");
    }

    @Override
    public String toString() {
        return super.toString() + "\nResolution: " + resolution + "\nWidth: " + width + "\nHeight: " + height + "\n4K: " + is4K;
    }
}
