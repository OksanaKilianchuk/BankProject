package entities;

import javax.persistence.*;

@Entity
@Table(name = "rates")
@NamedQuery(name = "ExchangeRate.findPair", query = "SELECT r FROM ExchangeRate r WHERE r.currencyFrom= :currencyFrom and r.currencyTo= :currencyTo")
public class ExchangeRate {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "rate_from")
    private String currencyFrom;

    @Column(name = "rate_to")
    private String currencyTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }
}
