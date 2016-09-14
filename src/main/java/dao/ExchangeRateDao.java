package dao;

import entities.ExchangeRate;
import entities.User;
import iInterfaces.IExchangeRateDao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Currency;

/**
 * Created by uzer on 12.09.2016.
 */
public class ExchangeRateDao implements IExchangeRateDao {

    public Double convertMoney(EntityManager em, Double sum, String currencyFrom, String currencyTo) {
        Query query = em.createNamedQuery("ExchangeRate.findPair");
        query.setParameter("currencyFrom", currencyFrom);
        query.setParameter("currencyTo", currencyTo);
        ExchangeRate rate;
        Double converSum;
        try {
            rate = (ExchangeRate) query.getSingleResult();
            converSum = sum * rate.getRate();
            return converSum;
        } catch (javax.persistence.NoResultException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
