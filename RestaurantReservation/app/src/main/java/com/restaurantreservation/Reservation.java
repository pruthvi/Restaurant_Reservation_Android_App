package com.restaurantreservation;

public class Reservation {

    int phoneNumber;
    int tableNumber;
    int numberOfGuess;
    String date;
    String time;
    String note;

    public Reservation(int phoneNumber, int tableNumber, int numberOfGuess, String date, String time, String note) {
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
        this.numberOfGuess = numberOfGuess;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getNumberOfGuess() {
        return numberOfGuess;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }
}
