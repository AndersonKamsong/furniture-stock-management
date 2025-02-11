package com.mycompany.views;

import javax.swing.*;
import java.awt.*;

public class UserManagementPanel extends JPanel {
    public UserManagementPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("User Management", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Menu");

        backButton.addActionListener(e -> mainFrame.switchPanel("Menu"));

        // Embed ManageUserView
        ManageUserView manageUserView = new ManageUserView();

        // Add components
        add(label, BorderLayout.NORTH);
        add(manageUserView, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
