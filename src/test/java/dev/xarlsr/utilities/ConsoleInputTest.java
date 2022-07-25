package dev.xarlsr.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputTest {

    /**
     * Variable to store a backup of the System.in previous to test.
     */
    static InputStream backupSI;

    /**
     * A backup of the actual default System.in InputStream is created.
     * It will be restored after test completion
     */
    @BeforeAll
    static void setUp() {
        backupSI = System.in;
    }

    /**
     * The System.in InputStream previous to test is restored.
     */
    @AfterAll
    static void restore(){
        System.setIn(backupSI);
    }


    /**
     * Tests the ConsoleInput.readString() method.
     *
     * @param testString Strings with the test cases stored in CsvSource
     * @author XarlsR 2022
     * @since v2.3.7
     */
    @ParameterizedTest
    @CsvSource({
            "La gramola es lo que mola",
            "Pero la bola mola mas que la gramola",
            "1277",
            "345.78",
            "20220720",
            "R"
    })
    void readStringTest(String testString) {
        // We create a new InputStream with the mock values we want to test
        // got from the CsvSource.
        InputStream input = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        // Replaces the default InputStream -usually keyboard- with our test InputStream,
        // mocking this way the keyboard input with our test cases.
        System.setIn(input);
        System.out.println((ConsoleInput.readString(input)));
        //assertEquals(testString, ConsoleInput.readString(input));
    }


    /**
     * Tests the readString(InputStram, int) method.
     * @param testString String to test
     * @param maxLength Max length of the returned string. Test is passed if the returned String
     *                  shorter or equals than this parameter.
     */
    @ParameterizedTest
    @CsvSource({
            "A test string 32 characters long, 27",
            "This string is shorter, 27",
            "This string is same size as the maxLength,41"
    })
    void ReadStringMaxLengthTest(String testString, int maxLength) {
        // We create a new InputStream with the mock values we want to test
        // got from the CsvSource.
        InputStream input = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        // Replaces the default InputStream -usually keyboard- with our test InputStream,
        // mocking this way the keyboard input with our test cases.
        System.setIn(input);
        assertTrue(ConsoleInput.readString(input, maxLength).length()<=maxLength, "La longitud de salida es mayor");
    }


    /**
     * Test the readInteger(InputStream) method with correct values.
     * @param testString String with value parseable to <code>int</code>
     * @since 3.0.0
     */
    @ParameterizedTest
    @CsvSource({
            "23","64","0","1"})
    void readIntegerTest(String testString){
        InputStream input = new ByteArrayInputStream((testString.getBytes(StandardCharsets.UTF_8)));
        int output = ConsoleInput.readInteger(input);
        assertEquals(Integer.parseInt(testString), output);
    }


    /**
     * Test the readInteger(InputStream) method with wrong (not parseable to int) values.
     * @param testString String with value not parseable to <code>int</code>
     * @since 3.0.0
     */
    @ParameterizedTest
    @CsvSource({
            "3.1415","Alberto","#"})
    void readIntegerWrongTest(String testString){
        InputStream input = new ByteArrayInputStream((testString.getBytes(StandardCharsets.UTF_8)));
        assertThrows(NumberFormatException.class, () ->ConsoleInput.readInteger(input));
    }


    /**
     * <b>Tests the readCar(InputStram) method.</b><br>
     * The test is passed if the returned value is a character (char)
     * UNICODE defined.
     * @param testString Csv String source to test.
     * @since 3.0.0
     */
    @ParameterizedTest
    @CsvSource({
            "A","b","1","@","_","€","Hola"
    })
    void readCharTest(String testString){
        InputStream input = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        assertTrue(Character.isDefined(ConsoleInput.readChar(input)));
    }

    /**
     * <b>Tests positive cases for readDouble(InputStream) method.</b> <br>
     * Test will pass if no exception is returned by the method.
     * @param testString Test values.
     * @since v3.0.0
     */
    @ParameterizedTest
    @CsvSource({
            "1.20","3.141562","0.001","3","1.0658E12"
    })
    void readDoubleTest(String testString) {
        InputStream input = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        //double tested = ConsoleInput.readDouble(input);
        assertAll(()->ConsoleInput.readDouble(input));
    }

    /**
     * <b>Test with exception values for readDouble(InputStream) method</b><br>
     * Test will pass if a NumberFormatException exception is thrown for every tested value.
     * @param testString Origin to be tested
     */
    @ParameterizedTest
    @CsvSource({
            "Sebastian", "#:)"
    })
    void readDoubleExceptionTest(String testString){
        InputStream input = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        assertThrows(NumberFormatException.class, ()->ConsoleInput.readDouble(input));
    }
}