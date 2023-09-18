package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DAO.VendorDAO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class AddVendorController {

    @FXML
    private AnchorPane assetPane;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField loc;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField vendorName;

    @FXML
    void onSubmit(ActionEvent event) {
        Connection conn = ConnectionFactory.getInstance().open();

        String name = vendorName.getText();
        String contactNumber = contact.getText();
        String emailId = email.getText();
        String locationName = loc.getText();

        int success = new VendorDAO(conn).insertVendor(name, contactNumber, locationName, emailId);

        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        if(success != -1) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Vendor Added successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Vendor could not be added");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }
}
