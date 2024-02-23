package com.example.students_marks_recorder;

public class Exam {
    private int exam_id;
    private String examType;
    private String examDate;
    private int mark;
    public Exam(int exam_id, String examType, String examDate,int mark) {
        this.exam_id=exam_id;
        this.examType = examType;
        this.examDate = examDate;
        this.mark = mark;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}

