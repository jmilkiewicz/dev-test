package foo.bar.service;


import java.util.Collection;

public interface LocationSink {
    void storeLocations(String locationName, Collection<String> exportOutput);
}
