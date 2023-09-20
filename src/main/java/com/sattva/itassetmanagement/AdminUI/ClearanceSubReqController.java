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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;

public class ClearanceSubReqController {

    private Connection conn = ConnectionFactory.getInstance().open();

    private String empCode, typeName, subTypeName, serialName;
    private int allocationId;

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
    private Text name;

    @FXML
    private Text serial;

    @FXML
    private Text space;

    @FXML
    private Text subType;

    @FXML
    private Text type;

    @FXML
    private TextArea comments;

    @FXML
    void onReject(ActionEvent event) {
        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);

        boolean success = new AllocationDAO(conn).
                updateStatusAndRemarksOnReturn(allocationId, "reject", comments.getText());
        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Clearance Request rejected successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Clearance Request could not be rejected");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    @FXML
    void onSubmit(ActionEvent event) {
        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);

        boolean success = new AllocationDAO(conn).
                updateStatusAndRemarksOnReturn(allocationId, "accept", comments.getText());
        if(success) {
            success = new AssetsDAO(conn).updateInstockOnClearance(serialName);
            if(success) {
                alertBox.setHeaderText("Successful");
                alertBox.setContentText("Clearance Accepted successfully");
            }
            else {
                alertBox.setHeaderText("Unsuccessful");
                alertBox.setContentText("Clearance could not be accepted successfully");
            }
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
        } else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Asset Request could not be placed");
        }
    }

    public void setReqData(int allocationId, String code, String type, String subType, String serial) {
        this.allocationId = allocationId;
        this.empCode = code;
        this.typeName = type;
        this.subTypeName = subType;
        this.serialName = serial;
        populateData();
    }

    public void populateData() {
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

        type.setText(typeName);
        subType.setText(subTypeName);

        serial.setText(serialName);
    }

}
