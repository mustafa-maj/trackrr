module com.example.app {
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
}