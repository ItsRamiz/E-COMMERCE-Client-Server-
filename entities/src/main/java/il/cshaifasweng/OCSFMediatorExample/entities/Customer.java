package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "customers_table")
public class Customer implements Serializable{
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    //private LinkedList<Account> AccountList;
    @Column(name = "Customer Address")
    private String Address;
    @Column(name = "Customer personID")
    private int personID;
    //private LinkedList<String> MessageList;
    public Customer(String fullName, String address, String email, String password, long phoneNumber, long creditCardNumber, Date creditCardExpire, int ccv, String Address, int personID)
    {
        //super(fullName, address, email, password, phoneNumber, creditCardNumber, creditCardExpire, ccv);
        this.Address = Address;
        this.personID = personID;
        //this.setPrivilage(1);
    }
    public Customer()
    { }
    public void setId(int id) {
        this.customerID = id;
    }
    public int getCustomerID() {
        return customerID;
    }
   /* public void setAccountList(LinkedList<Account> newAccountList){
        this.AccountList = newAccountList;
    }
    public LinkedList<Account> getAccountList(){
        return this.AccountList;
    }*/
    public void setAddress(String address){
        this.Address = address;
    }
    public String getAddress(){
        return this.Address;
    }
    public void setpersonID(int number){
        this.personID = number;
    }
    public int getpersonID(){
        return this.personID;
    }
   /* public void setMessageList(LinkedList<String> newMessageList){
        this.MessageList = newMessageList;
    }
    public LinkedList<String> getMessageList(){
        return this.MessageList;
    }*/


}
