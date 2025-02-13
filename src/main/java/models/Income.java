package main.java.models;

/**
 * Class to represent an Income object. An Income object has a description of the income, the amount of the income, and the date of the income (expected in mm-dd-yy format).
 */
public class Income implements Comparable<Income> {

    private String description;
    private double amount;
    private String date;


    public String getDescription() {
        return description;
    }

    private void setDescription(String description) throws IllegalArgumentException {
        if (description.contains(",")) {
            throw new IllegalArgumentException("Descriptions cannot contain ',' characters!");
        }
        else {
            this.description = description;
        }
        
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot have an income of less than 0!");
        }
        else {
            this.amount = amount;
        }
        
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) throws IllegalArgumentException {
        if (date.matches("[0-9]{1,2}-[0-9]{1,2}-[0-9]{2}")) {
            this.date = date;
        }
        else {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public Income(String description, double amount, String date) throws IllegalArgumentException {
        setDescription(description);
        setAmount(amount);
        setDate(date);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Income other = (Income) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }

    @Override
    public int compareTo(Income o) {
        return this.getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return description + '\t' + "$" + amount + '\t' + date;
    }
}
