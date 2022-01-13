package main.java.az.coders.project.dao;

import main.java.az.coders.project.entity.Booking;
import main.java.az.coders.project.entity.Flight;
import main.java.az.coders.project.entity.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface JDBC {
    Connection connection();

    Statement statement(Connection con) throws SQLException;

    List<Flight> getFlights();

    Flight getFlightInfoById(long id);

    List<Flight> getSearchingFlights(Flight flight);

    void bookingFlights(Flight flight, Person person);

    void cancelFlights(long id);

    List<Booking> getMyFlights();

    long findMaxId();

    long findMaxBookId();

    long getMyFlightsCount();

    void updateFlightsDate(SimpleDateFormat format, Date date);
}
