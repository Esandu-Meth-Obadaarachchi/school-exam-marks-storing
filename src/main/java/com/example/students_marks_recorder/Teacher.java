package com.example.students_marks_recorder;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String teacherId;
    private String Fname;

    private String Lname;
    private ArrayList<Subject> subjects = new ArrayList<>() ;
    private List<Student> students;

    private String password;


    public Teacher(String teacherId, String Fname,String Lname, ArrayList subjects, List<Student> students,String password) {
        this.teacherId = teacherId;
        this.Fname = Fname;
        this.Lname=Lname;
        this.subjects = subjects;
        this.students = students;
    }

    public Teacher(String  teacherId, String Fname, String Lname,ArrayList subject, String password) {
        this.teacherId = teacherId;
        this.Fname = Fname;
        this.Lname=Lname;
        this.subjects = subject;
        this.password=password;
    }

    // Getters and setters

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public String getPassword() {
        return password;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Method to add a mark to a student's subject
    public void addMark(int studentID, String subjectName, String examDate, String examType, int mark) {
        for (Student student : students) {
            if (student.getAdmissionNumber() == studentID) {
                student.addMark(subjectName, examDate, examType, mark);
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    // Method to update an existing mark
    public void updateMark(int studentID, String subjectName, String examDate, String examType, int newMark) {
        for (Student student : students) {
            if (student.getAdmissionNumber() == studentID) {
                student.updateMark(subjectName, examDate, examType, newMark);
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    // Method to view a student's results
    public void viewStudentResults(int studentID) {
        for (Student student : students) {
            if (student.getAdmissionNumber() == studentID) {
                student.printStudentResults();
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }
}

