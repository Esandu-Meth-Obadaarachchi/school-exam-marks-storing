package com.example.students_marks_recorder;

import java.util.List;

public class Student {
    private String fname;
    private String lname;
    private int admissionNumber;
    private String grade;
    private List<Subject> subjects;

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

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student(String fname, String lname, int admissionNumber, String grade, List<Subject> subjects) {
        this.fname = fname;
        this.lname = lname;
        this.admissionNumber = admissionNumber;
        this.grade = grade;
        this.subjects = subjects;
    }

    // Getters and setters...

    // Method to add a mark to a subject
    public void addMark(String subjectName, String examDate, String examType, int mark) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectName)) {
                subject.addMark(examDate, examType, mark);
                return;
            }
        }
        System.out.println("Subject " + subjectName + " not found for student " + fname + " " + lname);
    }

    // Method to update an existing mark
    public void updateMark(String subjectName, String examDate, String examType, int newMark) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(subjectName)) {
                subject.updateMark(examDate, examType, newMark);
                return;
            }
        }
        System.out.println("Subject " + subjectName + " not found for student " + fname + " " + lname);
    }

    // Method to print student results
    public void printStudentResults() {
        System.out.println("Student: " + fname + " " + lname);
        for (Subject subject : subjects) {
            System.out.println("Subject: " + subject.getName());
            for (Marks mark : subject.getMarksList()) {
                System.out.println("Exam Type: " + mark.getExamType() + ", Exam Date: " + mark.getExamDate() + ", Mark: " + mark.getMark());
            }
        }
    }
}
