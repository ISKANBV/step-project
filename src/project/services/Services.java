package project.services;

import project.commands.Cmd;
import project.dao.JDBC;
import project.dao.PgJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Services implements Service{

    private static final Scanner sc = new Scanner(System.in);
    private static final JDBC pgjdbc = new PgJDBC();

    @Override
    public boolean checkCmd(String cmd) {
        for (Cmd cmdd: Cmd.values()) {
            if (cmd.trim().equalsIgnoreCase(cmdd.name())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long checkFlightId() {
        boolean c = true;
        int Id = 0;
        while (c){
            System.out.print("Enter Id: ");
            Id = sc.nextInt();

            if(Id < 0){
                System.out.println("Id can't be negative!");
            }
            else if(Id > lastFlightId()){
                System.out.println("Id not found.");
            }
            else {
                c = false;
            }
        }
        sc.nextLine();
        return Id;
    }

    @Override
    public Date checkDate() {
        boolean c = true;
        String date = null;
        while (c){
            System.out.print("Enter date(YYYY-MM-DD): ");
            date = sc.nextLine();


            if(date.isEmpty()){
                System.out.println("Date can't be empty!");
            }
            else if((boolean)dateFormat(date).get(0)){
                System.out.println("Please try again");
            }
            else {
                c = false;
            }
        }
        return (Date) dateFormat(date).get(1);
    }

    @Override
    public String checkDestination() {
        boolean c = true;
        String destination = null;
        while (c){
            System.out.print("Enter destination: ");
            destination = sc.nextLine();

            if(destination.isEmpty()){
                System.out.println("Destination can't be empty!");
            }
            else {
                c = false;
            }
        }
        return destination;
    }

    @Override
    public String checkFlightNumber() {
        boolean c = true;
        String flightNumber = null;
        while (c){
            System.out.print("Enter flightNumber: ");
            flightNumber = sc.nextLine();

            if(flightNumber.isEmpty()){
                System.out.println("FlightNumber can't be empty!");
            }
            else {
                c = false;
            }
        }
        return flightNumber;
    }

    @Override
    public int checkPeopleNumber() {
        boolean c = true;
        int peopleNumber = 0;
        while (c){
            System.out.print("Enter people number: ");
            peopleNumber = sc.nextInt();

            if(peopleNumber <= 0){
                System.out.println("People number can't be 0 or negative!");
            }
            else {
                c = false;
            }
        }
        sc.nextLine();
        return peopleNumber;
    }

    @Override
    public String checkName() {
        boolean c = true;
        String name = null;
        while (c){
            System.out.print("Enter name: ");
            name = sc.nextLine();

            if(name.isEmpty()){
                System.out.println("Name can't be empty!");
            }
            else {
                c = false;
            }
        }
        return name;
    }

    @Override
    public String checkSurname() {
        boolean c = true;
        String surname = null;
        while (c){
            System.out.print("Enter surname: ");
            surname = sc.nextLine();

            if(surname.isEmpty()){
                System.out.println("Surname can't be empty!");
            }
            else {
                c = false;
            }
        }
        return surname;
    }

    @Override
    public int checkAge() {
        boolean c = true;
        int age = 0;
        while (c){
            System.out.print("Enter age: ");
            age = sc.nextInt();

            if(age <= 0){
                System.out.println("Age can't be 0 or negative!");
            }
            else {
                c = false;
            }
        }
        sc.nextLine();
        return age;
    }

    @Override
    public long checkBookingId() {

        boolean c = true;
        int bookId = 0;
        while (c){
            System.out.print("Enter Id: ");
            bookId = sc.nextInt();

            if(bookId < 0){
                System.out.println("Id can't be negative!");
            }
            else if(bookId > lastBookId()){
                System.out.println("Id not found.");
            }
            else {
                c = false;
            }
        }
        sc.nextLine();
        return bookId;
    }

    public static long lastBookId(){
        int lastId = 0;
        try {
            ResultSet rs = pgjdbc.statement(pgjdbc.connection()).executeQuery("select bookid from booking order by bookid desc limit 1");
            while (rs.next()){
                lastId = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    public static long lastFlightId(){
        int lastId = 0;
        try {
            ResultSet rs = pgjdbc.statement(pgjdbc.connection()).executeQuery("select id from flight order by id desc limit 1");
            while (rs.next()){
                lastId = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    private static List<Object> dateFormat(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = null;

        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            System.out.println("Date format wrong!");
            return Arrays.asList(true);
        }
        return Arrays.asList(false,date1);
    }
}
