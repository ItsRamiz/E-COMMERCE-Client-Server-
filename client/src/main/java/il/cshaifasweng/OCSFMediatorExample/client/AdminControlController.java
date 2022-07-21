/**
 * Sample Skeleton for 'admincontrol.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.FoundTable;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;

public class AdminControlController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="accountsList"
    private ListView<String> accountsList; // Value injected by FXMLLoader

    @FXML // fx:id="accountsText"
    private Text accountsText; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="loadProfile"
    private Button loadProfile; // Value injected by FXMLLoader

    @FXML // fx:id="profileType"
    private ComboBox<String> profileType; // Value injected by FXMLLoader

    @FXML
    void loadSelectProfile(ActionEvent event) {

    }

    @FXML
    void openCatalog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent roott = loader.load();
        PrimaryController cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Catalog");
        stage.show();
        Stage stagee = (Stage)backButton.getScene().getWindow();
        stagee.close();

    }
    public List<Manager> all_managers = new ArrayList<>();
    public List<Worker> all_workers = new ArrayList<>();
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert accountsList != null : "fx:id=\"accountsList\" was not injected: check your FXML file 'admincontrol.fxml'.";
        assert accountsText != null : "fx:id=\"accountsText\" was not injected: check your FXML file 'admincontrol.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'admincontrol.fxml'.";
        assert loadProfile != null : "fx:id=\"loadProfile\" was not injected: check your FXML file 'admincontrol.fxml'.";
        assert profileType != null : "fx:id=\"profileType\" was not injected: check your FXML file 'admincontrol.fxml'.";
        try {
            SimpleClient.getClient().sendToServer("get Managers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            SimpleClient.getClient().sendToServer("get Workers");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            SimpleClient.getClient().sendToServer("get Accounts");
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileType.getItems().add("Customers");
        profileType.getItems().add("Workers");
        profileType.getItems().add("Managers");
    }

    @Subscribe
    public void getManagersOrWorkersFromDB(FoundTable foundTable){
        if(foundTable.getMessage().equals("managers table found")){
             all_managers = foundTable.getRecievedManagers();
        }
        else{
            all_workers = foundTable.getRecievedWokers();
        }
    }

}
