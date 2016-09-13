package dao;

import entities.Account;
import entities.User;
import iInterfaces.IUserDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UserDao implements IUserDao {

    public User findUser(EntityManager em, String login, String pass){
        User user;
        Query query = em.createNamedQuery("User.findUser",User.class);
        query.setParameter("login", login);
        query.setParameter("pass",pass);
        try {
            user = (User) query.getSingleResult();
        }catch(javax.persistence.NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

    public void printAllAccounts(User user){
        for (Account account :user.getAccounts()){
            System.out.println(account.toString());
        }
    }
}
