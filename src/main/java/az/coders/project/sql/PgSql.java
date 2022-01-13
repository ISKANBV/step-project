package main.java.az.coders.project.sql;


import main.java.az.coders.project.entity.Flight;
import main.java.az.coders.project.entity.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PgSql implements Sql {

    @Override
    public String getAllFlights() {
        return "select * from flights";
    }

    @Override
    public String getFlightById(long id) {
        return String.format("select * from flights where id = %s", id);
    }

    @Override
    public String getFlightByDestinitionAndDate(Flight flight) {
        return String.format("select * from flights where destination = '%s' and date = '%s'", flight.getDestination(), flight.getDate());
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
        return String.format("delete from booking where bookId = %s", id);
    }

    @Override
    public String myFlights() {
        return "select * from booking";
    }

    @Override
    public String findMaxId() {
        return "select id from flights order by id desc limit 1";
    }

    @Override
    public String findMaxBookId() {
        return "select bookid from booking order by bookid desc limit 1";
    }

    @Override
    public String getMyFlightsCount() {
        return "select count (*) from booking";
    }

    @Override
    public String updateDate(SimpleDateFormat format, Date date) {
        return String.format("update flights set date = %s", format.format(date));
    }
}
