package tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {

    private String name;
    private String email;
    private String phone;
    private String gender;
    private String password;
    private String country;
    private boolean weeklyEmail;
    private boolean monthlyEmail;
    private boolean occasionalEmail;

    @Test
    public void newAccountTest() {
        System.out.println("NEW RECORD: " + name + " " + email + " " + phone + " " + gender + " " + password + " " + country + " " + weeklyEmail + " " +
                " " + monthlyEmail + " " + occasionalEmail);

    }


    @Parameters
    public static List<String[]> getData() {
        return utilities.CSV.get("/Users/alexander/SDET_Files/UserAccounts.csv");

    }

    //Constructor that passes parameters to the test method


    public NewAccountDDT(String name, String email, String phone, String gender, String password, String country,
                         String weeklyEmail, String monthlyEmail, String occasionalEmail) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.country = country;

        if (weeklyEmail.equals("TRUE")) {
            this.weeklyEmail = true;
        } else {
            this.weeklyEmail = false;
        }

        if (monthlyEmail.equals("TRUE")) {
            this.monthlyEmail = true;
        } else {
            this.monthlyEmail = false;
        }

        if (occasionalEmail.equals("TRUE")) {
            this.occasionalEmail = true;
        } else {
            this.occasionalEmail = false;
        }

    }
}
