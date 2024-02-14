package com.example.students_marks_recorder;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private List<Marks> marksList;

    public Subject(String name) {
        this.name = name;
        this.marksList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Marks> getMarksList() {
        return marksList;
    }

    public void setMarksList(List<Marks> marksList) {
        this.marksList = marksList;
    }

    // Method to add a mark
    public void addMark(String examDate, String examType, int mark) {
        marksList.add(new Marks(examType, examDate, mark));
    }

    // Method to update an existing mark
    public void updateMark(String examDate, String examType, int newMark) {
        for (Marks mark : marksList) {
            if (mark.getExamDate().equals(examDate) && mark.getExamType().equals(examType)) {
                mark.setMark(newMark);
                return;
            }
        }
        System.out.println("Mark for exam type " + examType + " and exam date " + examDate + " not found.");
    }
}
