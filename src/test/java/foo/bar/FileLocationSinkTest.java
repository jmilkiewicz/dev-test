package foo.bar;


import foo.bar.external.FileLocationSink;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class FileLocationSinkTest {
    public static final String SAMPLE_LOCATION = "location1";
    public static final String OUTPUT_FILE_NAME = SAMPLE_LOCATION + ".csv";
    private FileLocationSink sut = new FileLocationSink();

    @Before
    public void removeSampleFileName() throws Exception {
        File file = new File(OUTPUT_FILE_NAME);
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void shallSaveDataToAFile() throws IOException {
        String[] fileContent = {"a1,a2", "b1,b2", "c1,c2"};

        sut.storeLocations(SAMPLE_LOCATION, Arrays.asList(fileContent));

        assertThat(readFileContent(OUTPUT_FILE_NAME), Matchers.contains(fileContent));
    }

    private List<String> readFileContent(String fileName) throws IOException {
        List<String> fileRows = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),
                "UTF-8"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileRows.add(line);
            }
            return fileRows;
        }
    }
}