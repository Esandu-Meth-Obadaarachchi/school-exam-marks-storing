package com.example.students_marks_recorder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class HelloController {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/school_marks_management";
    private static final String USER = "root";
    private static final String PASSWORD = "esandu12345";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Registering the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Creating and returning a connection to the database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Log or handle the ClassNotFoundException appropriately
            throw new SQLException("MySQL JDBC driver not found", e);
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = getConnection();
            // Do something with the connection...
        } catch (SQLException e) {
            // Log or handle the SQLException appropriately
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Log or handle the SQLException appropriately
                e.printStackTrace();
            }
        }
    }
    private Connection connections;
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
    String fileName;
    Stage stage;
    public boolean isInputNotNull(String input){
        if ( input== null || input.equals("")){
            return false;
        }
        return true;
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

    //the method to save the new TEACHER to the database

    public void stageLoader(ActionEvent event, String fileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToLoginPage(ActionEvent event) {
        System.out.println("heelo");
    }

    @FXML
    public void onRequestToJoinButtonClick(ActionEvent event) throws Exception {

        teacherID =  TeacherIdTextField.getText();
        advisorFName = stdFirstNameTextField.getText();
        advisorLName = stdLastNameTextField.getText();
        stdPassword = stdPasswordTextField.getText();
        subjects = stdIdTextField.getText();

        for (String subjectName : subjects.split(" ")) {
            Subject subject = new Subject(subjectName);
            subjectsNeeded.add(subject);
        }

        // Validation done
        if (advisorInputValidator()) {
            // Finally creating the object
            Teacher teacher = new Teacher(teacherID, advisorFName, advisorLName, subjectsNeeded, stdPassword);
            savingNewTeacher(teacher);
            System.out.println("Teacher saved successfully.");

            TidErrorText.setText("");
            pwErrorText.setText("");
            lNameErrorText.setText("");
            fNameErrorText.setText("");
        }
    }

    @FXML
    public void Login(ActionEvent event) throws SQLException {
        connections = getConnection();
        System.out.println("heelo");
    }



    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        stageLoader(event, "teacherSignUp.fxml");
    }

    public boolean isTeacherValid(String stdId, String stdPassword) throws Exception{
        Statement st = connections.createStatement();
        String query = "select * from student where id = '"+stdId+"'";
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            if(stdPassword.equals(rs.getString("password"))){
                return true;
            }
        }
        return false;
    }

    public void savingNewTeacher(Teacher teacher) throws SQLException {
        String insertTeacherQuery = "INSERT INTO teachers (teacher_id, fname, lname, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement insertTeacherStatement = this.connections.prepareStatement(insertTeacherQuery)) {
            insertTeacherStatement.setString(1, teacher.getTeacherId());
            insertTeacherStatement.setString(2, teacher.getFname());
            insertTeacherStatement.setString(3, teacher.getLname());
            insertTeacherStatement.setString(4, teacher.getPassword());

            insertTeacherStatement.executeUpdate();
        }
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
}
