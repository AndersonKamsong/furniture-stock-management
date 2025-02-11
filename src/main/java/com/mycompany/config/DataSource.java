package com.mycompany.config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSource {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/furniture_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection conn;

    public DataSource() {
        this.conn = getConnection();
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, "Database connection failed", ex);
        }
        return null;
    }

    public List<Map<String, Object>> select(String query, Object... params) {
        List<Map<String, Object>> result = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            setParameters(statement, params);
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), resultSet.getObject(i));
                    }
                    result.add(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, "Select query failed", ex);
        }
        return result;
    }

    public int update(String query, Object... params) {
        return executeUpdate(query, params);
    }

    public int insert(String query, Object... params) {
        return executeUpdate(query, params);
    }

    public int delete(String query, Object... params) {
        return executeUpdate(query, params);
    }

    private int executeUpdate(String query, Object... params) {
        int affectedRows = 0;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            setParameters(statement, params);
            affectedRows = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, "Update/Insert/Delete query failed", ex);
        }
        return affectedRows;
    }

    private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
