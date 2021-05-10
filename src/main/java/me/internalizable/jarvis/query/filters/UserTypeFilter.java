package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class UserTypeFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by User Type";
    }

    @Override
    public int getFilterID() {
        return 10;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getUser().getType().name().equalsIgnoreCase((String) filterValue);
    }

}
