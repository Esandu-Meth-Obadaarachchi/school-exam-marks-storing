package com.example.students_marks_recorder;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String fname;
    private String lname;
    private int admissionNumber;
    private String grade;
    private ArrayList<Subject> subjects;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(int admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student(String fname, String lname, int admissionNumber, String grade, ArrayList<Subject> subjects) {
        this.fname = fname;
        this.lname = lname;
        this.admissionNumber = admissionNumber;
        this.grade = grade;
        this.subjects = subjects;
    }

    // Getters and setters...



}
