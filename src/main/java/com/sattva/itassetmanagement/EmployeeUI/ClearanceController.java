package com.sattva.itassetmanagement.EmployeeUI;

import com.sattva.itdatabase.DAO.AllocationDAO;
import com.sattva.itdatabase.DTO.AllocationDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;

public class ClearanceController {

    private Connection conn = ConnectionFactory.getInstance().open();

    public void setCode(String code) {
        populateTable(code);
    }

    @FXML
    private AnchorPane assetPane;

    @FXML
    private TableView<AllocationDTO> assetTable;

    @FXML
    private TableColumn<AllocationDTO, String> dateColumn;

    @FXML
    private TableColumn<AllocationDTO, Integer> allocationId;

    @FXML
    private TableColumn<AllocationDTO, String> serialColumn;

    @FXML
    private TableColumn<AllocationDTO, String> subTypeColumn;

    @FXML
    private Button submitBtn;

    @FXML
    private TableColumn<AllocationDTO, String> typeColumn;

    @FXML
    void onSubmit(ActionEvent event) {
        AllocationDTO selected = assetTable.getSelectionModel().getSelectedItem();
        int allocId = selected.getAllocationId();
        String serial = selected.getSerial();

        boolean success = new AllocationDAO(conn).updateStatusOnReturnRequest(allocId);

        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);

        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Clearance Request placed successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Clearance Request could not be placed");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    public void populateTable(String code) {
        allocationId.setCellValueFactory(new PropertyValueFactory<>("allocationId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        ArrayList<AllocationDTO> allocations = new Datasource(conn).getAllocationsByEmployeeNotWaiting(code);
        if(allocations.isEmpty())
            return;

        ObservableList<AllocationDTO> allocationsData = FXCollections.observableArrayList(allocations);
        assetTable.setItems(allocationsData);
    }
}
