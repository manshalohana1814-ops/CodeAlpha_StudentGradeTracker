package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Student Grade Tracker Login");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridLayout(1, 2));

        // ================= LEFT PANEL =================
        JPanel left = new JPanel();
        left.setBackground(new Color(30, 90, 160)); // BLUE THEME
        left.setLayout(null);

        JLabel brand = new JLabel("STUDENT GRADE TRACKER");
        brand.setBounds(40, 140, 400, 40);
        brand.setForeground(Color.WHITE);
        brand.setFont(new Font("Segoe UI", Font.BOLD, 22));
        left.add(brand);

        JLabel desc = new JLabel("<html>Simple & Modern<br>Grade Management System</html>");
        desc.setBounds(40, 200, 300, 60);
        desc.setForeground(Color.WHITE);
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        left.add(desc);

        // ================= RIGHT PANEL =================
        JPanel right = new JPanel();
        right.setBackground(new Color(250, 250, 255));
        right.setLayout(null);

        JLabel title = new JLabel("Welcome Back!");
        title.setBounds(80, 60, 300, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        right.add(title);

        JLabel subtitle = new JLabel("Sign in to continue");
        subtitle.setBounds(80, 100, 300, 25);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        right.add(subtitle);

        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setBounds(80, 150, 200, 20);
        right.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(80, 175, 250, 30);
        right.add(userField);

        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(80, 220, 200, 20);
        right.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(80, 245, 250, 30);
        right.add(passField);

        JButton loginBtn = new JButton("SIGN IN");
        loginBtn.setBounds(80, 300, 250, 35);
        loginBtn.setBackground(new Color(30, 90, 160));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        right.add(loginBtn);

        JLabel hint = new JLabel("Default: admin / 1234");
        hint.setBounds(80, 350, 250, 20);
        hint.setForeground(Color.GRAY);
        right.add(hint);

        loginBtn.addActionListener(e -> {

            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                dispose();
                new StudentGradeTrackerGUI(); // dashboard open
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });

        main.add(left);
        main.add(right);

        add(main);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}