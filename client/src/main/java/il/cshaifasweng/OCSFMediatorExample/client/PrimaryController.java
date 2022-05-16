package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;

import java.awt.*;
import java.awt.Dialog;
//import java.awt.Label;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import il.cshaifasweng.OCSFMediatorExample.entities.RemovedProduct;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.sql.Update;

import static com.sun.xml.bind.v2.schemagen.Util.equal;


public class PrimaryController {
	static List<Product> allProducts = new ArrayList<>();
	public  int flowersnum2=6;
	static boolean returnedFromSecondaryController = false;
	boolean firstRun = true;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	private int catalog_start_index;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="adminEditCatalog"
	private ComboBox<String> adminEditCatalog; // Value injected by FXMLLoader


	@FXML // fx:id="EditItemDesc"
	private TextField EditItemDesc; // Value injected by FXMLLoader

	@FXML // fx:id="EditItemPrice"
	private TextField EditItemPrice; // Value injected by FXMLLoader

	@FXML // fx:id="EditItemType"
	private TextField EditItemType; // Value injected by FXMLLoader


	@FXML // fx:id="EditItemExtra"
	private TextField EditItemExtra; // Value injected by FXMLLoader

	@FXML // fx:id="customColor"
	private TextField customColor; // Value injected by FXMLLoader

	@FXML // fx:id="customPrice"
	private TextField customPrice; // Value injected by FXMLLoader

	@FXML
	private TextField customid;

	@FXML // fx:id="customType"
	private TextField customType; // Value injected by FXMLLoader

	@FXML // fx:id="CreateCustomItem"
	public Button CreateCustomItem; // Value injected by FXMLLoader

	@FXML // fx:id="CancelCustomItem"
	private Button CancelCustomItem; // Value injected by FXMLLoader

	@FXML // fx:id="FinishCustomItem"
	private Button FinishCustomItem; // Value injected by FXMLLoader


	@FXML // fx:id="flower_button1"
	private ImageView flower_button1; // Value injected by FXMLLoader

	@FXML // fx:id="flower_button2"
	private ImageView flower_button2; // Value injected by FXMLLoader

	@FXML // fx:id="flower_button3"
	private ImageView flower_button3; // Value injected by FXMLLoader

	@FXML // fx:id="flower_button4"
	private ImageView flower_button4; // Value injected by FXMLLoader

	@FXML // fx:id="flower_button5"
	private ImageView flower_button5; // Value injected by FXMLLoader

	@FXML // fx:id="flower_button6"
	private ImageView flower_button6; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name1"
	private Label name1; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name2"
	private Label name2; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name3"
	private Label name3; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name4"
	private Label name4; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name5"
	private Label name5; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name6"
	private Label name6; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price1"
	private DialogPane flower_price1; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price2"
	private DialogPane flower_price2; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price3"
	private DialogPane flower_price3; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price4"
	private DialogPane flower_price4; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price5"
	private DialogPane flower_price5; // Value injected by FXMLLoader

	@FXML // fx:id="flower_price6"
	private DialogPane flower_price6; // Value injected by FXMLLoader


	@FXML // fx:id="AddItem"
	private Button AddItem; // Value injected by FXMLLoader

	@FXML
	private Button accbtn; // Value injected by FXMLLoader

	//@FXML // fx:id="AddItem"
	//private Button remID;

	@FXML // fx:id="RemoveItem"
	private Button RemoveItem; // Value injected by FXMLLoader

	@FXML // fx:id="UpdateItem"
	private Button UpdateItem; // Value injected by FXMLLoader

	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container1; // Value injected by FXMLLoader
	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container2;
	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container3;
	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container4;
	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container5;
	@FXML // fx:id="adminEditCatalog"
	private AnchorPane container6;
	@FXML
	private Button compln;

	public static String current_button;

	@FXML
	Product createCustomitem(ActionEvent event) {

		FinishCustomItem.setText("Add Custom Item To Cart");
		CancelCustomItem.setText("Cancel Custom Item Designer");
		flower_button1.setVisible(true);
		flower_button2.setVisible(true);
		flower_button3.setVisible(true);
		flower_button4.setVisible(true);
		flower_button5.setVisible(true);
		flower_button6.setVisible(true);

		flower_price1.setVisible(true);
		flower_price2.setVisible(true);
		flower_price3.setVisible(true);
		flower_price4.setVisible(true);
		flower_price5.setVisible(true);
		flower_price6.setVisible(true);

		name1.setVisible(true);
		name2.setVisible(true);
		name3.setVisible(true);
		name4.setVisible(true);
		name5.setVisible(true);
		name6.setVisible(true);

		CreateCustomItem.setVisible(false);
		adminEditCatalog.setVisible(false);
		//	remID.setVisible(false);

		EditItemExtra.setVisible(false);
		EditItemDesc.setVisible(false);
		EditItemType.setVisible(false);
		EditItemPrice.setVisible(false);
		AddItem.setVisible(false);
		RemoveItem.setVisible(false);
		UpdateItem.setVisible(false);

		customColor.setVisible(true);
		customPrice.setVisible(true);
		customid.setVisible(true);
		customType.setVisible(true);

		CancelCustomItem.setVisible(true);
		FinishCustomItem.setVisible(true);

		Product newProduct = new Product(0,"test","test","test","test");  // Please Insert Values
		//CREATE A NEW PRODUCT DYNAMICALLY
		// Tips: A global variable called ProductID which is incremented after each product created.
		// A function GetNextProductID that returns a fresh ID for the new product to be added.
		// Static fields and functions.
		return newProduct;
	}
	@FXML
	void cancelCustomitem(ActionEvent event) {
		//.setVisible(false);
		flower_button1.setVisible(true);
		flower_button2.setVisible(true);
		flower_button3.setVisible(true);
		flower_button4.setVisible(true);
		flower_button5.setVisible(true);
		flower_button6.setVisible(true);

		flower_price1.setVisible(true);
		flower_price2.setVisible(true);
		flower_price3.setVisible(true);
		flower_price4.setVisible(true);
		flower_price5.setVisible(true);
		flower_price6.setVisible(true);

		name1.setVisible(true);
		name2.setVisible(true);
		name3.setVisible(true);
		name4.setVisible(true);
		name5.setVisible(true);
		name6.setVisible(true);

		CreateCustomItem.setVisible(true);
		adminEditCatalog.setVisible(true);

		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);
	}

	@FXML
	void adminAddItemFunc(ActionEvent event)
	{

		String newType = EditItemType.getText();
		String newDesc = EditItemDesc.getText();
		String newPrice = EditItemPrice.getText();
		// Create a new product with the current variables
		Product new_flower=new Product();
		new_flower.setPrice(newPrice);
		new_flower.setButton(newType);
		flowersnum2++;
		new_flower.setID(flowersnum2);
		try {
			SimpleClient.getClient().sendToServer(new_flower); // sends the updated product to the server class
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateCustomItem.setVisible(true);
		adminEditCatalog.setVisible(true);

		//	remID.setVisible(false);
		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);

		EditItemType.setVisible(false);
		EditItemDesc.setVisible(false);
		EditItemPrice.setVisible(false);
		EditItemExtra.setVisible(false);
	}

	@FXML
	void adminRemoveItemFunc(ActionEvent event)
	{

		String deleteID = EditItemExtra.getText();
		// Remove the item with the currnet ID from the catalog
		// create removeItem object
		// give the deleteID to the removeItem object
		// send the object to the server
		//System.out.println(deleteID);
		//	System.out.println(deleteID);
		RemovedProduct removeflower=new RemovedProduct();
		removeflower.setID(deleteID);
		flowersnum2--;
		try {
			SimpleClient.getClient().sendToServer(removeflower); // sends the updated product to the server class
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateCustomItem.setVisible(true);
		adminEditCatalog.setVisible(true);

		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);

		EditItemType.setVisible(false);
		EditItemDesc.setVisible(false);
		EditItemPrice.setVisible(false);
		EditItemExtra.setVisible(false);
		RemoveItem.setVisible(false);

		int deleted = Toint(deleteID)%6;
		for(int u =1;u<7;u++){
			if(u == deleted){
				swab(u,6);
			}
		}
		//redo Ids
		if(deleted == 1){
			container1.setVisible(false);
		}
		if(deleted == 2){
			container2.setVisible(false);
		}
		if(deleted == 3){
			container3.setVisible(false);
		}
		if(deleted == 4){
			container4.setVisible(false);
		}
		if(deleted == 5){
			container5.setVisible(false);
		}
		if(deleted == 6){
			container1.setVisible(false);
		}


	}
	void swab(int a , int b){

	}

	int Toint(String a){
		return 2;
	}
	String Tostring(int a){
		return "2";
	}
	@FXML
	void adminUpdateItemFunc(ActionEvent event)
	{
		String newType = EditItemType.getText();
		System.out.println(newType);
		String newDesc = EditItemDesc.getText();
		System.out.println(newDesc);
		String newPrice = EditItemPrice.getText();
		System.out.println(newPrice);
		String updateID = EditItemExtra.getText();
		System.out.println(updateID);

		// Update the item with the current ID with the new variables
		//remID.setVisible(false);
		CreateCustomItem.setVisible(true);
		adminEditCatalog.setVisible(true);

		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);

		EditItemType.setVisible(false);
		EditItemDesc.setVisible(false);
		EditItemPrice.setVisible(false);
		EditItemExtra.setVisible(false);


	}
	@FXML
	void chooseAdminEditCatalog(ActionEvent event)
	{
		//	remID.setVisible(false);
		CreateCustomItem.setVisible(false);
		adminEditCatalog.setVisible(true);

		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);

		String chosen = adminEditCatalog.getSelectionModel().getSelectedItem();
		System.out.println(chosen);
		System.out.println("OKAY OKAY");
		if(chosen == "Add Item")
		{
			EditItemType.setText("New Item Type");
			EditItemDesc.setText("New Item Desc");
			EditItemPrice.setText("New Item Price");
			EditItemType.setVisible(true);
			EditItemDesc.setVisible(true);
			EditItemPrice.setVisible(true);
			EditItemExtra.setVisible(false);
			System.out.println("WENT ADD");
			RemoveItem.setVisible(false);
			UpdateItem.setVisible(false);
			AddItem.setVisible(true);
		}
		if(chosen == "Remove Item")
		{
			//EditItemExtra.setText("Item ID To Remove");
			UpdateItem.setText("Remove");
			EditItemType.setVisible(false);
			EditItemDesc.setVisible(false);
			EditItemPrice.setVisible(false);
			EditItemExtra.setVisible(true);
			EditItemExtra.setText("ID to remove");
			//.setVisible(true);
			System.out.println("WENT REMOVE");
			AddItem.setVisible(false);
			UpdateItem.setVisible(false);
			RemoveItem.setVisible(true);
			//send id to server
		}
		if(chosen == "Edit Item")
		{
			EditItemType.setText("New Item Type");
			EditItemDesc.setText("New Item Desc");
			EditItemPrice.setText("New Item Price");
			EditItemExtra.setText("Item ID To Update");
			EditItemType.setVisible(true);
			EditItemDesc.setVisible(true);
			EditItemPrice.setVisible(true);
			EditItemExtra.setVisible(true);
			System.out.println("WENT UPDATE");
			AddItem.setVisible(false);
			RemoveItem.setVisible(false);
			UpdateItem.setVisible(true);
		}
	}


	public void updateFields(List<Product> allProducts)
	{
		name1.setText(allProducts.get(0).getName());
		name2.setText(allProducts.get(1).getName());
		name3.setText(allProducts.get(2).getName());
		name4.setText(allProducts.get(3).getName());
		name5.setText(allProducts.get(4).getName());
		name6.setText(allProducts.get(5).getName());

		flower_price1.setContentText(allProducts.get(0).getPrice());
		flower_price2.setContentText(allProducts.get(1).getPrice());
		flower_price3.setContentText(allProducts.get(2).getPrice());
		flower_price4.setContentText(allProducts.get(3).getPrice());
		flower_price5.setContentText(allProducts.get(4).getPrice());
		flower_price6.setContentText(allProducts.get(5).getPrice());
	}
	@FXML
	void product_clicked(javafx.scene.input.MouseEvent event ) throws IOException {
		current_button = ((ImageView)event.getSource()).getId();
		App.setRoot("secondary");
	}





	@FXML
	void initialize() throws MalformedURLException {
		assert flower_button1 != null : "fx:id=\"flower_button1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button2 != null : "fx:id=\"flower_button2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button3 != null : "fx:id=\"flower_button3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button4 != null : "fx:id=\"flower_button4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button5 != null : "fx:id=\"flower_button5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button6 != null : "fx:id=\"flower_button6\" was not injected: check your FXML file 'primary.fxml'.";
		assert name1 != null : "fx:id=\"name1\" was not injected: check your FXML file 'primary.fxml'.";
		assert name2 != null : "fx:id=\"name2\" was not injected: check your FXML file 'primary.fxml'.";
		assert name3 != null : "fx:id=\"name3\" was not injected: check your FXML file 'primary.fxml'.";
		assert name4 != null : "fx:id=\"name4\" was not injected: check your FXML file 'primary.fxml'.";
		assert name5 != null : "fx:id=\"name5\" was not injected: check your FXML file 'primary.fxml'.";
		assert name6 != null : "fx:id=\"name6\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price1 != null : "fx:id=\"flower_price1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price2 != null : "fx:id=\"flower_price2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price3 != null : "fx:id=\"flower_price3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price4 != null : "fx:id=\"flower_price4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price5 != null : "fx:id=\"flower_price5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_price6 != null : "fx:id=\"flower_price6\" was not injected: check your FXML file 'primary.fxml'.";

		customColor.setVisible(false);
		customPrice.setVisible(false);
		customid.setVisible(false);
		customType.setVisible(false);

		EditItemType.setVisible(false);
		EditItemDesc.setVisible(false);
		EditItemPrice.setVisible(false);
		EditItemExtra.setVisible(false);

		AddItem.setVisible(false);
		RemoveItem.setVisible(false);
		UpdateItem.setVisible(false);
		//remID.setVisible(false);
		CancelCustomItem.setVisible(false);
		FinishCustomItem.setVisible(false);

		adminEditCatalog.getItems().add("Add Item");
		adminEditCatalog.getItems().add("Remove Item");
		adminEditCatalog.getItems().add("Edit Item");


		initializeData();
		if(returnedFromSecondaryController)
		{
			updateFields(allProducts);
		}
	}


	void initializeData(){
		if(!returnedFromSecondaryController){

			Product flower1 = new Product(1,flower_button1.getId(),name1.getText(),"",flower_price1.getContentText());
			allProducts.add(flower1);
			Product flower2 = new Product(2,flower_button2.getId(),name2.getText(),"",flower_price2.getContentText());
			allProducts.add(flower2);
			Product flower3 = new Product(3,flower_button3.getId(),name3.getText(),"",flower_price3.getContentText());
			allProducts.add(flower3);
			Product flower4 = new Product(4,flower_button4.getId(),name4.getText(),"",flower_price4.getContentText());
			allProducts.add(flower4);
			Product flower5 = new Product(5,flower_button5.getId(),name5.getText(),"",flower_price5.getContentText());
			allProducts.add(flower5);
			Product flower6 = new Product(6,flower_button6.getId(),name6.getText(),"",flower_price6.getContentText());
			allProducts.add(flower6);

			List<Product> productList = new ArrayList<Product>() ;
			productList.add(flower1);
			productList.add(flower2);
			productList.add(flower3);
			productList.add(flower4);
			productList.add(flower5);
			productList.add(flower6);
			try {
				SimpleClient.getClient().sendToServer(productList); // sends the updated product to the server class
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date datetemp = new Date();
			Account testAccount = new Account("first name","new york","@gmail.com","123",123,323,datetemp,123);
			try {
				System.out.println("before sending the account to server");
				SimpleClient.getClient().sendToServer(testAccount); // sends the updated product to the server class
				System.out.println("after sending the account to server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else{ // this is only for limited TIME SHOULD DELETE LATER !

			Product flowerToAdd = new Product(7,"stam string","stam string 2","stam string 3","er fek");

		}

	}

	static Product getCurrent_button()
	{
		for (int i = 0; i < allProducts.size(); i++) {
			if (equal(allProducts.get(i).getButton(),current_button)) {
				return allProducts.get(i);
			}
		}
		return allProducts.get(0); // need to change this
	}

	static void setReturnedFromSecondaryController(boolean retFromSecond) {
		returnedFromSecondaryController = retFromSecond;
	}

	static boolean getReturnedFromSecondaryController(){
		return returnedFromSecondaryController ;
	}

	@FXML
	void complinstart(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("complaint.fxml"));
		Parent roott = loader.load();
		ComplaintController cc = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(roott));
		stage.setTitle("complaint application");
		stage.show();
	}

	@FXML
	void accbtnlogin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInPrim.fxml"));
		Parent roott = loader.load();
		LogInPrimary cc = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(roott));
		stage.setTitle("complaint application");
		stage.show();

		Stage stagee = (Stage) accbtn.getScene().getWindow();
		// do what you have to do
		stagee.close();
	}
}
