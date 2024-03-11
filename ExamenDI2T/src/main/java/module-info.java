module com.example.examendi2t {
    requires javafx.controls;
    requires javafx.fxml;
    requires jasperreports;
    requires java.sql;
    requires javafx.swing;

    opens utils;
    opens clase;


    opens com.example.examendi2t to javafx.fxml;
    exports com.example.examendi2t;
}