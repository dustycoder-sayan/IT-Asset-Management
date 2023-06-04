module com.sattva.itassetmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.sattva.itassetmanagement to javafx.fxml;
    exports com.sattva.itassetmanagement;
}