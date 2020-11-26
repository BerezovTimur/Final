package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    public static String url = "jdbc:mysql://192.168.99.100:3306/app";
    public static String login = "app";
    public static String password = "pass";

    public static void cleanData() {
        val runner = new QueryRunner();
        val creditPayment = "DELETE FROM credit_request_entity;";
        val order = "DELETE FROM order_entity;";
        val debitPayment = "DELETE FROM payment_entity;";

        try {
            val connection = DriverManager.getConnection(
                    url, login, password);
            runner.update(connection, creditPayment);
            runner.update(connection, order);
            runner.update(connection, debitPayment);

        } catch (SQLException ex) {
            System.out.println("SQLException message:" + ex.getMessage());
        }
    }

    public static String getStatusPayment() {
        val request = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                val connect = DriverManager.getConnection(url, login, password);
                val createStmt = connect.createStatement();
        ) {
            try (val resultSet = createStmt.executeQuery(request)) {
                if (resultSet.next()) {
                    val status = resultSet.getString(1);
                    return status;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
