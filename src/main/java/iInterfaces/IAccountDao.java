package iInterfaces;

import entities.Account;
import entities.User;

import javax.persistence.EntityManager;
import entities.Currency;

/**
 * Created by uzer on 11.09.2016.
 */
public interface IAccountDao {
    public void fillAccount();
    public Account addAccount(EntityManager em, User user, Currency currency);
    public Account findByNumber(EntityManager em, long number);

}
