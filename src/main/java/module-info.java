module com.sattva.itassetmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.sattva.itassetmanagement to javafx.fxml;
    exports com.sattva.itassetmanagement;

    opens com.sattva.itassetmanagement.EmployeeUI to javafx.fxml;
    exports com.sattva.itassetmanagement.EmployeeUI;

    opens com.sattva.itassetmanagement.AdminUI to javafx.fxml;
    exports com.sattva.itassetmanagement.AdminUI;

    opens com.sattva.itdatabase.DTO to javafx.fxml;
    exports com.sattva.itdatabase.DTO;
}