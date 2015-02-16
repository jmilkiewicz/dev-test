package foo.bar.external;


import foo.bar.service.LocationSink;

import java.io.*;
import java.util.Collection;
//TODO Maybe add folder as constructor parameter
public class FileLocationSink implements LocationSink{
    public final String CVS_EXTENSION = ".csv";
    @Override
    public void storeLocations(String locationName, Collection<String> exportOutput) {
        String fileName = locationName + CVS_EXTENSION;
        try {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8"));){
                for (String s : exportOutput) {
                    bufferedWriter.write(s+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("unable to store file", e);
        }
    }
}
