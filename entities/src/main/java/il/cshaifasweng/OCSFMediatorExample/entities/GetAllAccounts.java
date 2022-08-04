package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
import java.util.List;

public class GetAllAccounts {
    List<Account> all_accounts = new ArrayList<>();
    public GetAllAccounts(){

    }

    public void setAll_accounts(List<Account> all_accounts) {
        this.all_accounts = all_accounts;
    }

    public List<Account> getAll_accounts() {
        return all_accounts;
    }
}
