package web.fundraising.common;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

public class HtmlDatetimeLocalToSQLDateTimeUtil {
	public static java.sql.Timestamp parse(HttpServletRequest req, String DateTimefromHTML) {
		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm");
		    
	    	String[] start = DateTimefromHTML.split("T");
		    String startDateTime = start[0] + " " + start[1];
		    java.util.Date startDate = null;
			try {
				startDate = formatter.parse(startDateTime);
			} catch (ParseException e) {
				// TODO: handle exception
			}
			java.sql.Timestamp startTimestamp = new java.sql.Timestamp(startDate.getTime());
			return startTimestamp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
