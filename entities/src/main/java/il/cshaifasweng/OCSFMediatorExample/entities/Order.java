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
    private Date arrivalTime;
    @Column(name = "Order_Time")
    private Date orderTime;
    @Column(name = "Credit_Card_Expire")
    private Date creditCardExpire;
    @Column(name = "Credit_Card_Number")
    private long creditCardNumber;
    @Column(name = "CVV")
    private int cvv;
    /*@ManyToMany
    private List<Product> products;
    @ManyToOne
    private Account accOrder;
*/
    public Order(){}

    public Order(int orderID, boolean pickUp, int shopID, String greeting, int totalPrice, String deliveredAddress, boolean gift, boolean delivered, Date arrivalTime, Date orderTime, Date creditCardExpire, long creditCardNumber, int cvv/*, Account accOrder*/) {
        this.orderID = orderID;
        this.pickUp = pickUp;
        this.shopID = shopID;
        this.greeting = greeting;
        this.totalPrice = totalPrice;
        this.deliveredAddress = deliveredAddress;
        this.gift = gift;
        this.delivered = delivered;
        this.arrivalTime = arrivalTime;
        this.orderTime = orderTime;
        this.creditCardExpire = creditCardExpire;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        //this.accOrder = accOrder;
    }

    public int getOrderID() {
        return orderID;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public int getShopID() {
        return shopID;
    }

    public String getGreeting() {
        return greeting;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDeliveredAddress() {
        return deliveredAddress;
    }

    public boolean isGift() {
        return gift;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Date getCreditCardExpire() {
        return creditCardExpire;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDeliveredAddress(String deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }


    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setCreditCardExpire(Date creditCardExpire) {
        this.creditCardExpire = creditCardExpire;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
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
