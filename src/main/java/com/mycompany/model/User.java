package com.mycompany.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role; // Admin, Employee, etc.
    private boolean isBlocked;

    public User(int id, String username, String password, String role, boolean isBlocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isBlocked() { return isBlocked; }
    public void setBlocked(boolean isBlocked) { this.isBlocked = isBlocked; }
}
