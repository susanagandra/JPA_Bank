import mindswap.jpa.bank.AccountEntity;
import mindswap.jpa.bank.BankEntity;
import mindswap.jpa.bank.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank");
        EntityManager em = emf.createEntityManager();

        // Insert Bank
        System.out.println("Bank name");
        em.getTransaction().begin();

        BankEntity bank1 = new BankEntity("Milleniuander");
        em.persist(bank1);

        // Inserting and listing all the customers
        System.out.println("Customers");

        CustomerEntity c1 = new CustomerEntity("Rui");
        CustomerEntity c2 = new CustomerEntity("Susana");
        CustomerEntity c3 = new CustomerEntity("Nuno");
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.createQuery("SELECT a FROM CustomerEntity a", CustomerEntity.class)
                .getResultList()
                .forEach(CustomerEntity::printCustomers);

        // Insert Accounts
        AccountEntity a1 = new AccountEntity(1000, bank1);
        AccountEntity a2 = new AccountEntity(1750, bank1);
        AccountEntity a3 = new AccountEntity(43693464, bank1);
        AccountEntity a4 = new AccountEntity(1, bank1);

        c1.addAccount(a1);
        c1.addAccount(a4);
        c2.addAccount(a2);
        c3.addAccount(a3);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);

        // listing all the accounts of one customer
        em.createQuery("SELECT a FROM CustomerEntity a WHERE a = :customer", CustomerEntity.class)
                .setParameter("customer", c2)
                .getResultList()
                .forEach(CustomerEntity::printAccounts);

        //Retrieve the balance of one particular account
        em.createQuery("SELECT a FROM AccountEntity a WHERE a = :account", AccountEntity.class)
                .setParameter("account", a1)
                .getSingleResult()
                .printBalance();

        em.getTransaction().commit();
    }
}
