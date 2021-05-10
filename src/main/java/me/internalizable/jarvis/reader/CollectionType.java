package me.internalizable.jarvis.reader;

import lombok.NonNull;

public enum CollectionType {
    OPERATION("operations"), ACCESSORY("accessories"), USER("users");

    String baseName;

    CollectionType(String baseName) {
        this.baseName = baseName;
    }

    @NonNull
    public String buildPath(ParserType parserType) {
        switch (parserType) {
            case XML -> {
                return "data/xml/" + baseName + ".xml";
            }

            case JSON -> {
                return "data/json/" + baseName + ".json";
            }
        }

        return baseName;
    }
}
