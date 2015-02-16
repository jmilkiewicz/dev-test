package foo.bar;

import foo.bar.foo.bar.domain.Location;
import foo.bar.foo.bar.domain.Position;
import foo.bar.service.LocationSink;
import foo.bar.service.LocationSource;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationShallExportLocationsTest {
    private LocationSource locationSource = Mockito.mock(LocationSource.class);
    private LocationSink locationSink = Mockito.mock(LocationSink.class);
    private List<Location> locationsFound = new LinkedList<>();
    private Application sut;

    @Before
    public void setUp() throws Exception {
        sut = new Application(locationSource, locationSink);
        when(locationSource.getLocationsFor(anyString())).thenReturn(locationsFound);
    }

    @Test
    public void shouldExportCVSRowForBerlinLocation() throws Exception {
        locationsFound.add(new Location(1, "name", "location", Position.latitudeLongitude(51.45775, 10.2384)));

        sut.exportFor("Berlin");

        verify(locationSink).storeLocations(eq("Berlin"), containsInAnyOrder("1,\"name\",\"location\",51.45775,10.2384"));
    }

    private Collection<String> containsInAnyOrder(String... cvsRows) {
        return (Collection<String>) org.mockito.Matchers.argThat(Matchers
                .containsInAnyOrder(cvsRows));
    }


    @Test
    public void shouldExportCVSRowForPoznanLocation() throws Exception {
        locationsFound.add(new Location(1, "somename", "location", Position.latitudeLongitude(52.41747, 16.88414)));

        sut.exportFor("Poznan");

        verify(locationSink).storeLocations(eq("Poznan"), containsInAnyOrder("1,\"somename\",\"location\",52.41747,16.88414"));
    }

    @Test
    public void shouldExportCVSRowForMultipleLocations() throws Exception {
        locationsFound.add(new Location(1, "name", "location", Position.latitudeLongitude(1.1, 2.2)));
        locationsFound.add(new Location(2, "name2", "location2", Position.latitudeLongitude(3.3, 4.4)));

        sut.exportFor("Poznan");

        verify(locationSink).storeLocations(eq("Poznan"), containsInAnyOrder("1,\"name\",\"location\",1.1,2.2", "2," +
                "\"name2\",\"location2\",3.3,4" +
                ".4"));
    }


    @Test
    public void shouldCallLocationSourceWithCorrectLocationName() throws Exception {
        String locationName = "Poznan";
        sut.exportFor(locationName);

        verify(locationSource).getLocationsFor(locationName);
    }

    @Test
    public void shouldExportCVSRowForLocationWithCommas() throws Exception {
        locationsFound.add(new Location(1, "na,me", "lo,ca,tion", Position.latitudeLongitude(1.1, 2.2)));

        sut.exportFor("Poznan");

        verify(locationSink).storeLocations(eq("Poznan"), containsInAnyOrder("1,\"na,me\",\"lo,ca,tion\",1.1,2.2"));
    }

    @Test
    public void shouldExportCVSRowForLocationWithQuotes() throws Exception {
        locationsFound.add(new Location(1, "n\"am\"e", "loc\"", Position.latitudeLongitude(1.1, 2.2)));

        sut.exportFor("Poznan");

        verify(locationSink).storeLocations(eq("Poznan"), containsInAnyOrder("1,\"n\"\"am\"\"e\",\"loc\"\"\",1.1,2.2"));
    }


}
