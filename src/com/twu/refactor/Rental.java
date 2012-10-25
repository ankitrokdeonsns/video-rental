package com.twu.refactor;

public class Rental {

    private Movie movie;

    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    double amount() {
        return movie.rentalCharge(daysRented);
    }

    public boolean isApplicableForBonusFrequentRenterPoint() {
        return (movie.getPriceCode() == Movie.NEW_RELEASE)
                && daysRented > 1;
    }

    public int frequentRenterPoints() {
        if (this.isApplicableForBonusFrequentRenterPoint())
            return 2;
        return 1;
    }

    String statement() {
        return "\t" + movie.getTitle() + "\t"
                + String.valueOf(amount()) + "\n";
    }
}