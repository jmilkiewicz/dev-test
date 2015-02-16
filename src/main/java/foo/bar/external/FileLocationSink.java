package foo.bar.external;


import foo.bar.service.LocationSink;

import java.io.*;
import java.util.Collection;

public class FileLocationSink implements LocationSink{
    @Override
    public void storeLocations(String locationName, Collection<String> exportOutput) {
        try {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(locationName), "UTF-8"));){
                for (String s : exportOutput) {
                    bufferedWriter.write(s+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("unable to store file", e);
        }
    }
}
