package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    public static String mySqlUrl = "jdbc:mysql://192.168.99.100:3306/app";
    public static String login = "app";
    public static String password = "pass";

    public static Connection getConnection() {
        Connection connection = getMySQLConnection();
        if (connection == null) {
            connection = getPostgreSQLConnection();
            if (connection == null) {
                return null;
            } else {
                return connection;
            }
        } else {
            return connection;
        }
    }

    private static Connection getMySQLConnection() {
        try {
            return DriverManager.getConnection(mySqlUrl, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getPostgreSQLConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/app",
                    "app", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearTables() {
        execute("DELETE FROM credit_request_entity;");
        execute("DELETE FROM order_entity;");
        execute("DELETE FROM payment_entity;");
    }

    public static void execute(String query) {
        val runner = new QueryRunner();
        try (val conn = getConnection()) {
            runner.execute(conn, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPaymentStatusTransactions(){
        String status = null;
        val countSQL = "select status from payment_entity;";
        try (val conn = getConnection();
             val countStmt = conn.createStatement();) {
            try (val rs = countStmt.executeQuery(countSQL)) {
                status = "";
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getCreditStatusTransactions(){
        String status = null;
        val countSQL = "select status from credit_request_entity;";
        try (val conn = getConnection();
             val countStmt = conn.createStatement();) {
            try (val rs = countStmt.executeQuery(countSQL)) {
                status = "";
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


    /*private static String getData(String sqlQuery, String column) throws SQLException {

        try (
                val conn = DriverManager.getConnection(mySqlUrl, login, password);
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(sqlQuery)) {
                if (rs.next()) {
                    val data = rs.getString(column);
                    return data;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }*/

    /*public static String getLastPaymentStatus() throws SQLException {
        val getStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "status";
        return SQLHelper.getData(getStatus, column);
    }*/

    /*public static String getLastPaymentID() throws SQLException {
        val getStatus = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        val column = "payment_id";
        return SQLHelper.getData(getStatus, column);
    }*/

    /*public static String getLastTransactionID() throws SQLException {
        val getStatus = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "transaction_id";
        return SQLHelper.getData(getStatus, column);
    }*/

    /*public static void comparePaymentAndTransactionID() throws SQLException {
        getPaymentID().equals(getTransactionID());
    }*/
}
