package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers Table")
public class Customer {

    @Id
    private int id;
    @Column(name = "Customer Email")
    private String Email;
    @Column(name = "Customer Name")
    private String Name;
    @Column(name = "Customer Address")
    private String Address;
    @Column(name = "Customer PhoneNumber")
    private String PhoneNumber;

    public Customer(int id, String email, String name, String address, String phoneNumber) {
        this.id = id;
        Email = email;
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setName(String name){
        this.Name = name;
    }
    public String getName(){
        return this.Name;
    }
    public void setAddress(String address){
        this.Address = address;
    }
    public String getAddress(){
        return this.Address;
    }
    public void setPhoneNumber(String number){
        this.PhoneNumber = number;
    }
    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
}
