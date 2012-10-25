package com.twu.refactor;

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Rental> rentalList = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentalList.add(arg);
    }

    public String statement() {
        return header() + body() + footer();
    }

    private String footer() {
        return "Amount owed is " + String.valueOf(calculateTotalAmount()) + "\n" +
                "You earned " + String.valueOf(calculateFrequentRenterPoints())
                + " frequent renter points";
    }

    private String header() {
        return "Rental Record for " + name + "\n";
    }

    private double calculateTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentalList) {
            totalAmount += rental.amount();
        }
        return totalAmount;
    }

    private int calculateFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentalList) {
            frequentRenterPoints++;
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && rental.getDaysRented() > 1)
                frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    private String body() {
        String result = "";
        for (Rental rental : rentalList) {
            result += statementFor(rental);
        }
        return result;
    }

    private String statementFor(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t"
                + String.valueOf(rental.amount()) + "\n";
    }

}
