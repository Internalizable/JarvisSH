package me.internalizable.jarvis.utils;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.Jarvis;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.internal.users.UserType;
import me.internalizable.jarvis.reader.CollectionType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class JarvisLists {
    @Getter @Setter
    private static List<Operation> operationList;

    @Getter @Setter
    private static List<User> usersList;

    @Getter @Setter
    private static List<Accessory> accessoryList;

    public JarvisLists() {
        operationList = new ArrayList<>();
        usersList = new ArrayList<>();
        accessoryList = new ArrayList<>();
    }

    public static boolean hasUser(String username) {
        return usersList.stream().anyMatch(user -> user.getName().equalsIgnoreCase(username));
    }

    public static boolean hasLogin(String username, String password) {
        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        return usersList.stream().anyMatch(user -> user.getName().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(hashedPassword));
    }

    public static void registerUser(String username, String password, UserType userType) {
        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        User autoIncrementation = JarvisLists.getUsersList().stream().max(Comparator.comparingInt(User::getId)).orElse(null);

        int id;

        if(autoIncrementation == null)
            id = 1;
        else
            id = autoIncrementation.getId() + 1;

        User user = new User();
        user.setId(id);
        user.setName(username);
        user.setPassword(hashedPassword);
        user.setType(userType);

        JarvisLists.getUsersList().add(user);

        Jarvis.getWriter().writeCollection(JarvisLists.getUsersList(), CollectionType.USER);
    }

    public static Operation getOperationFromID(int id) {
        return operationList.stream().filter(operation -> operation.getId() == id).findFirst().orElse(null);
    }

    public static User getUserFromID(int id) {
        return usersList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public static Accessory getAccessoryFromID(int id) {
        return accessoryList.stream().filter(accessory -> accessory.getId() == id).findFirst().orElse(null);
    }

    public static void printLastOperations(int n) {
        operationList.subList(0, Math.min(operationList.size(), n)).forEach(Operation::printSummaryOperation);
    }

    public static void showBetween(Date lowestDate, Date highestDate) {
        JarvisLists.getOperationList().stream().filter(accessory -> accessory.getEpochMillis() > lowestDate.getTime() && accessory.getEpochMillis() < highestDate.getTime()).forEach(Operation::printSummaryOperation);
    }

    public static void showChangeOfStates(int id) {
        Operation search = getOperationFromID(id);

        if(search == null) {
            System.out.println("No operation could be found with the given ID");
            return;
        }

        Operation lastOperation = operationList.stream().filter(operation -> operation.getAccessoryID() == search.getAccessoryID() && operation.getEpochMillis() < search.getEpochMillis()).findFirst().orElse(null);

        if (lastOperation == null)
            System.out.println("The accessory was initially installed, there is no status.");
        else {
            System.out.println("Before Status: ");
            lastOperation.printStatusList();
        }

        System.out.println("After Status: ");
        search.printStatusList();
    }
}
