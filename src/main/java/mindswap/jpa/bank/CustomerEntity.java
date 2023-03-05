package mindswap.jpa.bank;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CustomerEntity extends Person {

    @ManyToMany
    private Set<AccountEntity> accounts = new HashSet<>();

    public CustomerEntity(String name) {
        super(name);
    }

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void addAccount(AccountEntity account) {
        accounts.add(account);
    }

    public void printCustomers() {
        System.out.println("Customers:" +
                "id =" + getPersonId() +
                ", name ='" + getName() + '\'' +
                '}');
    }

    public void printAccounts() {
        System.out.println(getAccounts());
    }
}