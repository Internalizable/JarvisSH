package me.internalizable.jarvis.reader.xml;

import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.reader.CollectionType;
import me.internalizable.jarvis.reader.IReader;
import me.internalizable.jarvis.reader.IWriter;
import me.internalizable.jarvis.reader.xml.parsers.XMLAccessoryParser;
import me.internalizable.jarvis.reader.xml.parsers.XMLOperationParser;
import me.internalizable.jarvis.reader.xml.parsers.XMLUserParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

import static me.internalizable.jarvis.reader.CollectionType.*;
import static me.internalizable.jarvis.reader.ParserType.XML;

public class XMLReader implements IReader, IWriter {

    private final Serializer ser;

    public XMLReader() {
        ser = new Persister();
    }

    @Override
    public void readData() {
        try {
            XMLOperationParser xmlOperationParser = ser.read(XMLOperationParser.class, new File(OPERATION.buildPath(XML)));
            XMLUserParser xmlUserParser = ser.read(XMLUserParser.class, new File(USER.buildPath(XML)));
            XMLAccessoryParser xmlAccessoryParser = ser.read(XMLAccessoryParser.class, new File(ACCESSORY.buildPath(XML)));

            writeToList(xmlOperationParser.getList(), xmlUserParser.getList(), xmlAccessoryParser.getList());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void writeCollection(List<?> collection, CollectionType collectionType) {
        try {
            switch (collectionType) {
                case OPERATION -> {
                    XMLOperationParser xmlOperationParser = new XMLOperationParser();
                    xmlOperationParser.setList((List<Operation>) collection);

                    ser.write(xmlOperationParser, new File(collectionType.buildPath(XML)));
                }

                case ACCESSORY -> {
                    XMLAccessoryParser xmlAccessoryParser = new XMLAccessoryParser();
                    xmlAccessoryParser.setList((List<Accessory>) collection);

                    ser.write(xmlAccessoryParser, new File(collectionType.buildPath(XML)));
                }

                case USER -> {
                    XMLUserParser xmlUserParser = new XMLUserParser();
                    xmlUserParser.setList((List<User>) collection);

                    ser.write(xmlUserParser, new File(collectionType.buildPath(XML)));
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
