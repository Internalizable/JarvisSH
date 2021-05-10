package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class StatusFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by information contained in status";
    }

    @Override
    public int getFilterID() {
        return 3;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> {
            if(operation.getStatus() == null)
                return false;

            return operation.getStatus().stream().anyMatch(s -> s.contains((String) filterValue));
        };
    }
}
