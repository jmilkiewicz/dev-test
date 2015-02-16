package foo.bar;

import foo.bar.external.HttpLocationSource;
import foo.bar.foo.bar.domain.Location;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.core.IsNot.not;

public class HttpLocationSourceTest {
    private HttpLocationSource sut = new HttpLocationSource();

    @Test
    public void shallReturnValidCollectionOfLocationsForUSA() throws Exception {
        Collection<Location> usaLocations = sut.getLocationsFor("USA");

        assertThat(usaLocations, notNullValue());
        assertThat(usaLocations, not(emptyCollectionOf(Location.class)));

    }

    @Test
    public void shallReturnEmptyJsonForNonExistingLocation() throws Exception {
        Collection<Location> nonExistingLocations = sut.getLocationsFor("_SADADSA");

        assertThat(nonExistingLocations, emptyCollectionOf(Location.class));
    }

}
