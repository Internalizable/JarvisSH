package me.internalizable.jarvis.reader.json;

import com.google.gson.*;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.types.appliances.LightingAccessory;
import me.internalizable.jarvis.internal.types.appliances.TVAccessory;
import me.internalizable.jarvis.internal.types.appliances.TemperatureAccessory;
import me.internalizable.jarvis.internal.types.security.CameraAccessory;
import me.internalizable.jarvis.internal.types.security.FireAccessory;
import me.internalizable.jarvis.internal.types.security.MotionSensor;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccessoryAdapter implements JsonSerializer<ArrayList<Accessory>>, JsonDeserializer<List<Accessory>> {

    private final Map<String, Class<?>> classMappings;

    public AccessoryAdapter() {
        classMappings = new TreeMap<>();

        classMappings.put("ACCESSORY", Accessory.class);
        classMappings.put("TEMPERATURE", TemperatureAccessory.class);
        classMappings.put("LIGHTING", LightingAccessory.class);
        classMappings.put("TV", TVAccessory.class);
        classMappings.put("CAMERA", CameraAccessory.class);
        classMappings.put("FIRE", FireAccessory.class);
        classMappings.put("MOTION", MotionSensor.class);
    }

    @Override
    public JsonElement serialize(ArrayList<Accessory> list, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (list == null)
            return null;
        else {
            JsonArray jsonArray = new JsonArray();

            list.forEach(accessory -> {
                Class c = classMappings.get(accessory.getType().name());

                if (c == null)
                    throw new RuntimeException("Unknown class: " + accessory.getType());

                jsonArray.add(context.serialize(accessory, c));
            });

            return jsonArray;
        }
    }

    public List<Accessory> deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {
        List list = new ArrayList<Accessory>();

        JsonArray jsonArray = json.getAsJsonArray();

        jsonArray.forEach(jsonElement -> {
            String type = jsonElement.getAsJsonObject().get("type").getAsString();

            Class c = classMappings.get(type);

            if (c == null)
                throw new RuntimeException("Unknown class: " + type);

            list.add(context.deserialize(jsonElement, c));
        });

        return list;
    }
}
