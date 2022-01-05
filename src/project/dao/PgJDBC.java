package project.dao;

import project.entity.Booking;
import project.entity.Flight;
import project.entity.Person;
import project.sql.PgSql;
import project.sql.Sql;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PgJDBC implements JDBC {

    private static final Sql sql = new PgSql();
    private static final String path = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pass = "9171";


    @Override
    public Connection connection() {
        System.out.println("Connection started...");

        Connection con = null;

        try {
            con = DriverManager.getConnection(path, user, pass);
            System.out.println("Connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection ended.");
        return con;

    }

    @Override
    public Statement statement(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        System.out.println("Statement created successfully.");
        return stmt;
    }

    public List<Flight> showFlights(){
        List<Flight> flights = null;

        try {
            Statement s = statement(connection());
            ResultSet rs = s.executeQuery(sql.showFlights());

            while (rs.next()){
                String flightNumber = rs.getString("flightnumber");
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                String destination = rs.getString("destination");
                Time duration = rs.getTime("duration");

                assert false;
                flights.add(new Flight(flightNumber,date,time,destination,duration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public Flight flightInfo(long id){
        Flight flight = null;

        try {
            Statement s = statement(connection());
            ResultSet rs = s.executeQuery(sql.flightInfo(id));

            while (rs.next()){
                long idd = rs.getLong("id");
                String flightNumber = rs.getString("flightnumber");
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                String destination = rs.getString("destination");
                Time duration = rs.getTime("duration");

                flight = new Flight(idd,flightNumber,date,time,destination,duration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flight;
    }

    public List<Flight> searching(Flight flight){
        List<Flight> flights = null;

        try {
            Statement s = statement(connection());
            ResultSet rs = s.executeQuery(sql.searching(flight));

            while (rs.next()){
                long id = rs.getLong("id");
                String flightNumber = rs.getString("flightnumber");
                Date date = rs.getDate("date");
                Time time = rs.getTime("time");
                String destination = rs.getString("destination");
                Time duration = rs.getTime("duration");

                assert false;
                flights.add(new Flight(id,flightNumber,date,time,destination,duration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void booking(Flight flight,Person person){

        try {
            Statement s = statement(connection());
            s.execute(String.valueOf(sql.booking(flight,person)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }



    public void cancel(long id){

        try {
            Statement s = statement(connection());
            s.execute(sql.cancel(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> myFlights(){
        List<Booking> bookings = null;

        try {
            Statement s = statement(connection());
            ResultSet rs = s.executeQuery(sql.myFlights());

            while (rs.next()){
                long idd = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                String destination = rs.getString("destination");
                Date date = rs.getDate("date");

                assert false;
                bookings.add(new Booking(idd,new Person(name,surname,age),new Flight(date,destination)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }


}
