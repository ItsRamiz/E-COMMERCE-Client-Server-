package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField City_Address;

    @FXML
    private TextField Email;

    @FXML
    private TextField Name;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField Street_Address;

    @FXML
    private TextField ZipCode;

    @FXML
    void AddCustomerToDB(ActionEvent event) {
        // TODO : CHECK IF EMAIL EXISTS IN DB
        String Address = Street_Address.getText() + ", " + City_Address.getText() + ", Zip Code " + ZipCode.getText();
        Customer new_customer = new Customer(1,Email.getText(), Name.getText(),Address,PhoneNumber.getText());
        // TODO : ADD CUSTOMER TO DB
    }

    @FXML
    void initialize() {
        assert City_Address != null : "fx:id=\"City_Address\" was not injected: check your FXML file 'register.fxml'.";
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'register.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'register.fxml'.";
        assert PhoneNumber != null : "fx:id=\"PhoneNumber\" was not injected: check your FXML file 'register.fxml'.";
        assert RegisterButton != null : "fx:id=\"RegisterButton\" was not injected: check your FXML file 'register.fxml'.";
        assert Street_Address != null : "fx:id=\"Street_Address\" was not injected: check your FXML file 'register.fxml'.";
        assert ZipCode != null : "fx:id=\"ZipCode\" was not injected: check your FXML file 'register.fxml'.";

    }

}
