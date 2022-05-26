package il.cshaifasweng.OCSFMediatorExample.client;

public class CheckMail {
    private String email;
    private boolean exists;
    private String person;
    public CheckMail(String email,String person) {
        this.email = email;
        this.person = person;
    }
    public CheckMail(boolean exists){
        this.exists = exists;
    }
    public String getEmail() {
        return email;
    }
    public boolean getExists(){
        return exists;
    }

}
