package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.Date;

public class Worker extends Customer{

    public Worker(String fullName, String address, String email, String password, long phoneNumber, long creditCardNumber, Date creditCardExpire, int ccv, String Address, int personID)
    {
        super(fullName, address, email, password, phoneNumber, creditCardNumber, creditCardExpire, ccv, Address, personID);
        this.setPrivilage(2);
    }
    public Worker() {}
}
