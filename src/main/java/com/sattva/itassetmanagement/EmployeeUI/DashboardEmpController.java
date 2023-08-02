package com.sattva.itassetmanagement.EmployeeUI;

import com.sattva.itassetmanagement.StartApplication;
import com.sattva.itdatabase.DAO.DepartmentDAO;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class DashboardEmpController {
    @FXML
    private Button assetAcqBtn;
    @FXML
    private Button assetReqBtn;
    @FXML
    private Button clearanceBtn;
    @FXML
    private Label deptLabel;
    @FXML
    private Label desgnLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Button sapReqBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button vpnReqBtn;
    @FXML
    private Label codeLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private Button logoutBtn;
    @FXML
    private AnchorPane mainPane;

    private String code, deptId;
    private Connection conn;

    public void display(String code, String name, String designation, String department, String contact, String email,
    String company) {
        this.code = code;
        codeLabel.setText(code);
        nameLabel.setText(name);
        desgnLabel.setText(designation);
        deptLabel.setText(department);
        numberLabel.setText(contact);
        emailLabel.setText(email);
        companyLabel.setText(company);
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void onAcquired(ActionEvent e) {
        // Table view displaying all assets, vpns and saps taken
    }

    public void onAssetRequest(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardEmpController.class.getResource("asset-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Asset Request");
        AssetReqController controller = fxmlLoader.getController();
        controller.setCode(this.code);
        controller.setConn(this.conn);
        controller.setLocationId(new DepartmentDAO(conn).getDeptLocation(this.deptId));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onVpnRequest(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardEmpController.class.getResource("vpn-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("VPN Request");
        VpnReqController controller = fxmlLoader.getController();
        controller.setCode(this.code);
        controller.setConn(this.conn);
        controller.setLocationId(new DepartmentDAO(conn).getDeptLocation(this.deptId));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onSAPRequest(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardEmpController.class.getResource("sap-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SAP Request");
        SapReqController controller = fxmlLoader.getController();
        controller.setCode(this.code);
        controller.setConn(this.conn);
        controller.setLocationId(new DepartmentDAO(conn).getDeptLocation(this.deptId));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onClearance(ActionEvent e) throws IOException {
        // One Table View that displays all assets taken
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardEmpController.class.getResource("clearance.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Clearance Request");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onSettings(ActionEvent e) {
        // Decide at enddddd of project
    }

    public void onLogout(ActionEvent e) {
        final Stage stage = (Stage)mainPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setHeaderText("You are about to Logout");
        alertBox.setContentText("Are you sure you want to logout?");
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    public String getCode() {
        return code;
    }
}
