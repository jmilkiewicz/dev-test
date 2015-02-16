package foo.bar.service;

import foo.bar.foo.bar.domain.Location;

public class CvsMapper {
    public static final String SEPARATOR = ",";

    public String map(Location location) {
        return location.getId() + SEPARATOR + escape(location.getName()) + SEPARATOR + escape(location.getType()) + SEPARATOR +
                location
                        .getPosition()
                        .getLatitude() + SEPARATOR +
                location.getPosition().getLongitude();
    }

    private static String escape(String valueToEscape) {
        return "\"" + escapeQuotesWithQuotes(valueToEscape) + "\"";
    }

    private static String escapeQuotesWithQuotes(String valueToEscape) {
        return valueToEscape.replaceAll("\"", "\"\"");
    }
}
