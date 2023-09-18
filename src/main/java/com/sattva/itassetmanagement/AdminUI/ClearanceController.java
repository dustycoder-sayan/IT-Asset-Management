package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DTO.AllocationDTO;
import com.sattva.itdatabase.DTO.AssetReleasedDTO;
import com.sattva.itdatabase.DTO.ViewClearancesDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClearanceController implements Initializable {

    @FXML
    private TableView<ViewClearancesDTO> assetTable;

    @FXML
    private TableColumn<ViewClearancesDTO, String> empCodeCol;

    @FXML
    private TableColumn<ViewClearancesDTO, String> locationCol;

    @FXML
    private TableColumn<ViewClearancesDTO, String> serialCol;

    @FXML
    private TableColumn<ViewClearancesDTO, String> subTypeColumn;

    @FXML
    private Button submitBtn;

    @FXML
    private TableColumn<ViewClearancesDTO, String> typeColumn;

    @FXML
    void onSubmit(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = ConnectionFactory.getInstance().open();

        empCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        serialCol.setCellValueFactory(new PropertyValueFactory<>("serial"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn action = new TableColumn("Action");
        assetTable.getColumns().add(action);
        action.setCellValueFactory(new PropertyValueFactory<>("button"));

        ArrayList<ViewClearancesDTO> clearances = new Datasource(conn).getAllClearanceRequests();
        ObservableList<ViewClearancesDTO> clearanceData = FXCollections.observableArrayList(clearances);

        assetTable.setItems(clearanceData);
    }
}
