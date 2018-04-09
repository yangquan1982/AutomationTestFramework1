package common.map.exception;

public class MapException extends Exception {
	private static final long serialVersionUID = -7290878348059697876L;

	public MapException(String message) {
		super(message);
	}

	public MapException(String format, Object... args) {
		this(String.format(format, args));
	}
}
