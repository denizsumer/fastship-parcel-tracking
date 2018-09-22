/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fastship.ws;

import java.util.Scanner;

/**
 * IOHandler class is a utility class which defines methods to read different
 * type of user inputs.
 *
 * @author Deniz Sumner
 * @version v1.0.1738
 */
public class IOHandler
{

    private static final Scanner reader = new Scanner(System.in);

    /**
     * A method prints a question and reads the user input as String from
     * console
     *
     * @param prompt a question to user
     * @return String user input
     */
    public static String readString(String prompt)
    {
        print(prompt);
        return reader.nextLine();
    }

    /**
     * A method prints a question and reads the user input as integer from
     * console The method checks the user input whether it is an integer or not
     * If user input is not an integer, displays error message and prompts again
     *
     * @param prompt a question to user
     * @return input user input
     */
    public static int readInteger(String prompt)
    {
        int input;
        while (true)
        {
            try
            {
                print(prompt);
                input = Integer.parseInt(reader.nextLine());
                break;
            }
            catch (Exception exc)
            {
                print("Invalid input. Enter a number.");
            }
        }
        return input;
    }

    /**
     * A method reads the user input and checks if it is in a given range The
     * method checks the user input whether is in the range or not If user input
     * is not in the range, displays error message and prompts again
     *
     * @param prompt a question to user
     * @param min allowed minimumum limit of the user input
     * @param max allowed maximum limit of the user input
     * @return output user input
     */
    public static int readIntegerRange(String prompt, int min, int max)
    {
        int input = readInteger(prompt); //check if int
        int output;
        while (true)
        {
            if (input >= min && input <= max)
            {
                output = input;
                break;
            }
            else
            {
                input = readInteger("Invalid input. Enter a number between " + min + " and " + max + ".");
            }
        }
        return output;
    }

    /**
     * A method that waits for user input
     *
     */
    public static void pressEnter()
    {
        print("\n\nPress ENTER to continue...");
        reader.nextLine();
    }

    /**
     * A method that clears the console
     *
     */
    public static void clearConsole()
    {
        System.out.println("\f");
    }

    public static void print(String str)
    {
        System.out.println(str);
    }
}
