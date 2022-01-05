package project.services;

import java.util.Date;

public interface Service {

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
