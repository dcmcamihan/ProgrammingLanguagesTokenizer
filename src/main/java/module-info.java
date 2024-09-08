module com.example.programminglanguagestokenizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programminglanguagestokenizer to javafx.fxml;
    exports com.example.programminglanguagestokenizer;
}