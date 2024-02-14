package com.example.students_marks_recorder;

import java.util.List;

public class Teacher {
    private int teacherId;
    private String name;
    private String subject;
    private List<Student> students;

    public Teacher(int teacherId, String name, String subject, List<Student> students) {
        this.teacherId = teacherId;
        this.name = name;
        this.subject = subject;
        this.students = students;
    }

    // Getters and setters
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

