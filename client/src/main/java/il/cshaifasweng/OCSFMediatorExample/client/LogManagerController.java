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
import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
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
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    void backToCatalog(ActionEvent event) throws IOException {
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
    void loadLog(ActionEvent event) {
        int FromDayInt = FromDay.getValue().intValue();
        int UntilDayInt = UntilDay.getValue().intValue();
        int FromMonthInt = FromMonth.getValue().intValue();
        int UntilMonthInt = UntilMonth.getValue().intValue();
        int FromYearInt = FromYear.getValue().intValue();
        int UntilYearInt = UntilYear.getValue().intValue();
        String LogTypeString = LogType.getSelectionModel().getSelectedItem();
        List<Order> searchResultOrder = new ArrayList<Order>();
        List<Complaint> searchResultComplaint = new ArrayList<Complaint>();
        int firstWorks = 0, LastWorks = 0, totalIncome = 0, i = 0;
        XYChart.Series set = new XYChart.Series();
        if (LogTypeString == "Income Log" || LogTypeString == "Orders Log")
        {
            for (i = 0; i < orders.size(); i++)
            {
                if (orders.get(i).getYear() <= UntilYearInt && orders.get(i).getYear() >= FromYearInt)
                {
                    if (orders.get(i).getMonth() <= UntilMonthInt && orders.get(i).getMonth() >= FromMonthInt)
                    {
                        if (orders.get(i).getDay() <= UntilDayInt && orders.get(i).getDay() >= FromDayInt)
                        {
                            searchResultOrder.add(orders.get(i));
                        }
                    }
                }
            }
        }
        else
        {
            for (i = 0; i < complaints.size(); i++) {
                if (complaints.get(i).getYear() <= UntilYearInt && complaints.get(i).getYear() >= FromYearInt)
                {
                    if (complaints.get(i).getMonth() <= UntilMonthInt && complaints.get(i).getMonth() >= FromMonthInt)
                    {
                        if (complaints.get(i).getDay() <= UntilDayInt && complaints.get(i).getDay() >= FromDayInt)
                        {
                            searchResultComplaint.add(complaints.get(i));
                        }
                    }
                }
            }

        }
        if (LogTypeString == "Income Log") {
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
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
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
        chooseShop.getItems().add("ID 0: - Chain");
        chooseShop.getItems().add("ID 1: Tiberias, Big Danilof");
        chooseShop.getItems().add("ID 2: Haifa, Merkaz Zeiv");
        chooseShop.getItems().add("ID 3: Tel Aviv, Ramat Aviv");
        chooseShop.getItems().add("ID 4: Eilat, Ice mall");
        chooseShop.getItems().add("ID 5: Be'er Sheva, Big Beer Sheva");

        LogType.getItems().add("Income Log");
        LogType.getItems().add("Orders Log");
        LogType.getItems().add("Complaint Log");
        Order new1 = new Order(1,false,2,"First",500,"aa",true,true,12,12);
        new1.setDay(17);
        new1.setMonth(5);
        new1.setYear(2022);
        Order new2 = new Order(1,false,2,"Second",392,"aa",true,true,12,12);
        new2.setDay(17);
        new2.setMonth(5);
        new2.setYear(2022);
        Order new3 = new Order(1,false,2,"Third",55,"aa",true,true,12,12);
        new3.setDay(23);
        new3.setMonth(5);
        new3.setYear(2022);
        Order new4 = new Order(1,false,2,"Fourth",500,"aa",true,true,12,12);
        new4.setDay(28);
        new4.setMonth(5);
        new4.setYear(2022);
        orders.add(new1);
        orders.add(new2);
        orders.add(new3);
        orders.add(new4);
        LogChart.maxWidth(20);
        Day.setAnimated(false);

        /*
        XYChart.Series set = new XYChart.Series();
        set.getData().add(new XYChart.Data("Test1",2));
        set.getData().add(new XYChart.Data("Test2",5));
        set.getData().add(new XYChart.Data("Test3",10));
        LogChart.getData().addAll(set);
        */



    }

}
