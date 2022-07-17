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
	 * <b>Converts a <code>String</code> representing a date into a <code>LocalDate</code> object containing that date.</b><br>
	 * Conversion between date formats is supported via parameters.
	 * @param dateString <code>String</code> with the date to convert.
	 * @param inputFormat <code>String</code> with the input format of the string date. A custom user defined can be user or one of the built-in formats.
	 * @return {@link LocalDate} object with converted date.
	 * @since 2.3.3
	 */
	public static LocalDate stringToDate(String dateString, String inputFormat) throws DateTimeParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(inputFormat);
		DateTimeFormatter errdtf = DateTimeFormatter.BASIC_ISO_DATE;
		try {
			LocalDate lDate = LocalDate.parse(dateString, dtf);
			return lDate;
		} catch (DateTimeParseException e) {
			System.out.println("The entered date is not valid or doesn't match the input format");
		}
		return LocalDate.parse("19000101", errdtf);
	}
	
	/**
	 * <b>Converts a String representing a date in an object LocalDate containing that date.</b><br>
	 * The entered date must be in the predefined <code>BASIC_ISO_DATE</code> (yyyyMMdd) format.
	 * @param dateString <code>String</code> with the date to convert.
	 * @return <code>LocalDate</code> object with the converted date. If the date is not correct or not parseable it returns the date "<i>19000101</i>"
	 * @since 2.3.3
	 * @see String
	 * @see LocalDate
	 */
	public static LocalDate stringToDate(String dateString) throws DateTimeParseException {
		DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
		try {
			LocalDate lDate = LocalDate.parse(dateString, dtf);
			return lDate;
		} catch (DateTimeParseException e) {
			System.out.println("The entered date is not valid or doesn't match the input format");
		}
		return LocalDate.parse("19000101", dtf);
	}

	/** <b>Converts a <code>LocalDate</code> date object in a <code>String</code> object with custom format,
	 * passed as second parameter.</b><br>
	 * @param localDate <code>LocalDate</code> to be converted.
	 * @param outputPattern <code>String</code> with the output outputPattern.
	 * @return <code>String</code> with the localDate object formatted.
	 * @see String
	 * @see LocalDate
	 */
	public static String dateToString(LocalDate localDate, String outputPattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputPattern);
		return localDate.format(formatter);
	}

	/** <b>Converts a <code>LocalDate</code> date object in a <code>String</code> object with the format 'yyyyMMdd'.</b><br>
	 * @param localDate <code>LocalDate</code> to be converted.
	 * @return <code>String</code> with the localDate object formatted.
	 * @since Version 2.3.0
	 * @see String
	 * @see LocalDate
	 */
	public static String dateToString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		return localDate.format(formatter);
	}

	/**
	 * <b>Converts a String representing a date from a format to other.</b><br>
	 * @param inputDate <code>String</code> with the date to to convert.
	 * @param inputFormat <code>String</code> with the input format. A built-in format
	 *                      can be used or a user's custom one.
	 * @param outputFormat <code>String</code> with the output format. A built-in format
	 *                      can be used or a user's custom one.
	 * @return <code>String</code> converted to the output format.
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
	 * <b>Checks if a <code>String</code> representing a date is valid or not.</b><br>
	 * @param strDate <code>String</code> representing the date to check.
	 * It must stick the "yyyyMMdd" format (<code>BASIC_ISO_DATE</code>).
	 * @return True or false
	 * @throws DateTimeParseException
	 * @since Version 2.1.0
	 */
	public static boolean isValidDate (String strDate) throws DateTimeParseException{
		DateTimeFormatter dtFormatter = DateTimeFormatter.BASIC_ISO_DATE
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate ld = LocalDate.parse(strDate, dtFormatter);
			System.out.println();
		} catch (DateTimeParseException e) {
			System.out.println("The entered date is not valid");
			return false;
		}
		return true;
	}

	/**
	 * <b>Generates a random date between two user selected dates.</b><br>
	 * Got from https://www.baeldung.com/java-random-dates
	 * @param startInclusive <code>LocalDate</code>. Begin of the period within the date will be generated.
	 * @param endExclusive <code>LocalDate</code>. End of the period within the date will be generated, excluded.
	 * @return <code>LocalDate</code> with the random generated date.
	 * @since Version 2.2.0
	 * @see LocalDate
	 */
	public static LocalDate generateRandomDate (LocalDate startInclusive, LocalDate endExclusive){
		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endExclusive.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
		return LocalDate.ofEpochDay(randomDay);
	}

}
