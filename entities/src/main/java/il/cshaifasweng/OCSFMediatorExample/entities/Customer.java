package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customers_table")
public class Customer extends Account {
    @Id
    private int customerID;
    private LinkedList<Account> AccountList;
    private String Address;
    private int personID;
    private LinkedList<String> MessageList;
    public Customer(String fullName, String address, String email, String password, long phoneNumber, long creditCardNumber, Date creditCardExpire, int ccv, String Address, int personID)
    {
        super(fullName, address, email, password, phoneNumber, creditCardNumber, creditCardExpire, ccv);
        this.Address = Address;
        this.personID = personID;
        this.setPrivilage(1);
    }
    public Customer()
    { }

}
