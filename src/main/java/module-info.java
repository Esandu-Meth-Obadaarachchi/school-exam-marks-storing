module com.example.students_marks_recorder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.students_marks_recorder to javafx.fxml;
    exports com.example.students_marks_recorder;
}