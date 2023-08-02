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

public class AssetReqController implements Initializable {

    @FXML
    private ChoiceBox<String> assetChoice;
    @FXML
    private TextArea dataCardJustField;
    @FXML
    private TextField dataCardNumField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField newEmailField;
    @FXML
    private ChoiceBox<String> subAssetChoice;
    @FXML
    private Button submitBtn;
    @FXML
    private Label dataCardJustLabel;
    @FXML
    private Label dataCardNumLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label emailIdLabel;
    @FXML
    private AnchorPane assetPane;

    private final String[] assetTypes = {"System", "Printer", "Accessories", "Email", "Admin Requirement"};
    private final String[] systemSubAsset = {"Laptop", "Desktop"};
    private final String[] printerSubAsset = {"Sharing", "Non-Sharing", "Not-Applicable"};
    private final String[] accessorySubAsset = {"Back Pack", "HDD", "Pen Drive", "Wireless Keyboard", "Wireless Mouse",
                                    "Cables"};
    private final String[] emailSubAsset = {"New", "Existing"};
    private final String[] adminReqSubAsset = {"Data Card"};

    private String assetSelected, subAssetSelected;
    private int choice = 0;

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
        String type = assetChoice.getValue(), subType = subAssetChoice.getValue();
        if(type == null || subType == null) {
            final Stage stage = (Stage)assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Asset Request could not be placed");
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
            return;
        }

        String locationId = this.locationId, date = LocalDate.now().toString();
        int quantity = 1, duration=0;
        String newEmail = newEmailField.getText();
        String datacardNum = dataCardNumField.getText();
        String datacardJust = dataCardJustField.getText();

        if(!durationField.getText().equals(""))
            duration = Integer.parseInt(durationField.getText());

        boolean success = new AllocationDAO(conn).
                requestAllocation(this.code, type, subType, quantity, newEmail, datacardNum,
                        datacardJust, locationId, date, duration);

        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Asset Request placed successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Asset Request could not be placed");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assetChoice.getItems().addAll(assetTypes);
        assetChoice.setOnAction(this::setSubAssetChoice);
    }

    public void setSubAssetChoice(ActionEvent e) {
        switch(choice) {
            case 1:
                subAssetChoice.getItems().removeAll(systemSubAsset);
                break;
            case 2:
                subAssetChoice.getItems().removeAll(printerSubAsset);
                break;
            case 3:
                subAssetChoice.getItems().removeAll(accessorySubAsset);
                break;
            case 4:
                subAssetChoice.getItems().removeAll(emailSubAsset);
                break;
            case 5:
                subAssetChoice.getItems().removeAll(adminReqSubAsset);
                break;
        }

        assetSelected = assetChoice.getValue();
        switch(assetSelected) {
            case "System":
                subAssetChoice.getItems().addAll(systemSubAsset);
                choice=1;
                break;
            case "Printer":
                subAssetChoice.getItems().addAll(printerSubAsset);
                choice=2;
                break;
            case "Accessories":
                subAssetChoice.getItems().addAll(accessorySubAsset);
                choice=3;
                break;
            case "Email":
                subAssetChoice.getItems().addAll(emailSubAsset);
                choice=4;
                break;
            case "Admin Requirement":
                subAssetChoice.getItems().addAll(adminReqSubAsset);
                choice=5;
                break;
        }
        subAssetChoice.setOnAction(this::subAssetAction);
    }

    public void subAssetAction(ActionEvent e) {
        durationLabel.setDisable(true);
        durationField.setDisable(true);
        durationField.setText("");
        emailIdLabel.setDisable(true);
        newEmailField.setDisable(true);
        newEmailField.setText("");
        dataCardJustLabel.setDisable(true);
        dataCardNumLabel.setDisable(true);
        dataCardNumField.setDisable(true);
        dataCardJustField.setText("");
        dataCardJustField.setText("");
        dataCardJustField.setDisable(true);
        switch(choice) {
            case 1:
            case 3:
                durationLabel.setDisable(false);
                durationField.setDisable(false);
                break;
            case 4:
                emailIdLabel.setDisable(false);
                newEmailField.setDisable(false);
                break;
            case 5:
                dataCardJustLabel.setDisable(false);
                dataCardNumLabel.setDisable(false);
                dataCardNumField.setDisable(false);
                dataCardJustField.setDisable(false);
                break;
        }
    }
}
