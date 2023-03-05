package mindswap.jpa.bank;

import javax.persistence.*;
import java.util.List;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    int id;

    int balance;

    @ManyToMany
    List<CustomerEntity> owners;

    @ManyToOne
    BankEntity bank;

    public AccountEntity(int balance, BankEntity bank) {
        this.balance = balance;
        this.bank = bank;
    }

    public List<CustomerEntity> getOwners() {
        return owners;
    }

    public void printBalance() {
        System.out.println(balance);
    }

    public void printAccounts() {
        System.out.println("Accounts:" +
                "id =" + id +
                ", owner ='" + getOwners() + '\'' +
                ", balance ='" + balance + '\'' +
                '}');
    }

    public void setOwner(CustomerEntity customer) {
        this.owners.add(customer);
    }
}
