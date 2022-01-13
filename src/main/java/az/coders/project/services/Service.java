package main.java.az.coders.project.services;

import main.java.az.coders.project.entity.Booking;
import main.java.az.coders.project.entity.Flight;

import java.util.Date;
import java.util.List;

public interface Service {

    List<Flight> showFlights();

    Flight getFlightInfoById();

    List<Flight> getSearchingFlights();

    void bookingFlights();

    void cancelFlights();

    List<Booking> getMyFlights();

    void updateFlightsDate();

    boolean checkCmd(String cmd);

    long checkFlightId();

    Date checkDate();

    String checkDestination();

    String checkFlightNumber();

    int checkPeopleNumber();

    String checkName();

    String checkSurname();

    int checkAge();

    long checkBookingId();
}
