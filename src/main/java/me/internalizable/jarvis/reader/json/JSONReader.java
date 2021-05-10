package me.internalizable.jarvis.reader.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.reader.CollectionType;
import me.internalizable.jarvis.reader.IReader;
import me.internalizable.jarvis.reader.IWriter;
import static me.internalizable.jarvis.reader.CollectionType.*;
import static me.internalizable.jarvis.reader.ParserType.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JSONReader implements IReader, IWriter {

    private final Type ACCESSORY_TYPE;
    private final Type OPERATION_TYPE;
    private final Type USER_TYPE;
    private final Gson gson;

    public JSONReader() {
        ACCESSORY_TYPE = new TypeToken<List<Accessory>>() {}.getType();
        OPERATION_TYPE = new TypeToken<List<Operation>>() {}.getType();
        USER_TYPE = new TypeToken<List<User>>() {}.getType();
        gson = new GsonBuilder().registerTypeAdapter(ACCESSORY_TYPE, new AccessoryAdapter()).setPrettyPrinting().create();
    }

    @Override
    public void readData() {
        JsonReader operationReader = null;
        JsonReader userReader = null;
        JsonReader accessoryReader = null;

        try {
            operationReader = new JsonReader(new FileReader(OPERATION.buildPath(JSON)));
            userReader = new JsonReader(new FileReader(USER.buildPath(JSON)));
            accessoryReader = new JsonReader(new FileReader(ACCESSORY.buildPath(JSON)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert operationReader != null;
        assert userReader != null;
        assert accessoryReader != null;

        List<Operation> operationData = gson.fromJson(operationReader, OPERATION_TYPE);
        List<User> userData = gson.fromJson(userReader, USER_TYPE);
        List<Accessory> accessoryData = gson.fromJson(accessoryReader, ACCESSORY_TYPE);

        writeToList(operationData, userData, accessoryData);
    }

    @Override
    public void writeCollection(List<?> collection, CollectionType type) {
        String pathType = type.buildPath(JSON);

        try {
            FileWriter fileWriter = new FileWriter(pathType);
            gson.toJson(collection, fileWriter);

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
