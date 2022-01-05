package project;

import project.commands.Cmd;
import project.controllers.Controller;
import project.exceptions.CommandNotFoundException;

import java.util.Scanner;

public class ConsoleApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void run(){

        System.out.println("ConsoleApp run!");

        while (true){
            System.out.println("1. Online table.(SHOW)");
            System.out.println("2. Flight information(INFO).");
            System.out.println("3. Flights search and booking(BOOK).");
            System.out.println("4. Booking cancelling(CANCEL).");
            System.out.println("5. My flights(FLIGHTS).");
            System.out.println("8. Exit(EXIT).");
            System.out.print("Enter command: ");
            String cmd = sc.nextLine();

//            switch (cmd.toUpperCase()){
//                case Cmd.EXIT.name():
//
//                    break;
//                case Cmd.SHOW.name():
//
//                    break;
//                case Cmd.INFO.name():
//
//                    break;
//                case Cmd.FLIGHTS.name():
//
//                    break;
//                case Cmd.CANCEL.name():
//
//                    break;
//                case Cmd.BOOK.name():
//
//                    break;
//                default:
//                    System.out.println("s");
//                    break;
//            }

            if(Controller.cmd(cmd.trim())){
                try {
                    throw new CommandNotFoundException("Could not find command:" + cmd);
                } catch (CommandNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(cmd.equalsIgnoreCase(Cmd.EXIT.name())){
                System.out.println("bye");
                break;
            }
            else if(cmd.equalsIgnoreCase(Cmd.SHOW.name())){
                Controller.show();
            }
            else if (cmd.equalsIgnoreCase(Cmd.INFO.name())){
                Controller.info();
            }
            else if(cmd.equalsIgnoreCase(Cmd.BOOK.name())){
                Controller.book();
            }
            else if(cmd.equalsIgnoreCase(Cmd.CANCEL.name())){
                Controller.cancel();
            }
            else if(cmd.equalsIgnoreCase(Cmd.FLIGHTS.name())){
                Controller.flights();
            }
        }

        System.out.println("ConsoleApp stop!");
    }
}
