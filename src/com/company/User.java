package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    static String userName;
    String userPassword;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }

    // Function for adding new user in MySQL database
    public static void addUser(Connection conn, User user) {
        try {
            Statement statement = conn.createStatement();

            statement.executeUpdate("INSERT INTO usersdata (userName, userPassword) VALUE ('"+user.userName+"', '"+user.userPassword+"')");

            System.out.println("User has ben added. What now?");
            System.out.println();

            conn.close();

        } catch(Exception e) {
            System.out.println("Didn't connected" + e);
        }
    }

    // Function for checking all users data in MySQL database
    public static void checkAllUsersData(Connection conn) {
        try {
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM usersData");

            while (result.next()) {
                String allUsersData = String.format("User ID: %s, User name: %s, User password: %s" +
                        " \n----------------------------------------------------------------------------\n",
                        result.getString("userID"), result.getString("userName"), result.getString("userPassword"));

                System.out.println("----------------------------------------------------------------------------");
                System.out.printf(allUsersData);
            }

            conn.close();

        } catch(Exception e) {
            System.out.println("Didn't connected" + e);
        }
    }

    // Function for changing user password in MySQL database
    public static void updateUserPassword(Connection conn, int userID, String newPassword) {
        try {
            Statement statement = conn.createStatement();

            int update = statement.executeUpdate("UPDATE usersData SET userPassword='"+newPassword+"' WHERE userID='"+userID+"'");

            System.out.println("User password has ben updated. What now?");
            System.out.println();

            conn.close();

        } catch(Exception e) {
            System.out.println("Didn't connected" + e);
        }
    }
}
