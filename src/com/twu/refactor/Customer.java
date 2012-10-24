package com.twu.refactor;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
            Rental each = rentals.next();
            double thisAmount = calculateAmoutFor(each);
            frequentRenterPoints = calculateFrequentRenterPointsFor(frequentRenterPoints, each);
            // show figures for this rental
			result += statementForEachRental(each, thisAmount);
			totalAmount += thisAmount;

		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

    private String statementForEachRental(Rental rental, double thisAmount) {
        return "\t" + rental.getMovie().getTitle() + "\t"
                + String.valueOf(thisAmount) + "\n";
    }

    private int calculateFrequentRenterPointsFor(int frequentRenterPoints, Rental rental) {
        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double calculateAmoutFor(Rental rental) {
        double thisAmount = 0;

        // determine amounts for each line
        switch (rental.getMovie().getPriceCode()) {
        case Movie.REGULAR:
            thisAmount += 2;
            if (rental.getDaysRented() > 2)
                thisAmount += (rental.getDaysRented() - 2) * 1.5;
            break;
        case Movie.NEW_RELEASE:
            thisAmount += rental.getDaysRented() * 3;
            break;
        case Movie.CHILDRENS:
            thisAmount += 1.5;
            if (rental.getDaysRented() > 3)
                thisAmount += (rental.getDaysRented() - 3) * 1.5;
            break;

        }
        return thisAmount;
    }

}
