package me.internalizable.jarvis.internal;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.utils.StaticUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.text.SimpleDateFormat;
import java.util.List;

@Getter
@Setter
@Root(name = "operation")
@Entity("operations")
public class Operation {

    @Id
    @Element(name = "id")
    private long id;

    @Element(name = "userID")
    private int userID;

    @Element(name = "roomName")
    private String roomName;

    @Element(name = "epochMillis")
    private long epochMillis;

    @Element(name = "accessoryID")
    private int accessoryID;

    @ElementList(name = "status")
    private List<String> status;

    private transient User user;
    private transient Accessory accessory;

    public Operation() {
    }

    public void printStatusList() {
        if (status == null)
            System.out.println("\tNo status set.");
        else
            status.forEach(s -> System.out.println("\t- " + s));
    }

    public void printSummaryOperation() {
        System.out.println(StaticUtils.getDivider());
        System.out.println("Operation ID: " + getId());
        System.out.println("User ID: " + getUserID());
        System.out.println("Accessory ID: " + getAccessoryID());
    }

    public void printOperation() {
        System.out.println(StaticUtils.getDivider());
        System.out.println("Operation ID: " + getId());
        System.out.println("Operation Room: " + getRoomName());
        System.out.println("Operation Time: " + getDateString());
        System.out.println("Operation Status: ");

        printStatusList();

        System.out.println("\n" + accessory.toString() + "\n");
        System.out.println(user.toString());
    }

    public String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(epochMillis);
    }
}
