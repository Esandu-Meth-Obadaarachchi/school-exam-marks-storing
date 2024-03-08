package com.example.students_marks_recorder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class MainLoginController {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/school_marks_management";
    private static final String USER = "root";
    private static final String PASSWORD = "esandu12345";

    public MainLoginController() throws SQLException {
    }

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
    @FXML
    private Button RegisterButton;

    @FXML
    private Ellipse ellipseOne;

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

    private ObservableList<String> hello = FXCollections.observableArrayList();
    public Connection connections = getConnection();
    ArrayList<Subject> subjectsNeeded = new ArrayList<>();
    String teacherID;
    String teacherFName;
    String teacherLName;
    String teacherPassword;

    Stage stage;
    @FXML
    public void onRegisterButtonClick(ActionEvent event) throws IOException, SQLException {
        stageLoader(event, "Fxml Files/teacherSignUp.fxml");
        connections = getConnection();

    }
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
    @FXML
    public void Login(ActionEvent event) throws SQLException, IOException {
        try {
            connections = getConnection();
            System.out.println("heelo");
            teacherID = teacherIdTextField.getText();
            teacherPassword = passwordTextField.getText();

            if (isLoginValid(teacherID, teacherPassword)) {
                stageLoader(event, "Fxml Files/PressClub.fxml");
            } else {
                stdNameErrorText.setText("Incorrect Teacher ID/Password");
            }
        } catch (SQLException e) {
            // Handle SQLException appropriately
            e.printStackTrace();
            stdNameErrorText.setText("Error connecting to the database");
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
}


//======================================================================================================================


    public ArrayList<Student> getStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        allSubjects = getSubjectsByTeacherId(teacherID);

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Retrieve student details from the result set
                int admissionNumber = resultSet.getInt("admission_number");
                String fname = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String grade = resultSet.getString("grade");

                // Create a Student object with the retrieved data
                Student student = new Student(fname, lname, admissionNumber, grade, new ArrayList<>()); // Assuming Student constructor accepts fname, lname, admissionNumber, grade, and an empty ArrayList of subjects
                students.add(student);
            }
        }

        return students;
    }

    // Retrieve subjects by teacher_id
    public ArrayList<Subject> getSubjectsByTeacherId(String teacherId) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects WHERE teacher_id = ?";

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            statement.setString(1, teacherId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Retrieve subject details from the result set
                String name = resultSet.getString("name");
                String grade = resultSet.getString("grade");

                // Retrieve exams for the current subject
                //ArrayList<Exam> exams = getExamsForSubject(name); // Assuming you have a method to get exams by subject name

                // Create a Subject object with the retrieved data and exams
                //Subject subject = new Subject(name, exams,grade); // Assuming Subject constructor accepts name, grade, and exams
                //subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exceptio
        }

        return subjects;
    }


    public ObservableList<String> getSubjectNamesByTeacherId(String teacherId) {
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        String query = "SELECT name FROM subjects WHERE teacher_id = ?";

        try (PreparedStatement statement = connections.prepareStatement(query)) {
            statement.setString(1, teacherId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                subjectNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }

        return subjectNames;
    }

    // Method to retrieve exams for a given subject name
//    private ArrayList<Exam> getExamsForSubject(String subjectName) throws SQLException {
//        ArrayList<Exam> exams = new ArrayList<>();
//        String query = "SELECT * FROM exams WHERE subject_name = ?";
//
//        try (PreparedStatement statement = connections.prepareStatement(query)) {
//            statement.setString(1, subjectName);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                // Retrieve exam details from the result set
//                int examId = resultSet.getInt("exam_id");
//                String examType = resultSet.getString("exam_type");
//                String examDate = resultSet.getString("exam_date");
//                int mark = resultSet.getInt("mark");
//
//                // Create an Exam object with the retrieved data
//                Exam exam = new Exam(examId, examType, examDate, mark); // Assuming Exam constructor accepts examId, examType, examDate, and mark
//                exams.add(exam);
//            }
//        }
//        return exams;
  }