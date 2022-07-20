package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.FoundTable;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import javafx.fxml.FXML;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

public class ManagersWorkers {

    @FXML
    void initialize(){
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
    }

    @Subscribe
    public void getManagersOrWorkersFromDB(FoundTable foundTable){
        if(foundTable.getMessage().equals("managers table found")){
            List<Manager> allManagers = foundTable.getRecievedManagers();
        }
        else{
            List<Worker> allWorkers = foundTable.getRecievedWokers();
        }

    }
}
