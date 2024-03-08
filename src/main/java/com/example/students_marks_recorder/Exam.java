package com.example.students_marks_recorder;

public class Exam {
    private int exam_id;
    private String examType;
    private String examDate;

    public Exam(int exam_id, String examType, String examDate) {
        this.exam_id=exam_id;
        this.examType = examType;
        this.examDate = examDate;

    }

    // Getters and setters
    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

}

