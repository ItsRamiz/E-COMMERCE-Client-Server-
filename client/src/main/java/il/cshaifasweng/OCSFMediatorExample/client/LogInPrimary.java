package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LogInPrimary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Guest;

    @FXML
    private Button LogIn;

    @FXML
    private Button Register;

    @FXML
    void gotoLogInSecondary(ActionEvent event) throws IOException {
        App.setRoot("LogInSecondary");
    }

    @FXML
    void gotoCatalog(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void gotoRegisterPage(ActionEvent event) throws IOException {
        App.setRoot("register");
    }

    @FXML
    void initialize() {
        assert Guest != null : "fx:id=\"Guest\" was not injected: check your FXML file 'LogInPrimary.fxml'.";
        assert LogIn != null : "fx:id=\"LogIn\" was not injected: check your FXML file 'LogInPrimary.fxml'.";
        assert Register != null : "fx:id=\"Register\" was not injected: check your FXML file 'LogInPrimary.fxml'.";

    }

}
