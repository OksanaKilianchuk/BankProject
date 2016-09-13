package iInterfaces;

import entities.User;

import javax.persistence.EntityManager;

/**
 * Created by uzer on 12.09.2016.
 */
public interface IUserDao {
    public User findUser(EntityManager em, String login, String pass);
    public void printAllAccounts(User user);
}
