package foo.bar;

import foo.bar.foo.bar.domain.Location;
import foo.bar.foo.bar.domain.Position;
import foo.bar.service.LocationSource;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ApplicationShallExportLocationsTest {

    private LocationSource locationSource = Mockito.mock(LocationSource.class);

    @Test
    public void shouldExportCVSRowForASingleLocation() throws Exception {
        Application application = new Application(locationSource);
        Collection<Location> sampleLocations = new ArrayList(){{
            add(new Location(1,"someType",new Position(14.12,15.222)));
        }};
        when(locationSource.getLocationsFor(anyString())).thenReturn(sampleLocations);

        Collection<String> processedLocations = application.exportFor("Berlin");

        assertThat(processedLocations, Matchers.containsInAnyOrder("1,someType,14.12,15.222"));
    }
}
