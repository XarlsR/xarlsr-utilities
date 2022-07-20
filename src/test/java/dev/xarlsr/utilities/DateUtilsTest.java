package dev.xarlsr.utilities;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {


    /**
     * <b>Positive test case for {@code DateUtils.dateFormatChanger(String,String,String)}.</b><br>
     * All the passed parameters to the method are correct, so it should return
     * the expected results.<br>
     * In addition the result is checked as a valid date via apache-commons-validator
     * <code>GenericValidator.isDate()</code> method.
     * @param dateI <code>String</code> with the date to convert format
     * @param formatI {@code String} with the format of the input date.
     * @param formatO {@code String} with the output format to convert to
     * @param expectedResult {@code String} with the expected result
     * @see <a href="https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/GenericValidator.html">Apache Commons Generic Validator</a>
     */
    @Passed(date = "18.07.22",version = "2.3.6")
    @ParameterizedTest
    @CsvSource({
            // Correct dates and matching formats
            "20100131, yyyyMMdd, dd/MM/yyyy, 31/01/2010",
            "31/01/2010, dd/MM/yyyy, dd-MM-yyyy, 31-01-2010",
            "29/02/16, dd/MM/yy, yyyy.MM.dd, 2016.02.29",
            "25/04/2014,,,20140425"
    })
    void dateFormatChangerOKTestCase(String dateI, String formatI, String formatO, String expectedResult){
        try {
            String result = DateUtils.dateFormatChanger(dateI, formatI, formatO);
            assertEquals(expectedResult, result);
            System.out.print(dateI+" | "+expectedResult+" | "+result+" [OK]");
            assertTrue(GenericValidator.isDate(result, formatO, true), "TT OK");
            System.out.println(" isDate: [OK]");
        } catch (Exception e) {
            System.out.println("Input and/or output format null. Default applied.");
        }
    }


    /**
     * <b>Wrong dates test case for {@code DateUtils.dateFormatChanger(String,String,String)}.</b><br>
     * All the passed parameters to the method are wrong dates, so it should return
     * values corrected by the parsing phase of the method.<br>
     * In addition the result is checked as a valid date via apache-commons-validator
     * <code>GenericValidator.isDate()</code> method.<br>
     * The test code is surrounded by try/catch as the tested method throws a {@code ParseException} when internal parsing fails.
     * @param dateI <code>String</code> with the date to convert format
     * @param formatI {@code String} with the format of the input date.
     * @param formatO {@code String} with the output format to convert to
     * @param expectedResult {@code String} with the expected result
     * @see <a href="https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/GenericValidator.html">Apache Commons Generic Validator</a>
     */
    @Passed(date = "18.07.22", version = "2.3.6")
    @ParameterizedTest
    @CsvSource({
            // Wrong date, matching input format.
            // The expected results are the ones expected to be returned
            // by the parsing phase of the tested method after correcting
            // the entered dates.
            "20100229, yyyyMMdd, dd/MM/yyyy, 01/03/2010",
            "31/06/2015, dd/MM/yyyy, dd.MM.yyyy, 01.07.2015",
    })
    void dateFormatChangerWRONGTestCase(String dateI, String formatI, String formatO, String expectedResult){
        try {
            String result = DateUtils.dateFormatChanger(dateI, formatI, formatO);
            assertEquals(expectedResult, result);
            System.out.print(dateI+" | "+expectedResult+" | "+result+" [OK]");
            assertTrue(GenericValidator.isDate(result, formatO, true));
            System.out.println(" isDate: [OK]");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * <b>Exception test case for {@code DateUtils.dateFormatChanger(String,String,String)}.</b><br>
     * All the passed parameters to the method are expected to generate a ParseException return, as the input format doesn't match the entered date format.<br>
     * @param dateI <code>String</code> with the date to convert format
     * @param formatI {@code String} with the format of the input date.
     * @param formatO {@code String} with the output format to convert to
     * @param expectedResult {@code String} with the expected result
     * @see ParseException
     */
    @Passed(date="18.07.22", version = "2.3.6")
    @ParameterizedTest
    @CsvSource({
            // Valid date, mismatch input format
            "05/12/2017, dd.MM.yyyy, yyyyMMdd, 20171205"
    })
    void dateFormatChangerExceptionTestCase(String dateI, String formatI, String formatO, String expectedResult){
        assertThrows(ParseException.class, () -> DateUtils.dateFormatChanger(dateI, formatI, formatO));
    }


    /**
     * <b>Test the dateToString(LocalDate) method</b><br>
     * Gets the actual date from the system and passes it to the tested method.
     * Test is passed if the returned {@code String} is a valid date in format 'yyyyMMdd', using the
     * <code>GenericValidator.isDate()</code> method from apache-commons-validator artifact.
     * @see DateUtils#dateToString(LocalDate)
     * @see <a href="https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/GenericValidator.html">Apache Commons Generic Validator</a>
     * @since v2.3.6
     */
    @Test
    void dateToStringTest(){
        LocalDate ld1 = LocalDate.now();
        String dts1 = DateUtils.dateToString(ld1);
        assertTrue(GenericValidator.isDate(dts1, "yyyyMMdd", true));
    }

    /**
     * <b>Test the dateToString(LocalDate, String) method</b><br>
     * Gets the actual date from the systems and passes it to the tested method.
     * Test is passes if the returned {@code String} is a valid date in the user
     * defined format passed to the method. Validity check is performed by the
     * <code>GenericValidator.isDate()</code> method from apache-commons-validator artifact.
     * @since v2.3.6
     * @see DateUtils#dateToString(LocalDate, String)
     * @see <a href="https://commons.apache.org/proper/commons-validator/apidocs/org/apache/commons/validator/GenericValidator.html">Apache Commons Generic Validator</a>
     */

    @Test
    void dateToStringParamsTest(){
        LocalDate ld1 = LocalDate.now();
        String dts1 = DateUtils.dateToString(ld1, "dd/MM/yyyy");
        assertTrue(GenericValidator.isDate(dts1, "dd/MM/yyyy", true));
    }

    /**
     * Test the stringToDate(String date, String format) method.
     */
    @Test
    void stringToDateParamsTest(){
    }

    /**
     * Test the stringToDate(String date) method
     */
    @Test
    void stringToDateNoParamsTest(){
        String d1d = "20200216";
        String d2d = "20160631";
        String d3d = "22-06-11";
        String d4d = "18/10/1950";
        LocalDate ld1 = DateUtils.stringToDate(d1d);
        String d1p = ld1.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d1p, "yyyyMMdd", true));
        LocalDate ld2 = DateUtils.stringToDate(d2d);
        String d2p = ld2.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d2p, "yyyyMMdd", true));
        LocalDate ld3 = DateUtils.stringToDate(d3d);
        String d3p = ld3.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d1p, "yyyyMMdd", true));
        LocalDate ld4 = DateUtils.stringToDate(d4d);
        String d4p = ld4.format(DateTimeFormatter.BASIC_ISO_DATE);
        try {
            assertTrue(GenericValidator.isDate(d4p, "yyyyMMdd", true));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Test the isValidDate() method
     */
    @Test
    void isValidDateTest() {
        // Normal year
        String okDate1 = "20150210";
        // Leap year
        String okDate2 = "20160229";
        // Wrong normal year
        String wDate1 = "20220631";
        // Wrong leap year
        String wDate2 = "20150229";
        // Wrong date format
        String wDate3 = "31121980";

        assertTrue(DateUtils.isValidDate(okDate1));
        assertTrue(DateUtils.isValidDate(okDate2));
        assertFalse(DateUtils.isValidDate(wDate1));
        assertFalse(DateUtils.isValidDate(wDate2));
        assertFalse(DateUtils.isValidDate(wDate3));

    }

    /**
     * <b>Test the generateRandomDate() method</b><br>
     * Every result is checked to be within the required interval and
     * whether is a valid date.
     * @since v2.3.6
     */
    @Passed(date="18.07.22", version = "2.3.6")
    @Test
    void generateRandomDateTest() {
        LocalDate d1 = DateUtils.stringToDate("19500101","yyyyMMdd");
        LocalDate d2 = DateUtils.stringToDate("20220301","yyyyMMdd");
        for (int i = 1; i<=1000;i++) {
            LocalDate lDate = DateUtils.generateRandomDate(d1, d2);
            String strDate = DateUtils.dateToString(lDate);
            assertTrue((lDate.isEqual(d1) || lDate.isAfter(d1)),"Date "+lDate+" is BEFORE the starting interval date");
            assertTrue(lDate.isBefore(d2),"Date "+lDate+" is AFTER the ending date of the interval");
            assertTrue(GenericValidator.isDate(strDate, "yyyyMMdd", true), "Date "+strDate+" is NOT a VALID date");
        }
    }
}