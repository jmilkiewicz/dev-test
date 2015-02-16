package foo.bar.external;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foo.bar.foo.bar.domain.Location;
import foo.bar.service.LocationSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;


public class HttpLocationSource implements LocationSource {
    private final String urlFormat;

    public HttpLocationSource(String urlPrefix) {
        this.urlFormat = urlPrefix + "%s";
    }

    @Override
    public Collection<Location> getLocationsFor(String location) {

        try {
            String fullURL = String.format(urlFormat,location);
            URL url = new URL(fullURL);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                    Charset.forName
                            ("UTF-8"));
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Location>>(){}.getType();
            return gson.fromJson(inputStreamReader, collectionType);
        } catch (MalformedURLException e) {
            throw new RuntimeException("unable to parse URL", e);
        } catch (IOException e) {
            throw new RuntimeException("unable to readData", e);
        }
    }
}
