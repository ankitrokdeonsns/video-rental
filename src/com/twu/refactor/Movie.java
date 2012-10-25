package com.twu.refactor;

public class Movie {
	public static final int  CHILDRENS = 2;
	public static final int  REGULAR = 0;
	public static final int  NEW_RELEASE = 1;

	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int arg) {
    	priceCode = arg;
	}

	public String getTitle () {
		return title;
	}

    public double rentalCharge(int daysRented) {
        double amount = 0;

        // determine amounts for each line
        switch (priceCode) {
            case Movie.REGULAR:
                amount += 2;
                if (daysRented > 2)
                    amount += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += daysRented * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (daysRented > 3)
                    amount += (daysRented - 3) * 1.5;
                break;

        }
        return amount;
    }
}

