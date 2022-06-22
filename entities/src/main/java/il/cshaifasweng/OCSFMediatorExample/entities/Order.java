package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders_table")
public class Order implements Serializable {

    @Id
    private int orderID;
    @Column(name = "Pick_Up")
    private boolean pickUp;
    @Column(name = "Shop_ID")
    private int shopID;                     // Privilage 0 = GUEST
    @Column(name = "Greeting")                 // Privilage 1 = Customer
    private String greeting;                   // Privilage 2 = Worker
    @Column(name = "Total_Price")              // Privilage 3 = Manager
    private int totalPrice;                // Privilage 4 = Chain Manager
    @Column(name = "Delivered_Address")
    private String deliveredAddress;
    //@Column(name = "Account_ID")
    //private int accountID;
    @Column(name = "Gift")
    private boolean gift;
    @Column(name = "Delivered")
    private boolean delivered;
    @Column(name = "Arrival_Time")
    private long creditCardNumber;
    @Column(name = "CVV")
    // MUST ADD CreditCardExpire Date, ArrivalTime,OrderTime
    private int cvv;
    private int day;
    private int month;
    private int year;
    /*@ManyToMany
    @Entity
    private List<Product> products;
    //@ManyToOne
    /*private Account accOrder;
*/
    public Order(){}

    public Order(int orderID, boolean pickUp, int shopID, String greeting, int totalPrice, String deliveredAddress, boolean gift, boolean delivered, long creditCardNumber, int cvv/*, Account accOrder*/) {
        this.orderID = orderID;
        this.pickUp = pickUp;
        this.shopID = shopID;
        this.greeting = greeting;
        this.totalPrice = totalPrice;
        this.deliveredAddress = deliveredAddress;
        this.gift = gift;
        this.delivered = delivered;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        //this.accOrder = accOrder;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveredAddress() {
        return deliveredAddress;
    }

    public void setDeliveredAddress(String deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public boolean sameDate(Order other)
    {
        if(this.day == other.day && this.month == other.month && this.year == other.year)
            return true;
        else
            return false;
    }
    public String getDate()
    {
        String result = "";
        result = this.day + "/" + this.month;
        return result;
    }
    /*
    public Account getAccOrder() {
        return accOrder;
    }

    public void setAccOrder(Account accOrder) {
        this.accOrder = accOrder;
    }

 */
}
