/**
Apache License
Version 2.0, January 2004
Copyright 2022 Carlos R. Puente (XarlsR)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
 */
package dev.xarlsr.utilities;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Reads and return different data types from keyboard<br>
 * @author XarlsR 2022
 * @version 2.3.0
 */
public class ConsoleInput {

    /**
     * <b>Used for the deprecated method readChar() only.</b>
     *
     */
    static final Scanner scan = new Scanner(System.in);


    /**
     * <b>Reads and returns a String from a InputStream object</b><br>
     * Injecting the InputStream object as a parameter unlinks the reading
     * procedure (BufferedReader) of the source of the String to read, making
     * the method more flexible and easily testable, as keyboard input can be
     * simulated by changing the InputStream from default System.in to any other.<br>
     * The InputStream object is passed via parameter -mandatory- and may be
     * any of the classes of InputStream superclass.
     * Then it's read by a BufferedReader via readLine() method.<br>
     * If a console keyboard reading is desired, the passed parameter must be
     * System.in or, as an option, the InputStream object must be set to System.in
     * at the calling class or method.<br>
     * Replaces the previous deprecated readString() method.
     * @param inputStream InputStream object which the String will be read from.
     * @return Read String
     * @see InputStream
     * @see System#in
     * @see BufferedReader
     * @see #readString()
     * @author XarlsR 2022
     * @since v2.3.7
     */
    public static String readString(InputStream inputStream) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String string = (br.readLine());
            // ISSUE I001: Avoids to close System.in as it can´t be used anymore after closing.
            if (inputStream != System.in) {
                inputStream.close();
            }
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * <b>Reads and returns a String from a InputStream object with a maximum length</b><br>
     * Injecting the InputStream object as a parameter unlinks the reading
     * procedure (BufferedReader) of the source of the String to read, making
     * the method more flexible and easily testable, as keyboard input can be
     * simulated by changing the InputStream from default System.in to any other.<br>
     * The InputStream object is passed via parameter -mandatory- and may be
     * any of the classes of InputStream superclass.
     * Then it's read by a BufferedReader via readLine() method.<br> After that the length
     * of the String is checked. If it's longer than {@code maxLength} parameter then
     * it's truncated prior to return. If not, it's returned as is.
     * If a console keyboard reading is desired, the passed parameter must be
     * System.in or, as an option, the InputStream object must be set to System.in
     * at the calling class or method.<br>
     * Replaces the deprecated readString(int) method.
     * @param inputStream InputStream object which the String will be read from.
     * @return Read String
     * @see InputStream
     * @see System#in
     * @see BufferedReader
     * @see #readString(int)
     * @author XarlsR 2022
     * @since v2.3.7
     */
    public static String readString(InputStream inputStream, int maxLength){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String string = (br.readLine());
            inputStream.close();
            if (string.length() > maxLength) {
                return string.substring(0, maxLength);
            }
            else {return string;}
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * <b>Returns a <code>int</code> value read from a InputStream.</b><br>
     * The value is read via <code>readString(InputStream)</code> method and
     * parsed to <code>int</code>. In case the
     * entered String wouldn't be a <code>int</code> number, the method throws
     * a <code>NumberFormatException</code> exception<br>
     * Replaces the deprecated method <code>readInteger()</code>
     * @param inputStream InputStream object selected as source to read from. If keyboard is the desired
     *                    source, default <code>System.in</code> must be passed as parameter.
     * @return Integer (<code>int</code>) read from InputStream.
     * @throws NumberFormatException If the entered value is not parseable to <code>int</code>.
     * @since version 3.0.0
     * @see InputStream
     * @see System#in
     * @see #readString(InputStream) 
     * @see #readInteger()
     * @see NumberFormatException
     */
    public static int readInteger(InputStream inputStream) throws NumberFormatException {
            return Integer.parseInt(readString(inputStream));
    }


    /**
     * <b>Returns a integer (<code>int</code>) number of a max maxLength read from an InputStream.</b><br>
     * The value is read as a <code>String</code> by <code>readString(InputStream, int)</code> method,
     * then parsed to int.<br>
     * If the entered value is longer than the maxLength parameter, it's truncated by the reading method
     * prior to parsing.
     * @param inputStream InputStream to read the value from.
     * @param maxLength Maximum length of the integer number to return.
     * @return Integer (<code>int</code>) read from InputStream.
     * @throws NumberFormatException when the read value can't be parsed to int
     * @since version 3.0.0
     * @see InputStream
     * @see Integer#parseInt(String)
     * @see #readString(InputStream, int)
     *
     */
    public static int readInteger(InputStream inputStream, int maxLength) throws NumberFormatException{
        return Integer.parseInt(readString(inputStream, maxLength));
    }


    /**
     * <b>Returns a character (char) read from an InputStream</b><br>
     * The entered value is read by the {@code readString(InputStream)} method and then
     * the first position of the String is returned. <br>
     * Replaces the deprecated method readChar().
     * @param inputStream InputStream to read the value from.
     * @return char with the read character
     * @since 3.0.0
     * @see InputStream
     * @see #readString(InputStream)
     */
    public static char readChar(InputStream inputStream) {
        return readString(inputStream).charAt(0);
    }


    /**
     * <b>Returns a double precision (double) number read from a InputStream</b><br>
     * The InputStream is read by the {@code readSting(InputSteam)} method and then
     * parsed to double. <br>
     * If the read value is a integer number, that number is
     * accepted and parsed to {@code double} too, as the subset of integer numbers
     * is part of the double precision numbers set, so any exception won't be thrown.<br>
     * Replaces the deprecated method {@code readDouble()} since v3.0.0.
     * @param inputStream InputStream to read the value from.
     * @return Double precision number.
     * @throws NumberFormatException case the read value couldn't be parsed to {@code double}.
     * @since v3.0.0
     * @see InputStream
     * @see #readString(InputStream)
     */
    public static double readDouble(InputStream inputStream) throws NumberFormatException{
        return Double.parseDouble(readString(inputStream));
    }


    /**
     * <b>Clears the console in Windows systems</b>
     * @since version 1.3
     */
    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * <b>Asks the user to press ENTER to continue.</b>
     */
    public static void pressEnter() {
        System.out.print("Press ENTER to continue");
        ConsoleInput.readString(System.in);
    }



    // -----------------------------------------------------------------------------------------
    //                           |||   DEPRECATED METHODS BELOW   |||
    // -----------------------------------------------------------------------------------------

    /**
     * <b>Deprecated method to read a String from console keyboard.</b><br>
     * Replaced by <code>readString(InputStream)</code> since version 2.3.7<br>
     * Returns a String read from keyboard (System.in).<br>
     * @return String read from keyboard
     * @since version 1.0.0
     * @deprecated
     * @see #readString(InputStream)
     */
    @Deprecated
    public static String readString(){
        String string="";
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try{
            string = buff.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return string;
    }


    /**
     * <b>DEPRECATED method to read a String from console keyboard with a max length</b><br>
     * Replaced by readString(InputStream, int) method since version 2.3.7<br>
     * Reads a <code>String</code> value from keyboard with the maximum maxLength set by <code>maxLength</code>
     * parameter.<br>
     * If the entered line is longer than <code>maxLength</code> then it's truncated.
     * @deprecated
     * @param maxLength <code>int</code> number with the max maxLength of the returned string.
     * @return <code>String</code> with the read value, truncated to <code>maxLength</code> if longer.
     * @since v1.0.0
     * @see #readString(InputStream, int)
     */
    @Deprecated
    public static String readString(int maxLength){
        String string = "";
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try{
            string = buff.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (string.length() > maxLength) {
            return string.substring(0, maxLength);
        }
        else return string;
    }


    /**
     * <b>Deprecated method to read a int value from console keyboard</b><br>
     * Replaced by <code>readInteger(InputStream)</code> since v3.0.0.
     * <b>Returns a <code>int</code> value read from keyboard.</b><br>
     * Prior to returning the result, it checks whether the read string
     * is numeric or not. After verify, it parses the string to int.
     * @deprecated
     * @return Integer (<code>int</code>) read from keyboard.
     * @since version 1.0
     * @see #readInteger(InputStream)
     */
    @Deprecated
    public static int readInteger(){
        int integer = 0;
        boolean isNumber;
        String string;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            do {
                string = br.readLine();
                isNumber = StringUtils.isNumeric(string);
                if (!isNumber) System.out.print("The entered value is not a number. Please enter a number: ");
            }
            while (!isNumber);
            integer = Integer.parseInt(string);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return integer;
    }


    /**
     * <b>Deprecated method to return a in value read from keyboard with a limited length</b><br>
     * Replaced by <code>readInteger(InputStream,int)</code> since v3.0.0.<br>
     * <b>Returns a integer (<code>int</code>) number of a max length read from keyboard.</b><br>
     * The value is read as a <code>String</code>, then checked if it's a number, then
     * truncated to the max length and finally parsed to int.
     * @param length Max length of the integer number to return.
     * @return Integer (<code>int</code>) read from keyboard.
     * @since version 1.6
     * @deprecated
     * @see #readInteger(InputStream, int)
     */
    @Deprecated
    public static int readInteger(int length){
        int integer = 0;
        boolean isNumber;
        String string;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            do {
                string = br.readLine();
                isNumber = StringUtils.isNumeric(string);
                if (!isNumber) System.out.print("The entered value is not a number. Please enter a number: ");
            }
            while (!isNumber);
            if (string.length() > length){
                string = string.substring(0, length);
            }
            integer = Integer.parseInt(string);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return integer;
    }


    /**
     * <b>Deprecated method to read a character from keyboard.</b><br>
     * Returns a character (<code>char</code>) read from keyboard.<br>
     * If more than one character is entered only the first one is returned.<br>
     * Replaced by {@code readChar(InputStream} since v3.0.0.
     * @deprecated
     * @return character (<code>char</code>).
     * @since  version 1.2
     */
    @Deprecated
    public static char readChar(){
        boolean ok = false;
        char character = ' ';
        do{
            try{
                character = scan.next().charAt(0);
                ok = true;
            }
            catch(Exception e){
                System.out.println("The entered value is not a valid character");
                System.out.println("Please enter a new valid character 'char' type: ");
            }
        }
        while (!ok);
        return character;
    }


    /**
     * <b>Deprecated method to read a double precision number from keyboard</b><br>
     * Returns a double precision number (<code>double</code>) read from keyboard.<br>
     * Replaced by {@code readDouble(InputStream)} since v3.0.0.
     * @deprecated
     * @return Double precision (<code>double</code>) number read from keyboard.
     * @since version 1.0
     * @see #readDouble(InputStream)
     */
    @Deprecated
    public static double readDouble(){
        boolean ok = false;
        double dbl = 0.0f;
        do{
            try{
                String  cad = readString();
                dbl = Double.parseDouble(cad);
                ok = true;
            }
            catch (Exception e){
                System.out.println("The entered value is not a 'double' type number");
                System.out.print("Please enter a 'double' type number: ");
            }
        }
        while (!ok);
        return dbl;
    }


}
