package com.example.recipes_helper.model;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class UpdateTables {
    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql://localhost:5432/mydatabase";
        String username = "myuser";
        String password = "mypassword";
        String sqlFilePath = "recipes-helper\\src\\main\\resources\\data.sql";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             Statement stmt = conn.createStatement();
             FileReader fr = new FileReader(sqlFilePath)) {

            StringBuilder sql = new StringBuilder();
            int ch;
            while ((ch = fr.read())!= -1) {
                sql.append((char) ch);
            }
            fr.close();

            String[] sqlStatements = sql.toString().split(";");

            for (String statement : sqlStatements) {
                statement = statement.trim();
                if (!statement.isEmpty()) {
                    stmt.executeUpdate(statement);
                }
            }

            System.out.println("Таблицы созданы успешно!");

        } catch (SQLException e) {
            System.err.println("Ошибка создания таблиц: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка чтения SQL-скрипта: " + e.getMessage());
        }
    }
}

