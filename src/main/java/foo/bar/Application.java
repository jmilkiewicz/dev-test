package foo.bar;


import foo.bar.foo.bar.domain.Location;
import foo.bar.service.LocationSource;

import java.util.Collection;
import java.util.LinkedList;

public class Application {

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
        return location.getId() + "," + escape(location.getType()) + ","+ location.getPosition()
                .getLatitude() +"," +
                location.getPosition().getLongitude();
    }

    private static String escape(String valueToEscape) {
        return "\"" +valueToEscape + "\"";
    }
}
