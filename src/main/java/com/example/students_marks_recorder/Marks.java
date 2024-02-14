package com.example.students_marks_recorder;

class Marks {
    private String examType;
    private String examDate;
    private int mark;

    public Marks(String examType, String examDate, int mark) {
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

