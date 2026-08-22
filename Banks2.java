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
        int accno = sc.nextInt();
        sc.nextLine();

        if (accounts.containsKey(accno)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }

        Account acc = new Account(accno, name, balance);
        accounts.put(accno, acc);

        System.out.printf(
            "[SUCCESS] Account %d created for %s | Balance: ₹%,.2f%n",
            accno, name, balance
        );
    }

    public void deposit() {

        System.out.print("Enter Account ID: ");
        int accno = sc.nextInt();
        sc.nextLine();

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        System.out.print("Enter Date-Time (YYYY-MM-DDTHH:MM:SS): ");
        String dateTime = sc.nextLine();

        LocalDateTime time;

        try {
            time = LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            System.out.println("Invalid Date-Time format.");
            return;
        }

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        acc.setBalance(acc.getBalance() + amount);

        acc.transactions.put(
            time,
            new Transaction(time, "CREDIT", amount, description)
        );

        System.out.printf(
            "[SUCCESS] Account %d credited with +₹%,.2f | New Balance: ₹%,.2f%n",
            accno, amount, acc.getBalance()
        );
    }

    public void withdraw() {

        System.out.print("Enter Account ID: ");
        int accno = sc.nextInt();
        sc.nextLine();

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (amount > acc.getBalance()) {
            System.out.println("Insufficient Balance.");
            return;
        }

        System.out.print("Enter Date-Time (YYYY-MM-DDTHH:MM:SS): ");
        String dateTime = sc.nextLine();

        LocalDateTime time;

        try {
            time = LocalDateTime.parse(dateTime);
        } catch (Exception e) {
            System.out.println("Invalid Date-Time format.");
            return;
        }

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        acc.setBalance(acc.getBalance() - amount);

        acc.transactions.put(
            time,
            new Transaction(time, "DEBIT", amount, description)
        );

        System.out.printf(
            "[SUCCESS] Account %d debited with -₹%,.2f | New Balance: ₹%,.2f%n",
            accno, amount, acc.getBalance()
        );
    }

    public void statement() {

        System.out.print("Enter Account ID: ");
        int accno = sc.nextInt();
        sc.nextLine();

        Account acc = accounts.get(accno);

        if (acc == null) {
            System.out.println("Invalid Account.");
            return;
        }

        System.out.print("Enter Start Date-Time: ");
        String startDate = sc.nextLine();

        System.out.print("Enter End Date-Time: ");
        String endDate = sc.nextLine();

        LocalDateTime start;
        LocalDateTime end;

        try {
            start = LocalDateTime.parse(startDate);
            end = LocalDateTime.parse(endDate);
        } catch (Exception e) {
            System.out.println("Invalid Date-Time format.");
            return;
        }

        if (start.isAfter(end)) {
            System.out.println("Start Date-Time cannot be after End Date-Time.");
            return;
        }

        NavigableMap<LocalDateTime, Transaction> result =
            acc.transactions.subMap(start, true, end, true);

        System.out.println();
        System.out.println("============================================================");
        System.out.printf(
            "         ACCOUNT STATEMENT: %d (%s)%n",
            acc.getAccNo(), acc.getName()
        );
        System.out.printf(
            "         Filter Period: %s to %s%n",
            start.toLocalDate(), end.toLocalDate()
        );
        System.out.println("============================================================");

        System.out.println(
            "DATE & TIME          | TYPE    | AMOUNT       | DESCRIPTION"
        );

        System.out.println(
            "------------------------------------------------------------"
        );

        for (Map.Entry<LocalDateTime, Transaction> entry : result.entrySet()) {

            Transaction t = entry.getValue();

            String amount;

            if (t.type.equals("CREDIT")) {
                amount = String.format("+₹%,.2f", t.amount);
            } else {
                amount = String.format("-₹%,.2f", t.amount);
            }

            System.out.printf(
                "%-20s | %-7s | %-12s | %s%n",
                t.time.toString(),
                t.type,
                amount,
                t.description
            );
        }

        System.out.println(
            "------------------------------------------------------------"
        );

        System.out.printf(
            "Statement complete (%d transaction(s) found in date range)%n",
            result.size()
        );
    }
}

public class Banks2 {

    public static void main(String[] args) {
             Scanner sc=new Scanner(System.in);
        BankLedger op = new BankLedger();

        int ch;

        do {

            System.out.println();
            System.out.println("============================================================");
            System.out.println("              SECUREBANK — CONSOLE MENU");
            System.out.println("============================================================");
            System.out.println("1. Add Account");
            System.out.println("2. Add Money (Deposit)");
            System.out.println("3. Debit Money (Withdrawal)");
            System.out.println("4. Display User Statement");
            System.out.println("5. Exit");
            System.out.println("============================================================");

            System.out.print("Select Option: ");
            ch = sc.nextInt();
          

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
                    System.out.println("Exiting SecureBank. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Option.");
            }

        } while (ch != 5);
    }
}