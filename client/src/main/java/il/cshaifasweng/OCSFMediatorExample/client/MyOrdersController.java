package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.Column;

public class MyOrdersController {


    @FXML // fx:id="sendComplaint"
    private Button sendComplaint; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RecepAddress"
    private TextField RecepAddress; // Value injected by FXMLLoader

    @FXML // fx:id="RecepName"
    private TextField RecepName; // Value injected by FXMLLoader

    @FXML // fx:id="RecepNumber"
    private TextField RecepNumber; // Value injected by FXMLLoader

    @FXML // fx:id="accountID"
    private TextField accountID; // Value injected by FXMLLoader

    @FXML // fx:id="creditCVV"
    private TextField creditCVV; // Value injected by FXMLLoader

    @FXML // fx:id="creditExpire"
    private TextField creditExpire; // Value injected by FXMLLoader

    @FXML // fx:id="creditNumber"
    private TextField creditNumber; // Value injected by FXMLLoader

    @FXML // fx:id="dateOrder"
    private TextField dateOrder; // Value injected by FXMLLoader

    @FXML // fx:id="datePrepare"
    private TextField datePrepare; // Value injected by FXMLLoader

    @FXML // fx:id="deliverService"
    private TextField deliverService; // Value injected by FXMLLoader

    @FXML // fx:id="deliverStatus"
    private TextField deliverStatus; // Value injected by FXMLLoader

    @FXML // fx:id="enterID"
    private TextField enterID; // Value injected by FXMLLoader

    @FXML // fx:id="gift"
    private TextField gift; // Value injected by FXMLLoader

    @FXML // fx:id="greetingText"
    private TextField greetingText; // Value injected by FXMLLoader

    @FXML // fx:id="orderID"
    private TextField orderID; // Value injected by FXMLLoader

    @FXML // fx:id="orderList"
    private ListView<String> orderList; // Value injected by FXMLLoader

    @FXML // fx:id="shopID"
    private TextField shopID; // Value injected by FXMLLoader

    @FXML // fx:id="totalPrice"
    private TextField totalPrice; // Value injected by FXMLLoader

    @FXML // fx:id="viewOrder"
    private Button viewOrder; // Value injected by FXMLLoader

    @FXML // fx:id="complaintText"
    private TextField complaintText; // Value injected by FXMLLoader

    @FXML // fx:id="openComplaint"
    private Button openComplaint; // Value injected by FXMLLoader


    @FXML // fx:id="backToCatalog"
    private Button backToCatalog; // Value injected by FXMLLoader

    @FXML
    void GoToCatalog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent roott = loader.load();
        PrimaryController cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Catalog");
        stage.show();
        Stage stagee = (Stage)backToCatalog.getScene().getWindow();
        stagee.close();

    }


    @FXML
    void openOrderComplaint(ActionEvent event)
    {
        complaintText.setVisible(true);
        sendComplaint.setVisible(true);
    }

    @FXML
    void sendOrderComplaint(ActionEvent event)
    {
        /*
        Complaint newComplaint = new Complaint();
        newComplaint.setCustomerID(currentUser.getAccountID());
        newComplaint.setOrderID(Integer.parseInt(enterID.getText()));
        newComplaint.setAccepted(false);
        newComplaint.setIn24Hours(false);
        newComplaint.setShopID(currentOrderShopID);
        newComplaint.setAnswerworkerID(0);
        newComplaint.setReturnedMoney(false);
        newComplaint.setReturnedmoneyvalue(0);
        sendComplaint.setVisible(false);
        complaintText.setVisible(false);
         */
    }


    @FXML
    void openOrder(ActionEvent event)
    {
        int theID = Integer.parseInt(enterID.getText());
        Order retrievedOrder = new Order();  // This is the order with theID
        openComplaint.setVisible(true);
        complaintText.setVisible(true);
        sendComplaint.setVisible(true);
        orderID.setText(String.valueOf(retrievedOrder.getOrderID()));
        accountID.setText(String.valueOf(currentUser.getAccountID()));
        creditNumber.setText(String.valueOf(retrievedOrder.getCreditCardNumber()));
        String creditXpire = "" + retrievedOrder.getCreditCardExpMonth() + "/" + retrievedOrder.getCreditCardExpYear();
        creditExpire.setText(creditXpire);
        creditCVV.setText(String.valueOf(retrievedOrder.getCreditCardCVV()));
        totalPrice.setText(String.valueOf(retrievedOrder.getTotalPrice()));
        shopID.setText(String.valueOf(retrievedOrder.getShopID()));
        if(retrievedOrder.isGift() == true)
            gift.setText("true");
        else
            gift.setText("false");
        String orderDate = "" + retrievedOrder.getOrderDay() + "/" + retrievedOrder.getOrderMonth() + "/" + retrievedOrder.getOrderYear();
        dateOrder.setText(orderDate);
        String prepareDate = "" + retrievedOrder.getPrepareDay() + "/" + retrievedOrder.getPrepareMonth() + "/" + retrievedOrder.getPrepareYear();
        datePrepare.setText(prepareDate);
        if(retrievedOrder.isPickUp() == true)
            deliverService.setText("Pick Up");
        else
            deliverService.setText("Delivery");
        if(retrievedOrder.isDelivered() == true)
            deliverService.setText("Delivered/Picked Up");
        else
            deliverService.setText("Not Delivered/Picked Up");
        RecepName.setText(retrievedOrder.getRecepName());
        RecepAddress.setText(retrievedOrder.getRecepAddress());
        RecepNumber.setText(String.valueOf(retrievedOrder.getRecepPhone()));
        if(retrievedOrder.getGreeting() != "")
            greetingText.setText(retrievedOrder.getGreeting());
        else
            greetingText.setText("No Greeting");
        currentOrderShopID = retrievedOrder.getShopID();
    }
    Account currentUser;
    int currentOrderShopID;
    List<Order> allOrders = new ArrayList<Order>();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RecepAddress != null : "fx:id=\"RecepAddress\" was not injected: check your FXML file 'Untitled'.";
        assert RecepName != null : "fx:id=\"RecepName\" was not injected: check your FXML file 'Untitled'.";
        assert RecepNumber != null : "fx:id=\"RecepNumber\" was not injected: check your FXML file 'Untitled'.";
        assert accountID != null : "fx:id=\"accountID\" was not injected: check your FXML file 'Untitled'.";
        assert creditCVV != null : "fx:id=\"creditCVV\" was not injected: check your FXML file 'Untitled'.";
        assert creditExpire != null : "fx:id=\"creditExpire\" was not injected: check your FXML file 'Untitled'.";
        assert creditNumber != null : "fx:id=\"creditNumber\" was not injected: check your FXML file 'Untitled'.";
        assert dateOrder != null : "fx:id=\"dateOrder\" was not injected: check your FXML file 'Untitled'.";
        assert datePrepare != null : "fx:id=\"datePrepare\" was not injected: check your FXML file 'Untitled'.";
        assert deliverService != null : "fx:id=\"deliverService\" was not injected: check your FXML file 'Untitled'.";
        assert deliverStatus != null : "fx:id=\"deliverStatus\" was not injected: check your FXML file 'Untitled'.";
        assert enterID != null : "fx:id=\"enterID\" was not injected: check your FXML file 'Untitled'.";
        assert gift != null : "fx:id=\"gift\" was not injected: check your FXML file 'Untitled'.";
        assert greetingText != null : "fx:id=\"greetingText\" was not injected: check your FXML file 'Untitled'.";
        assert orderID != null : "fx:id=\"orderID\" was not injected: check your FXML file 'Untitled'.";
        assert orderList != null : "fx:id=\"orderList\" was not injected: check your FXML file 'Untitled'.";
        assert shopID != null : "fx:id=\"shopID\" was not injected: check your FXML file 'Untitled'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'Untitled'.";
        assert viewOrder != null : "fx:id=\"viewOrder\" was not injected: check your FXML file 'Untitled'.";
        openComplaint.setVisible(true);
        sendComplaint.setVisible(true);
        complaintText.setVisible(true);
        String orderDetails = "";
        /*
        for(int i = 0 ; i < allOrders.size(); i ++)
        {
            if(allOrders.get(i).getAccountID() == currentUser.getAccountID())
            {
                String orderString = "";
                orderString = allOrders.get(i).getOrderID() + " - " + allOrders.get(i).getDate();
                orderList.getItems().add(orderString);
            }
        }
         */
    }


}
