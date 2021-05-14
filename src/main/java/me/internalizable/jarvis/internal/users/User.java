package me.internalizable.jarvis.internal.users;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.utils.StaticUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Getter
@Setter
@Root(name = "user")
@Entity("users")
public class User {
    @Id
    @Element(name = "id")
    private int id;
    @Element(name = "name")
    private String name;
    @Element(name = "password")
    private String password;
    @Element(name = "type")
    private UserType type;

    public User() {
    }

    public void printUser() {
        System.out.println(StaticUtils.getDivider());
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "User ID: " + id + "\nUser Name: " + name + "\nUser Type: " + type;
    }
}
