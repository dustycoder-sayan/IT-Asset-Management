package com.sattva.itassetmanagement;

import com.sattva.itdatabase.DAO.EmployeeDAO;
import com.sattva.itdatabase.Database.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private final EmployeeDAO loginCreden = new EmployeeDAO(conn);

    public void loginToSystem(ActionEvent e) {
        String code = codeInput.getText();
        String password = passInput.getText();
        boolean userCheck = loginCreden.employeeExists(code);
        if(!userCheck) {
            incorrectLabel.setText("Incorrect Code");
            codeInput.setText("");
            passInput.setText("");
        }
        else {
            String passwordCheck = loginCreden.login(code, password);
            if(passwordCheck == null) {
                incorrectLabel.setText("Incorrect Password");
                passInput.setText("");
            } else {
                if(passwordCheck.contains("admin") || passwordCheck.contains("it")) {
                    // TODO: setScene to admin dashboard
                    System.out.println("Admin");
                }
                else {
                    // TODO: setScene to employee dashboard
                    System.out.println("Employee");
                }
            }
        }
    }
}
