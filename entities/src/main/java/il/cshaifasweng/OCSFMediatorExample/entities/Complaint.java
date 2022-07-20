package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "complaints_table")
public class Complaint implements Serializable {

    @Id
    int complaintID;
    @Column(name = "Customer_Id")
    int CustomerID;
    @Column(name = "Order_Id")
    int OrderID;
    @Column(name = "Accepted")
    boolean Accepted; // Update
    @Column(name = "in24Hours")
    boolean in24Hours;   // Update
    @Column(name = "complaintText")
    String complaintText;
    @Column(name = "shop_Id")
    int shopID;
    @Column(name = "AnswerWorker_Id")
    int answerworkerID;
    @Column(name = "returnedMoney")
    boolean returnedMoney;
    @Column(name = "returnedmoneyvalue")
    int returnedmoneyvalue;
    @Column(name = "RecDay")
    private int day;
    @Column(name = "RecMonth")
    private int month;
    @Column(name = "RecYear")
    private int year;
    @Column(name = "Text")
    private String ComplaintText;   // This is the reply text - Update

    public Complaint(int complaintID,int CustomerID, int OrderID, boolean Accepted, boolean in24Hours, String complaintText, int shopID, int answerworkerID, boolean returnedMoney, int returnedmoneyvalue,int day,int month,int year) {

        this.complaintID = complaintID;
        this.CustomerID = CustomerID;
        this.OrderID = OrderID;
        this.Accepted = Accepted;
        this.in24Hours = in24Hours;
        this.complaintID = complaintID;
        this.shopID = shopID;
        this.answerworkerID = answerworkerID;
        this.returnedMoney = returnedMoney;
        this.returnedmoneyvalue = returnedmoneyvalue;
    }

    public  Complaint() {
    }

    public int getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(int complaintID) {
        this.complaintID = complaintID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public boolean isAccepted() {
        return Accepted;
    }

    public void setAccepted(boolean accepted) {
        Accepted = accepted;
    }

    public boolean isIn24Hours() {
        return in24Hours;
    }

    public void setIn24Hours(boolean in24Hours) {
        this.in24Hours = in24Hours;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public int getAnswerworkerID() {
        return answerworkerID;
    }

    public void setAnswerworkerID(int answerworkerID) {
        this.answerworkerID = answerworkerID;
    }

    public boolean isReturnedMoney() {
        return returnedMoney;
    }

    public void setReturnedMoney(boolean returnedMoney) {
        this.returnedMoney = returnedMoney;
    }

    public int getReturnedmoneyvalue() {
        return returnedmoneyvalue;
    }

    public void setReturnedmoneyvalue(int returnedmoneyvalue) {
        this.returnedmoneyvalue = returnedmoneyvalue;
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
    public String getDate()
    {
        String result = "";
        result = this.day + "/" + this.month;
        return result;
    }
    public boolean sameDate(Complaint other)
    {
        if(this.day == other.day && this.month == other.month && this.year == other.year)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintID=" + complaintID +
                ", CustomerID=" + CustomerID +
                ", OrderID=" + OrderID +
                ", Accepted=" + Accepted +
                ", in24Hours=" + in24Hours +
                ", complaintText='" + complaintText + '\'' +
                ", shopID=" + shopID +
                ", answerworkerID=" + answerworkerID +
                ", returnedMoney=" + returnedMoney +
                ", returnedmoneyvalue=" + returnedmoneyvalue +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", ComplaintText='" + ComplaintText + '\'' +
                '}';
    }
}