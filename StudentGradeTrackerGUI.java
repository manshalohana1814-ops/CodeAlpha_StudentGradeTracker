package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();

    private JTextField nameField, rollField, subjectField, marksField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public StudentGradeTrackerGUI() {

        setTitle("Student Grade Tracker Dashboard");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== THEME (MATCH LOGIN BLUE) =====
        Color bg = new Color(245, 248, 255);
        Color sidebar = new Color(30, 90, 160);
        
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(bg);

        // ================= TOP HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 90, 160));
        header.setPreferredSize(new Dimension(1200, 70));

        JLabel title = new JLabel("STUDENT GRADE DASHBOARD");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.add(title);

        main.add(header, BorderLayout.NORTH);

        // ================= LEFT SIDEBAR =================
        JPanel left = new JPanel();
        left.setBackground(sidebar);
        left.setPreferredSize(new Dimension(300, 0));
        left.setLayout(new GridLayout(16, 1, 8, 8));
        left.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        nameField = new JTextField();
        rollField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();
        searchField = new JTextField();

        left.add(label("Name"));
        left.add(nameField);

        left.add(label("Roll No"));
        left.add(rollField);

        left.add(label("Subject"));
        left.add(subjectField);

        left.add(label("Marks"));
        left.add(marksField);

        JButton addBtn = button("ADD STUDENT");
        JButton updateBtn = button("UPDATE");
        JButton deleteBtn = button("DELETE");
        JButton statsBtn = button("STATS");

        left.add(addBtn);
        left.add(updateBtn);
        left.add(deleteBtn);
        left.add(statsBtn);

        left.add(label("Search Roll No"));
        left.add(searchField);

        JButton searchBtn = button("SEARCH");
        left.add(searchBtn);

        main.add(left, BorderLayout.WEST);

        // ================= TABLE =================
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Roll No");
        model.addColumn("Subject");
        model.addColumn("Marks");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane sp = new JScrollPane(table);
        main.add(sp, BorderLayout.CENTER);

        // ================= ACTIONS =================
        addBtn.addActionListener(e -> {
            if (!validateForm()) return;

            Student s = new Student(
                    nameField.getText(),
                    rollField.getText(),
                    subjectField.getText(),
                    Double.parseDouble(marksField.getText())
            );

            students.add(s);
            model.addRow(toRow(s));
            clear();
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                msg("Select row first!");
                return;
            }

            Student s = new Student(
                    nameField.getText(),
                    rollField.getText(),
                    subjectField.getText(),
                    Double.parseDouble(marksField.getText())
            );

            students.set(row, s);

            model.setValueAt(s.getName(), row, 0);
            model.setValueAt(s.getRollNo(), row, 1);
            model.setValueAt(s.getSubject(), row, 2);
            model.setValueAt(s.getMarks(), row, 3);

            clear();
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                msg("Select row first!");
                return;
            }

            students.remove(row);
            model.removeRow(row);
        });

        searchBtn.addActionListener(e -> {
            String roll = searchField.getText();

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRollNo().equalsIgnoreCase(roll)) {
                    table.setRowSelectionInterval(i, i);

                    Student s = students.get(i);
                    nameField.setText(s.getName());
                    rollField.setText(s.getRollNo());
                    subjectField.setText(s.getSubject());
                    marksField.setText(String.valueOf(s.getMarks()));
                    return;
                }
            }

            msg("Student not found!");
        });

        statsBtn.addActionListener(e -> showStats());

        add(main);
        setVisible(true);
    }

    // ================= STATS =================
    private void showStats() {

        if (students.isEmpty()) {
            msg("No data!");
            return;
        }

        double total = 0;
        double max = students.get(0).getMarks();
        double min = students.get(0).getMarks();

        for (Student s : students) {
            double m = s.getMarks();
            total += m;
            if (m > max) max = m;
            if (m < min) min = m;
        }

        double avg = total / students.size();

        JFrame f = new JFrame("Statistics");
        f.setSize(350, 250);
        f.setLocationRelativeTo(this);

        JPanel p = new JPanel(new GridLayout(4, 1, 10, 10));
        p.setBackground(new Color(245, 248, 255));

        p.add(card("Average: " + String.format("%.2f", avg)));
        p.add(card("Highest: " + max));
        p.add(card("Lowest: " + min));
        p.add(card("Total Students: " + students.size()));

        f.add(p);
        f.setVisible(true);
    }

    // ================= HELPERS =================
    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return l;
    }

    private JButton button(String t) {
        JButton b = new JButton(t);
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(30, 90, 160));
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return b;
    }

    private JPanel card(String text) {
        JPanel p = new JPanel();
        p.setBackground(new Color(30, 90, 160));

        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));

        p.add(l);
        return p;
    }

    private boolean validateForm() {

    return !nameField.getText().isEmpty()
            && !rollField.getText().isEmpty()
            && !subjectField.getText().isEmpty()
            && !marksField.getText().isEmpty();
}

    private void clear() {
        nameField.setText("");
        rollField.setText("");
        subjectField.setText("");
        marksField.setText("");
    }

    private Object[] toRow(Student s) {
        return new Object[]{
                s.getName(),
                s.getRollNo(),
                s.getSubject(),
                s.getMarks()
        };
    }

    private void msg(String m) {
        JOptionPane.showMessageDialog(this, m);
    }
}