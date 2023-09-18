package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DAO.AllocationDAO;
import com.sattva.itdatabase.DAO.AssetsDAO;
import com.sattva.itdatabase.DAO.DepartmentDAO;
import com.sattva.itdatabase.DAO.LocationDAO;
import com.sattva.itdatabase.DTO.EmployeeDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class AssetSubReqController {
    private final Connection conn = ConnectionFactory.getInstance().open();

    private String empCode, type, subType;
    private String assetNameSelected = null, modelSelected = null;
    private int allocationId;

    @FXML
    private ChoiceBox<String> assetName;

    @FXML
    private AnchorPane assetPane;

    @FXML
    private Text city;

    @FXML
    private Text code;

    @FXML
    private Text company;

    @FXML
    private Text contact;

    @FXML
    private Text dept;

    @FXML
    private Text desgn;

    @FXML
    private Text email;

    @FXML
    private Text loc;

    @FXML
    private ChoiceBox<String> model;

    @FXML
    private Text name;

    @FXML
    private ChoiceBox<String> serial;

    @FXML
    private Text space;

    @FXML
    private Button submitBtn;

    @FXML
    void onSubmit(ActionEvent event) {
        try {
            conn.setAutoCommit(false);
            String serialSelected = serial.getValue();
            boolean success = new AssetsDAO(conn).assetReleased(serialSelected, 1);
            final Stage stage = (Stage) assetPane.getScene().getWindow();
            Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
            if (success) {
                success = new AllocationDAO(conn).updateStatusAndSerial(allocationId, "accept", serialSelected);
                if(success) {
                    alertBox.setHeaderText("Successful");
                    alertBox.setContentText("Asset Request accepted successfully");
                    conn.commit();
                }
                else {
                    alertBox.setHeaderText("Unsuccessful");
                    alertBox.setContentText("Asset Request could not be accepted");
                    conn.rollback();
                }
            } else {
                alertBox.setHeaderText("Unsuccessful");
                alertBox.setContentText("Asset Request could not be accepted");
                conn.rollback();
            }
            if (alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ignored) {}
        }
        finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ignored) {}
        }
    }

    @FXML
    void onReject(ActionEvent event) {
        boolean success = new AllocationDAO(conn).updateStatusAndSerial(allocationId, "reject", modelSelected);
        final Stage stage = (Stage) assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Asset Request Rejected");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Reject Request could not be completed");
        }
        if (alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    public void setReqData(int allocationId, String code, String type, String subType) {
        this.allocationId = allocationId;
        this.empCode = code;
        this.type = type;
        this.subType = subType;
        populateData();
    }

    private void populateData() {
        EmployeeDTO empDetails = new Datasource(conn).getEmployeeDetails(empCode);
        code.setText(empCode);
        name.setText(empDetails.getName());
        company.setText(empDetails.getCompany());
        desgn.setText(empDetails.getDesignation());
        contact.setText(empDetails.getContact());
        email.setText(empDetails.getEmailId());

        String deptId = empDetails.getDeptId();
        String deptName = new DepartmentDAO(conn).getDepartmentName(deptId);
        String locationId = new DepartmentDAO(conn).getDeptLocation(deptId);

        dept.setText(deptName);

        String locationName = new LocationDAO(conn).getLocationName(locationId);
        String cityName = new LocationDAO(conn).getCityName(locationId);
        String spaceName = new LocationDAO(conn).getSpaceName(locationId);

        loc.setText(locationName);
        space.setText(spaceName);
        city.setText(cityName);

        assetName.getItems().addAll(new AssetsDAO(conn).assetNames(type, subType));
        assetName.setOnAction(this::setModel);
    }

    public void setModel(ActionEvent e) {
        if(!model.getItems().isEmpty())
            model.getItems().removeAll();
        assetNameSelected = assetName.getValue();
        model.getItems().addAll(new AssetsDAO(conn).assetModels(type, subType, assetNameSelected));
        model.setOnAction(this::setSerial);
    }

    private void setSerial(ActionEvent event) {
        if(!serial.getItems().isEmpty())
            serial.getItems().removeAll();
        modelSelected = model.getValue();
        serial.getItems().addAll(new AssetsDAO(conn).assetSerials(type, subType, assetNameSelected, modelSelected));
    }
}
