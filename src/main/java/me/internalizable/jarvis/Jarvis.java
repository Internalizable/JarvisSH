package me.internalizable.jarvis;

import lombok.Getter;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.query.CLISearch;
import me.internalizable.jarvis.reader.CollectionType;
import me.internalizable.jarvis.reader.IReader;
import me.internalizable.jarvis.reader.IWriter;
import me.internalizable.jarvis.reader.ParserType;
import me.internalizable.jarvis.reader.json.JSONReader;
import me.internalizable.jarvis.reader.nosql.MongoReader;
import me.internalizable.jarvis.reader.xml.XMLReader;
import me.internalizable.jarvis.utils.JarvisLists;

public class Jarvis {

    @Getter
    private static IReader reader;

    @Getter
    private static IWriter writer;

    public static void main(String[] args) {
        if(args[0] != null && args[0].equalsIgnoreCase("synchronize")) {
            System.out.println("Synchronizing from JSON file...");

            reader = new JSONReader();
            writer = new JSONReader();

            reader.readData();

            XMLReader xmlReader = new XMLReader();
            MongoReader mongoReader = new MongoReader();

            xmlReader.writeCollection(JarvisLists.getOperationList(), CollectionType.OPERATION);
            xmlReader.writeCollection(JarvisLists.getUsersList(), CollectionType.USER);
            xmlReader.writeCollection(JarvisLists.getAccessoryList(), CollectionType.ACCESSORY);

            mongoReader.writeCollection(JarvisLists.getOperationList(), CollectionType.OPERATION);
            mongoReader.writeCollection(JarvisLists.getUsersList(), CollectionType.USER);
            mongoReader.writeCollection(JarvisLists.getAccessoryList(), CollectionType.ACCESSORY);

            System.out.println("Synchronized XML and Mongo databases with JSON data.");
            System.exit(-1);
        }

        if(args.length != 2) {
            System.out.println("Incorrect arguments, please relaunch with correct options.");
            System.exit(-1);
        }

        ParserType parserType = ParserType.valueOf(args[0]);

        switch (parserType) {
            case JSON -> {
                reader = new JSONReader();
                writer = new JSONReader();
            }

            case XML -> {
                reader = new XMLReader();
                writer = new XMLReader();
            }

            case MONGODB -> {
                reader = new MongoReader();
                writer = new MongoReader();
            }
        }

        reader.readData();

        CLISearch cliSearch = new CLISearch();

        if(args[1].equalsIgnoreCase("gui"))
            App.open();
        else
            cliSearch.start();

    }


}
