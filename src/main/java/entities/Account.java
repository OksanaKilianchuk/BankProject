package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findByNumber", query = "SELECT a FROM Account a WHERE a.number= :number")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private long number;

    private String currency;

    private Double sum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    private User user;

    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL)
    List<Transaction> transactionsFrom;

    @OneToMany(mappedBy = "accountTo", cascade = CascadeType.ALL)
    List<Transaction> transactionsTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public List<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", currency=" + currency +
                ", sum=" + sum +
                ", user=" + user.getLogin() +
                '}';
    }
}
