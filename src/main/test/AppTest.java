package main.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.models.Budget;
import main.java.models.Income;

public class AppTest {

    Budget b;

    @Before
    public void setUp() {
        b = new Budget();
    }


    @Test
    public void testCreateValidIncome() {
        Income i = new Income("tutoring", 15, "02-15-25");
        Assert.assertEquals("02-15-25", i.getDate());
    }

    @Test
    public void testCreateInvalidAmountIncome() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Income("tutoring", -1, "02-15-25");
        });
    }

    @Test
    public void testCreateInvalidDescriptionIncome() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Income("asdf,asdfdas", 15, "02-15-25");
        });
    }

    @Test
    public void testCreateInvalidDateIncome() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Income("tutoring", 15, "invalid");
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new Income("tutoring", 15, "02/15/25");
        });
    }

    @Test
    public void testAddIncome() {

        b.addIncome(new Income("tutoring", 15, "02-15-25"));
        Assert.assertEquals(1, b.getIncome().size());
        Assert.assertEquals( (double) 15, b.getTotalIncome(), .01);

    }

    
}
