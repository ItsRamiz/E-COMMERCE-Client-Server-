package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;

import java.awt.*;
import java.awt.Dialog;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.sql.Update;

public class LogInPrimary {

    @FXML
    private Button Guest;

    @FXML
    private Button LogIn;

    @FXML
    private Button Register;

    @FXML
    void gotoCatalog(ActionEvent event) throws IOException {
        System.out.println("gotoCatalog");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent roott = loader.load();
        PrimaryController cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Catalog");
        stage.show();
        Stage stagee = (Stage) Guest.getScene().getWindow();
        // do what you have to do
        stagee.close();

    }

    @FXML
    void gotoLogInSecondary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInSecond.fxml"));
        Parent roott = loader.load();
        LogInSecondary cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Log In");
        stage.show();
        Stage stagee = (Stage) LogIn.getScene().getWindow();
        // do what you have to do
        stagee.close();

    }

    @FXML
    void gotoRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent roott = loader.load();
        RegisterController cc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(roott));
        stage.setTitle("Register");
        stage.show();
        Stage stagee = (Stage) Register.getScene().getWindow();
        // do what you have to do
        stagee.close();

    }

}
