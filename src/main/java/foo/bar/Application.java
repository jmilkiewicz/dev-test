package foo.bar;


import foo.bar.foo.bar.domain.Location;
import foo.bar.service.LocationSource;

import java.util.Collection;
import java.util.LinkedList;

public class Application {

    public static final String SEPARATOR = ",";
    private final LocationSource locationSource;

    public Application(LocationSource locationSource) {
        this.locationSource = locationSource;
    }

    public Collection<String> exportFor(String locationName) {
        Collection<String> result = new LinkedList<>();
        Collection<Location> locations = locationSource.getLocationsFor(locationName);
        for (Location location : locations) {
            String csvRow = map(location);
            result.add(csvRow);
        }
        return result;
    }

    private String map(Location location) {
        return location.getId() +SEPARATOR  +escape(location.getName())+SEPARATOR + escape(location.getType()) + SEPARATOR+
                location
                .getPosition()
                .getLatitude() + SEPARATOR +
                location.getPosition().getLongitude();
    }

    private static String escape(String valueToEscape) {
        return "\"" +valueToEscape + "\"";
    }
}
