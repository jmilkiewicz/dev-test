package foo.bar;


import foo.bar.external.FileLocationSink;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class FileLocationSinkTest {
    private FileLocationSink sut = new FileLocationSink();

    @Test
    public void shallSaveDataToAFile() throws FileNotFoundException, UnsupportedEncodingException {
        String[] fileContent = {"a1,a2", "b1,b2", "c1,c2"};

        sut.storeLocations("aaa.csv", Arrays.asList(fileContent));

        assertThat(readFileContent("aaa.csv"), Matchers.contains(fileContent));
    }

    private List<String> readFileContent(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> fileRows = new LinkedList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                "UTF-8"))){
            String line;
            while( (line = bufferedReader.readLine()) != null){
                fileRows.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  fileRows;
    }

}