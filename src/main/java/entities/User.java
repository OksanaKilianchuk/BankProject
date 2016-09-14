package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.findUser", query = "SELECT u FROM User u WHERE u.login= :login and u.pass= :pass")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "login", nullable = false)
    private String login;
    private String pass;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person")
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
