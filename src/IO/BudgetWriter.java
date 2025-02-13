package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import models.Budget;
import models.Category;
import models.Expense;
import models.Income;

/**
 * This class is used to write a given budget to a file with specific formatting so that the file can later be retrieved and loaded back into the system.
 * 
 * A Budget is formatted like the following:
 * Income: '+ description, amount, date'
 * Expense: '- description, amount, date, category'
 * Category Budget: '* category, amount'
 */
public class BudgetWriter {

    /**
     * Saves the passed Budget to a chosen file in a format that can later be read by the program
     * @param b the budget being saved
     * @param filename the name of the file that is being saved to
     */
    public static void saveBudget(Budget b, String filename) {

        // sort the incomes and expenses for better formatting in the file
        b.getIncome().sort(null);
        b.getExpenses().sort(null);

        /**
         * Try-with-resources to handle closing links and catching Errors that may occur
         */
        try (FileWriter fw = new FileWriter(new File(filename))) {
            for (Income i : b.getIncome()) {
                fw.write(String.format("+ %s, %.2f, %s\n", i.getDescription(), i.getAmount(), i.getDate()));
            }
            for (Expense e : b.getExpenses()) {
                fw.write(String.format("- %s, %.2f, %s, %s\n", e.getDescription(), e.getAmount(), e.getDate(), e.getCategory()));
            }
            for (Entry<Category, Double> entry : b.getCategoryBudgets().entrySet()) {
                fw.write(String.format("* %s, %.2f\n", entry.getKey().toString(), entry.getValue()));
            }
            System.out.println("Budget successfully saved!");
        }
        catch (IOException e ) {
            System.err.println("Error writing to the file: " + filename);
            System.out.println("An error occurred while trying to save!");
        }
    }
}
