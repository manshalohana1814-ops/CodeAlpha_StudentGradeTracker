package GUI;

public class Student {

    private String name;
    private String rollNo;
    private String subject;
    private double marks;

    public Student(String name, String rollNo, String subject, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.subject = subject;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public double getMarks() {
        return marks;
    }
} 
