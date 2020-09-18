package br.com.alura.ecommerce.database;

import java.sql.*;

public class LocalDatabase {

    private final Connection connection;

    public LocalDatabase(String name) throws SQLException {
        String url = "jdbc:sqlite:target/" + name + ".db";
        connection = DriverManager.getConnection(url);
    }

    public void createIfNotExists(String sql) {
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String statement, String... params) throws SQLException {
        var preparedStatement = prepare(statement, params);
        preparedStatement.execute();
    }

    private PreparedStatement prepare(String statement, String[] params) throws SQLException {
        var preparedStatement = connection.prepareStatement(statement);
        for (int i = 0; i <= params.length; i++) {
            preparedStatement.setString(i + 1, params[i]);
        }
        return preparedStatement;
    }

    public ResultSet query(String query, String... params) throws SQLException {
        var preparedStatement = prepare(query, params);
        return preparedStatement.executeQuery();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
