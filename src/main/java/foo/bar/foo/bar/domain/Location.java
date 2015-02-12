package foo.bar.foo.bar.domain;

/**
 * Created by milus on 2/12/15.
 */
public class Location {
    private final long _id;
    private final String name;
    private final String type;
    private final Position geo_position;


    public Location(long id, String name, String type, Position geo_position) {
        _id = id;
        this.name = name;
        this.type = type;
        this.geo_position = geo_position;
    }


    public long getId() {
        return _id;
    }

    public String getType() {
        return type;
    }

    public Position getPosition() {
        return geo_position;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (_id != location._id) return false;
        if (geo_position != null ? !geo_position.equals(location.geo_position) : location.geo_position != null)
            return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (type != null ? !type.equals(location.type) : location.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (_id ^ (_id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (geo_position != null ? geo_position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", geo_position=" + geo_position +
                '}';
    }
}
