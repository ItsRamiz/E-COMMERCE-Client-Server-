package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;

public class Manager{
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Passowrd")
    private String password;
    private int ShopID;
    private int personID;
    private boolean loggedIn;
    public Manager(String fullName,String email, String password,int personID)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.personID = personID;
        this.loggedIn = false;


    }
    public Manager() {}

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShopID(int shopID) {
        ShopID = shopID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getShopID() {
        return ShopID;
    }

    public int getPersonID() {
        return personID;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
