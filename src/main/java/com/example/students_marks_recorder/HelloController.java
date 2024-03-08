package com.example.students_marks_recorder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private TextField exam;

    @FXML
    private TextField subject;
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
    @FXML
    private Button addMarks;
    @FXML
    private TextField examDateField;

    @FXML
    private TextField examNameField;

    @FXML
    private TextField examTimeField;

    @FXML
    private Button addNewExamButton;

    @FXML
    private Button addNewSubjectButton;

    @FXML
    private Button addStudentsButton;

    @FXML
    private Button backButtonCDD;

    @FXML
    private TextField confirmText;

    @FXML
    private TextField confirmText1;

    @FXML
    private AnchorPane contentOne;

    @FXML
    private AnchorPane contentTwo;

    @FXML
    private Label deletingStatus;

    @FXML
    private Label deletingStatus1;

    @FXML
    private ComboBox<?> examComboBox;

    @FXML
    private Button generateReportsButton;

    @FXML
    private TextField newAdvisorId;

    @FXML
    private TextField newAdvisorId1;

    @FXML
    private TextField newAdvisorId11;

    @FXML
    private ComboBox subjectComboBox1;

    @FXML
    private Button updateMarksButton;

    @FXML
    private Label welcomeClub;
    //==========================================================================================
    String subjects ;
    ArrayList<Subject> subjectsNeeded = new ArrayList<>();
    String teacherID;
    String teacherFName;
    String teacherLName;
    String teacherPassword;
    String fileName;

    ArrayList allSubjects;
    ArrayList allExams;
    ArrayList<String> subsName;
    Stage stage;
    //==========================================================================================

//==========================================================================================
    //the method to save the new TEACHER to the database

    public void stageLoader(ActionEvent event, String fileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //==========================================================================================
    //SIGNUP PAGE STUFF
    @FXML
    public void backToLoginPage(ActionEvent event) throws IOException {
        System.out.println("heelo");

    }

    public boolean isInputNotNull(String input){
        if ( input== null || input.equals("")){
            return false;
        }
        return true;
    }
    //==========================================================================================
    public boolean teacherInputValidator() throws Exception {
        if (!isInputNotNull(teacherID)){
            TidErrorText.setText("pls enter the Teacher Id");
            return false;
        }
        if (!isInputNotNull(subjects)){
            idErrorText.setText("pls enter the subjects");
            return false;
        }
        if (!isInputNotNull(teacherFName)){
            fNameErrorText.setText("pls enter the First name");
            return false;
        }
        if (!isInputNotNull(teacherLName)){
            lNameErrorText.setText("pls enter the Last name");
            return false;
        }
        if (!isInputNotNull(teacherPassword)){
            TidErrorText.setText("pls enter the Password");
            return false;
        }
        if (teacherPassword.length() < 4) {
            pwErrorText.setText("Password must be at least 6 characters long");
            return false;
        }
        //checking whether the teacher id is correct
        if (!isTeacherIdValid(teacherID)){
            TidErrorText.setText("already exists");
            return false ;
        }
        return true;
    }
    //==========================================================================================
    @FXML
    public void onRequestToJoinButtonClick(ActionEvent event) throws Exception {
        connections = getConnection();
        teacherID =  TeacherIdTextField.getText();
        teacherFName = stdFirstNameTextField.getText();
        teacherLName = stdLastNameTextField.getText();
        teacherPassword = stdPasswordTextField.getText();
        subjects = stdIdTextField.getText();

        for (String subjectName : subjects.split(" ")) {
            Subject subject = new Subject(subjectName);
            subjectsNeeded.add(subject);
        }

        // Validation done
        if (teacherInputValidator()) {
            // Finally creating the object
            Teacher teacher = new Teacher(teacherID, teacherFName, teacherLName, subjectsNeeded, teacherPassword);
            savingNewTeacher(teacher);
            System.out.println("Teacher saved successfully.");
            stageLoader(event, "Fxml Files/MainLoginPage.fxml");
            TidErrorText.setText("");
            pwErrorText.setText("");
            lNameErrorText.setText("");
            fNameErrorText.setText("");
        }
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException, SQLException {
        stageLoader(event, "Fxml Files/teacherSignUp.fxml");
        connections = getConnection();

    }
    public void savingNewTeacher(Teacher teacher) throws SQLException {
        String insertTeacherQuery = "INSERT INTO teachers (teacher_id, fname, lname, password) VALUES (?, ?, ?, ?)";
        String insertSubjectQuery = "INSERT INTO subjects (name, teacher_id) VALUES (?, ?)";

        try (PreparedStatement insertTeacherStatement = this.connections.prepareStatement(insertTeacherQuery);
             PreparedStatement insertSubjectStatement = this.connections.prepareStatement(insertSubjectQuery)) {

            // Insert teacher details
            insertTeacherStatement.setString(1, teacher.getTeacherId());
            insertTeacherStatement.setString(2, teacher.getFname());
            insertTeacherStatement.setString(3, teacher.getLname());
            insertTeacherStatement.setString(4, teacher.getPassword());
            insertTeacherStatement.executeUpdate();

            // Insert subjects
            for (Subject subject : teacher.getSubjects()) {
                insertSubjectStatement.setString(1, subject.getName());
                insertSubjectStatement.setString(2, teacher.getTeacherId());
                insertSubjectStatement.executeUpdate();
            }

            System.out.println("Teacher and subjects saved successfully.");
        }
    }


    public boolean isTeacherIdValid(String teacherId) throws SQLException {
        String query = "SELECT * FROM teachers WHERE teacher_id = ?";

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            statement.setString(1, teacherId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next(); // Returns true if the teacher ID does not exist, false otherwise
            }
        }
    }

    //==========================================================================================
    //LOGIN STUFF
    @FXML
    public void Login(ActionEvent event) throws SQLException, IOException {
        connections = getConnection();
        System.out.println("heelo");
        teacherID = teacherIdTextField.getText();
        teacherPassword = passwordTextField.getText();

        if (isLoginValid(teacherID, teacherPassword)){  //1.1.  calling the method to check student validity
            stageLoader(event, "Fxml Files/PressClub.fxml");

        }
        stdNameErrorText.setText("Incorrect Teacher ID/ Password");

    }

    public boolean isLoginValid(String teacherId, String password) throws SQLException {
        String query = "SELECT * FROM teachers WHERE teacher_id = ? AND password = ?";

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            statement.setString(1, teacherId);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if the combination is valid, false otherwise
            }
        }
    }
    //==========================================================================================

//==========================================================================================
    public String examName;
    public String examDate;
    public String examTime;

    @FXML
    public void addNewExamButtonClick(ActionEvent event) {
        examName = examNameField.getText();
        examDate = examDateField.getText();
        examTime = examTimeField.getText();


    }

    @FXML
    public void addMarksClick(ActionEvent event) {

    }



    @FXML
    public void addNewSubjectButtonClick(ActionEvent event) {

    }

    @FXML
    public void addStudentsButtonClick(ActionEvent event) {

    }

    @FXML
    public void backButtonCDD(ActionEvent event) {

    }

    @FXML
    public void onGenerateReportButtonClicked(ActionEvent event) {

    }

    @FXML
    public void updateMarksButtonClick(ActionEvent event) {

    }

}
