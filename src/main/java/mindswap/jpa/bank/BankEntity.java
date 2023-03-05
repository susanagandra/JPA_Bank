package mindswap.jpa.bank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    int id;

    String name;

    @OneToMany
    Set<AccountEntity> accounts = new HashSet<>();

    public BankEntity(String name) {
        this.name = name;
    }

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}