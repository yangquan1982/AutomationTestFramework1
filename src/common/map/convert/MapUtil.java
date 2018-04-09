package common.map.convert;

import common.map.model.Direction;
import common.map.model.Location;
import common.map.model.Point;
import common.map.model.Size;

public class MapUtil {
	private static final int INVALID_VALUE = -1;

	public static Point toPoint(Location source, Location topLeft, Location bottomRight, Point start, Size size) {
		Point result = new Point(INVALID_VALUE, INVALID_VALUE);
		if (null == source || null == topLeft || null == bottomRight) {
			System.out.println("null-----");
			return result;
		}
		result.setX(toXY(source.getLongitude(), topLeft.getLongitude(), bottomRight.getLongitude(), start, size,
				Direction.HORIZONTAL));
		result.setY(toXY(source.getLatitude(), bottomRight.getLatitude(), topLeft.getLatitude(), start, size,
				Direction.VERTICAL));
		return result;
	}

	public static int toXY(double source, double min, double max, Point topLeft, Size size, Direction drc) {
		if (null == topLeft || null == size) {
			return INVALID_VALUE;
		}
		switch (drc) {
		case HORIZONTAL:
			if (Double.compare(source, min) >= 0 && Double.compare(source, max) <= 0) {
				return topLeft.getX() + round((source - min) / (max - min) * size.getWidth());
			}
			break;
		case VERTICAL:
			if (Double.compare(source, max) <= 0 && Double.compare(source, min) >= 0) {
				return topLeft.getY() + round((source - max) / (min - max) * size.getHeight());
			}
			break;
		default:
			break;
		}
		return INVALID_VALUE;
	}

	public static boolean isValid(Point target) {
		return target != null && (!target.contains(INVALID_VALUE));
	}

	private static int round(double input) {
		if (Double.compare(input, 0) < 0) {
			return -round(-input);
		}
		final int result = (int) input;
		return Double.compare(input - result, 0.5) < 0 ? result : result + 1;
	}
}
