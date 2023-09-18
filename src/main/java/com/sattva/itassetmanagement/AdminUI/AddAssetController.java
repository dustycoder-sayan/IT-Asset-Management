package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DAO.AssetsDAO;
import com.sattva.itdatabase.DAO.VendorDAO;
import com.sattva.itdatabase.DTO.VendorDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAssetController implements Initializable {

    @FXML
    private ChoiceBox<String> assetChoice;

    @FXML
    private AnchorPane assetPane;

    @FXML
    private TextField modelText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField serialText;

    @FXML
    private TextField stockText;

    @FXML
    private ChoiceBox<String> subAssetChoice;

    @FXML
    private Button submitBtn;

    @FXML
    private ChoiceBox<String> vendorChoice;

    private final Connection conn = ConnectionFactory.getInstance().open();

    private final String[] assetTypes = {"System", "Printer", "Accessories", "Admin Requirement"};
    private final String[] systemSubAsset = {"Laptop", "Desktop"};
    private final String[] printerSubAsset = {"Sharing", "Non-Sharing", "Not-Applicable"};
    private final String[] accessorySubAsset = {"Back Pack", "HDD", "Pen Drive", "Wireless Keyboard", "Wireless Mouse",
            "Cables"};
    private final String[] adminReqSubAsset = {"Data Card"};

    private final ArrayList<VendorDTO> vendors = new Datasource(conn).getAllVendors();

    private String[] vendorNames = new String[vendors.size()];

    private String assetSelected, subAssetSelected;
    private int choice = 0;

    @FXML
    void onSubmit(ActionEvent event) {
        String type = assetChoice.getValue(), subType = subAssetChoice.getValue();
        if(type == null || subType == null) {
            final Stage stage = (Stage)assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Please select Asset type and subtype");
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
            return;
        }

        String serial = serialText.getText(), name = nameText.getText(), model = modelText.getText();
        String vendor = vendorChoice.getValue();
        int vendorId = new VendorDAO(conn).getVendorId(vendor);
        int stock;
        try {
            stock = Integer.parseInt(stockText.getText());
        } catch (Exception e) {
            final Stage stage = (Stage)assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Stock should be a Number");
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
            return;
        }

        AssetsDAO assets = new AssetsDAO(conn);
        if(assets.assetExists(serial)) {
            boolean success = assets.updateInstock(serial, stock);
            final Stage stage = (Stage)assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            if(success) {
                alertBox.setHeaderText("Successful");
                alertBox.setContentText("Asset Added Successfully");
            } else {
                alertBox.setHeaderText("Unsuccessful");
                alertBox.setContentText("Asset could not be Added Successfully");
            }
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
        } else {
            boolean success;
            if(type.equals("System")) {
                success = assets.insertSystemAsset(serial, type, subType, name, model, "Windows 11", stock
                        , 0, vendorId);
            } else {
                success = assets.insertNotSystem(serial, type, subType, name, model, stock, 0, vendorId);
            }
            final Stage stage = (Stage)assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            if(success) {
                alertBox.setHeaderText("Successful");
                alertBox.setContentText("Asset Added Successfully");
            } else {
                alertBox.setHeaderText("Unsuccessful");
                alertBox.setContentText("Asset could not be Added Successfully");
            }
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<vendors.size();i++) {
            vendorNames[i] = vendors.get(i).getName();
        }
        vendorChoice.getItems().addAll(vendorNames);
        assetChoice.getItems().addAll(assetTypes);
        assetChoice.setOnAction(this::setSubAssetChoice);
    }

    private void setSubAssetChoice(ActionEvent e) {
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
            case "Admin Requirement":
                subAssetChoice.getItems().addAll(adminReqSubAsset);
                choice=5;
                break;
        }
    }
}
