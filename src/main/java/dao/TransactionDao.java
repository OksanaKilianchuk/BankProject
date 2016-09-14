package dao;

import entities.Account;
import entities.Transaction;
import iInterfaces.ITransactionDAO;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by uzer on 13.09.2016.
 */
public class TransactionDao implements ITransactionDAO {
    public void createTransaction(EntityManager em, Account accFrom, Account accTo, Double sum) {
        em.getTransaction().begin();
        try {
            Transaction transaction = new Transaction();
            transaction.setAccountFrom(accFrom);
            transaction.setAccountTo(accTo);
            transaction.setSum(sum);
            transaction.setCommission(0.1);
            transaction.setPaymentPurpose("transfer between user accounts");
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        }
    }
}
