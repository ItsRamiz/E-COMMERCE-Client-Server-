package il.cshaifasweng.OCSFMediatorExample.entities;
import il.cshaifasweng.OCSFMediatorExample.entities.Account;
import java.util.Date;

public class Chain_Manager extends Manager {

    public Chain_Manager(String fullName, String address, String email, String password, long phoneNumber, long creditCardNumber, Date creditCardExpire, int ccv, String Address, int personID) {
       // super(fullName, address, email, password, phoneNumber, creditCardNumber, creditCardExpire, ccv, Address, personID);
        this.setShopID(-1);
        //this.setPrivilage(4);
    }

    public Chain_Manager() {
    }
}
