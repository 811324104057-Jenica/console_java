import java.util.*;

class Account {

    private int accNo;
    private String name;
    private double balance;

    public Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    public int getAccNo() {
        return accNo;
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

class Operations {

    HashMap<Integer, Account> accounts = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // --------Create Account------------
    public void createAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (accounts.containsKey(accNo)) {
            System.out.println("Account Already Exists.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Account account = new Account(accNo, name, balance);

        accounts.put(accNo, account);

        System.out.println("Account Created Successfully.");
    }

    // ----------Deposit------------
    public void deposit() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        Account account = accounts.get(accNo);

        if (account == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        account.setBalance(account.getBalance() + amount);

        System.out.println("Amount Deposited Successfully.");
        System.out.println("Current Balance: " + account.getBalance());
    }

    // ----------Withdraw-------------
    public void withdraw() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        Account account = accounts.get(accNo);

        if (account == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (amount <= account.getBalance()) {

            account.setBalance(account.getBalance() - amount);

            System.out.println("Amount Withdrawn Successfully.");
            System.out.println("Current Balance: " + account.getBalance());

        } else {
            System.out.println("Insufficient Balance.");
        }
    }

    // ---------Check Balance--------
    public void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        Account account = accounts.get(accNo);

        if (account == null) {
            System.out.println("Account Not Found.");
            return;
        }

        System.out.println("\n------ Account Details ------");
        System.out.println("Account Number : " + account.getAccNo());
        System.out.println("Account Holder : " + account.getName());
        System.out.println("Balance        : " + account.getBalance());
    }
}

public class Banks {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Operations obj = new Operations();

        while (true) {

            System.out.println("\n------ MENU ------");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    obj.createAccount();
                    break;

                case 2:
                    obj.deposit();
                    break;

                case 3:
                    obj.withdraw();
                    break;

                case 4:
                    obj.checkBalance();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}