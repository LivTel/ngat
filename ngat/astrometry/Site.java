package ngat.astrometry;

public class Site {

    protected double latitude;

    protected double longitude;

    protected String name;

    public Site(String name, double latitude, double longitude) {
	this.name      = name;
	this.latitude  = latitude;
	this.longitude = longitude;
    }

    public String getName() { return name; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    /** Returns a representation of this site.*/
    public String toString() {
	return "Site ["+name+"] Lat = "+
	    Position.toDegrees(latitude,3)+
	    ", Long = "+Position.toDegrees(longitude, 3);
    }

}
