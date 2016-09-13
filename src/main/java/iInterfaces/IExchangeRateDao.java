package iInterfaces;

import javax.persistence.EntityManager;
import java.util.Currency;

/**
 * Created by uzer on 12.09.2016.
 */
public interface IExchangeRateDao {
    public Double convertMoney(EntityManager em, Double sum, String currencyFrom, String currencyTo);
}
