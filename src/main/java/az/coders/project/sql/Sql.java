package main.java.az.coders.project.sql;

import main.java.az.coders.project.entity.Flight;
import main.java.az.coders.project.entity.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Sql {

    String getAllFlights();

    String getFlightById(long id);

    String getFlightByDestinitionAndDate(Flight flight);

    String booking(Flight flight, Person person);

    String cancel(long id);

    String myFlights();

    String findMaxId();

    String findMaxBookId();

    String getMyFlightsCount();

    String updateDate(SimpleDateFormat format, Date date);
}
