package me.internalizable.jarvis.reader.xml.parsers;

import lombok.Getter;
import lombok.Setter;
import me.internalizable.jarvis.internal.Operation;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "operations")
@Getter @Setter
public class XMLOperationParser {

    @ElementList(inline=true)
    private List<Operation> list = new ArrayList<>();

    public XMLOperationParser() {}
}
