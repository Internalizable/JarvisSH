package me.internalizable.jarvis.internal.types.appliances;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.IControllable;
import me.internalizable.jarvis.internal.IMonitor;
import org.simpleframework.xml.Element;

@Getter
@Setter
public class TemperatureAccessory extends Accessory implements IMonitor, IControllable {

    @Element(name = "temperature")
    private double temperature;

    @Element(name = "fanSpeed")
    private FanSpeed fanSpeed;

    public TemperatureAccessory(int id) {
        super(id);
        super.setType(AccessoryType.TEMPERATURE);
    }

    public TemperatureAccessory() {
        super.setType(AccessoryType.TEMPERATURE);
    }

    @Override
    public void generateData() {
        System.out.println("Generating data for the temperature system");
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on the temperature accessory");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the temperature accessory");
    }

    public enum FanSpeed {
        LOW, MEDIUM, HIGH
    }

    @Override
    public String toString() {
        return super.toString() + "\nTemperature: " + temperature + "\nFan Speed: " + fanSpeed.name();
    }
}
