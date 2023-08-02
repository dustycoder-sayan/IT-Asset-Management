package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itassetmanagement.EmployeeUI.DashboardEmpController;
import com.sattva.itdatabase.DAO.AllocationDAO;
import com.sattva.itdatabase.DTO.AllocationDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable {

    @FXML
    private Button blrBtn;
    @FXML
    private HBox cityWiseCharts;
    @FXML
    private Label codeLabel;
    @FXML
    private Button coimBtn;
    @FXML
    private Label companyLabel;
    @FXML
    private Label deptLabel;
    @FXML
    private Label desgnLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Button goaBtn;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button hydBtn;
    @FXML
    private VBox leftPane;
    @FXML
    private Button logoutBtn;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label nameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private HBox overallITCharts;
    @FXML
    private Button punBtn;
    @FXML
    private Button settingsBtn;

    private String code;

    @FXML
    void onLogout(ActionEvent event) {

    }

    @FXML
    void onSettings(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        CategoryAxis xAxis = new CategoryAxis();
//        CategoryAxis yAxis = new CategoryAxis();
//        xAxis.setLabel("Assets");
//        yAxis.setLabel("Quantity");
//
//        BarChart barChart = new BarChart(xAxis, yAxis);
//
//        XYChart.Series data = new XYChart.Series();
//
    }

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

    public void addLocation(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("location-add.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Location");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void addEmployee(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("add-employee.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Employee");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void addDepartment(ActionEvent e) throws IOException {

    }

    public void addAsset(ActionEvent e) throws IOException {

    }

    public void checkAssetRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("asset-requests.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Employee");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkVpnRequests(ActionEvent e) throws IOException {

    }

    public void checkSapRequests(ActionEvent e) throws IOException {

    }

    public void checkClearanceRequests(ActionEvent e) throws IOException {

    }

    public void displayAllAllocation(ActionEvent e) throws IOException {

    }

    public void addNewVendors(ActionEvent e) throws IOException {

    }

    public void placeNewOrders(ActionEvent e) throws IOException {

    }

    public void seePrevOrders(ActionEvent e) throws IOException {

    }
}
