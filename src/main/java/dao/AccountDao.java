package dao;

import entities.Account;
import entities.User;
import iInterfaces.IAccountDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Currency;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by uzer on 11.09.2016.
 */
public class AccountDao implements IAccountDao {

    public Account addAccount(EntityManager em, User user, Currency currency) {
        Account account = null;
        em.getTransaction().begin();
        try {
            account = new Account();
            account.setCurrency(currency.toString());
            account.setUser(user);
            account.setSum(0d);
            account.setNumber(new Date().getTime());
            em.persist(account);
            em.getTransaction().commit();
            return account;
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return account;
    }

    public Account findByNumber(EntityManager em, long number) {
        Account account = null;
        try {
            Query query = em.createNamedQuery("Account.findByNumber");
            query.setParameter("number", number);
            account = (Account) query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }
}
