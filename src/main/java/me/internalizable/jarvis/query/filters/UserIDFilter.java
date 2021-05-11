package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class UserIDFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by User ID";
    }

    @Override
    public int getFilterID() {
        return 8;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getUserID() == Math.toIntExact((Long) filterValue);
    }
}
