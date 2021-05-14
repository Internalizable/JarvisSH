package me.internalizable.jarvis.reader;

import java.util.List;

public interface IWriter {
    void writeCollection(List<?> collection, CollectionType collectionType);
}
