package com.sattva.itassetmanagement.AdminUI;

import com.sattva.itdatabase.DAO.AllocationDAO;
import com.sattva.itdatabase.DAO.EmployeeDAO;
import com.sattva.itdatabase.DTO.CityDTO;
import com.sattva.itdatabase.DTO.DepartmentDTO;
import com.sattva.itdatabase.DTO.EmployeeDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import com.sattva.itdatabase.Datasource.Datasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    private Connection conn;

    @FXML
    private AnchorPane assetPane;

    @FXML
    private TextField company;

    @FXML
    private TextField contact;

    @FXML
    private ChoiceBox<String> deptChoice;

    @FXML
    private TextField desgn;

    @FXML
    private TextField email;

    @FXML
    private TextField empCode;

    @FXML
    private TextField empName;

    @FXML
    private TextField reprtManager;

    @FXML
    private Button submitBtn;

    @FXML
    void onSubmit(ActionEvent event) {
        String date = java.time.LocalDate.now().toString();
        String name = empName.getText();
        String code = empCode.getText();
        String company = this.company.getText();
        String contact = this.contact.getText();
        String desgn = this.desgn.getText();
        String email = this.email.getText();
        String reprtManager = this.reprtManager.getText();
        String deptId = this.deptChoice.getValue();

        final Stage stage = (Stage)assetPane.getScene().getWindow();
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);

        ArrayList<String> ids = new EmployeeDAO(conn).getAllEmployeeCodes();
        if(!ids.contains(reprtManager)) {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Invalid Reporting Manager Code");
            if(alertBox.showAndWait().get() == ButtonType.OK)
                stage.close();
            return;
        }

        boolean success = new EmployeeDAO(conn).insertEmployee(code, name, name, company, desgn, contact, email, deptId,
                reprtManager, date);

        if(success) {
            alertBox.setHeaderText("Successful");
            alertBox.setContentText("Employee Added successfully");
        }
        else {
            alertBox.setHeaderText("Unsuccessful");
            alertBox.setContentText("Employee could not be added");
        }
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = ConnectionFactory.getInstance().open();
        ArrayList<DepartmentDTO> departments = new Datasource(conn).getAllDept();

        String[] deptNames = new String[departments.size()];

        for(int i=0;i<departments.size();i++)
            deptNames[i] = departments.get(i).getDeptId();

        deptChoice.getItems().addAll(deptNames);
    }
}
