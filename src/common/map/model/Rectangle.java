package common.map.model;

public class Rectangle {
	private final Point topLeft;
	private final Point bottomRight;

	public Rectangle(Point topLeft, Point bottomRight) {
		assert bottomRight.getY() > topLeft.getY() && bottomRight.getX() > topLeft.getX();
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public Rectangle(Point topLeft, Size size) {
		this.topLeft = topLeft;
		bottomRight = new Point(topLeft.getX() + size.getWidth(), topLeft.getY() + size.getHeight());
	}

	public Point getBottomRightPoint() {
		return bottomRight;
	}

	public int getHeight() {
		return bottomRight.getY() - topLeft.getY();
	}

	public Point getTopLeftPoint() {
		return topLeft;
	}

	public int getWidth() {
		return bottomRight.getX() - topLeft.getX();
	}

	public boolean isIn(Point point) {
		return point.getX() >= topLeft.getX() && point.getX() <= bottomRight.getX() && point.getY() >= topLeft.getY()
				&& point.getY() <= bottomRight.getY();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TopLeft:");
		sb.append(topLeft);
		sb.append(";");
		sb.append("BottomRight:");
		sb.append(bottomRight);
		return sb.toString();
	}
}
