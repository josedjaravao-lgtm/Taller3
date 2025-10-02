module com.example.actividad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;


    opens com.example.actividad3 to javafx.fxml;
    exports com.example.actividad3;
}