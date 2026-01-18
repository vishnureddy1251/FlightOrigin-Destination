package com.example.flightapp.model;

import java.time.LocalTime;

public class Flight {
    private String origin;
    private String destination;
    private int flightNumber;
    private double price;
    private LocalTime departureTime;

    //Constructor
    public Flight(String origin,String destination, int flightNumber,
                    double price, LocalTime departureTime){
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.price = price;
        this.departureTime = departureTime;

    }

    // Getters and Setters
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightNumber=" + flightNumber +
                ", price=" + price +
                ", departureTime=" + departureTime +
                "}";

    }
}
