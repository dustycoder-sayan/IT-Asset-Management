package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DTO.AssetsDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllAssetController implements Initializable {

    @FXML
    private TableView<AssetsDTO> assetTable;

    @FXML
    private TableColumn<AssetsDTO, Integer> inStockCol;

    @FXML
    private TableColumn<AssetsDTO, String> modelCol;

    @FXML
    private TableColumn<AssetsDTO, String> nameCol;

    @FXML
    private TableColumn<AssetsDTO, Integer> outStockCol;

    @FXML
    private TableColumn<AssetsDTO, String> serialCol;

    @FXML
    private TableColumn<AssetsDTO, String> subTypeColumn;

    @FXML
    private TableColumn<AssetsDTO, Integer> totalStockCol;

    @FXML
    private TableColumn<AssetsDTO, String> typeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = ConnectionFactory.getInstance().open();

        inStockCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        outStockCol.setCellValueFactory(new PropertyValueFactory<>("outStock"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        serialCol.setCellValueFactory(new PropertyValueFactory<>("serial"));
        subTypeColumn.setCellValueFactory(new PropertyValueFactory<>("subType"));
        totalStockCol.setCellValueFactory(new PropertyValueFactory<>("totalStock"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        ArrayList<AssetsDTO> assets = new Datasource(conn).getAllAssets();
        ObservableList<AssetsDTO> assetData = FXCollections.observableArrayList(assets);

        assetTable.setItems(assetData);
    }
}
