package models;


public class Expense implements Comparable<Expense> {

    private String description;
    private double amount;
    private String date;
    private Category category;

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    public Expense(String description, double amount, String date, Category category) {
        setDescription(description);
        setAmount(amount);
        setDate(date);
        setCategory(category);
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
        result = prime * result + ((category == null) ? 0 : category.hashCode());
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
        Expense other = (Expense) obj;
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
        if (category != other.category)
            return false;
        return true;
    }

    @Override
    public int compareTo(Expense o) {
        if (this.getAmount() < o.getAmount()) return -1;
        else if (this.getAmount() > o.getAmount()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return description + '\t' + "$" + amount + '\t' + date + '\t'
                + category;
    }

    

    


}
