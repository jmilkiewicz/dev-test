package foo.bar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.core.IsNot.not;

public class GoEuroAPITest {

    @Test
    public void shallReturnNonEmptyJsonForUSA() throws Exception {
        Collection<Location> usaLocations = getLocations("USA");

        assertThat(usaLocations, notNullValue());
        assertThat(usaLocations, not(emptyCollectionOf(Location.class)));
    }

    private Collection<Location> getLocations(String location) throws IOException {
        //TODO use string format instead of lame string concatenation
        URL url = new URL("http://api.goeuro.com/api/v2/position/suggest/en/"+ location);
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                Charset.forName
                        ("UTF-8"));

        Gson gson = new Gson();

        Type collectionType = new TypeToken<Collection<Location>>(){}.getType();

        return gson.fromJson(inputStreamReader, collectionType);
    }

    @Test
    public void shallReturnEmptyJsonForNonExistingLocation() throws Exception {
        Collection<Location> nonExistingLocations = getLocations("_SADADSA");

        assertThat(nonExistingLocations, emptyCollectionOf(Location.class));
    }


}
