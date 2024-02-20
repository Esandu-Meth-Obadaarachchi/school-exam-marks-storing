package com.example.students_marks_recorder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private Button RegisterButton;
    @FXML
    private Ellipse ellipseTwo;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private Text passwordErrorText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Text stdNameErrorText;

    @FXML
    private TextField teacherIdTextField;

    @FXML
    private Button teacherLoginButton;

    @FXML
    private Text titleText;
    @FXML
    private TextField TeacherIdTextField;

    @FXML
    private Text TidErrorText;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane content;

    @FXML
    private Text dOBErrorText;

    @FXML
    private Ellipse ellipseOne;


    @FXML
    private Text fNameErrorText;

    @FXML
    private Text idErrorText;

    @FXML
    private Text lNameErrorText;

    @FXML
    private Text pwErrorText;

    @FXML
    private Button requestToJoinButton;

    @FXML
    private DatePicker stdDOBDatePicker;

    @FXML
    private TextField stdFirstNameTextField;

    @FXML
    private TextField stdIdTextField;

    @FXML
    private TextField stdLastNameTextField;

    @FXML
    private TextField stdPasswordTextField;
    String subjects ;
    ArrayList<Subject> subjectsNeeded = new ArrayList<>();
    String teacherID;
    String advisorFName;
    String advisorLName;
    String stdPassword;

    public boolean isInputNotNull(String input){
        if ( input== null || input.equals("")){
            return false;
        }
        return true;
    }

    public boolean isTeacherIdValid(String teacherId) throws SQLException {
        String query = "SELECT * FROM Teacher WHERE teacherId = ?";

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            statement.setString(1, teacherId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if the teacher ID exists, false otherwise
            }
        }
    }
    public boolean advisorInputValidator() throws Exception {
        if (!isInputNotNull(teacherID)){
            TidErrorText.setText("pls enter the Teacher Id");
            return false;
        }
        if (!isInputNotNull(subjects)){
            idErrorText.setText("pls enter the subjects");
            return false;
        }
        if (!isInputNotNull(advisorFName)){
            fNameErrorText.setText("pls enter the First name");
            return false;
        }
        if (!isInputNotNull(advisorLName)){
            lNameErrorText.setText("pls enter the Last name");
            return false;
        }
        if (!isInputNotNull(stdPassword)){
            TidErrorText.setText("pls enter the Password");
            return false;
        }
        if (stdPassword.length() < 6) {
            pwErrorText.setText("Password must be at least 6 characters long");
            return false;
        }
        //checking whether the teacher id is correct
        if (!isTeacherIdValid(teacherID)){
            TidErrorText.setText("Invalid id entered, pls re try");
            return false ;
        }
        return true;
    }

    //the method to save the new advisor to the database
    public void savingNewTeacher(Teacher teacher) throws SQLException {
        String insertAdvisorQuery = "INSERT INTO ClubAdvisor (id, firstName, lastName, dateOfBirth, password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement insertAdvisorStatement = this.connections.prepareStatement(insertAdvisorQuery)) {
            insertAdvisorStatement.setString(1, newAdvisor.getId());
            insertAdvisorStatement.setString(2, newAdvisor.getFirstName());
            insertAdvisorStatement.setString(3, newAdvisor.getLastName());
            insertAdvisorStatement.setString(4, newAdvisor.getDateOfBirth());
            insertAdvisorStatement.setString(5, newAdvisor.getPassword());

            insertAdvisorStatement.executeUpdate();
        }
    }
    @FXML
    public void backToLoginPage(ActionEvent event) {
        System.out.println("heelo");
    }
    @FXML
    public void onRequestToJoinButtonClick(ActionEvent event) throws Exception {

        teacherID =  TeacherIdTextField.getText();
        subjects = stdIdTextField.getText();
        advisorFName = stdFirstNameTextField.getText();
        advisorLName = stdLastNameTextField.getText();
        stdPassword = stdPasswordTextField.getText();

        for(int i= 1;i<subjects.split(" ").length;i++) {
            Subject subject = new Subject(subjects.split(" ")[i]);
            subjectsNeeded.add(subject);
        }
        //validation done
        if (advisorInputValidator()) {
            //finally creating the object
            Teacher teacher = new Teacher(teacherID,advisorFName,advisorLName,subjectsNeeded,stdPassword);
            System.out.println("done");
            TidErrorText.setText("");
            pwErrorText.setText("");
            lNameErrorText.setText("");
            fNameErrorText.setText("");

        }
    }
    @FXML
    public void Login(ActionEvent event) {

    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {

    }

}
