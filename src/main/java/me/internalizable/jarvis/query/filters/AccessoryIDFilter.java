package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class AccessoryIDFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by Accessory ID";
    }

    @Override
    public int getFilterID() {
        return 4;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getAccessoryID() ==  Integer.parseInt(String.valueOf(filterValue));
    }
}
