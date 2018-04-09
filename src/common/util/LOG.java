package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import common.util.ZIP;

public class LOG {

	private static Logger logger = null;

	public static void info(String... info)
	{
		StringBuffer sb = new StringBuffer();
		for( String s : info)
		{
			sb.append(s);
		}
		print(Level.INFO, sb.toString());
	}
	
	public static String info(String info) {
		print(Level.INFO, "["+ZIP.IP()+"]"+info);
		return info;
	}

	public static String error(String info) {
		print(Level.WARNING, ZIP.IP()+info);
		return info;
	}

	public static void info_testcase(String info) {
		LOG.info(" [TC] " + info);
	}

	public static void info_aw(String info) {
		LOG.info(" [AW] " + info);
	}

	private static void print(Level level, String info) {
		if (logger == null) {
			logger = Logger.getLogger("");
			Handler[] handlers = logger.getHandlers();
			for (Handler handler : handlers) {
				if (handler instanceof ConsoleHandler) {
					handler.setFormatter(new MyLogFormatter());
				}
			}
		}
		logger.log(level, info);
	}

	public static String getDateString() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		Date nowdate = c.getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(date);
	}

	public static void main(String[] args) {
		LOG.info_testcase("tetgfoefjeojf");
		LOG.info_testcase("tetgfoefjeojf");
		LOG.info_testcase("tetgfoefjeojf");
	}
}

class MyLogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return LOG.getDateString() + "[" + record.getLevel() + "]"
				+ record.getMessage() + "\n";
	}
}
