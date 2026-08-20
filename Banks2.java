import java.time.*;
import java.util.*;

class Transaction {
    LocalDateTime time;
    String type;
    double amount;
    String description;

    Transaction(LocalDateTime time, String type, double amount, String description) {
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
}

class Account {
    private int accno;
    private String name;
    private double balance;

    TreeMap<LocalDateTime, Transaction> transactions = new TreeMap<>();

    Account(int accno, String name, double balance) {
        this.accno = accno;
        this.name = name;
        this.balance = balance;
    }

    public int getAccNo() {
        return accno;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class BankLedger {

    TreeMap<Integer, Account> accounts = new TreeMap<>();
    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter Account ID: ");
        int accno = Integer.parseInt(sc.nextLine());

        if (accounts.containsKey(accno)) {
            System.out.println("Account already exists");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = Double.parseDouble(sc.nextLine());

        Account acc = new Account(accno, name, balance);
        accounts.put(accno, acc);

        System.out.println("Account created successfully");
    }

    public void deposit() {
        System.out.print("Enter Account ID: ");
        int accno = Integer.parseInt(sc.nextLine());

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Date-Time: ");
        LocalDateTime time = LocalDateTime.parse(sc.nextLine());

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        acc.setBalance(acc.getBalance() + amount);

        acc.transactions.put(time,
                new Transaction(time, "CREDIT", amount, description));

        System.out.println("Deposited successfully");
        System.out.println("Current Balance: " + acc.getBalance());
    }

    public void withdraw() {
        System.out.print("Enter Account ID: ");
        int accno = Integer.parseInt(sc.nextLine());

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount > acc.getBalance()) {
            System.out.println("Insufficient Balance");
            return;
        }

        System.out.print("Enter Date-Time: ");
        LocalDateTime time = LocalDateTime.parse(sc.nextLine());

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        acc.setBalance(acc.getBalance() - amount);

        acc.transactions.put(time,
                new Transaction(time, "DEBIT", amount, description));

        System.out.println("Withdraw successful");
        System.out.println("Current Balance: " + acc.getBalance());
    }

    public void statement() {
        System.out.print("Enter Account ID: ");
        int accno = Integer.parseInt(sc.nextLine());

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account");
            return;
        }

        System.out.print("Enter Start Date-Time: ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine());

        System.out.print("Enter End Date-Time: ");
        LocalDateTime end = LocalDateTime.parse(sc.nextLine());

        NavigableMap<LocalDateTime, Transaction> result =
                acc.transactions.subMap(start, true, end, true);

        System.out.println("\nACCOUNT STATEMENT");
        System.out.println("Account: " + acc.getAccNo());
        System.out.println("Name: " + acc.getName());

        result.forEach((time, t) -> {
            System.out.println(time + " | " + t.type +
                    " | " + t.amount + " | " + t.description);
        });
    }
}

public class Banks2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
       BankLedger op =new BankLedger();
        int ch;

        do {
            System.out.println("\n----- SECUREBANK MENU -----");
            System.out.println("1. Add Account");
            System.out.println("2. Add Money");
            System.out.println("3. Debit Money");
            System.out.println("4. Display Statement");
            System.out.println("5. Exit");

            System.out.print("Enter the choice: ");
            ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    op.createAccount();
                    break;
                case 2:
                    op.deposit();
                    break;
                case 3:
                    op.withdraw();
                    break;
                case 4:
                    op.statement();
                    break;
                case 5:
                    System.out.println("Thank You");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (ch != 5);
    }
}