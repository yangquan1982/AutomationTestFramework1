package common.map.model;

/**
 * represents a rectangle with width and height.
 * 
 * @author l00346781
 */
public class Size {
	private int width;
	private int height;

	public Size() {
	}

	public Size(int width, int height) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
