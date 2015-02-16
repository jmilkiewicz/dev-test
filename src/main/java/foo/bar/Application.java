package foo.bar;


import foo.bar.foo.bar.domain.Location;
import foo.bar.service.CvsMapper;
import foo.bar.service.LocationSink;
import foo.bar.service.LocationSource;

import java.util.Collection;
import java.util.LinkedList;

public class Application {
    private final LocationSource locationSource;
    private final LocationSink locationSink;
    private final CvsMapper cvsMapper;

    public Application(LocationSource locationSource, LocationSink locationSink) {
        this.locationSource = locationSource;
        this.locationSink = locationSink;
        this.cvsMapper = new CvsMapper();
    }

    public void exportFor(String locationName) {
        Collection<String> result = new LinkedList<>();
        Collection<Location> locations = locationSource.getLocationsFor(locationName);
        for (Location location : locations) {
            String csvRow = cvsMapper.map(location);
            result.add(csvRow);
        }
        locationSink.storeLocations(locationName, result);
    }
}
