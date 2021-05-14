package me.internalizable.jarvis.query.filters.time;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;
import me.internalizable.jarvis.utils.StaticUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;

public class YearFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by given year using [yyyy]";
    }

    @Override
    public int getFilterID() {
        return 13;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> {
            LocalDate operationDate = StaticUtils.convertToLocalDate(new Date(operation.getEpochMillis()));
            LocalDate filterDate = StaticUtils.convertToLocalDate((Date) filterValue);

            return operationDate.getYear() == filterDate.getYear();
        };
    }
}
