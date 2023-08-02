package com.sattva.itassetmanagement.EmployeeUI;

import com.sattva.itdatabase.DAO.AllocationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SapReqController implements Initializable {
    @FXML
    private AnchorPane assetPane;
    @FXML
    private Label functionLabel;
    @FXML
    private TextArea functionText;
    @FXML
    private TextField processOwnerName;
    @FXML
    private ChoiceBox<String> sapChoice;
    @FXML
    private TextField sapIdField;
    @FXML
    private Label sapIdLabel;
    @FXML
    private Label processLabel;
    @FXML
    private Button submitBtn;

    private final String[] actions = {"User ID Creation", "User ID Deletion", "User ID Blocking/De-activating"};

    private String code, locationId;
    private Connection conn;

    public void setCode(String code) {
        this.code = code;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void onSubmit(ActionEvent e) {
        String action = sapChoice.getValue();
        String id = sapIdField.getText();
        String process = processOwnerName.getText();
        String function = functionText.getText();
        String date = LocalDate.now().toString();

        boolean success = new AllocationDAO(conn).requestSap(this.code, this.locationId, action, id, process, function,
                date);
        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("SAP Request placed successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("SAP Request could not be placed");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sapChoice.getItems().addAll(actions);
        sapChoice.setOnAction(this::onSapAction);
    }

    public void onSapAction(ActionEvent e) {
        functionText.setDisable(true);
        functionLabel.setDisable(true);
        sapIdField.setDisable(true);
        sapIdLabel.setDisable(true);
        processLabel.setDisable(true);
        processOwnerName.setDisable(true);
        functionText.setText("");
        String selected = sapChoice.getValue();
        if(selected.equals("User ID Creation")) {
            functionText.setDisable(false);
            functionLabel.setDisable(false);
            processLabel.setDisable(false);
            processOwnerName.setDisable(false);
        } else {
            sapIdLabel.setDisable(false);
            sapIdField.setDisable(false);
        }
    }
}
