package com.learning.learningjavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionDB {
    Connection connection;
    Statement statement;
    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public ConnectionDB(){
        try {
            String jdbcURL = "jdbc:mysql://localhost/Airline";
            String username = "root";
            String password = "tjbrvT1234@";
            this.connection = DriverManager.getConnection(jdbcURL, username, password);
            this.statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
