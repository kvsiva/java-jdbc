package com.sv.jdbc.statement.row;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class RowInsert {

    public static void main(String[] args) {

        // auto close connection and statement
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://172.17.0.2:5432/postgres", "postgres", "tiger");
             Statement statement = conn.createStatement()) {

            //System.out.println(generateInsert("sv", new BigDecimal(999.80)));

            //int row = statement.executeUpdate(generateInsert("sv", new BigDecimal(999.80)));
            int row = statement.executeUpdate(generateInsert("Mani", new BigDecimal(2000.80)));

            // rows affected
            System.out.println(row);
            int row2 = statement.executeUpdate(generateInsert("Alekya", new BigDecimal(1000.80)));
            System.out.println(row2);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String generateInsert(String name, BigDecimal salary) {

        return "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) " +
                "VALUES ('" + name + "','" + salary + "','" + LocalDateTime.now() + "')";

    }


}