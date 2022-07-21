package il.cshaifasweng.OCSFMediatorExample.client;

import com.sun.javafx.image.IntPixelGetter;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.UpdateMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckoutController {

    @FXML
    private CheckBox anotherMethodBox;

    @FXML
    private TextField creditNumberField;

    @FXML
    private Text creditNumberText;

    @FXML
    private TextField cvvField;

    @FXML
    private Text cvvText;

    @FXML
    private ComboBox<Integer> dayCheckout;

    @FXML
    private CheckBox deliveryBox;

    @FXML
    private Text deliveryDateCheckout;

    @FXML
    private ComboBox<Integer> expiryMonth;

    @FXML
    private Text expiryText;

    @FXML
    private ComboBox<Integer> expiryYear;

    @FXML
    private ComboBox<String> hourCheckout;

    @FXML
    private CheckBox greetingBoxCheckout;

    @FXML
    private TextField greetingTextCheckout;

    @FXML
    private ComboBox<Integer> monthCheckout;

    @FXML
    private Button placeOrderButton;

    @FXML
    private TextField recepAddressField;

    @FXML
    private Text recepAddressText;

    @FXML
    private TextField recepNameField;

    @FXML
    private Text recepNameText;

    @FXML
    private TextField recepPhoneField;

    @FXML
    private Text recepPhoneText;

    @FXML
    private ComboBox<Integer> yearCheckout;

    @FXML
    private Button back;

    @FXML
    private CheckBox deliverToHome;


    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent roott = loader.load();
        PrimaryController cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Catalog");
        stage.show();
        Stage stagee = (Stage)back.getScene().getWindow();
        // do what you have to do
        stagee.close();

        Account recAcc = currentUser;
        System.out.println("the server sent me the account , NICE 2 !!");
        PassAccountEvent recievedAcc = new PassAccountEvent(recAcc);
        System.out.println("the server sent me the account , NICE 3 !!");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(recievedAcc);
                        System.out.println("the server sent me the account , NICE 4 !!");
                    }
                },4000
        );

    }
    @FXML
    void PlaceOrder(ActionEvent event) {
        int shopID = 0;
        Calendar calle = Calendar.getInstance();
        int currentYear = calle.get(Calendar.YEAR);
        int currentMonth = calle.get(Calendar.MONTH);
        int currentHour = calle.get(Calendar.HOUR_OF_DAY);
        int currentMintue = calle.get(Calendar.MINUTE);
        int currentDay = calle.get(Calendar.DAY_OF_MONTH);
        String chainShop = chooseShopID.getSelectionModel().toString();
        switch (chooseShopID.getSelectionModel().toString()) {
            case "ID 0: - Chain":
                shopID = 0;
                break;
            case "ID 1: Tiberias, Big Danilof":
                shopID = 1;
                break;
            case "ID 2: Haifa, Merkaz Zeiv":
                shopID = 2;
                break;
            case "ID 3: Tel Aviv, Ramat Aviv":
                shopID = 3;
                break;
            case "ID 4: Eilat, Ice mall":
                shopID = 4;
                break;
            case "ID 5: Be'er Sheva, Big Beer Sheva":
                shopID = 5;
                break;
        }
        boolean pickUp = true;
        boolean gift = false;
        String deliveredAddress = "";
        String recepName = "";
        long recepPhone = 0;
        if (deliverToHome.isSelected()) {
            pickUp = false;
            recepName = currentUser.getFullName();
            if (deliveryBox.isSelected()) {
                recepPhone = Integer.parseInt(recepPhoneField.getText());
                recepName = recepNameField.getText();
                deliveredAddress = recepAddressField.getText();
                gift = true;
            } else {
                recepPhone = currentUser.getPhoneNumber();
                recepName = currentUser.getFullName();
                deliveredAddress = currentUser.getAddress();
                gift = false;
            }
        } else {
            pickUp = false;
            deliveredAddress = "none";
        }
        String greeting = "";
        if (greetingBoxCheckout.isSelected()) {
            greeting = greetingTextCheckout.getText();
        } else {
            greeting = "none";
        }
        long creditCardNumber;
        int creditCardMonth;
        int creditCardYear;
        int creditCardCVV;
        if (anotherMethodBox.isSelected() == true) {
            creditCardNumber = Integer.parseInt(creditNumberField.getText());
            creditCardMonth = expiryMonth.getSelectionModel().getSelectedItem();
            creditCardYear = expiryYear.getSelectionModel().getSelectedItem();
            creditCardCVV = Integer.parseInt(cvvField.getText());
        } else {
            creditCardNumber = currentUser.getCreditCardNumber();
            creditCardMonth = currentUser.getCreditMonthExpire();
            creditCardYear = currentUser.getCreditYearExpire();
            creditCardCVV = currentUser.getCcv();
        }
        int dayCheckoutInt = dayCheckout.getSelectionModel().getSelectedItem();
        int monthCheckoutInt = monthCheckout.getSelectionModel().getSelectedItem();
        int yearCheckoutInt = yearCheckout.getSelectionModel().getSelectedItem();
        Order newOrder = new Order(0,pickUp,shopID,greeting,0,deliveredAddress,currentUser.getAccountID(),gift,false,dayCheckoutInt,monthCheckoutInt,yearCheckoutInt,currentDay,currentMonth,currentYear,creditCardNumber,creditCardMonth,creditCardYear,creditCardCVV,recepName,recepPhone,deliveredAddress);

        System.out.println(newOrder);
        UpdateMessage new_msg2=new UpdateMessage("order","add");
        new_msg2.setOrder(newOrder);
        try {
            System.out.println("before sending updateMessage to server ");
            SimpleClient.getClient().sendToServer(new_msg2); // sends the updated product to the server class
            System.out.println("afater sending updateMessage to server ");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void DeliverToMyHome(ActionEvent event)
    {
        deliveryBox.setVisible(true);
    }

    @FXML
    void addGreeting(ActionEvent event) {

        if(greetingBoxCheckout.isSelected())
            greetingTextCheckout.setVisible(true);
        else
            greetingTextCheckout.setVisible(false);
    }

    @FXML
    void deliverySomeone(ActionEvent event) {

        boolean mode;
        if(deliveryBox.isSelected())
            mode = true;
        else
            mode = false;
        recepNameText.setVisible(mode);
        recepNameField.setVisible(mode);
        recepPhoneText.setVisible(mode);
        recepPhoneField.setVisible(mode);
        recepAddressText.setVisible(mode);
        recepAddressField.setVisible(mode);
    }

    @FXML
    void useAnotherMethod(ActionEvent event)
    {
        boolean mode;
        if(anotherMethodBox.isSelected())
            mode = true;
        else
            mode = false;
        creditNumberField.setVisible(mode);
        creditNumberText.setVisible(mode);
        expiryText.setVisible(mode);
        expiryYear.setVisible(mode);
        expiryMonth.setVisible(mode);
        cvvText.setVisible(mode);
        cvvField.setVisible(mode);
    }


    @FXML
    private ComboBox<String> chooseShopID;

    Account currentUser;
    @Subscribe
    public void PassAccountEvent(PassAccountEventCheckout passAcc){ // added today
        System.out.println("Arrived To Pass Account - CheckoutController");
        Account recvAccount = passAcc.getRecievedAccount();
        System.out.println(recvAccount.getPassword());
        System.out.println(recvAccount.getAccountID());
        System.out.println(recvAccount.getEmail());
        System.out.println(recvAccount.getFullName());
        System.out.println(recvAccount.getAddress());
        System.out.println(recvAccount.getCreditCardNumber());
        System.out.println(recvAccount.getCreditMonthExpire());
        currentUser = recvAccount;
        cart = passAcc.getProductsToCheckout();

    }
    List<Product> cart = new ArrayList<>();
    @FXML
    void initialize() throws MalformedURLException
    {
        EventBus.getDefault().register(this);
        int i;
        System.out.println("Here");
        for(i = 1 ; i < 31 ; i++)
        {
            dayCheckout.getItems().add(i);
        }
        for(i = 1 ; i < 13 ; i++)
        {
            expiryMonth.getItems().add(i);
            monthCheckout.getItems().add(i);
        }
        for(i = 2022 ; i < 2030 ; i++)
        {
            yearCheckout.getItems().add(i);
            expiryYear.getItems().add(i);
        }
        int startHour = 7;
        String FullHour;
        for(i = 0 ; i < 15 ; i++)
        {
            FullHour = startHour + ":" + "00";
            FullHour = startHour + ":" + "15";
            FullHour = startHour + ":" + "30";
            FullHour = startHour + ":" + "45";
            hourCheckout.getItems().add(FullHour);
            startHour++;
        }
        chooseShopID.getItems().add("ID 1: Tiberias, Big Danilof");
        chooseShopID.getItems().add("ID 2: Haifa, Merkaz Zeiv");
        chooseShopID.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
        chooseShopID.getItems().add("ID 4: Eilat, Ice mall");
        chooseShopID.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");
        recepNameText.setVisible(false);
        recepNameField.setVisible(false);
        recepPhoneText.setVisible(false);
        recepPhoneField.setVisible(false);
        recepAddressText.setVisible(false);
        recepAddressField.setVisible(false);

        creditNumberField.setVisible(false);
        creditNumberText.setVisible(false);
        expiryText.setVisible(false);
        expiryYear.setVisible(false);
        expiryMonth.setVisible(false);

        cvvField.setVisible(false);
        cvvText.setVisible(false);

        greetingTextCheckout.setVisible(false);
        deliveryBox.setVisible(false);
    }
}
