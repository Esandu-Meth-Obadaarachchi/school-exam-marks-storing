package com.example.students_marks_recorder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML //hello boi //git finally working
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}