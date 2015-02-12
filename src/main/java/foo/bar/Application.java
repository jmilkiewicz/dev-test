package foo.bar;


import foo.bar.foo.bar.domain.Location;
import foo.bar.service.LocationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Application {

    private final LocationSource locationSource;

    public Application(LocationSource locationSource) {
        this.locationSource = locationSource;
    }

    public Collection<String> exportFor(String locationName) {
        Collection<String> result = new LinkedList<>();
        Collection<Location> locations = locationSource.getLocationsFor("sda");
        for (Location location : locations) {
            String csv = location.getId() + "," + location.getType()+","+ location.getPosition().getLatitude() +"," +
                    location.getPosition().getLongitude();
            result.add(csv);
        }


        return result;
    }
}
