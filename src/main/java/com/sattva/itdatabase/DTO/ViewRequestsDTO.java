package com.sattva.itdatabase.DTO;

import com.sattva.itassetmanagement.AdminUI.AssetSubReqController;
import com.sattva.itassetmanagement.AdminUI.DashboardAdminController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewRequestsDTO {
    private String code, location, subType, type;
    int allocationId;
    private Button button;

    public ViewRequestsDTO(String code, String location, String subType, String type, int allocationId) {
        this.code = code;
        this.location = location;
        this.subType = subType;
        this.type = type;
        this.allocationId = allocationId;
        this.button = new Button("View Request");

        this.button.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("asset-sub-req.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Asset Request");
            AssetSubReqController controller = fxmlLoader.getController();
            controller.setReqData(allocationId, code, type, subType);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getLocation() {
        return location;
    }

    public String getSubType() {
        return subType;
    }

    public String getType() {
        return type;
    }
}
