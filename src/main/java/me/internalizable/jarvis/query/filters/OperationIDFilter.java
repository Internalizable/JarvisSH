package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class OperationIDFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by operation ID";
    }

    @Override
    public int getFilterID() {
        return 1;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getId() == (Long) filterValue;
    }

}
