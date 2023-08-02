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

public class VpnReqController implements Initializable {
    @FXML
    private Button submitBtn;
    @FXML
    private ChoiceBox<String> vpnActionChoice;
    @FXML
    private TextArea vpnPurpose;
    @FXML
    private TextArea vpnResources;
    @FXML
    private Label vpnPurposeLabel;
    @FXML
    private Label vpnResourcesLabel;
    @FXML
    private Label vpnIdLabel;
    @FXML
    private TextField durationField;
    @FXML
    private TextField vpnIdField;
    @FXML
    private Label datePickerLabel;
    @FXML
    private AnchorPane assetPane;

    private String[] vpnActions = {"Create a VPN ID", "Delete a VPN ID", "Modify a VPN ID", "Renew Existing VPN ID"};

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vpnActionChoice.getItems().addAll(vpnActions);
        vpnActionChoice.setOnAction(this::enable);
    }

    public void enable(ActionEvent e) {
        vpnPurpose.setDisable(true);
        vpnPurpose.setText("");
        vpnResources.setDisable(true);
        vpnResources.setText("");
        vpnPurposeLabel.setDisable(true);
        vpnResourcesLabel.setDisable(true);
        datePickerLabel.setDisable(true);
        durationField.setDisable(true);
        vpnIdLabel.setDisable(true);
        vpnIdField.setDisable(true);
        String choiceMade = vpnActionChoice.getValue();
        if(choiceMade.equals("Create a VPN ID")) {
            vpnPurpose.setDisable(false);
            vpnResources.setDisable(false);
            vpnPurposeLabel.setDisable(false);
            vpnResourcesLabel.setDisable(false);
            datePickerLabel.setDisable(false);
            durationField.setDisable(false);
        } else {
            vpnIdLabel.setDisable(false);
            vpnIdField.setDisable(false);
        }
    }

    public void onSubmit(ActionEvent e) {
        String vpnAction = vpnActionChoice.getValue(), id = vpnIdField.getText(), purpose = vpnPurpose.getText(),
                application = vpnResources.getText();
        int duration = 0;

        if(!durationField.getText().equals(""))
            duration = Integer.parseInt(durationField.getText());

        String date = LocalDate.now().toString();
        boolean success = new AllocationDAO(conn).requestVpn(this.code, this.locationId, vpnAction, id, purpose,
                application, duration, date);
        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("VPN Request placed successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("VPN Request could not be placed");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }
}
