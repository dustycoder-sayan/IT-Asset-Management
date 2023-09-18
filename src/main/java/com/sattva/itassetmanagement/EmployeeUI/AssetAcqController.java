package com.sattva.itassetmanagement.EmployeeUI;

import com.sattva.itdatabase.DTO.AllocationDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class AssetAcqController {

    @FXML
    private TableView<AllocationDTO> assetTable;

    @FXML
    private TableColumn<AllocationDTO, String> dateColumn;

    @FXML
    private TableColumn<AllocationDTO, String> serialColumn;

    @FXML
    private TableColumn<AllocationDTO, String> subTypeColumn;

    @FXML
    private TableColumn<AllocationDTO, String> typeColumn;

    @FXML
    private TableColumn<?, ?> statusCol;

    private Connection conn = ConnectionFactory.getInstance().open();
    private String code;

    public void setCode(String code) {
        this.code = code;
        populateTable(code);
    }

    public void populateTable(String code) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList<AllocationDTO> allocations = new Datasource(conn).getAllocationsByEmployeeAllStatus(code);
        if(allocations.isEmpty())
            return;

        ObservableList<AllocationDTO> allocationsData = FXCollections.observableArrayList(allocations);
        assetTable.setItems(allocationsData);
    }
}