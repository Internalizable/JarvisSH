package me.internalizable.jarvis.reader.xml.parsers;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.users.User;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "users")
@Getter
@Setter
public class XMLUserParser {

    @ElementList(inline = true)
    private List<User> list = new ArrayList<>();

    public XMLUserParser() {
    }
}
