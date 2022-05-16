package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class FoundTable implements Serializable {

    private String message ;
    List<Product> recievedProducts ;

    public FoundTable(String msg, List<Product> recievedProds){
        this.message = msg;
        this.recievedProducts = recievedProds;
    }

    public String getMessage() {
        return message;
    }

    public List<Product> getRecievedProducts() {
        return recievedProducts;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecievedProducts(List<Product> recievedProducts) {
        this.recievedProducts = recievedProducts;
    }
}
