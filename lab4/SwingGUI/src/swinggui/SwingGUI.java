/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package swinggui;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Trinh
 */
public class SwingGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Login Demo");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel userLabel = new JLabel("Username:");
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        frame.setVisible(true);

        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            
            if (username.equals("admin") && password.equals("123")) {
                JOptionPane.showMessageDialog(null, "Login successful");
            } else {
                JOptionPane.showMessageDialog(null, "Login failed", "Message", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
}
