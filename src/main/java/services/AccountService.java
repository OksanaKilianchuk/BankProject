package services;

import dao.AccountDao;
import dao.ExchangeRateDao;
import dao.TransactionDao;
import dao.UserDao;
import entities.Account;
import entities.Currency;
import entities.Transaction;
import entities.User;
import iInterfaces.IAccountDao;
import iInterfaces.IExchangeRateDao;
import iInterfaces.ITransactionDAO;
import iInterfaces.IUserDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Created by uzer on 13.09.2016.
 */
public class AccountService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");
    EntityManager em = emf.createEntityManager();
    IUserDao userDao = new UserDao();
    IAccountDao accountDao = new AccountDao();
    IExchangeRateDao rateDao = new ExchangeRateDao();


    public boolean transferMoney(long numberFrom, long numberTo, Double sum) {
        IAccountDao accountDao = new AccountDao();
        IExchangeRateDao rateDao = new ExchangeRateDao();
        ITransactionDAO transactionDAO = new TransactionDao();

        em.getTransaction().begin();
        try {
            Account accountFrom = accountDao.findByNumber(em, numberFrom);
            Account accountTo = accountDao.findByNumber(em, numberTo);
            if (!accountFrom.getCurrency().equals(accountTo.getCurrency())) {
                sum = rateDao.convertMoney(em, sum, accountFrom.getCurrency(), accountTo.getCurrency());
            }
            accountFrom.setSum(accountFrom.getSum() - sum);
            accountTo.setSum(accountTo.getSum() + sum);
            em.refresh(accountFrom);
            em.refresh(accountTo);
            transactionDAO.createTransaction(em, accountFrom, accountTo, sum);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public boolean createAccount(User user, Currency currency) {
        Account account = accountDao.addAccount(em, user, currency);
        if (account != null)
            return true;
        else return false;
    }

    public void viewAccounts(User user) {
        userDao.printAllAccounts(user);
    }

    public void printAllTransaction(User user) {
        for (Account account : user.getAccounts()) {
            System.out.println(account.getTransactionsFrom().toString());
            System.out.println(account.getTransactionsTo().toString());
        }
    }

    public boolean fillAccount(Long number, Double sum, Currency currency) {
        em.getTransaction().begin();
        try {
            Account account = accountDao.findByNumber(em, number);
            if (!account.getCurrency().equals(currency)) {
                sum = rateDao.convertMoney(em, sum, account.getCurrency(), currency.toString());
            }
            account.setSum(account.getSum() + sum);
            em.refresh(account);
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    public Double totalFunds(User user, Currency currency){
        Double totalSum=0.;
        for (Account account :user.getAccounts()) {
            Double sum = account.getSum();
            if (!account.getCurrency().equals(currency))
                sum = rateDao.convertMoney(em, sum, account.getCurrency(), currency.toString());
            totalSum += sum;
        }
        return totalSum;
    }

    public User autorization(String login, String pass) {
        return userDao.findUser(em, login, pass);
    }

    public void logOut() {
        em.close();
        emf.close();
    }
}
