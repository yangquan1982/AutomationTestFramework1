package common.map.mouse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.map.model.Direction;
import common.map.model.Point;
import common.map.model.Size;

public class Mouse {
	protected static Logger log = LoggerFactory.getLogger(Mouse.class);

	private Robot robot;
	private final int minX;
	private final int width;
	private final int minY;
	private final int height;
	private final int centerX;
	private final int centerY;
	private boolean success = true;

	public Mouse(int minX, int minY, int width, int height) {
		this.minX = minX;
		this.minY = minY;
		this.width = width;
		this.height = height;
		centerX = this.minX + width / 2;
		centerY = this.minY + height / 2;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			success = false;
		}
		log.info(String.format("Mouse: MinX:%d, MinY:%d;Width:%d, Height:%d", this.minX, this.minY, this.width,
				this.height));
	}

	public Mouse(Point topLeft, Size size) {
		this(topLeft.getX(), topLeft.getY(), size.getWidth(), size.getHeight());
	}

	private void clckPoint(int x, int y) {
		mvTo(x, y);
		this.click();
	}

	public void click() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}


	public void clickCenter() {
		clckPoint(centerX, centerY);
	}

	public void clickPoint(int x, int y) {
		int[] norm = normalizePoint(x, y);
		clckPoint(norm[0], norm[1]);
	}

	private void dblClick(int x, int y) {
		mvTo(x, y);
		doubleClick();
	}

	public void doubleClick() {
		this.click();
		//robot.delay(100);
		this.click();
	}

	public void doubleClickPoint(int x, int y) {
		int[] norm = normalizePoint(x, y);
		dblClick(norm[0], norm[1]);
	}

	public void dragFrom(int x, int y) {
		int[] norm = normalizePoint(x, y);
		drgFrom(norm[0], norm[1]);
	}

	public void dragToBottom() {
		dragFrom(centerX, Integer.MIN_VALUE);
	}

	public void dragToCenter(int from, Direction drc) {
		switch (drc) {
		case HORIZONTAL:
			this.dragToCenter(from, centerY);
			break;
		case VERTICAL:
			this.dragToCenter(centerX, from);
			break;
		}
	}

	public void dragToCenter(int fromX, int fromY) {
		if (fromX != centerX || fromY != centerY) {
			dragFrom(fromX, fromY);
		}
	}

	public void dragToLeft() {
		dragFrom(Integer.MAX_VALUE, centerY);
	}

	public void dragToRight() {
		dragFrom(Integer.MIN_VALUE, centerY);
	}

	public void dragToTop() {
		dragFrom(centerX, Integer.MAX_VALUE);
	}

	private void drgFrom(int x, int y) {
		robot.delay(100);
		robot.mouseMove(x, y);
		this.click();
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(centerX, centerY);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		this.click();
		robot.delay(100);
	}

	public boolean isInitSuccess() {
		return success;
	}

	public void moveTo(int x, int y) {
		int[] norm = normalizePoint(x, y);
		mvTo(norm[0], norm[1]);
	}

	public void moveToBottomLeft() {
		moveTo(minX+1, minY + height-1);
	}

	public void moveToBottomRight() {
		moveTo( minX + width-1, minY + height-1);
	}

	public void moveToCenter() {
		moveTo(centerX, centerY);
	}

	public void moveToTopLeft() {
		moveTo(minX+1, minY+1);
	}

	public void moveToTopRight() {
		moveTo( minX + width-1, minY+1);
	}

	private void mvTo(int x, int y) {
		robot.mouseMove(x, y);
	}

	private int normalize(int input, int min, int max) {
		if (input < min) {
			return min;
		} else if (input > max) {
			return max;
		}
		return input;
	}

	private int[] normalizePoint(int x, int y) {
		int xNorm = normalize(x, minX, minX + width);
		int yNorm = normalize(y, minY, minY + height);
		return new int[] { xNorm, yNorm };
	}

	public void scroll(int h) {
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseWheel(h);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
	}

	public void scrollInCenter(int h) {
		moveToCenter();
		this.click();
		scroll(h);
	}
}
