package healthSafe.dvds20222cg4hce.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import healthSafe.dvds20222cg4hce.Constantes;

public final class DateUtils {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(Constantes.FORMATO_FECHA);
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(Constantes.FORMATO_FECHA_TIEMPO);

	
	private DateUtils() {}
	
	public static String getStringDate(Long timestamp) {
		return DATE_FORMAT.format(timestamp);
	}

	public static String getStringDateTime(Long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = instant.atZone(ZoneOffset.UTC).toLocalDateTime();
        return localDateTime.format(DATE_TIME_FORMAT);
	}
	
	public static Long getFechaTimestamp(String dateStr) {
		Long fechaTimestamp = null;
		try {
			fechaTimestamp = DATE_FORMAT.parse(dateStr).getTime();
		} catch (ParseException e) { e.printStackTrace();}
		catch(NullPointerException e) { e.getMessage();}
		return fechaTimestamp;
	}

	public static Long getFechaTimestampFromDatetime(String dateStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DATE_TIME_FORMAT);
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
	}
	
	public static Long getFechaNowTimestamp() {
		Long fechaTimestamp = null;
		try {
			fechaTimestamp = System.currentTimeMillis();
		}catch(NullPointerException e) { e.getMessage();}
		return fechaTimestamp;
	}
	
	public static Boolean dateIsInRange(String after, String before, String requestDate) {
		try {
			Date afterDate = DATE_FORMAT.parse(after);
			Date beforeDate = DATE_FORMAT.parse(before);
			Date requestedDate = DATE_FORMAT.parse(requestDate);
			
			return (requestedDate.after(afterDate) && requestedDate.before(beforeDate)) || (requestedDate.equals(afterDate) || requestedDate.equals(beforeDate));
		} catch (ParseException e) { e.printStackTrace();}
		return false;
	}
	
	public static Integer compareDates(String date, String dateToCompare) {
		try {
			Date firstDate = DATE_FORMAT.parse(date);
			Date secondDate = DATE_FORMAT.parse(dateToCompare);
			return firstDate.compareTo(secondDate);
		} catch (ParseException e) { e.printStackTrace(); }
		return null;
	}
	
	public static Long addDaysToTimestamp(Integer days, Long timestamp) {
		LocalDate fechaInicio = LocalDate.parse(getStringDate(timestamp));
		return getFechaTimestamp(fechaInicio.plusDays(days).toString());
	}
	
	public static Long addWeeksToTimestamp(Integer weeks, Long timestamp) {
		LocalDate fechaInicio = LocalDate.parse(getStringDate(timestamp));
		return getFechaTimestamp(fechaInicio.plusWeeks(weeks).toString());
	}
	
	public static Long addMonthsToTimestamp(Integer months, Long timestamp) {
		LocalDate fechaInicio = LocalDate.parse(getStringDate(timestamp));
		return getFechaTimestamp(fechaInicio.plusMonths(months).toString());
	}
	
	public static Long addYearsToTimestamp(Integer years, Long timestamp) {
		LocalDate fechaInicio = LocalDate.parse(getStringDate(timestamp));
		return getFechaTimestamp(fechaInicio.plusYears(years).toString());
	}
	
}
