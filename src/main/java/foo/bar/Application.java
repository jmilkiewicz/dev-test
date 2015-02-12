package foo.bar;


import foo.bar.service.LocationSource;

import java.util.Collection;

public class Application {

    private final LocationSource locationSource;

    public Application(LocationSource locationSource) {

        this.locationSource = locationSource;
    }

    public Collection<String> exportFor(String berlin) {
        return null;
    }
}
