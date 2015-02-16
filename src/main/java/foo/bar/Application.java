package foo.bar;


import foo.bar.foo.bar.domain.Location;
import foo.bar.service.CvsMapper;
import foo.bar.service.LocationSource;

import java.util.Collection;
import java.util.LinkedList;

public class Application {
    private final LocationSource locationSource;
    private final CvsMapper cvsMapper;

    public Application(LocationSource locationSource) {
        this.locationSource = locationSource;
        this.cvsMapper = new CvsMapper();
    }

    public Collection<String> exportFor(String locationName) {
        Collection<String> result = new LinkedList<>();
        Collection<Location> locations = locationSource.getLocationsFor(locationName);
        for (Location location : locations) {
            String csvRow = cvsMapper.map(location);
            result.add(csvRow);
        }
        return result;
    }
}
