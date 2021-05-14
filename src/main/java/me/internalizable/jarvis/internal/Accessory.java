package me.internalizable.jarvis.internal;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.utils.StaticUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Getter
@Setter
@Root(name = "accessory")
@Entity("accessories")
public class Accessory {

    @Id
    @Element(name = "id")
    private int id;

    @Element(name = "name")
    private String name;

    @Element(name = "manufacturer")
    private String manufacturer;

    @Element(name = "type")
    private AccessoryType type;

    public Accessory() {
    }

    public Accessory(int id) {
        this.id = id;
    }

    public void printAccessory() {
        System.out.println(StaticUtils.getDivider());
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Accessory ID: " + id + "\nAccessory Name: " + name + "\nAccessory Manufacturer: " + manufacturer + "\nAccessory Type: " + type;
    }

    public enum AccessoryType {
        CAMERA, FIRE, MOTION, TV, LIGHTING, TEMPERATURE;
    }
}
