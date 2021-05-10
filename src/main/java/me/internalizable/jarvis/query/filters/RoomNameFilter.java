package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class RoomNameFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by Room Name";
    }

    @Override
    public int getFilterID() {
        return 2;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getRoomName().equalsIgnoreCase((String) filterValue);
    }

}
