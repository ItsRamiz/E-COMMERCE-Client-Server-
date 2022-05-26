package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.UpdateMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CVV;

    @FXML
    private TextField CardNumber;

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
    private Button backk;


    @FXML
    private TextField Street_Address;

    @FXML
    private TextField Validity;

    @FXML
    private TextField ZipCode;

    @FXML
    private Label ErrorMsg;

    @FXML
    private TextField Password;

    private LinkedList<String> RegisteredAccounts = new LinkedList<>(); // list of all registered emails


    @FXML
    void AddCustomerToDB(ActionEvent event) throws IOException {
        if(!RegisteredAccounts.contains(Email.getText())){
            String Address = Street_Address.getText() + ", " + City_Address.getText() + ", Zip Code " + ZipCode.getText();
            Account new_account = new Account(Name.getText(),Address,Email.getText(),Password.getText(),Integer.parseInt(PhoneNumber.getText()),Integer.parseInt(CardNumber.getText()), Date.valueOf(Validity.getText()),Integer.parseInt(CVV.getText()),1);
            RegisteredAccounts.add(Email.getText());
            // TODO : ADD ACCOUNT TO DB
            UpdateMessage new_msg2=new UpdateMessage("account","add");
            java.util.Date date=new java.util.Date();

            Account new_acc=new Account("khaled","ssakhnen","@gmail","555",457,777,date,445,4);
            // new_acc.setAccountID(1);
            new_msg2.setAccount(new_acc);
            try {
                System.out.println("before sending updateMessage to server ");
                SimpleClient.getClient().sendToServer(new_msg2); // sends the updated product to the server class
                System.out.println("afater sending updateMessage to server ");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInSecond.fxml"));
            // TODO : CHECK IF EMAIL EXISTS IN DB
           // String Address = Street_Address.getText() + ", " + City_Address.getText() + ", Zip Code " + ZipCode.getText();
            //Customer new_customer = new Customer(1,Email.getText(), Name.getText(),Address,PhoneNumber.getText());
            // TODO : ADD CUSTOMER TO DB

            Parent roott = loader.load();
            LogInSecondary cc = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(roott));
            stage.setTitle("Log In");
            stage.show();
            Stage stagee = (Stage) RegisterButton.getScene().getWindow();
            // do what you have to do
            stagee.close();

        }
        else{
            ErrorMsg.setVisible(true);
        }

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
        ErrorMsg.setVisible(false);

    }

    @FXML
    void backkk(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPrim.fxml"));
        Parent roott = loader.load();
        LogInPrimary cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Login");
        stage.show();
        Stage stagee = (Stage) backk.getScene().getWindow();
        // do what you have to do
        stagee.close();
    }
    public LinkedList<String> getRegisteredAccounts(){
        return RegisteredAccounts;
    }



}
