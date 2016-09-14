package app;

import dao.AccountDao;
import dao.UserDao;
import entities.Account;
import entities.User;
import iInterfaces.IAccountDao;
import iInterfaces.IUserDao;
import services.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Currency;

import java.util.Scanner;

/**
 * Created by uzer on 10.09.2016.
 */
public class Main {

    static AccountService service= new AccountService();
    static User user;

    public static void main(String... args) {
        try (Scanner sc = new Scanner(System.in)) {
                if (autorization(sc)) {
                    System.out.println("Welcome " + user.getLogin() + "!");

                    while (true) {
                        System.out.println("\n Add new account -> 'new account'");
                        System.out.println("View all your accounts - > 'all accounts'");
                        System.out.println("Fill account -> 'fill account'");
                        System.out.println("Transfer money between your accounts -> 'transfer'");
                        System.out.println("Get total funds in yours accounts -> 'total funds'");
                        System.out.println("Print all yours transaction -> 'all transactions'");
                        System.out.println("Log out -> 'log out'");
                        String command = sc.nextLine();
                        switch (command) {
                            case "new account":
                                createAccount(sc);
                                break;
                            case "all accounts":
                                service.viewAccounts(user);
                                break;
                            case "fill account":
                                fillAccount(sc);
                                break;
                            case "transfer":
                                transfer(sc);
                                break;
                            case "total funds":
                                totalFunds(sc);
                                break;
                            case "all transactions":
                                service.printAllTransaction(user);
                                break;
                            case "log out":
                                break;

                            default:
                                System.out.println("Enter correct command, please!");
                        }
                        if (command.equals("log out"))
                            break;
                    }
                } else {
                    System.out.println("Login or password is incorrect!");
                }
            logOut();
        }

    }

    static boolean autorization(Scanner sc) {
        System.out.println("Login:");
        String login = sc.nextLine();
        System.out.println("Password:");
        String pass = sc.nextLine();
        try {
            user = service.autorization(login, pass);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    static void totalFunds(Scanner sc) {
        System.out.println("Enter currency for evaluation:");
        Currency currency = validateCurrency(sc.nextLine());
        Double sum = service.totalFunds(user, currency);
        System.out.println("Your total sum by all accounts is " + sum + currency);
    }

    static void createAccount(Scanner sc) {
        System.out.println("Select currency (EUR, USD,UAN):");
        Currency currency = validateCurrency(sc.nextLine());
        if (service.createAccount(user, currency))
            System.out.println("New account successfully created");
    }

    static Currency validateCurrency(String currency) {
        Currency enumCurrency = null;
        try {
            enumCurrency = Currency.valueOf(currency);
        } catch (Exception ex) {
            System.out.print("Currency is incorrect");
        }
        return enumCurrency;
    }

    static void fillAccount(Scanner sc) {
        System.out.println("Enter the account number:");
        Long number = Long.parseLong(sc.nextLine());
        System.out.println("Enter sum:");
        Double sum = Double.parseDouble(sc.nextLine());
        System.out.println("Enter currency:");
        Currency currency = validateCurrency(sc.nextLine());
        if (service.fillAccount(number, sum, currency))
            System.out.println("Your account " + number + " \n" +
                    " successfully recharged");
    }

    static void transfer(Scanner sc) {
        System.out.println("Enter the account number of the sender");
        Long numberFrom = Long.parseLong(sc.nextLine());
        System.out.println("Enter the account number of the receiver:");
        Long numberTo = Long.parseLong(sc.nextLine());
        System.out.println("Enter sum: ");
        Double sum = Double.parseDouble(sc.nextLine());
        if (service.transferMoney(numberFrom, numberTo, sum))
            System.out.println("Transaction successful!");
    }

    static void logOut() {
        user = null;
        service.logOut();
    }
}
