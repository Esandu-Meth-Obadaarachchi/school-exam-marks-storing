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


}

