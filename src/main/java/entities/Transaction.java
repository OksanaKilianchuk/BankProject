package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="date", nullable = false)
    private Date date;

    private Double sum;
    private String paymentPurpose;
    private Double commission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="transactionsFrom")
    private Account accountFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="transactionsTo")
    private Account accountTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
}
