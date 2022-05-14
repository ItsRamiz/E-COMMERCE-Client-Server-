package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.Date;

public class Manager extends Worker{
    private int ShopID;
    public Manager(String fullName, String address, String email, String password, long phoneNumber, long creditCardNumber, Date creditCardExpire, int ccv, String Address, int personID)
    {
        super(fullName, address, email, password, phoneNumber, creditCardNumber, creditCardExpire, ccv, Address, personID);
        this.ShopID = 0; // Default (NO SHOP)
        this.setPrivilage(3);
    }
    public Manager() {}

    public void setShopID(int ShopID)
    {
        this.ShopID = ShopID;
    }
}
