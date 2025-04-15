module com.example.module06_csc311 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.module06_csc311 to javafx.fxml;
    exports com.example.module06_csc311;
}