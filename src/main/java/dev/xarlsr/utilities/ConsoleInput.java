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
            inputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * <b>Reads and returns a String from a InputStream object</b><br>
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
     * <b>Deprecated version of the readString() method.</b><br>
     * Returns a String read from keyboard (System.in).<br>
     * Replaced by readString(InputReader) method in version 2.3.7
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
     * <b>Deprecated version of the readStrign(InputStream, int) method</b>
     * <b>Reads a <code>String</code> value from keyboard with the maximum length set by <code>length</code>
     * parameter.</b><br>
     * If the entered line is longer than <code>length</code> then it's truncated.
     * @deprecated
     * @param length <code>int</code> number with the max length of the returned string.
     * @return <code>String</code> with the read value, truncated to <code>length</code> if longer.
     * @since v1.0.0
     * @see #readString(InputStream, int)
     */
    @Deprecated
    public static String readString(int length){
        String string = "";
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try{
            string = buff.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (string.length() > length) {
            return string.substring(0, length);
        }
        else return string;
    }

    /**
     * <b>Returns a <code>int</code> value read from keyboard.</b><br>
     * Prior to returning the result, it checks whether the read string
     * is numeric or not. After verify, it parses the string to int.
     * @return Integer (<code>int</code>) read from keyboard.
     * @since version 1.0
     */
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
     * <b>Returns a integer (<code>int</code>) number of a max length read from keyboard.</b><br>
     * The value is read as a <code>String</code>, then checked if it's a number, then
     * truncated to the max length and finally parsed to int.
     * @param length Max length of the integer number to return.
     * @return Integer (<code>int</code>) read from keyboard.
     * @since version 1.6
     */
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
     * <b>Returns a character (<code>char</code>) read from keyboard.</b><br>
     * If more than one character is entered only the first one is returned.
     * @return character (<code>char</code>).
     * @since  version 1.2
     */
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
     * <b>Returns a double precision number (<code>double</code>) read from keyboard.</b><br>
     * @return Double precision (<code>double</code>) number read from keyboard.
     * @since version 1.0
     */
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
        /*do{                   <---------------------- DEPRECATED SINCE VER. 1.6 ------------------>
            if (scan.hasNextDouble()){
                ok = true;
                doble = scan.nextDouble();
            }
            else{
                System.out.println("El valor introducido no es un double.");
                System.out.print("Introduzca un double: ");
                scan.nextLine();
            }
        }
        while (!ok);            <------------------------------------------------------>*/
        return dbl;
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
        ConsoleInput.readString();
    }

}
