package com.example.students_marks_recorder;

public class Mark {
    private String studentId;
    private String examId;

    private int marks;

    public Mark(String studentId, String examId, int marks) {
        this.studentId = studentId;
        this.examId = examId;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
