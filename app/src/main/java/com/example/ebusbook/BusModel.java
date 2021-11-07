package com.example.ebusbook;

public class BusModel {

    String arrival, busId, date, departure, seats;

    BusModel() {

    }

    public BusModel(String arrival, String busId, String date, String departure, String seats) {
        this.arrival = arrival;
        this.busId = busId;
        this.date = date;
        this.departure = departure;
        this.seats = seats;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
