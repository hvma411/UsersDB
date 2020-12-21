package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        boolean killSwitch = false;

        System.out.println();
        System.out.println("Welcome in User DataBase! What would you like to do?");
        System.out.println();

        while (!killSwitch) {

            System.out.println("1 - Add new user");
            System.out.println("2 - Print all users data");
            System.out.println("3 - Update user data");
            System.out.println("4 - Exit program");

            boolean didUserChoose = false;

            String userChoice = input.next();

            while (!didUserChoose) {

                if (userChoice.equals("1")) {

                    System.out.println("Type your user name:");
                    String userName = input.next();
                    System.out.println("Type your password:");
                    String userPassword = input.next();

                    User newUser = new User(userName, userPassword);

                    User.addUser(getConnection(), newUser);

                    didUserChoose = true;

                } else if (userChoice.equals("2")) {

                    User.checkAllUsersData(getConnection());

                    System.out.println();
                    System.out.println("User data above. What now?");
                    System.out.println();

                    didUserChoose = true;

                } else if (userChoice.equals("3")) {
                    // update user data

                    System.out.println("Type index to edit user data: ");
                    int index = input.nextInt();

                    System.out.println("Type new password: ");
                    String password = input.next();

                    User.updateUserPassword(getConnection(), index, password);

                    didUserChoose = true;

                } else if (userChoice.equals("4")) {
                    killSwitch = true;
                    didUserChoose = true;

                    System.out.println();
                    System.out.println("Bye bye!");

                } else {
                    System.out.println("Try again!");
                    System.out.println();
                    userChoice = input.next();
                }
            }
        }
    }

    public static Connection getConnection() throws Exception{
        try{
            String url = "jdbc:mysql://localhost/usersdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String username = "root";
            String password = "root";

            Connection conn = DriverManager.getConnection(url, username, password);

//            System.out.println("Connected");

            return conn;

        } catch(Exception e) {
            System.out.println("Didn't connected " + e);
        }
        return null;
    }
}
