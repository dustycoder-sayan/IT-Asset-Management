package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DTO.AssetCountDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable {

    @FXML
    private Text activeDesktops;

    @FXML
    private Text activeLaptops;

    @FXML
    private Text activeTotal;

    @FXML
    private MenuItem addAsset;

    @FXML
    private MenuItem addDept;

    @FXML
    private MenuItem addEmployee;

    @FXML
    private MenuItem addNewVendor;

    @FXML
    private MenuItem allAssets;

    @FXML
    private MenuItem assetRequests;

    @FXML
    private MenuItem assetRequests1;

    @FXML
    private MenuItem assetRequests3;

    @FXML
    private Text availableDesktops;

    @FXML
    private Text availableLaptops;

    @FXML
    private Text availableTotal;

    @FXML
    private MenuItem clearanceRequests;

    @FXML
    private Label codeLabel;

    @FXML
    private Label companyLabel;

    @FXML
    private Label deptLabel;

    @FXML
    private Label desgnLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox leftPane;

    @FXML
    private MenuItem locationAdd;

    @FXML
    private Button logoutBtn;

    @FXML
    private BorderPane mainPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Text overallDesktops;

    @FXML
    private Text overallLaptops;

    @FXML
    private Text overallTotal;

    @FXML
    private MenuItem releasedAssets;

    @FXML
    private MenuItem sapRequests;

    @FXML
    private Button settingsBtn;

    @FXML
    private MenuItem vendorPlaceOrders;

    @FXML
    private MenuItem vpnRequests;


    private String code;

    @FXML
    void onLogout(ActionEvent event) {
        final Stage stage = (Stage)mainPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setHeaderText("You are about to Logout");
        alertBox.setContentText("Are you sure you want to logout?");
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    @FXML
    void onSettings(ActionEvent event) {

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
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("add-asset.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Asset");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkAssetRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("asset-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Asset Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkDatacardRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("datacard-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Datacard Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkEmailRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("email-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Email Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkVpnRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("vpn-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("VPN Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkSapRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("sap-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SAP Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void checkClearanceRequests(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("clearance-req.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Clearance Requests");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void displayAllAllocation(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("all-asset.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Asset Inventory");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void addNewVendors(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("add-vendor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add Vendors");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void displayReleasedAssets(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminController.class.getResource("assets-released.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Assets Released");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void placeNewOrders(ActionEvent e) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = ConnectionFactory.getInstance().open();
        ArrayList<AssetCountDTO> overallAssetCountBasic = new Datasource(conn).getAssetCounts();
        int totalCnt = 0;

        // Overall IT Assets
        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        xAxis.setLabel("Stock");
        yAxis.setLabel("Asset");
        xAxis.setTickLabelFill(Color.BLACK);
        yAxis.setTickLabelFill(Color.BLACK);

        BarChart overallAssets = new BarChart(xAxis, yAxis);
        XYChart.Series data = new XYChart.Series();
        data.setName("Overall Asset Inventory");

        ObservableList<AssetCountDTO> overallAssetCount = FXCollections.observableArrayList(overallAssetCountBasic);

        for (AssetCountDTO assetCountDTO : overallAssetCount) {
            data.getData().add(new XYChart.Data(assetCountDTO.getTotalStock(), assetCountDTO.getAssetSubtype()));
            totalCnt += assetCountDTO.getTotalStock();
        }

        overallAssets.getData().add(data);
        gridPane.add(overallAssets,0,0);

        overallTotal.setText(Integer.toString(totalCnt));
        overallDesktops.setText(Integer.toString(overallAssetCount.get(0).getTotalStock()));
        overallLaptops.setText(Integer.toString(overallAssetCount.get(1).getTotalStock()));

        // Overall IT Assets - Active
        ObservableList<PieChart.Data> activeAssets = FXCollections.observableArrayList();
        for (AssetCountDTO assetCountDTO : overallAssetCount) {
            activeAssets.add(new PieChart.Data(assetCountDTO.getAssetSubtype(), assetCountDTO.getOutStock()));
        }

        PieChart activeAssetChart = new PieChart(activeAssets);
        activeAssetChart.setClockwise(true);
        activeAssetChart.setLabelLineLength(20);
        activeAssetChart.setLabelsVisible(true);
        activeAssetChart.setStartAngle(180);

        gridPane.add(activeAssetChart, 1, 1);

        int cnt1 = overallAssetCount.get(0).getOutStock();
        int cnt2 = overallAssetCount.get(1).getOutStock();
        totalCnt = cnt1+cnt2;

        activeTotal.setText(Integer.toString(totalCnt));
        activeDesktops.setText(Integer.toString(cnt1));
        activeLaptops.setText(Integer.toString(cnt2));

        // Overall IT Assets - In Stock
        ObservableList<PieChart.Data> availableAssets = FXCollections.observableArrayList();
        for (AssetCountDTO assetCountDTO : overallAssetCount) {
            availableAssets.add(new PieChart.Data(assetCountDTO.getAssetSubtype(), assetCountDTO.getInStock()));
            totalCnt += assetCountDTO.getInStock();
        }

        PieChart availableAssetChart = new PieChart(availableAssets);
        availableAssetChart.setClockwise(true);
        availableAssetChart.setLabelLineLength(20);
        availableAssetChart.setLabelsVisible(true);
        availableAssetChart.setStartAngle(180);

        gridPane.add(availableAssetChart, 0, 2);

        cnt1 = overallAssetCount.get(0).getInStock();
        cnt2 = overallAssetCount.get(1).getInStock();
        totalCnt = cnt1+cnt2;

        availableTotal.setText(Integer.toString(totalCnt));
        availableDesktops.setText(Integer.toString(cnt1));
        availableLaptops.setText(Integer.toString(cnt2));
    }
}
