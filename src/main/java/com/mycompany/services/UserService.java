package com.mycompany.services;

import com.mycompany.config.DataSource;
import java.util.List;
import java.util.Map;

public class UserService {
    private final DataSource dataSource;

    public UserService() {
        this.dataSource = new DataSource();
    }

    public int addUser(String username, String password, String role) {
        return dataSource.insert("INSERT INTO users (username, password, role) VALUES (?, ?, ?)", username, password, role);
    }

    public int blockUser(int userId) {
        return dataSource.update("UPDATE users SET status = 'blocked' WHERE id = ?", userId);
    }

    public int reactivateUser(int userId) {
        return dataSource.update("UPDATE users SET status = 'active' WHERE id = ?", userId);
    }

    public List<Map<String, Object>> getAllUsers() {
        return dataSource.select("SELECT * FROM users");
    }

    // Login method: check if username and password match
    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        List<Map<String, Object>> result = dataSource.select(query, username, password);

        // If a user is found and the list is not empty, login is successful
        return !result.isEmpty();
    }
}
