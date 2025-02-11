package models;

import java.util.ArrayList;

public class Budget {

    private ArrayList<Income> income;
    private double totalIncome;
    private ArrayList<Expense> expenses;
    private double totalExpenses;



    public ArrayList<Income> getIncome() {
        return income;
    }

    private void setIncome(ArrayList<Income> income) {
        this.income = income;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    private void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    private void setTotalIncome(double income) {
        this.totalIncome = income;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    private void setTotalExpenses(double expenses) {
        this.totalExpenses = expenses;
    }

    public Budget() {
        setIncome(new ArrayList<Income>());
        setExpenses(new ArrayList<Expense>());
        setTotalIncome(0);
        setTotalExpenses(0); 

    }

    public void listIncome() {
        System.out.println("Description" + '\t' + "Amount" + '\t' + "Date");
        System.out.println("----------------------------------------------");
        income.sort(null);
        for (Income i : income) {
            System.out.println(i.toString());
        }
    }

    public void listExpenses() {
        System.out.println("Description" + '\t' + "Amount" + '\t' + "Date" + '\t' + "Category");
        System.out.println("----------------------------------------------");
        expenses.sort(null);
        for (Expense e : expenses) {
            System.out.println(e.toString());
        }
    }

    public void filterExpensesByCategory(Category c) {
        System.out.println("Description" + '\t' + "Amount" + '\t' + "Date" + '\t' + "Category");
        System.out.println("----------------------------------------------");
        expenses.sort(null);
        double expensePerCategory = 0;
        for (Expense e: expenses) {
            if (e.getCategory().equals(c)) {
                expensePerCategory += e.getAmount();
                System.out.println(e.toString());
            }
        }
        System.out.println("Total spent on " + c.toString() + ": $" + expensePerCategory);
    }

    public void viewBudgetSummary() {
        System.out.println("Total Income: $" + getTotalIncome());
        System.out.println("Total Expenses: $" + getTotalExpenses());
        System.out.println("Remaining Balance: $" + (getTotalIncome() - getTotalExpenses()));
    }

    public void addIncome(Income i) {
        income.add(i);
        setTotalIncome(totalIncome + i.getAmount());
    }

    public void addExpense(Expense e) {
        expenses.add(e);
        setTotalExpenses(totalExpenses + e.getAmount());
    }
}
