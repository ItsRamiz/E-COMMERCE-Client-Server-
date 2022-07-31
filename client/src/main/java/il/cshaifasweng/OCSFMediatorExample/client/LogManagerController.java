package il.cshaifasweng.OCSFMediatorExample.client; /**
 * Sample Skeleton for 'log_manager.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.log.Log;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.getAllOrdersMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LogManagerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Day"
    private CategoryAxis Day; // Value injected by FXMLLoader

    @FXML // fx:id="FromDateText"
    private Text FromDateText; // Value injected by FXMLLoader

    @FXML // fx:id="FromDay"
    private ComboBox<Integer> FromDay; // Value injected by FXMLLoader

    @FXML // fx:id="FromMonth"
    private ComboBox<Integer> FromMonth; // Value injected by FXMLLoader

    @FXML // fx:id="FromYear"
    private ComboBox<Integer> FromYear; // Value injected by FXMLLoader

    @FXML // fx:id="LoadLogButton"
    private Button LoadLogButton; // Value injected by FXMLLoader

    @FXML // fx:id="LogChart"
    private BarChart<String, Integer> LogChart; // Value injected by FXMLLoader

    @FXML // fx:id="LogType"
    private ComboBox<String> LogType; // Value injected by FXMLLoader

    @FXML // fx:id="Number"
    private NumberAxis Number; // Value injected by FXMLLoader

    @FXML // fx:id="UntilDateText"
    private Text UntilDateText; // Value injected by FXMLLoader

    @FXML // fx:id="UntilDay"
    private ComboBox<Integer> UntilDay; // Value injected by FXMLLoader

    @FXML // fx:id="UntilMonth"
    private ComboBox<Integer> UntilMonth; // Value injected by FXMLLoader

    @FXML // fx:id="UntilYear"
    private ComboBox<Integer> UntilYear; // Value injected by FXMLLoader

    @FXML // fx:id="chooseShop"
    private ComboBox<String> chooseShop; // Value injected by FXMLLoader

    @FXML
    private Button goBack;

    @FXML
    private ComboBox<String> CompareShops;

    @FXML
    private CheckBox compareButton;

    @FXML
    void backToCatalog(ActionEvent event) throws IOException {
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
        Stage stagee = (Stage)goBack.getScene().getWindow();
        stagee.close();

    }
    static List<Order> orders = new ArrayList<Order>();
    static List<Complaint> complaints = new ArrayList<>();
    @FXML
    void loadLog(ActionEvent event)
    {
        int selectedShopID = 0;
        int selectCompareShopID = 0;
        switch(chooseShop.getValue().toString())
        {
            case "ID 0: - Chain":
                selectedShopID = 0;
                break;
            case "ID 1: Tiberias, Big Danilof":
                selectedShopID = 1;
                break;
            case "ID 2: Haifa, Merkaz Zeiv":
                selectedShopID = 2;
                break;
            case "ID 3: Tel Aviv, Ramat Aviv":
                selectedShopID = 3;
                break;
            case "ID 4: Eilat, Ice mall":
                selectedShopID = 4;
                break;
            case "ID 5: Be'er Sheva, Big Beer Sheva":
                selectedShopID = 5;
                break;
        }
        if(compareButton.isSelected())
        {
            switch (CompareShops.getValue().toString()) {
                case "ID 0: - Chain":
                    selectCompareShopID = 0;
                    break;
                case "ID 1: Tiberias, Big Danilof":
                    selectCompareShopID = 1;
                    break;
                case "ID 2: Haifa, Merkaz Zeiv":
                    selectCompareShopID = 2;
                    break;
                case "ID 3: Tel Aviv, Ramat Aviv":
                    selectCompareShopID = 3;
                    break;
                case "ID 4: Eilat, Ice mall":
                    selectCompareShopID = 4;
                    break;
                case "ID 5: Be'er Sheva, Big Beer Sheva":
                    selectCompareShopID = 5;
                    break;
            }
        }
        String LogTypeString = LogType.getSelectionModel().getSelectedItem();
        List<Order> searchResultOrder = new ArrayList<Order>();
        List<Complaint> searchResultComplaint = new ArrayList<Complaint>();
        int firstWorks = 0, LastWorks = 0, totalIncome = 0, i = 0;
        XYChart.Series set = new XYChart.Series();
        if (LogTypeString == "Income Log")
        {
            searchResultOrder = searchOrderResult(selectedShopID);
            System.out.println("First Shop Orders");
            printList(searchResultOrder);
            while (LastWorks < searchResultOrder.size()) {
                if (searchResultOrder.get(firstWorks).sameDate(searchResultOrder.get(LastWorks))) {
                    totalIncome = totalIncome + searchResultOrder.get(LastWorks).getTotalPrice();
                    LastWorks++;
                    if (LastWorks == searchResultOrder.size()) {
                        set.getData().add(new XYChart.Data(searchResultOrder.get(LastWorks - 1).getDate(), totalIncome));
                        totalIncome = 0;
                    }
                } else {
                    set.getData().add(new XYChart.Data(searchResultOrder.get(firstWorks).getDate(), totalIncome));
                    firstWorks = LastWorks;
                    totalIncome = 0;
                }
            }
            LogChart.getData().addAll(set);

        } else if (LogTypeString == "Orders Log") {
            searchResultOrder = searchOrderResult(selectedShopID);
            int totalOrders = 0;
            firstWorks = 0;
            LastWorks = 0;
            while (LastWorks < searchResultOrder.size()) {
                if (searchResultOrder.get(firstWorks).sameDate(searchResultOrder.get(LastWorks))) {
                    totalOrders++;
                    LastWorks++;
                    if (LastWorks == searchResultOrder.size()) {
                        set.getData().add(new XYChart.Data(searchResultOrder.get(LastWorks - 1).getDate(), totalOrders));
                        System.out.println("Insert " + totalOrders);
                        totalOrders = 0;
                    }
                } else {
                    set.getData().add(new XYChart.Data(searchResultOrder.get(firstWorks).getDate(), totalOrders));
                    System.out.println("Insert " + totalOrders);
                    firstWorks = LastWorks;
                    totalOrders = 0;
                }
            }
            LogChart.getData().addAll(set);
        } else {
            searchResultComplaint = searchComplaintResult(selectedShopID);
            int totalComplaints = 0;
            firstWorks = 0;
            LastWorks = 0;
            while (LastWorks < searchResultComplaint.size()) {
                if (searchResultComplaint.get(firstWorks).sameDate(searchResultComplaint.get(LastWorks))) {
                    totalComplaints++;
                    LastWorks++;
                    if (LastWorks == searchResultComplaint.size()) {
                        set.getData().add(new XYChart.Data(searchResultComplaint.get(LastWorks - 1).getDate(), totalComplaints));
                        totalComplaints = 0;
                    }
                } else {
                    set.getData().add(new XYChart.Data(searchResultComplaint.get(firstWorks).getDate(), totalComplaints));
                    firstWorks = LastWorks;
                    totalComplaints = 0;
                }
            }
            LogChart.getData().addAll(set);
        }
        if(compareButton.isSelected())
        {
            firstWorks = 0;
            LastWorks = 0;
            totalIncome = 0;
            i = 0;
            XYChart.Series set2 = new XYChart.Series();
            searchResultOrder.clear();
            searchResultComplaint.clear();
            if (LogTypeString == "Income Log")
            {
                searchResultOrder = searchOrderResult(selectCompareShopID);
                System.out.println("Second Shop Orders");
                printList(searchResultOrder);
                while (LastWorks < searchResultOrder.size())
                {
                    if (searchResultOrder.get(firstWorks).sameDate(searchResultOrder.get(LastWorks))) {
                        totalIncome = totalIncome + searchResultOrder.get(LastWorks).getTotalPrice();
                        LastWorks++;
                        if (LastWorks == searchResultOrder.size()) {
                            set2.getData().add(new XYChart.Data(searchResultOrder.get(LastWorks - 1).getDate(), totalIncome));
                            totalIncome = 0;
                        }
                    } else {
                        set2.getData().add(new XYChart.Data(searchResultOrder.get(firstWorks).getDate(), totalIncome));
                        firstWorks = LastWorks;
                        totalIncome = 0;
                    }
                }
                LogChart.getData().addAll(set2);

            } else if (LogTypeString == "Orders Log") {
                searchResultOrder = searchOrderResult(selectCompareShopID);
                int totalOrders = 0;
                firstWorks = 0;
                LastWorks = 0;
                while (LastWorks < searchResultOrder.size()) {
                    if (searchResultOrder.get(firstWorks).sameDate(searchResultOrder.get(LastWorks))) {
                        totalOrders++;
                        LastWorks++;
                        if (LastWorks == searchResultOrder.size()) {
                            set2.getData().add(new XYChart.Data(searchResultOrder.get(LastWorks - 1).getDate(), totalOrders));
                            System.out.println("Insert " + totalOrders);
                            totalOrders = 0;
                        }
                    } else {
                        set2.getData().add(new XYChart.Data(searchResultOrder.get(firstWorks).getDate(), totalOrders));
                        System.out.println("Insert " + totalOrders);
                        firstWorks = LastWorks;
                        totalOrders = 0;
                    }
                }
                LogChart.getData().addAll(set);
            } else {
                searchResultComplaint = searchComplaintResult(selectCompareShopID);
                int totalComplaints = 0;
                firstWorks = 0;
                LastWorks = 0;
                while (LastWorks < searchResultComplaint.size()) {
                    if (searchResultComplaint.get(firstWorks).sameDate(searchResultComplaint.get(LastWorks))) {
                        totalComplaints++;
                        LastWorks++;
                        if (LastWorks == searchResultComplaint.size()) {
                            set2.getData().add(new XYChart.Data(searchResultComplaint.get(LastWorks - 1).getDate(), totalComplaints));
                            totalComplaints = 0;
                        }
                    } else {
                        set2.getData().add(new XYChart.Data(searchResultComplaint.get(firstWorks).getDate(), totalComplaints));
                        firstWorks = LastWorks;
                        totalComplaints = 0;
                    }
                }

            }
        }
    }
    void printList(List<Order> orders)
    {
        for(int i = 0 ; i < orders.size() ; i++)
        {
            System.out.println(orders.get(i).getGreeting());
        }
    }

    List<Complaint> searchComplaintResult(int shopID)
    {
        int selectedShopID = 0;
        int selectCompareShopID = 0;
        int FromDayInt = FromDay.getValue().intValue();
        int UntilDayInt = UntilDay.getValue().intValue();
        int FromMonthInt = FromMonth.getValue().intValue();
        int UntilMonthInt = UntilMonth.getValue().intValue();
        int FromYearInt = FromYear.getValue().intValue();
        int UntilYearInt = UntilYear.getValue().intValue();
        String LogTypeString = LogType.getSelectionModel().getSelectedItem();
        List<Complaint> searchResultComplaint = new ArrayList<Complaint>();
        int firstWorks = 0, LastWorks = 0, totalIncome = 0, i = 0;
        XYChart.Series set = new XYChart.Series();
        for (i = 0; i < complaints.size(); i++) {
            if (complaints.get(i).getYear() <= UntilYearInt && complaints.get(i).getYear() >= FromYearInt)
            {
                if (complaints.get(i).getMonth() <= UntilMonthInt && complaints.get(i).getMonth() >= FromMonthInt)
                {
                    if (complaints.get(i).getDay() <= UntilDayInt && complaints.get(i).getDay() >= FromDayInt)
                    {
                        if(complaints.get(i).getShopID() == shopID)
                        searchResultComplaint.add(complaints.get(i));
                    }
                }
            }
        }
        return searchResultComplaint;

    }
    List<Order> searchOrderResult(int shopID)
    {
        int selectedShopID = 0;
        int selectCompareShopID = 0;
        int FromDayInt = FromDay.getValue().intValue();
        int UntilDayInt = UntilDay.getValue().intValue();
        int FromMonthInt = FromMonth.getValue().intValue();
        int UntilMonthInt = UntilMonth.getValue().intValue();
        int FromYearInt = FromYear.getValue().intValue();
        int UntilYearInt = UntilYear.getValue().intValue();
        String LogTypeString = LogType.getSelectionModel().getSelectedItem();
        List<Order> searchResultOrder = new ArrayList<Order>();
        int firstWorks = 0, LastWorks = 0, totalIncome = 0, i = 0;
        XYChart.Series set = new XYChart.Series();
        for (i = 0; i < orders.size(); i++)
        {
                if (orders.get(i).getOrderYear() <= UntilYearInt && orders.get(i).getOrderYear() >= FromYearInt)
                {
                    if (orders.get(i).getOrderMonth() <= UntilMonthInt && orders.get(i).getOrderMonth() >= FromMonthInt)
                    {
                        if (orders.get(i).getOrderDay() <= UntilDayInt && orders.get(i).getOrderDay() >= FromDayInt)
                        {
                            if(orders.get(i).getShopID() == shopID) {
                                searchResultOrder.add(orders.get(i));
                            }
                        }
                    }
                }
        }
        return searchResultOrder;
    }
    @FXML
    void clickedCompareButton(ActionEvent event)
    {
        if(compareButton.isSelected())
        {
            CompareShops.getItems().add("ID 0: - Chain");
            CompareShops.getItems().add("ID 1: Tiberias, Big Danilof");
            CompareShops.getItems().add("ID 2: Haifa, Merkaz Zeiv");
            CompareShops.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
            CompareShops.getItems().add("ID 4: Eilat, Ice mall");
            CompareShops.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");
            CompareShops.setVisible(true);
        }
        else
        {
            CompareShops.setVisible(false);
        }


    }
    Account currentUser;
    List<Complaint> allComplaints ;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        // added 30/7
        EventBus.getDefault().register(this);
        // added 30/7
        System.out.println("before sending getAllOrders message !");
        getAllOrdersMessage getOrdersMsg = new getAllOrdersMessage();
        // added 30/7
        SimpleClient.getClient().sendToServer(getOrdersMsg);
        // added 30/7
        System.out.println("after sending getAllOrders message !");
        assert CompareShops != null : "fx:id=\"CompareShops\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert Day != null : "fx:id=\"Day\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert FromDateText != null : "fx:id=\"FromDateText\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert FromDay != null : "fx:id=\"FromDay\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert FromMonth != null : "fx:id=\"FromMonth\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert FromYear != null : "fx:id=\"FromYear\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert LoadLogButton != null : "fx:id=\"LoadLogButton\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert LogChart != null : "fx:id=\"LogChart\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert LogType != null : "fx:id=\"LogType\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert Number != null : "fx:id=\"Number\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert UntilDateText != null : "fx:id=\"UntilDateText\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert UntilDay != null : "fx:id=\"UntilDay\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert UntilMonth != null : "fx:id=\"UntilMonth\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert UntilYear != null : "fx:id=\"UntilYear\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert chooseShop != null : "fx:id=\"chooseShop\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert compareButton != null : "fx:id=\"compareButton\" was not injected: check your FXML file 'log_manager.fxml'.";
        assert goBack != null : "fx:id=\"goBack\" was not injected: check your FXML file 'log_manager.fxml'.";
        CompareShops.setVisible(false);

        int i;
        for(i = 1 ; i < 31 ; i++)
        {
            FromDay.getItems().add(i);
            UntilDay.getItems().add(i);
        }
        for(i = 1 ; i < 13 ; i++)
        {
            FromMonth.getItems().add(i);
            UntilMonth.getItems().add(i);
        }
        for(i = 2022 ; i < 2030 ; i++)
        {
            FromYear.getItems().add(i);
            UntilYear.getItems().add(i);
        }
        /*
        switch (currentUser.getBelongShop()) {
            case 0:
                chooseShop.getItems().add("ID 0: - Chain");
                chooseShop.getItems().add("ID 1: Tiberias, Big Danilof");
                chooseShop.getItems().add("ID 2: Haifa, Merkaz Zeiv");
                chooseShop.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
                chooseShop.getItems().add("ID 4: Eilat, Ice mall");
                chooseShop.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");
                break;
            case 1:
                chooseShop.getItems().add("ID 1: Tiberias, Big Danilof");
                break;
            case 2:
                chooseShop.getItems().add("ID 2: Haifa, Merkaz Zeiv");
                break;
            case 3:
                chooseShop.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
                break;
            case 4:
                chooseShop.getItems().add("ID 4: Eilat, Ice mall");
                break;
            case 5:
                chooseShop.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");
                break;
        }

         */
        /*
            chooseShop.getItems().add("ID 0: - Chain");
            chooseShop.getItems().add("ID 1: Tiberias, Big Danilof");
            chooseShop.getItems().add("ID 2: Haifa, Merkaz Zeiv");
            chooseShop.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
            chooseShop.getItems().add("ID 4: Eilat, Ice mall");
            chooseShop.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");

         */

        LogType.getItems().add("Income Log");
        LogType.getItems().add("Orders Log");
        LogType.getItems().add("Complaint Log");
    }
    @Subscribe
    public void complaintEvent(PassAllComplaintsEvent allComps){ // added new 30/7
        System.out.println("arrived to complaintEvent Subscriber in log manager controller!!!!!");
        List<Complaint> recievedComplaints = allComps.getComplaintsToPass();
        allComplaints = allComps.getComplaintsToPass();
        for(int i=0;i<recievedComplaints.size();i++){
            System.out.println(recievedComplaints.get(i).getDay());
        }
    }

    @Subscribe
    public void passOrders(PassOrdersFromServer passOrders){ // added 30/7
        System.out.println("arrived to subscriebr of passOrders in logManager controller !");
        List<Order> recievedOrders = passOrders.getRecievedOrders();
        for(int i=0;i<recievedOrders.size();i++){
            System.out.println(recievedOrders.get(i).getOrderYear());
        }
    }

    @Subscribe
    public void PassAccountEvent(PassAccountEventLogManager passAcc){ // added 30/7
        System.out.println("Arrived To Pass Account - log manager");
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
