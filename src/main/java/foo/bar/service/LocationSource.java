package foo.bar.service;

import foo.bar.foo.bar.domain.Location;

import java.util.Collection;

public interface LocationSource {
    Collection<Location> getLocationsFor(String location);
}
