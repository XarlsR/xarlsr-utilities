/*
Apache License
Version 2.0, January 2004
Copyright [2022] [Carlos R. Puente (XarlsR)]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
 */

package dev.xarlsr.utilities;

import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Reads and return different data types from keyboard<br><br>
 * @author XarlsR 2022
 * @version 2.3.0
 */
public class ConsoleInput {

    static final Scanner scan = new Scanner(System.in);

    /**
     * Returns a String value read from keyboard<br>
     * @return String read from keyboard
     * @since version 1.0
     */
    public static String readString(){
        String string = "";
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try{
            string = buff.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    /**
     * Reads a String value from keyboard with the maximum length set by 'length' parameter.
     * If the entered string is longer than 'length' then it's truncated.
     * @param length Max length of the returned string.
     * @return String with the read value, truncated to length if longer.
     */
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
     * Returns a integer (int) value read from keyboard.<br>
     * Prior to returning the result, it checks whether the read string
     * is numeric or not. After verify, it parses the string to int.
     * @return Integer (int) read from keyboard.
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
     * Returns a integer (int) number of a max length read from keyboard.
     * The value is read as a String, then checked if it's a number, then
     * truncated to the max length and finally parsed to int.
     * @param length Max length of the integer number to return.
     * @return Integer (int) read from keyboard.
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
     * Returns a character (char) read from keyboard.
     * If more than one character is entered only the first one is returned.
     * @return character (char).
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
     * Returns a double precision number (double) read from keyboard.
     * @return Double precision (double) number read from keyboard.
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
     * Clears the console in Windows systems
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
     * Asks the user to press ENTER to continue.
     */
    public static void pressEnter() {
        System.out.print("Press ENTER to continue");
        ConsoleInput.readString();
    }




}
