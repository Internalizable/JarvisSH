package me.internalizable.jarvis.reader;

import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.utils.JarvisLists;

import java.util.Comparator;
import java.util.List;

public interface IReader {
    void readData();

    default void writeToList(List<Operation> operationData, List<User> userData, List<Accessory> accessoryData) {
        accessoryData.sort(Comparator.comparingInt(Accessory::getId));
        userData.sort(Comparator.comparingInt(User::getId));

        operationData.sort(Comparator.comparingLong(Operation::getEpochMillis).reversed());

        JarvisLists.setUsersList(userData);
        JarvisLists.setAccessoryList(accessoryData);

        operationData.forEach(operation -> {
            operation.setUser(JarvisLists.getUserFromID(operation.getUserID()));
            operation.setAccessory(JarvisLists.getAccessoryFromID(operation.getAccessoryID()));
        });

        JarvisLists.setOperationList(operationData);
    }

}
