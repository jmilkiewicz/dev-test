package foo.bar.external;

import foo.bar.Application;

public class Start {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide location as command line argument");

        }else{
            exportFor(args[0]);
        }


    }

    private static void exportFor(String location) {
        HttpLocationSource httpLocationSource = new HttpLocationSource("http://api.goeuro" +
                ".com/api/v2/position/suggest/en/");
        FileLocationSink fileLocationSink = new FileLocationSink();
        Application application = new Application(httpLocationSource, fileLocationSink);
        application.exportFor(location);
        System.out.println("DONE");
    }
}
