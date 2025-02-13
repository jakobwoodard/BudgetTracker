package main.java.IO;

import java.util.ArrayList;

import main.java.models.Budget;
import main.java.models.Category;

/**
 * Generates reports to a given file by a user. 
 * A user can generate a report of expenses by a single, multiple, or all categories.
 * A user can also generate reports for a given time frame.
 */
public class ReportWriter {

    /**
     * Generates a report in csv format for all expenses in the budget
     * @param b the budget that contains the expenses of the report
     */
    public static void writeFullReport(Budget b, String filename) {
        
    }

    /**
     * Generates a report in csv format for all expenses within the given time frame
     * @param b the budget that contains the expenses of the report
     * @param startDate the starting date (inclusive) of the generated report (mm-dd-yy)
     * @param endDate the ending date (inclusive) of the generated report (mm-dd-yy)
     * @param filename the filename of the generated report
     */
    public static void writeReportWithDateRange(Budget b, String startDate, String endDate, String filename) {

    }

    /**
     * Generates a report in csv format for all expenses of the desired category types. This method can take in 
     * 1 or multiple categories and will generate the report accordingly
     * @param b the budget that contains the expenses of the report
     * @param categories the category or categories that the user wants the reports for
     * @param filename the filename of the generated report
     */
    public static void writeReportByCategory(Budget b, ArrayList<Category> categories, String filename) {
        
    }

    /**
     * Generates a report in csv format for all expenses of the desired category types within the given time range.
     * This method can take in 1 or multiple categories and will generate the report accordingly. The generated report will be sorted by date
     * with the earliest entry being listed first.
     * @param b the budget that contains the expenses of the report
     * @param categories the category or categories contained in the report
     * @param startDate the start date (inclusive) of the report (mm-dd-yy)
     * @param endDate the end date (inclusive) of the report (mm-dd-yy)
     * @param filename the filename of teh generated report
     */
    public static void writeReportByCategoryAndRange(Budget b, ArrayList<Category> categories, String startDate, String endDate, String filename) {

    }

}
