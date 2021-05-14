package me.internalizable.jarvis.reader.nosql;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import lombok.Getter;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.reader.CollectionType;
import me.internalizable.jarvis.reader.IReader;
import me.internalizable.jarvis.reader.IWriter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MongoReader implements IReader, IWriter {

    @Getter
    private static Datastore datastore;

    private final Set<Class<? extends Accessory>> setClasses;

    public MongoReader() {
        Reflections reflections = new Reflections("me.internalizable.jarvis.internal.types");
        setClasses = reflections.getSubTypesOf(Accessory.class);

        datastore = Morphia.createDatastore(MongoClients.create(), "smarthome");

        datastore.getMapper().mapPackage("me.internalizable.jarvis.internal");
        datastore.getMapper().mapPackage("me.internalizable.jarvis.internal.types.security");
        datastore.getMapper().mapPackage("me.internalizable.jarvis.internal.types.appliances");
        datastore.getMapper().mapPackage("me.internalizable.jarvis.internal.users");

        datastore.ensureIndexes();
    }

    @Override
    public void readData() {
        List<Accessory> accessories = new ArrayList<>();
        setClasses.forEach(clazz -> accessories.addAll(datastore.find(clazz).iterator().toList()));

        List<Operation> operations = datastore.find(Operation.class).iterator().toList();
        List<User> users = datastore.find(User.class).iterator().toList();

        writeToList(operations, users, accessories);
    }

    @Override
    public void writeCollection(List<?> collection, CollectionType collectionType) {
        datastore.save(collection);
    }
}
