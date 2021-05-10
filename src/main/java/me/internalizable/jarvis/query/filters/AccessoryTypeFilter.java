package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class AccessoryTypeFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by Accessory Type";
    }

    @Override
    public int getFilterID() {
        return 6;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getAccessory().getType().name().equalsIgnoreCase((String) filterValue);
    }
}
