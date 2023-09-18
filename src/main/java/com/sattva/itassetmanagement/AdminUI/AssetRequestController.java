package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DTO.AllocationDTO;
import com.sattva.itdatabase.DTO.ViewRequestsDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AssetRequestController implements Initializable {

    private Connection conn;

    @FXML
    private TableView<ViewRequestsDTO> assetTable;

    @FXML
    private TableColumn<ViewRequestsDTO, String> empCodeCol;

    @FXML
    private TableColumn<ViewRequestsDTO, String> locationCol;

    @FXML
    private TableColumn<ViewRequestsDTO, String> subTypeColumn;

    @FXML
    private Button submitBtn;

    @FXML
    private TableColumn<ViewRequestsDTO, String> typeColumn;

    @FXML
    void onSubmit(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = ConnectionFactory.getInstance().open();

        empCodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn action = new TableColumn("Action");
        assetTable.getColumns().add(action);

        action.setCellValueFactory(new PropertyValueFactory<>("button"));

        ArrayList<ViewRequestsDTO> requests = new Datasource(conn).getAllAssetRequests();
        if(requests.isEmpty())
            return;

        ObservableList<ViewRequestsDTO> requestsData = FXCollections.observableArrayList(requests);
        assetTable.setItems(requestsData);
    }
}

