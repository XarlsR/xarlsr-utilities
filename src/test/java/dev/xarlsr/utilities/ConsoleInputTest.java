package dev.xarlsr.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
            "Pero la bola mola más que la gramola",
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
        assertEquals(testString, ConsoleInput.readString(input));
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
}