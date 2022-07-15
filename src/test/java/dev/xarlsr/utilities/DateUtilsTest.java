package dev.xarlsr.utilities;

import org.apache.commons.validator.GenericValidator;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void stringToDateParamsTest(){
        // Valid date with matching format
        String d1d = "20200216";
        String d1f = "yyyyMMdd";
        // Valid date with matching format
        String d2d = "18/10/2016";
        String d2f = "dd/MM/yyyy";
        // Valid date with matching format
        String d3d = "22-06-11";
        String d3f = "dd-MM-yy";
        // Valid date with mismatch format
        String d4d = "18/10/1950";
        String d4f = "dd-MM-yy";
        LocalDate ld1 = DateUtils.stringToDate(d1d, d1f);
        String d1p = ld1.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d1p, "yyyyMMdd", true));
        System.out.println("Params :"+d1p);
        LocalDate ld2 = DateUtils.stringToDate(d2d, d2f);
        String d2p = ld2.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d2p, "yyyyMMdd", true));
        System.out.println("Params :"+d2p);
        LocalDate ld3 = DateUtils.stringToDate(d3d, d3f);
        String d3p = ld3.format(DateTimeFormatter.BASIC_ISO_DATE);
        assertTrue(GenericValidator.isDate(d3p, "yyyyMMdd", true));
        System.out.println("Params :"+d3p);
        LocalDate ld4 = DateUtils.stringToDate(d4d, d4f);
        String d4p = ld4.format(DateTimeFormatter.BASIC_ISO_DATE);
        try {
            assertTrue(GenericValidator.isDate(d4p, "yyyyMMdd", true));
            System.out.println("Params :"+d4p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Test
    void generateRandomDateTest() {
        LocalDate d1 = DateUtils.stringToDate("19500101","yyyyMMdd");
        LocalDate d2 = DateUtils.stringToDate("20220301","yyyyMMdd");
        for (int i = 1; i<=1000;i++) {
            LocalDate lDate = DateUtils.generateRandomDate(d1, d2);
            String strDate = DateUtils.dateToString(lDate);
            assertTrue((lDate.isEqual(d1) || lDate.isAfter(d1)));
            assertTrue(lDate.isBefore(d2));
            assertTrue(GenericValidator.isDate(strDate, "yyyyMMdd", true));
            System.out.println("genRandomDateTest() [commons-validator]: " + strDate + " ok");
        }
    }
}