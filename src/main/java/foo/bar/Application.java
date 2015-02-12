package foo.bar;


import foo.bar.service.LocationSource;

import java.util.Arrays;
import java.util.Collection;

public class Application {

    private final LocationSource locationSource;

    public Application(LocationSource locationSource) {

        this.locationSource = locationSource;
    }

    public Collection<String> exportFor(String berlin) {
        return Arrays.asList("1,someType,14.12,15.222");
    }
}
