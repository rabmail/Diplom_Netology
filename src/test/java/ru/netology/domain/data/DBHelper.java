package ru.netology.domain.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static String url = System.getProperty("url");
    private static String user = System.getProperty("user");
    private static String password = System.getProperty("password");

    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
        return connection;
    }

    public static void cleanDB() {

        val runner = new QueryRunner();
        val credit = "DELETE FROM credit_request_entity";
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";

        try (Connection connection = getConnection()) {
            runner.update(connection, credit);
            runner.update(connection, order);
            runner.update(connection, payment);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static String getStatusPaymentBye() {
        val statusSql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";

        try (Connection connection = getConnection();
             val statusSt = connection.createStatement();
        ) {
            try (val rs = statusSt.executeQuery(statusSql)) {
                if (rs.next()) {
                    val status = rs.getString(1);

                    return status;
                }
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getStatusPaymentByeCredit() {
        val statusSql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";

        try (Connection connection = getConnection();
             val statusSt = connection.createStatement();
        ) {
            try (val rs = statusSt.executeQuery(statusSql)) {
                if (rs.next()) {
                    val status = rs.getString(1);

                    return status;
                }
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}