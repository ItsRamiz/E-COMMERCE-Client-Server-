package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.getAllOrdersMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.swing.*;

import static com.sun.xml.bind.v2.schemagen.Util.equal;

public class DeliveryController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="deliver"
    private Button deliver; // Value injected by FXMLLoader

    @FXML // fx:id="deliveryList"
    private ListView<String> deliveryList; // Value injected by FXMLLoader

    @FXML // fx:id="listOrders"
    private ComboBox<Integer> listOrders; // Value injected by FXMLLoader

    @FXML
    private Button back;

    @FXML
    void ApplyDelivery(ActionEvent event) {
        String aString = "";
        if (deliver.getText().equals("Load Pending Orders"))
        {
            for (int i = 0; i < Orders.size(); i++) {
                aString = "#" + Orders.get(i).getOrderID() + " - Ordered On " + Orders.get(i).getOrderDay() + "/" + Orders.get(i).getOrderMonth() + "/" + Orders.get(i).getOrderYear() + " Prepare For " + Orders.get(i).getPrepareDay() + "/" + Orders.get(i).getOrderMonth() + "/" + Orders.get(i).getPrepareYear();
                deliveryList.getItems().add(aString);
                aString = "";

            }
            deliver.setText("Deliver Selected");

        }
        else
        {
            String bb = "";
            String aa = deliveryList.getSelectionModel().getSelectedItem();
            for(int i = 1 ; aa.charAt(i) != ' '; i++)
            {
                bb = Character.toString(aa.charAt(i));
            }


            System.out.println("bb = " + bb);
            int selected = Integer.parseInt(bb);
            for (int i = 0; i < Orders.size(); i++) {
                if (selected == Orders.get(i).getOrderID())
                {
                    Orders.get(i).setDelivered(true);
                    try {
                        SimpleClient.getClient().sendToServer(Orders.get(i));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

    }

    @FXML
    void openCatalog(ActionEvent event) throws IOException {
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
    }

    Account currentUser;
    static List<Order> Orders = new ArrayList<>();
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        deliver.setVisible(false);
        EventBus.getDefault().register(this);
        getAllOrdersMessage getOrdersMsg = new getAllOrdersMessage();
        SimpleClient.getClient().sendToServer(getOrdersMsg);
        back.setVisible(true);
        assert deliver != null : "fx:id=\"deliver\" was not injected: check your FXML file 'delivery.fxml'.";
        assert deliveryList != null : "fx:id=\"deliveryList\" was not injected: check your FXML file 'delivery.fxml'.";
        assert listOrders != null : "fx:id=\"listOrders\" was not injected: check your FXML file 'delivery.fxml'.";
        /*Order order1 = new Order(1,true,2,"a",3,"Tel Aviv",true,false,2,2);
        Order order2 = new Order(2,true,2,"ddasd",3,"Haifa - Laskov",true,false,2,2);
        Order order3 = new Order(3,true,2,"a",3,"Tiberias - St 5",true,false,2,2);
        Order order4 = new Order(4,true,2,"a",3,"Eilaboun",true,false,2,2);
        Orders.add(order1);
        Orders.add(order2);
        Orders.add(order3);
        Orders.add(order4);
        String x;
        x = String.valueOf(order1.getOrderID()) + " - " + order1.getDeliveredAddress();
        deliveryList.getItems().add(x);
        x = String.valueOf(order2.getOrderID()) + " - " + order2.getDeliveredAddress();
        deliveryList.getItems().add(x);
        x = String.valueOf(order3.getOrderID()) + " - " + order3.getDeliveredAddress();
        deliveryList.getItems().add(x);
        x = String.valueOf(order4.getOrderID()) + " - " + order4.getDeliveredAddress();
        deliveryList.getItems().add(x);
        listOrders.getItems().add(order1.getOrderID());
        listOrders.getItems().add(order2.getOrderID());
        listOrders.getItems().add(order3.getOrderID());
        listOrders.getItems().add(order4.getOrderID());
        */
        		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						deliver.setVisible(true);
					}
				},2000
		);
    }
    @Subscribe
    public void passOrders(PassOrdersFromServer passOrders){ // added 30/7
        System.out.println("arrived to subscriebr of passOrders in delivery controller !");
        List<Order> recievedOrders = passOrders.getRecievedOrders();
        Orders = recievedOrders;
    }


    @Subscribe
    public void PassAccountEvent(PassAccountEventDelivery passAcc){ // added 30/7
        System.out.println("Arrived To Pass Account - deliveryManager!");
        Account recvAccount = passAcc.getRecievedAccount();
        System.out.println(recvAccount.getPassword());
        System.out.println(recvAccount.getAccountID());
        System.out.println(recvAccount.getEmail());
        System.out.println(recvAccount.getFullName());
        System.out.println(recvAccount.getAddress());
        System.out.println(recvAccount.getCreditCardNumber());
        System.out.println(recvAccount.getCreditMonthExpire());
        currentUser = recvAccount;
    }


}