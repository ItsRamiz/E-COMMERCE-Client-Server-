package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Cart;
import il.cshaifasweng.OCSFMediatorExample.entities.UpdateMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    int account_num = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private ComboBox<String> acc_type;
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

    @FXML
    private Label CVV_regex_error;

    @FXML
    private Label card_regex_error;

    @FXML
    private Label email_regex_error;

    @FXML
    private Label phone_regex_error;


    @FXML
    private ComboBox<String> chooseMonth;

    @FXML
    private ComboBox<String> chooseYear;

    private LinkedList<String> RegisteredAccounts = new LinkedList<>(); // list of all registered emails

    String email_regex = "^(.+)@(.+)$";
    String creditCard_regex = "^\\d{16}$";
    String CVV_regex = "^\\d{3}$";
    String phoneNum_regex = "^\\d{10}$";


    @FXML
    void AddCustomerToDB(ActionEvent event) throws IOException {
        Pattern pattern = Pattern.compile(email_regex);
        Matcher matcher = pattern.matcher(Email.getText());
        if(!matcher.matches()){
            email_regex_error.setVisible(true);
        }
        pattern = Pattern.compile(creditCard_regex);
        matcher = pattern.matcher(CardNumber.getText());
        if(!matcher.matches()){
            card_regex_error.setVisible(true);
        }
        pattern = Pattern.compile(CVV_regex);
        matcher = pattern.matcher(CVV.getText());
        if(!matcher.matches()){
            CVV_regex_error.setVisible(true);
        }
        pattern = Pattern.compile(phoneNum_regex);
        matcher = pattern.matcher(PhoneNumber.getText());
        if(!matcher.matches()){
            phone_regex_error.setVisible(true);
        }
        else if(!RegisteredAccounts.contains(Email.getText())){
            String Address = Street_Address.getText() + ", " + City_Address.getText() + ", Zip Code " + ZipCode.getText();
            java.util.Date date=new java.util.Date();
            Account new_acc = new Account(Name.getText(),Address,Email.getText(),Password.getText(),Long.parseLong(PhoneNumber.getText()),Long.parseLong(CardNumber.getText()),Integer.parseInt(chooseYear.getSelectionModel().getSelectedItem()),Integer.parseInt(chooseMonth.getSelectionModel().getSelectedItem()) ,Integer.parseInt(CVV.getText()), 0);
            RegisteredAccounts.add(Email.getText());
            // TODO : ADD ACCOUNT TO DB

            UpdateMessage new_msg2=new UpdateMessage("account","add");
            //Account new_acc = new Account("khaled","sakhnin","@eee","332",0504444444,889555,date,222,false, null, 0,false);
            account_num++;
            new_acc.setAccountID(account_num);
            new_msg2.setId(account_num);
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
        acc_type.getItems().add("client");
        acc_type.getItems().add("worker");
        acc_type.getItems().add("manager");
        ErrorMsg.setVisible(false);
        CVV_regex_error.setVisible(false);
        phone_regex_error.setVisible(false);
        email_regex_error.setVisible(false);
        card_regex_error.setVisible(false);

        int i;
        for(i = 1 ; i < 13 ; i++)
        { chooseMonth.getItems().add(String.valueOf(i)); }
        for( i = 2000 ; i < 2030 ; i++)
        { chooseYear.getItems().add(String.valueOf(i));  }
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
