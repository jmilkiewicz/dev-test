package foo.bar;

import foo.bar.foo.bar.domain.Location;
import foo.bar.foo.bar.domain.Position;
import foo.bar.service.LocationSource;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationShallExportLocationsTest {
    private LocationSource locationSource = Mockito.mock(LocationSource.class);
    private List<Location> locationsFound = new LinkedList<>();
    private Application sut;

    @Before
    public void setUp() throws Exception {
        sut = new Application(locationSource);
        when(locationSource.getLocationsFor(anyString())).thenReturn(locationsFound);
    }

    @Test
    public void shouldExportCVSRowForBerlinLocation() throws Exception {
        locationsFound.add(new Location(1, "name", "location",new Position(51.45775, 10.2384)));

        Collection<String> processedLocations = sut.exportFor("Berlin");

        assertThat(processedLocations, Matchers.containsInAnyOrder("1,\"name\",\"location\",51.45775,10.2384"));
    }

    @Test
    public void shouldExportCVSRowForPoznanLocation() throws Exception {
        locationsFound.add(new Location(1, "somename", "location",new Position(52.41747, 16.88414)));;

        Collection<String> processedLocations = sut.exportFor("Poznan");

        assertThat(processedLocations, Matchers.containsInAnyOrder("1,\"somename\",\"location\",52.41747,16.88414"));
    }

    @Test
    public void shouldExportCVSRowForMultipleLocations() throws Exception {
        locationsFound.add(new Location(1, "name", "location",new Position(1.1, 2.2)));;
        locationsFound.add(new Location(2, "name2", "location2",new Position(3.3, 4.4)));;

        Collection<String> processedLocations = sut.exportFor("Poznan");

        assertThat(processedLocations, Matchers.containsInAnyOrder("1,\"name\",\"location\",1.1,2.2", "2," +
                "\"name2\",\"location2\",3.3,4" +
                ".4"));
    }


    @Test
    public void shouldCallLocationSourceWithCorrectLocationName() throws Exception {
        String locationName = "Poznan";
        Collection<String> processedLocations = sut.exportFor(locationName);

        verify(locationSource).getLocationsFor(locationName);
    }

    @Test
    public void shouldExportCVSRowForLocationTypeWithCommas() throws Exception {
        locationsFound.add(new Location(1, "na,me", "lo,ca,tion",new Position(1.1, 2.2)));;

        Collection<String> processedLocations = sut.exportFor("Poznan");

        assertThat(processedLocations, Matchers.containsInAnyOrder("1,\"na,me\",\"lo,ca,tion\",1.1,2.2"));
    }



}
