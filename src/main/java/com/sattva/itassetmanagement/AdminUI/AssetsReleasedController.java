package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DTO.AssetReleasedDTO;
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

public class AssetsReleasedController implements Initializable {

    @FXML
    private TableView<AssetReleasedDTO> assetTable;

    @FXML
    private TableColumn<AssetReleasedDTO, String> cityCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> codeCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> dateCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> deptCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> serialCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> spaceCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> subTypeCol;

    @FXML
    private TableColumn<AssetReleasedDTO, String> typeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = ConnectionFactory.getInstance().open();

        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("dept"));
        serialCol.setCellValueFactory(new PropertyValueFactory<>("serial"));
        spaceCol.setCellValueFactory(new PropertyValueFactory<>("space"));
        subTypeCol.setCellValueFactory(new PropertyValueFactory<>("subType"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        ArrayList<AssetReleasedDTO> assets = new Datasource(conn).getAssetsReleased();
        ObservableList<AssetReleasedDTO> assetData = FXCollections.observableArrayList(assets);

        assetTable.setItems(assetData);
    }
}
