package me.internalizable.jarvis.query;

import me.internalizable.jarvis.internal.Operation;

import java.util.function.Predicate;

public interface IFilter {

    default String getFilterName() {
        return getClass().getName();
    }

    int getFilterID();

    Predicate<Operation> getPredicate(Object filterValue);

}
