package com.sv.jdbc.statement.row;

import com.sv.jdbc.model.Employee;

import java.math.BigDecimal;
import java.sql.*;

public class RowSelect {
    public static void main(String args[]){
    String sql = "SELECT * FROM EMPLOYEE";

        try (Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://172.17.0.2:5432/postgres", "postgres", "tiger");
    Statement statement = conn.createStatement()) {

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            BigDecimal salary = resultSet.getBigDecimal("SALARY");
            Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");

            Employee obj = new Employee();
            obj.setId(id);
            obj.setName(name);
            obj.setSalary(salary);
            // Timestamp -> LocalDateTime
            obj.setCreatedDate(createdDate.toLocalDateTime());

            System.out.println(obj);
            //statement.close();
            //conn.close();
        }

    } catch (SQLException e) {
        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}

