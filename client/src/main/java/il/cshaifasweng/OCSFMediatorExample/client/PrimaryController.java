package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import il.cshaifasweng.OCSFMediatorExample.entities.Worker;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import java.awt.*;
import java.awt.Dialog;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import il.cshaifasweng.OCSFMediatorExample.entities.RemovedProduct;
import il.cshaifasweng.OCSFMediatorExample.entities.UpdateMessage;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;
import org.hibernate.sql.Update;

import static com.sun.xml.bind.v2.schemagen.Util.equal;


public class PrimaryController {
	public int flowersnum2 = 6;
	public int workernum2 =0;
	public int managernum2 =0;
	static boolean returnedFromSecondaryController = false;
	boolean firstRun = true;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

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
	private javafx.scene.control.Label flower_name1; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name2"
	private javafx.scene.control.Label flower_name2; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name3"
	private javafx.scene.control.Label flower_name3; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name4"
	private javafx.scene.control.Label flower_name4; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name5"
	private javafx.scene.control.Label flower_name5; // Value injected by FXMLLoader

	@FXML // fx:id="flower_name6"
	private javafx.scene.control.Label flower_name6; // Value injected by FXMLLoader

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

	//@FXML // fx:id="AddItem"
	//private Button remID;

	@FXML // fx:id="RemoveItem"
	private Button RemoveItem; // Value injected by FXMLLoader

	@FXML // fx:id="UpdateItem"
	private Button UpdateItem; // Value injected by FXMLLoader

	@FXML
	private Button printProd;

	@FXML
	private Button justButton;

	@FXML
	private Text justText;

	@FXML
	private Button nextPage;

	@FXML
	private Button prevPage;

	@FXML
	void nextPageUpate(ActionEvent event)
	{
		int difference = allProducts.size() - CatalogENDIndex;
		CatalogSTARTIndex = CatalogENDIndex;
		if(difference < 7)
		{
			CatalogENDIndex = CatalogENDIndex + difference;
		}
		else
		{
			CatalogENDIndex = CatalogENDIndex + 6;
		}
		updateFields(allProducts,1);
	}

	@FXML
	void prevPageUpate(ActionEvent event)
	{
		int difference = CatalogSTARTIndex - 6;
		if(difference >= 0)
		{
			CatalogENDIndex = CatalogSTARTIndex;
			CatalogSTARTIndex = CatalogSTARTIndex - 6;
		}
		else
		{
			CatalogENDIndex = CatalogSTARTIndex + 1;
			CatalogSTARTIndex = 0;
		}
		updateFields(allProducts,1);

	}

	@FXML
	void justView(ActionEvent event) {
		updateFields(allProducts,2);
		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run()
					{
						// Unused Timer, Please Keep
					}
				},0
		);
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

		flower_name1.setVisible(true);
		flower_name2.setVisible(true);
		flower_name3.setVisible(true);
		flower_name4.setVisible(true);
		flower_name5.setVisible(true);
		flower_name6.setVisible(true);

		justText.setVisible(false);
		justButton.setVisible(false);
	}
	@FXML
	void printProducts(ActionEvent event)
	{
		for(int i = 0 ; i < allProducts.size() ; i++)
		{
			System.out.println("ID: " + allProducts.get(i).getID());
			System.out.println("Name: " + allProducts.get(i).getName());
			System.out.println("Price: " + allProducts.get(i).getPrice());
			System.out.println("### END ###");
		}
	}
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

		flower_name1.setVisible(true);
		flower_name2.setVisible(true);
		flower_name3.setVisible(true);
		flower_name4.setVisible(true);
		flower_name5.setVisible(true);
		flower_name6.setVisible(true);

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

		Product newProduct = new Product(0, "test", "test", "test", "test");  // Please Insert Values
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

		flower_name1.setVisible(true);
		flower_name2.setVisible(true);
		flower_name3.setVisible(true);
		flower_name4.setVisible(true);
		flower_name5.setVisible(true);
		flower_name6.setVisible(true);

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
	void adminAddItemFunc(ActionEvent event) {

		String newType = EditItemType.getText();
		String newDesc = EditItemDesc.getText();
		String newPrice = EditItemPrice.getText();
		// Create a new product with the current variables
		Product new_flower = new Product();
		new_flower.setPrice(newPrice);
		new_flower.setName(newType);
		new_flower.setDetails(newDesc);
		flowersnum2++;
		new_flower.setID(flowersnum2);
		UpdateMessage updateMessage1 = new UpdateMessage("product", "add");
		updateMessage1.setProduct(new_flower);
		updateMessage1.setId(flowersnum2);
		System.out.println("before try - edit");
		try {
			System.out.println("before sending updateMessage to server ");
			SimpleClient.getClient().sendToServer(updateMessage1); // sends the updated product to the server class
			System.out.println("afater sending updateMessage to server ");
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
	//	AddItem.setVisible(false);
	}

	@FXML
	void adminRemoveItemFunc(ActionEvent event) {

		String deleteID = EditItemExtra.getText();
		// Remove the item with the currnet ID from the catalog
		// create removeItem object
		// give the deleteID to the removeItem object
		// send the object to the server
		//System.out.println(deleteID);
		//	System.out.println(deleteID);
		UpdateMessage removeType = new UpdateMessage("product", "remove");
		removeType.setDelteId(deleteID);

		flowersnum2--;
		try {
			SimpleClient.getClient().sendToServer(removeType); // sends the updated product to the server class
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
	//	RemoveItem.setVisible(false);
		//.setVisible(false);

	}

	@FXML
	void adminUpdateItemFunc(ActionEvent event) {
		String newType = EditItemType.getText();
		System.out.println(newType);
		String newDesc = EditItemDesc.getText();
		System.out.println(newDesc);
		String newPrice = EditItemPrice.getText();
		System.out.println(newPrice);
		String updateID = EditItemExtra.getText();
		System.out.println(updateID);
		Product currtProduct  = il.cshaifasweng.OCSFMediatorExample.client.PrimaryController.getCurrent_button();


		currtProduct.setPrice(newPrice);
		currtProduct.setDetails(newDesc);
		currtProduct.setName(newType);
		int castedID = Integer.parseInt(updateID);
		currtProduct.setID(castedID);

		UpdateMessage updateMessage1 = new UpdateMessage("product","edit");
		updateMessage1.setProduct(currtProduct);


		updateMessage1.setId(castedID);
		System.out.println("arrived here before sending the updatemessage1");
		try {
			SimpleClient.getClient().sendToServer(updateMessage1); // sends the updated product to the server class
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	//	UpdateItem.setVisible(false);


	}

	@FXML
	void chooseAdminEditCatalog(ActionEvent event) {
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
		if (chosen == "Add Item") {
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
		if (chosen == "Remove Item") {
			//EditItemExtra.setText("Item ID To Remove");
			//UpdateItem.setText("Remove");
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
		if (chosen == "Edit Item") {
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


	public void updateFields(List<Product> allProducts,int mode)
	{

		System.out.println("START INDEX = " + CatalogSTARTIndex);
		System.out.println("END INDEX = " + CatalogENDIndex);
		System.out.println("Length = " + allProducts.size());
		if (mode == 0) {
			flower_name1.setText(allProducts.get(0).getName());
			flower_name2.setText(allProducts.get(1).getName());
			flower_name3.setText(allProducts.get(2).getName());
			flower_name4.setText(allProducts.get(3).getName());
			flower_name5.setText(allProducts.get(4).getName());
			flower_name6.setText(allProducts.get(5).getName());

			flower_price1.setContentText(allProducts.get(0).getPrice());
			flower_price2.setContentText(allProducts.get(1).getPrice());
			flower_price3.setContentText(allProducts.get(2).getPrice());
			flower_price4.setContentText(allProducts.get(3).getPrice());
			flower_price5.setContentText(allProducts.get(4).getPrice());
			flower_price6.setContentText(allProducts.get(5).getPrice());

			flower_price1.setContentText(allProducts.get(0).getPrice());
			flower_price2.setContentText(allProducts.get(1).getPrice());
			flower_price3.setContentText(allProducts.get(2).getPrice());
			flower_price4.setContentText(allProducts.get(3).getPrice());
			flower_price5.setContentText(allProducts.get(4).getPrice());
			flower_price6.setContentText(allProducts.get(5).getPrice());
		}
		else
		{
			if(mode == 2)
			{
				// MODE = 1 Does Normal Updating , CatalogSTARTIndex and CatalogENDIndex
				CatalogSTARTIndex = 0;          // Were updated from outside the function.
				if (allProducts.size() > 5)    // MODE = 2, Do not use Mode 2, Ramiz knows what this shit does, ask him
					CatalogENDIndex = 6;
				else
					CatalogENDIndex = allProducts.size();
				System.out.println("START INDEX = " + CatalogSTARTIndex);
				System.out.println("END INDEX = " + CatalogENDIndex);
			}

			flower_name1.setText("NULL");
			flower_name2.setText("NULL");
			flower_name3.setText("NULL");
			flower_name4.setText("NULL");
			flower_name5.setText("NULL");
			flower_name6.setText("NULL");

			flower_price1.setContentText("NaN");
			flower_price2.setContentText("NaN");
			flower_price3.setContentText("NaN");
			flower_price4.setContentText("NaN");
			flower_price5.setContentText("NaN");
			flower_price6.setContentText("NaN");

			if(CatalogENDIndex - CatalogSTARTIndex > 0)
			{
				flower_name1.setText(allProducts.get(CatalogSTARTIndex).getName());
				flower_price1.setContentText(allProducts.get(CatalogSTARTIndex).getPrice());
			}

			if (CatalogENDIndex - CatalogSTARTIndex > 1)
			{
				flower_name2.setText(allProducts.get(CatalogSTARTIndex + 1).getName());
				flower_price2.setContentText(allProducts.get(CatalogSTARTIndex + 1).getPrice());
			}
			if (CatalogENDIndex - CatalogSTARTIndex > 2)
			{
				flower_name3.setText(allProducts.get(CatalogSTARTIndex + 2).getName());
				flower_price3.setContentText(allProducts.get(CatalogSTARTIndex + 2).getPrice());
			}
			if (CatalogENDIndex - CatalogSTARTIndex > 3)
			{
				flower_name4.setText(allProducts.get(CatalogSTARTIndex + 3).getName());
				flower_price4.setContentText(allProducts.get(CatalogSTARTIndex + 3).getPrice());
			}
			if (CatalogENDIndex - CatalogSTARTIndex > 4)
			{
				flower_name5.setText(allProducts.get(CatalogSTARTIndex + 4).getName());
				flower_price5.setContentText(allProducts.get(CatalogSTARTIndex + 4).getPrice());
			}
			if (CatalogENDIndex - CatalogSTARTIndex > 5)
			{
				flower_name5.setText(allProducts.get(CatalogSTARTIndex + 4).getName());
				flower_price5.setContentText(allProducts.get(CatalogSTARTIndex + 4).getPrice());
			}
			if (CatalogENDIndex - CatalogSTARTIndex == 6)
			{
				flower_name6.setText(allProducts.get(CatalogSTARTIndex + 5).getName());
				flower_price6.setContentText(allProducts.get(CatalogSTARTIndex + 5).getPrice());
			}
		}
	}
	int CatalogSTARTIndex;
	int CatalogENDIndex;

	static List<Product> allProducts = new ArrayList<>();

	@FXML
	void product_clicked(javafx.scene.input.MouseEvent event) throws IOException {
		current_button = ((ImageView) event.getSource()).getId();
		App.setRoot("secondary");
	}


	@FXML
	void initialize() throws MalformedURLException {
		justText.setVisible(true);
		justButton.setVisible(false);
		System.out.println("arrived to initialize 1");
		EventBus.getDefault().register(this);
		assert flower_button1 != null : "fx:id=\"flower_button1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button2 != null : "fx:id=\"flower_button2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button3 != null : "fx:id=\"flower_button3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button4 != null : "fx:id=\"flower_button4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button5 != null : "fx:id=\"flower_button5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_button6 != null : "fx:id=\"flower_button6\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name1 != null : "fx:id=\"flower_name1\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name2 != null : "fx:id=\"flower_name2\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name3 != null : "fx:id=\"flower_name3\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name4 != null : "fx:id=\"flower_name4\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name5 != null : "fx:id=\"flower_name5\" was not injected: check your FXML file 'primary.fxml'.";
		assert flower_name6 != null : "fx:id=\"flower_name6\" was not injected: check your FXML file 'primary.fxml'.";
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

		flower_button1.setVisible(false);
		flower_button2.setVisible(false);
		flower_button3.setVisible(false);
		flower_button4.setVisible(false);
		flower_button5.setVisible(false);
		flower_button6.setVisible(false);

		flower_price1.setVisible(false);
		flower_price2.setVisible(false);
		flower_price3.setVisible(false);
		flower_price4.setVisible(false);
		flower_price5.setVisible(false);
		flower_price6.setVisible(false);

		flower_name1.setVisible(false);
		flower_name2.setVisible(false);
		flower_name3.setVisible(false);
		flower_name4.setVisible(false);
		flower_name5.setVisible(false);
		flower_name6.setVisible(false);

		adminEditCatalog.getItems().add("Add Item");
		adminEditCatalog.getItems().add("Remove Item");
		adminEditCatalog.getItems().add("Edit Item");


		initializeData();
		if (returnedFromSecondaryController) {
			updateFields(allProducts,0);
		}
		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						justButton.setVisible(true);
					}
				},2000
		);

	}


	void initializeData() {

		try {
			SimpleClient.getClient().sendToServer("first entry"); // sends the updated product to the server class
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void retRieveDatabase(RetrieveDataBaseEvent rtEvent) {
		System.out.println("arrived to the retreivedatabse event");
		System.out.println("the current table is:");
		for (int i = 0; i < rtEvent.getRecievedList().size(); i++) {
			System.out.println(rtEvent.getRecievedList().get(i).getButton());
		}
		allProducts = rtEvent.getRecievedList();


		// TESTING THE ACCOUNTS ADD MANUALLY
		UpdateMessage new_msg=new UpdateMessage("account","add");
		Date date=new Date();
		Account new_acc=new Account("khaled","sakhnin","@eee","332",457,889,date,445,2);
		new_msg.setAccount(new_acc);
		try {
			System.out.println("before sending updateMessage to server ");
			SimpleClient.getClient().sendToServer(new_msg); // sends the updated product to the server class
			System.out.println("afater sending updateMessage to server ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new_acc=new Account("abu-nebal","haifa","@nebal.com","111",457,889,date,445,2);
		new_msg.setAccount(new_acc);
		try {
			System.out.println("before sending updateMessage to server ");
			SimpleClient.getClient().sendToServer(new_msg); // sends the updated product to the server class
			System.out.println("afater sending updateMessage to server ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new_acc=new Account("molazem-ra2oof","haifa","@ra2of.com","111",457,889,date,445,2);
		new_msg.setAccount(new_acc);
		try {
			System.out.println("before sending updateMessage to server ");
			SimpleClient.getClient().sendToServer(new_msg); // sends the updated product to the server class
			System.out.println("afater sending updateMessage to server ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void initDatabase(InitDatabaseEvent event) {
		UpdateMessage new_msg=new UpdateMessage("account","add");
		Date date=new Date();
		Account new_acc=new Account("khaled","sakhnin","@eee","332",457,889,date,445,2);
		new_msg.setAccount(new_acc);
		try {
			System.out.println("before sending updateMessage to server ");
			SimpleClient.getClient().sendToServer(new_msg); // sends the updated product to the server class
			System.out.println("afater sending updateMessage to server ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("arrived to databaseInit");
		Product flower1 = new Product(1, flower_button1.getId(), flower_name1.getText(), "", flower_price1.getContentText());
		allProducts.add(flower1);
		Product flower2 = new Product(2, flower_button2.getId(), flower_name2.getText(), "", flower_price2.getContentText());
		allProducts.add(flower2);
		Product flower3 = new Product(3, flower_button3.getId(), flower_name3.getText(), "", flower_price3.getContentText());
		allProducts.add(flower3);
		Product flower4 = new Product(4, flower_button4.getId(), flower_name4.getText(), "", flower_price4.getContentText());
		allProducts.add(flower4);
		Product flower5 = new Product(5, flower_button5.getId(), flower_name5.getText(), "", flower_price5.getContentText());
		allProducts.add(flower5);
		Product flower6 = new Product(6, flower_button6.getId(), flower_name6.getText(), "", flower_price6.getContentText());
		allProducts.add(flower6);

		List<Product> productList = new ArrayList<Product>();
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

	}

	static Product getCurrent_button() {
		for (int i = 0; i < allProducts.size(); i++) {
			if (equal(allProducts.get(i).getButton(), current_button)) {
				return allProducts.get(i);
			}
		}
		return allProducts.get(0); // need to change this
	}

	static void setReturnedFromSecondaryController(boolean retFromSecond) {
		returnedFromSecondaryController = retFromSecond;
	}

	static boolean getReturnedFromSecondaryController() {
		return returnedFromSecondaryController;
	}

	@FXML
	private Button compln;

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

	@FXML // fx:id="RemoveItem"
	private Button accbtn; // Value injected by FXMLLoader

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