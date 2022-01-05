package project.sql;

import project.entity.Booking;
import project.entity.Flight;
import project.entity.Person;

import java.util.List;

public interface Sql {

    String showFlights();

    String flightInfo(long id);

    String searching(Flight flight);

    String booking(Flight flight, Person person);

    String cancel(long id);

    String myFlights();
}
