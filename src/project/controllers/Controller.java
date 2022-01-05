package project.controllers;

import project.dao.PgJDBC;
import project.entity.Flight;
import project.entity.Person;
import project.services.Service;
import project.services.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Controller {

    private static final Service service = new Services();
    private static final PgJDBC pgjdbc = new PgJDBC();

    public static boolean cmd(String cmd){
        return service.checkCmd(cmd);
    }

    public static void show(){
        System.out.println("Online Table Airport: Kiev Boryspil");

        for (int i = 0; i < pgjdbc.showFlights().size(); i++) {
            System.out.println(pgjdbc.showFlights().get(i) + "\n");
        }

    }

    public static void info(){
        System.out.println("Flight info.");

        long id = service.checkFlightId();

        System.out.println(pgjdbc.flightInfo(id));

    }


    public static void book(){
        boolean b = false;
        int peopleNumber = 0;
        List<Flight> flights = searching();
        Flight bookFlight = null;

        String flightNumber = service.checkFlightNumber();


        for (Flight flight: flights) {
            if(flight.getFlightNumber().equals(flightNumber)){
                b = true;
                bookFlight = flight;
            }
        }

        if(b){
            peopleNumber = service.checkPeopleNumber();
        }

        for (int i = 0; i < peopleNumber; i++) {
            String name = service.checkName();

            String surname = service.checkSurname();

            int age = service.checkAge();

            pgjdbc.booking(bookFlight,new Person(name,surname,age));
        }


    }

    public static void cancel(){
        System.out.println("Cancel flight");

        long id = service.checkBookingId();
        long previousCount = bookingCount();

        pgjdbc.cancel(id);

        long presentCount = bookingCount();

        if(presentCount < previousCount){
            System.out.println("Cancellation was successful");
        }
        else {
            System.out.println("Cancellation failed");
        }

    }

    public static void flights(){
        System.out.println("My flights.");

        for (int i = 0; i < pgjdbc.myFlights().size(); i++) {
            System.out.println(pgjdbc.myFlights().get(0));
        }

    }

    private static long bookingCount(){
        long count = 0;
        try {
            ResultSet rs = pgjdbc.statement(pgjdbc.connection()).executeQuery("select count (*) from booking");

            while (rs.next()){
                count = rs.getLong("count");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    private static List<Flight> searching(){
        System.out.println("Enter flight info.");

        Date date = service.checkDate();

        String destination = service.checkDestination();

        return pgjdbc.searching(new Flight(date,destination));
    }
}
