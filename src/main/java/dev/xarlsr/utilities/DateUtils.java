package dev.xarlsr.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Several tools for dates handling
 */
public class DateUtils {

	/**
	 * USA date format
	 */
	public static final String USA = "yyyy-MM-dd";
	/**
	 * Spain date format
	 */
	public static final String ESP = "dd/MM/yyyy";
	/**
	 * Spain short date format
	 */
	public static final String ESPS = "dd/MM/yy";
	/**
	 * Spain long date format
	 */
	public static final String ESPL = "dd 'de' MM 'de' yyyy";
	/**
	 * Spain bank date format
	 */
	public static final String BNK = "ddMMyyyy";
	/**
	 * Spain short bank date format
	 */
	public static final String BNKS = "ddMMyy";
	public static final Locale LOCALE_ES = new Locale("es", "ES");
	

	public DateUtils() {	}
	
	/**
	 * Converts a string representing a date into a LocalDate object containing that date.
	 * Conversion between date formats is supported via parameters.
	 * @param dateString String with the date to convert.
	 * @param inputFormat String with the input format of the string date. A custom user defined
	 *                    can be user or one of the built-in formats.
	 * @param outputFormat String with the output format of the string date. A custom user defined
	 * 	 *                 can be user or one of the built-in formats.
	 * @return LocalDate object with converted date.
	 * @since 2.0.0
	 */
	public static LocalDate stringToDate(String dateString, String inputFormat, String outputFormat) {
		String dateFormatted = dateFormatChanger(dateString, inputFormat, outputFormat);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(outputFormat);
		// parsing the string to convert it into date
		LocalDate lDate = LocalDate.parse(dateFormatted, dtf);
		return lDate;
	}
	
	/**
	 * Converts a String representing a date with predefined  "ddMMyyyy" format in an object LocalDate
	 * containing that date with the output format "yyyy-MM-dd", via the aux method
	 * 'dateFormatter()'.
	 * @param dateString String with the date to convert.
	 * @return LocalDate object with the converted date.
	 * @since 2.0.0
	 */
	public static LocalDate stringToDate(String dateString) {
		String dateFormatted = dateFormatChanger(dateString, BNK, USA);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(USA);
		// parsing the string to convert it into date
		LocalDate lDate = LocalDate.parse(dateFormatted, dtf);
		return lDate;
	}

	/** Converts a LocalDate date object in a String object with custom format,
	 * passed as second parameter.
	 * @param localDate LocalDate to be converted.
	 * @param pattern String with the output pattern.
	 * @return String with the localDate object formatted.
	 */
	public static String dateToString(LocalDate localDate, String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDate.format(formatter);
	}

	/** Converts a LocalDate date object in a String object with the format 'yyyyMMdd'.
	 * @param localDate LocalDate to be converted.
	 * @return String with the localDate object formatted.
	 * @since Version 2.3.0
	 */
	public static String dateToString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return localDate.format(formatter);
	}

	/**
	 * Convert Strings representing dates from a format to other.
	 * @param inputDate String with the date to to convert.
	 * @param inputFormat String with the input format. A built-in format
	 *                      can be used or a user's custom one.
	 * @param outputFormat String with the output format. A built-in format
	 *                      can be used or a user's custom one.
	 * @return String converted to the output format.
	 */
	public static String dateFormatChanger (String inputDate, String inputFormat, String outputFormat){
		//Defines input default format.
		String input = inputFormat.isEmpty() ? "yyyy-MM-dd hh:mm:ss" : inputFormat;
		//Defines output default format.
		String output = outputFormat.isEmpty() ? "d 'de' MMMM 'del' yyyy" : outputFormat;
		String outputDate = inputDate;
		try {
			outputDate = new SimpleDateFormat(output, LOCALE_ES).format(new SimpleDateFormat(input, LOCALE_ES).parse(inputDate));
		} catch (Exception e) {
			System.out.println("dateFormatter(): " + e.getMessage());
		}
		return outputDate;
	}

	/**
	 * Checks if a date is valid or not..
	 * Picked up from https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
	 * @param date String representing the date to check. It must stick the "yyyyMMdd" format.
	 * @return True or false
	 * @since Version 2.1.0
	 */
	public static boolean isValidDate (String date){
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(BNK)
				.withResolverStyle(ResolverStyle.STRICT);;
		try {
			dtFormatter.parse(date);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Generates a random date between two user selected dates.
	 * Got from https://www.baeldung.com/java-random-dates
	 * @param startInclusive Begin of the period within the date will be generated.
	 * @param endInclusive End of the period within the date will be generated.
	 * @return LocalDate with the random generated date.
	 * @since Version 2.2.0
	 */
	public static LocalDate generateRandomDate (LocalDate startInclusive, LocalDate endInclusive){
		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endInclusive.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
		System.out.println(startEpochDay);
		System.out.println(endEpochDay);
		System.out.println(randomDay);
		return LocalDate.ofEpochDay(randomDay);
	}

}
