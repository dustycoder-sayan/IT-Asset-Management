package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itassetmanagement.LoginController;
import com.sattva.itdatabase.DTO.CityDTO;
import com.sattva.itdatabase.DTO.Location2DTO;
import com.sattva.itdatabase.DTO.SpaceDTO;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LocationAddController implements Initializable {

    @FXML
    private TableColumn<CityDTO, String> cityColumn;

    @FXML
    private TextField cityInput;

    @FXML
    private TableView<CityDTO> cityTable;

    @FXML
    private TableColumn<Location2DTO, String> locationColumn;

    @FXML
    private TextField locationInput;

    @FXML
    private TableView<Location2DTO> locationTable;

    @FXML
    private TableColumn<SpaceDTO, String> spaceColumn;

    @FXML
    private TextField spaceInput;

    @FXML
    private TableView<SpaceDTO> spaceTable;

    @FXML
    private Button locationSubmitBtn;

    Connection conn = new LoginController().getConn();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<CityDTO> cities = FXCollections.observableArrayList(new Datasource(conn).getAllCities());
        ObservableList<Location2DTO> locations = FXCollections.observableArrayList(new Datasource(conn).getAllLocations());
        ObservableList<SpaceDTO> spaces = FXCollections.observableArrayList(new Datasource(conn).getAllSpaces());

        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        spaceColumn.setCellValueFactory(new PropertyValueFactory<>("spaceName"));

        cityTable.setItems(cities);
        locationTable.setItems(locations);
        spaceTable.setItems(spaces);
    }

    public void onSubmit(ActionEvent e) {

    }
}
