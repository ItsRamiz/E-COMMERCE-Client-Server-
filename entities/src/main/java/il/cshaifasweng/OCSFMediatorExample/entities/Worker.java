package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Column;

public class Worker
{
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Passowrd")
    private String password;
    private int personID;
    private boolean loggedIn;
    public Worker(String fullName, String email, String password,int personID)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.personID = personID;
        this.loggedIn = false;
    }
    public Worker() {}

}
