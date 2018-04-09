package common.map.model;

/**
 * represents a point on the screen.
 * 
 * @author l00346781
 */
public class Point {
	private int x;
	private int y;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean contains(int member) {
		if (x == member || y == member) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Point) {
			return equalTo((Point) obj);
		}
		return false;
	}

	private boolean equalTo(Point other) {
		if (other.x == x && other.y == y) {
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return x ^ y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Screen Point <X: " + x + "; Y: " + y + ">";
	}
}
