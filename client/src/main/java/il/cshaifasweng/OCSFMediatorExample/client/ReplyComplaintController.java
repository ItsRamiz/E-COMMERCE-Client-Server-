/**
 * Sample Skeleton for 'replycomplaint.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReplyComplaintController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="accountID"
    private TextField accountID; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="complaintDate"
    private TextField complaintDate; // Value injected by FXMLLoader

    @FXML // fx:id="complaintID"
    private TextField complaintID; // Value injected by FXMLLoader

    @FXML // fx:id="complaintList"
    private ListView<String> complaintList; // Value injected by FXMLLoader

    @FXML // fx:id="complaintText"
    private TextField complaintText; // Value injected by FXMLLoader

    @FXML // fx:id="loadButton"
    private Button loadButton; // Value injected by FXMLLoader

    @FXML // fx:id="orderID"
    private TextField orderID; // Value injected by FXMLLoader

    @FXML // fx:id="refundCheck"
    private CheckBox refundCheck; // Value injected by FXMLLoader

    @FXML // fx:id="refundPercent"
    private ComboBox<String> refundPercent; // Value injected by FXMLLoader

    @FXML // fx:id="sendButton"
    private Button sendButton; // Value injected by FXMLLoader

    @FXML
    void SendReply(ActionEvent event)
    {
        // Edit the complaint, with the ID of selectedComplaint
        // Accepted - Accodingly
        // in24Hour - If it was answered in 24
        //


    }

    @FXML
    void addRefund(ActionEvent event)
    {
        if(refundCheck.isSelected())
        {
            refundPercent.setVisible(true);
        }
        else
            refundPercent.setVisible(false);
    }

    @FXML
    void addRefundPercent(ActionEvent event) {

    }

    @FXML
    void loadComplaints(ActionEvent event)
    {
        String SelectedIDString = "";
        int SelectedID;
        String SelectedComplaint = complaintList.getSelectionModel().getSelectedItem();
        Complaint selectedComplaint = new Complaint();
        for(int i = 11 ; SelectedComplaint.charAt(i) != ' ' ; i++)
        {
            SelectedIDString = SelectedIDString + Character.toString(SelectedComplaint.charAt(i));
        }
        SelectedID = Integer.parseInt(SelectedIDString);
        for(int i = 0 ; i < complaintList.getItems().size() ; i++) {
            if (retrievedComplaints.get(i).getComplaintID() == SelectedID) {
                selectedComplaint = retrievedComplaints.get(i);
            }
        }
        complaintID.setText(String.valueOf(selectedComplaint.getComplaintID()));
        accountID.setText(String.valueOf(selectedComplaint.getCustomerID()));
        orderID.setText(String.valueOf(selectedComplaint.getOrderID()));
        complaintDate.setText(selectedComplaint.getDate());
        complaintText.setText(selectedComplaint.getComplaintText());

    }
    @FXML
    void BackToCatalog(ActionEvent event) throws IOException {
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

    List<Complaint> retrievedComplaints = new ArrayList<>();
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert accountID != null : "fx:id=\"accountID\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert complaintDate != null : "fx:id=\"complaintDate\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert complaintID != null : "fx:id=\"complaintID\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert complaintList != null : "fx:id=\"complaintList\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert complaintText != null : "fx:id=\"complaintText\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert loadButton != null : "fx:id=\"loadButton\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert orderID != null : "fx:id=\"orderID\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert refundCheck != null : "fx:id=\"refundCheck\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert refundPercent != null : "fx:id=\"refundPercent\" was not injected: check your FXML file 'replycomplaint.fxml'.";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'replycomplaint.fxml'.";

        Complaint a = new Complaint(0,23,22,false,false,"Fuck you",2,0,false,0,23,2,2004,"");
        refundPercent.setVisible(false);
        String aString = "Complaint #" + a.getComplaintID() + " " + "Received: " + a.getDay() + "/" + a.getMonth() + "/" + a.getYear();
        complaintList.getItems().add(aString);
        retrievedComplaints.add(a);
    }

}
