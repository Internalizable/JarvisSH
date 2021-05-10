package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class AccessoryNameFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by Accessory Name";
    }

    @Override
    public int getFilterID() {
        return 5;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getAccessory().getName().equalsIgnoreCase((String) filterValue);
    }
}
