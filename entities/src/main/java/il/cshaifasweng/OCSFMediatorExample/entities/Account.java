package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "accounts_table")
public class Account implements Serializable {

    @Id
    private int accountID;
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Address")
    private String address;                 // Privilage 0 = GUEST
    @Column(name = "Email")                 // Privilage 1 = Customer
    private String email;                   // Privilage 2 = Worker
    @Column(name = "Passowrd")              // Privilage 3 = Manager
    private String password;                // Privilage 4 = Chain Manager
    @Column(name = "Phone_Number")
    private long phoneNumber;
    @Column(name = "Credit_Card_Number")
    private long creditCardNumber;
    @Column(name = "Expire_Date")
    private Date creditCardExpire;
    private int creditDayExpire;
    private int creditMonthExpire;
    private int creditYearExpire;
    @Column(name = "CVV")
    private int ccv;
    @Column(name = "Logged_In")
    private Boolean loggedIn;
    //private List<Complaint> accountComplaints = new List<Complaint>;
    //private List<Order> accountOrders = new List<Order>;
    //@OneToOne
    //private Cart accCart;
    /*@OneToMany
    private List<Order> AccountOrders;*/
    private int belongShop;
    //private int wallet;
    static List<Product> allProducts = new ArrayList<>();



    public Account(String fullName, String address,String email, String password,long phoneNumber, long creditCardNumber,Date creditCardExpire,int ccv,int belongShop)
    {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpire = creditCardExpire;
        this.ccv = ccv;
        this.loggedIn = false;
        this.belongShop = belongShop;
        //this.wallet = 0;
       // this.AccountOrders = null;
    }
    public Account()
    {

    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCreditCardExpire(Date creditCardExpire) {
        this.creditCardExpire = creditCardExpire;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public void setLogged(Boolean logged) {
        this.loggedIn = logged;
    }

    public void setBelongShop(int belongShop) {
        this.belongShop = belongShop;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public Date getCreditCardExpire() {
        return creditCardExpire;
    }

    public int getCcv() {
        return ccv;
    }

    public Boolean getLogged() {
        return loggedIn;
    }

    public int getBelongShop() {
        return belongShop;
    }

   /* public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }*/
}