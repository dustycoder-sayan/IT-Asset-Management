package com.sattva.itdatabase.DTO;

import com.sattva.itassetmanagement.AdminUI.AssetSubReqController;
import com.sattva.itassetmanagement.AdminUI.ClearanceSubReqController;
import com.sattva.itassetmanagement.AdminUI.DashboardAdminController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewClearancesDTO {
    private String code, location, subType, type, serial;
    int allocationId;
    private Button button;

    public ViewClearancesDTO(String code, String location, String subType, String type, int allocationId, String serial) {
        this.code = code;
        this.location = location;
        this.subType = subType;
        this.type = type;
        this.allocationId = allocationId;
        this.serial = serial;
        this.button = new Button("View Request");

        this.button.setOnAction(event -> {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("clearance-sub-req.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Clearance Request");
            ClearanceSubReqController controller = fxmlLoader.getController();
            controller.setReqData(allocationId, code, type, subType, serial);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        });
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public String getSerial() {
        return serial;
    }

    public int getAllocationId() {
        return allocationId;
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
