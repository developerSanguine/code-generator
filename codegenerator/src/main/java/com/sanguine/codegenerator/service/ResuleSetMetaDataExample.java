package com.sanguine.codegenerator.service;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResuleSetMetaDataExample {
    private static final String QUERY = "select * from tblgrouphd";

    @Value("spring.datasource.url ")
    private String datasourceUrl;

    @Value("spring.datasource.username ")
    private String datasourceUname;

    @Value("spring.datasource.password ")
    private String datasourcePassword;

    public static void main(String[] args) {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/multiwebpos?useSSL=false", "root", "root");
             // Step 2:Create a statement using connection object
             Statement stmt = connection.createStatement();
             // Step 3: Execute the query or update query
             ResultSet rs = stmt.executeQuery(QUERY)) {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            System.out.println(resultSetMetaData.getColumnName(1));
            System.out.println(resultSetMetaData.getColumnName(2));
            System.out.println(resultSetMetaData.getColumnName(3));
            System.out.println(resultSetMetaData.getColumnName(4));
            System.out.println(resultSetMetaData.getColumnName(5));

        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
