package me.internalizable.jarvis.query.filters;

import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.query.IFilter;

import java.util.function.Predicate;

public class AccessoryManufacturerFilter implements IFilter {

    @Override
    public String getFilterName() {
        return "Filter by Accessory Manufacturer";
    }

    @Override
    public int getFilterID() {
        return 7;
    }

    @Override
    public Predicate<Operation> getPredicate(Object filterValue) {
        return operation -> operation.getAccessory().getManufacturer().equalsIgnoreCase((String) filterValue);
    }
}
