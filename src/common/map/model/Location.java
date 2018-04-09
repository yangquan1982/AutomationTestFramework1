package common.map.model;

/**
 * represents a geographical location with longitude and latitude.
 * 
 * @author l00346781
 */
public class Location {
	private static final int PRECISION = 100000000;

	private double lon;
	private double lat;

	public Location() {
	}

	public Location(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Location) {
			return equalTo((Location) obj, 1.0 / PRECISION);
		}
		return false;
	}

	private boolean equalTo(Location other, double precision) {
		if (Double.compare(Math.abs(lon - other.lon), precision) < 0
				&& Double.compare(Math.abs(lat - other.lat), precision) < 0) {
			return true;
		}
		return false;
	}

	public double getLatitude() {
		return lat;
	}

	public double getLongitude() {
		return lon;
	}

	@Override
	public int hashCode() {
		return (int) (lon * PRECISION) ^ (int) (lat * PRECISION);
	}

	public void setLatitude(double lat) {
		this.lat = lat;
	}

	public void setLongitude(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Geographical Location <Longitude: " + lon + "; Latitude: " + lat + ">";
	}
}
