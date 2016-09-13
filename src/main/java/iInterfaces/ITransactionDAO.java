package iInterfaces;

import entities.Account;

import javax.persistence.EntityManager;

/**
 * Created by uzer on 13.09.2016.
 */
public interface ITransactionDAO {
    public void createTransaction(EntityManager em, Account accFrom, Account accTo, Double sum);
}
