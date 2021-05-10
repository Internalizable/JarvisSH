package me.internalizable.jarvis.query.filters.utils;

import lombok.Getter;
import me.internalizable.jarvis.query.IFilter;
import me.internalizable.jarvis.query.filters.*;
import me.internalizable.jarvis.query.filters.time.DayFilter;
import me.internalizable.jarvis.query.filters.time.MonthFilter;
import me.internalizable.jarvis.query.filters.time.YearFilter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilterUtils {

    @Getter
    private static List<IFilter> availableFilters;

    public FilterUtils() {
        availableFilters = new ArrayList<>();
    }

    public void loadFilters() {
        availableFilters.add(new OperationIDFilter());
        availableFilters.add(new RoomNameFilter());
        availableFilters.add(new StatusFilter());

        availableFilters.add(new AccessoryIDFilter());
        availableFilters.add(new AccessoryTypeFilter());
        availableFilters.add(new AccessoryNameFilter());
        availableFilters.add(new AccessoryManufacturerFilter());

        availableFilters.add(new UserIDFilter());
        availableFilters.add(new UserNameFilter());
        availableFilters.add(new UserTypeFilter());

        availableFilters.add(new DayFilter());
        availableFilters.add(new MonthFilter());
        availableFilters.add(new YearFilter());

        availableFilters.sort(Comparator.comparingInt(IFilter::getFilterID));
    }
}
