package me.internalizable.jarvis.reader.xml.parsers;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.types.appliances.LightingAccessory;
import me.internalizable.jarvis.internal.types.appliances.TVAccessory;
import me.internalizable.jarvis.internal.types.appliances.TemperatureAccessory;
import me.internalizable.jarvis.internal.types.security.CameraAccessory;
import me.internalizable.jarvis.internal.types.security.FireAccessory;
import me.internalizable.jarvis.internal.types.security.MotionSensor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "accessories")
@Getter @Setter
public class XMLAccessoryParser {

    @ElementListUnion({
            @ElementList(entry="lighting", inline= true, type= LightingAccessory.class),
            @ElementList(entry="tv", inline= true, type= TVAccessory.class),
            @ElementList(entry="temperature", inline= true, type= TemperatureAccessory.class),
            @ElementList(entry = "camera", inline=true, type= CameraAccessory.class),
            @ElementList(entry = "fire", inline=true, type= FireAccessory.class),
            @ElementList(entry = "motion", inline = true, type= MotionSensor.class),
            @ElementList(entry = "unassigned", inline = true, type= Accessory.class)
    })
    private List<Accessory> list = new ArrayList<>();

    public XMLAccessoryParser() {}
}
