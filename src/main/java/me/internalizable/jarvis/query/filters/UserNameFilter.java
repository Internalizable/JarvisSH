package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class UserNameFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by User Name";
    }

    @Override
    public int getFilterID() {
        return 9;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getUser().getName().equalsIgnoreCase((String) filterValue);
    }

}
