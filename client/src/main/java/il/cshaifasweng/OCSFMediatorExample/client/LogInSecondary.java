package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LogInSecondary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Customer_login;

    @FXML
    private Button Employee_login;

    @FXML
    void CustomerLogIn(ActionEvent event) {

    }

    @FXML
    void EmployeeLogIn(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Customer_login != null : "fx:id=\"Customer_login\" was not injected: check your FXML file 'LogInSecondary.fxml'.";
        assert Employee_login != null : "fx:id=\"Employee_login\" was not injected: check your FXML file 'LogInSecondary.fxml'.";

    }

}
