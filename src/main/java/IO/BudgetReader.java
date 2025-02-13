package main.java.IO;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import main.java.models.Budget;
import main.java.models.Category;
import main.java.models.Expense;
import main.java.models.Income;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class that contains methods to read a budget from a file. 
 * A Budget file is formatted so that all Income is listed starting with a '+' and all expenses are listed starting with a '-'
 * Any Category that has a specific budget associated with it will be listed with a '*'
 * 
 * Income format: '+ description, amount, date'
 * Expense format: '- description, amount, date, category'
 * Category budget format: '* category, amount'
 */
public class BudgetReader {

    /**
     * Reads a budget from the provided input file. The budget is expected to be in the correct format.
     * @param filename the file that the budget will be 
     * @return the budget after being processed from the file
     */
    public static Budget readBudget(String filename) {
        Budget b = new Budget();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
            Scanner s;
            while (reader.ready()) {
                String line = reader.readLine();

                // Strip the '+', '-', or '*' from the string for processing
                s = new Scanner(line.substring(1));
                s.useLocale(Locale.US);
                s.useDelimiter(",");
                // Process Income data
                if (line.charAt(0) == '+') {
                    Income i = parseIncome(line, s);
                    if (i != null) {
                        b.addIncome(i);
                    }
                }
                // Process Expense data
                else if (line.charAt(0) == '-') {
                    Expense e = parseExpense(line, s);
                    if (e != null) {
                        b.addExpense(e);
                    }
                }
                // Process Category budget data
                else if (line.charAt(0) == '*') {
                    try {
                        Category c = parseCategory(s.next().strip());
                        String amount = s.next().strip();
                        b.setCategoryBudget(c, Double.parseDouble(amount));
                    }
                    catch (NullPointerException e) {
                        System.err.println("Invalid format for category budget.... Skipping....");
                    }

                }
                s.close();
            }
            System.out.println("Successfully loaded a budget from the file!");
        }
        catch (FileNotFoundException e) {
            System.out.println("No file found in the directory with the name: " + filename);
        }
        catch (IOException e) {
            System.out.println("Error opening the file in the directory with name: " + filename);
        }

        return b;
    }


    /**
     * Helper method to be used throughout the program when detecting a provided Category. If a Category does not match EXACTLY (ignoring case) to 
     * a predefined category, it is lumped into the OTHER category
     * @param category a string representation of the category that is attempting to be parsed
     * @return the category ENUM of the parsed input. If no matching category ENUM was found, it is given the value of OTHER
     */
    public static Category parseCategory(String category) {
        if (category.equalsIgnoreCase("Food")) {
            return Category.FOOD;
        }
        else if (category.equalsIgnoreCase("Entertainment")) {
            return Category.ENTERTAINMENT;
        }
        else if (category.equalsIgnoreCase("Rent")) {
            return Category.RENT;
        }
        else if (category.equalsIgnoreCase("Vehicle")) {
            return Category.VEHICLE;
        }
        else if (category.equalsIgnoreCase("Phone")) {
            return Category.PHONE;
        }
        else {
            return Category.OTHER;
        }
    }

    /**
     * Helper method to parse a file string for Income data
     * @param income the line from the file representing Income data, this line always starts with '+' but is stripped before processing
     * @param s the class-wide Scanner object being used for processing
     * @return the Income object (if a valid object can be made) or null if the object cannot be made.
     */
    private static Income parseIncome(String income, Scanner s) {
        try {
            String description = s.next().strip();
            String amount = s.next().strip();
            String date = s.next().strip();
            return new Income(description, Double.parseDouble(amount), date);
        }
        catch (NullPointerException e) {
            System.err.println("Invalid income detected.... Skipping....");
        }
        return null;
    }

    /**
     * Helper method to parse a file string for Expense data
     * @param expense the string representation of the Expense data. Expense data always starts with '-' but is stripped before processing
     * @param s the class-wide Scanner object used for parsing input
     * @return the Expense data object (if valid) or null if a valid Expense object could not be created
     */
    private static Expense parseExpense(String expense, Scanner s) {
        try {
            String description = s.next().strip();
            String amount = s.next().strip();
            String date = s.next().strip();
            Category c = parseCategory(s.next().strip());
            return new Expense(description, Double.parseDouble(amount), date, c);
        }
        catch (NullPointerException e) {
            System.err.println("Invalid income detected.... Skipping....");
        }
        return null;
    }

}
