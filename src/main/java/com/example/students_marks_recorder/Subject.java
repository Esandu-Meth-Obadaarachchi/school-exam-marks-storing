package com.example.students_marks_recorder;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private ArrayList<Exam> examsList;
    private String grade;
    public Subject(String name, ArrayList<Exam> examsList,String grade ) {
        this.name = name;
        this.examsList = examsList;
        this.grade=grade;
    }

    public Subject(String name) {
        this.name = name;
        this.examsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Exam> getMarksList() {
        return examsList;
    }


}
