package com.sattva.itassetmanagement;

import com.sattva.itassetmanagement.AdminUI.DashboardAdminController;
import com.sattva.itassetmanagement.EmployeeUI.DashboardEmpController;
import com.sattva.itdatabase.DAO.DepartmentDAO;
import com.sattva.itdatabase.DAO.EmployeeDAO;
import com.sattva.itdatabase.DTO.EmployeeDTO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {
    @FXML
    private TextField codeInput;
    @FXML
    private TextField passInput;
    @FXML
    private Button loginBtn;
    @FXML
    private Label incorrectLabel;

    private final Connection conn = ConnectionFactory.getInstance().open();
    private final EmployeeDAO employee = new EmployeeDAO(conn);

    public void loginToSystem(ActionEvent e) throws IOException {
        String code = codeInput.getText();
        String password = passInput.getText();
        boolean userCheck = employee.employeeExists(code);
        if(!userCheck) {
            incorrectLabel.setText("Incorrect Code");
            codeInput.setText("");
            passInput.setText("");
        }
        else {
            String passwordCheck = employee.login(code, password);
            if(passwordCheck == null) {
                incorrectLabel.setText("Incorrect Password");
                passInput.setText("");
            } else {
                passwordCheck = passwordCheck.toLowerCase();
                if(passwordCheck.contains("admin") || passwordCheck.contains("it")) {
                    FXMLLoader adminRoot = new FXMLLoader(DashboardAdminController.class.getResource("dashboard-admin.fxml"));
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(adminRoot.load());
                    stage.setTitle("Admin Dashboard");
                    DashboardAdminController controller = adminRoot.getController();

                    EmployeeDTO employeeDTO = employee.getEmployeeDetails(code);
                    String department = new DepartmentDAO(conn).getDepartmentName(employeeDTO.getDeptId());
                    controller.display(employeeDTO.getCode(), employeeDTO.getName(), employeeDTO.getDesignation(),
                            department, employeeDTO.getContact(), employeeDTO.getEmailId(), employeeDTO.getCompany());
                    stage.setScene(scene);
                    stage.show();
                }
                else {
                    FXMLLoader empRoot = new FXMLLoader(DashboardEmpController.class.getResource("dashboard-emp.fxml"));
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(empRoot.load());
                    stage.setTitle("Employee Dashboard");
                    DashboardEmpController controller = empRoot.getController();

                    EmployeeDTO employeeDTO = employee.getEmployeeDetails(code);
                    String department = new DepartmentDAO(conn).getDepartmentName(employeeDTO.getDeptId());
                    controller.display(employeeDTO.getCode(), employeeDTO.getName(), employeeDTO.getDesignation(),
                            department, employeeDTO.getContact(), employeeDTO.getEmailId(), employeeDTO.getCompany());
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void logout(Stage stage) {
        Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
        alertBox.setHeaderText("You are about to quit");
        alertBox.setContentText("Are you sure you want to quit?");
        if(alertBox.showAndWait().get() == ButtonType.OK)
            stage.close();
    }
}
