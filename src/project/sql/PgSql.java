package project.sql;

import project.entity.Flight;
import project.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PgSql implements Sql{
    @Override
    public String showFlights() {
        return "select * from flights";
    }

    @Override
    public String flightInfo(long id) {
        return String.format("select * from flight where id = %s",id);
    }

    @Override
    public String searching(Flight flight) {
        return String.format("select * from flight where destination = '%s' and date = '%s'",flight.getDestination(),flight.getDate());
    }

    @Override
    public String booking(Flight flight, Person person) {

        return String.format("insert into booking(name, surname, age, destination, date) values('%s','%s',%s,'%s','%s')",
                person.getName(),
                person.getSurname(),
                person.getAge(),
                flight.getDestination(),
                flight.getDate());

    }

    @Override
    public String cancel(long id) {
        return String.format("delete from booking where bookId = %s",id);
    }

    @Override
    public String myFlights() {
        return "select * from booking";
    }
}
