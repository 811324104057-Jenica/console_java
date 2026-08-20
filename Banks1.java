import java.util.*;

class Account {

    private int accNo;
    private String name;
    private double balance;

    private ArrayList<String> miniStatement;

    public Account(int accNo, String name, double balance) {

        this.accNo = accNo;
        this.name = name;
        this.balance = balance;

        miniStatement = new ArrayList<>();

        miniStatement.add("Account Created : +" + balance);
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

    public ArrayList<String> getMiniStatement() {
        return miniStatement;
    }
}


class Operations {

    LinkedHashMap<Integer, Account> accounts = new LinkedHashMap<>();

    Scanner sc = new Scanner(System.in);


    // -------- Create Account --------
   public void createAccount() {

    System.out.print("Enter Account Number: ");
    int accNo = sc.nextInt();

    // Check whether account already exists
    if (accounts.containsKey(accNo)) {
        System.out.println("Account Already Exists.");
        return;
    }

    sc.nextLine();

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

    // Validate name
    if (name.trim().isEmpty()) {
        System.out.println("Name Cannot Be Empty.");
        return;
    }

    System.out.print("Enter Initial Balance: ");
    double balance = sc.nextDouble();

    // Validate balance
    if (balance < 500) {
        System.out.println("Account Cannot Be Created.");
        System.out.println("Minimum Balance Required is Rs.500.");
        return;
    }

    // Create account
    Account account = new Account(accNo, name, balance);

    // Store account
    accounts.put(accNo, account);

    System.out.println("================================");
    System.out.println("     ACCOUNT CREATED SUCCESSFULLY");
    System.out.println("================================");
    System.out.println("Account Number : " + accNo);
    System.out.println("Account Holder : " + name);
    System.out.println("Initial Balance: Rs." + balance);
}


    // -------- Deposit --------
    public void deposit() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account Not Found.");
            return;
        }

        Account account = accounts.get(accNo);

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Deposit Amount.");
            return;
        }

        double newBalance = account.getBalance() + amount;

        account.setBalance(newBalance);

        // Add transaction to mini statement
        account.getMiniStatement().add(
                "Deposit : +" + amount +
                " | Balance : " + account.getBalance()
        );

        System.out.println("Deposit Successful.");
        System.out.println("Deposited Amount : " + amount);
        System.out.println("Current Balance  : " + account.getBalance());
    }


    // -------- Withdrawal --------
    public void withdraw() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account Not Found.");
            return;
        }

        Account account = accounts.get(accNo);

        System.out.print("Enter Withdrawal Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Withdrawal Amount.");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Insufficient Balance.");
            return;
        }

        if (account.getBalance() - amount < 500) {
            System.out.println("Withdrawal Cannot Be Completed.");
            System.out.println("Minimum Balance of Rs.500 Must Be Maintained.");
            return;
        }

        double newBalance = account.getBalance() - amount;

        account.setBalance(newBalance);

        // Add transaction to mini statement
        account.getMiniStatement().add(
                "Withdrawal : -" + amount +
                " | Balance : " + account.getBalance()
        );

        System.out.println("Withdrawal Successful.");
        System.out.println("Withdrawn Amount : " + amount);
        System.out.println("Current Balance  : " + account.getBalance());
    }


    // -------- Check Balance --------
    public void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account Not Found.");
            return;
        }

        Account account = accounts.get(accNo);

        System.out.println("Account Number : " + account.getAccNo());
        System.out.println("Account Name   : " + account.getName());
        System.out.println("Current Balance: " + account.getBalance());
      
      
    }


    // -------- Mini Statement --------
    public void miniStatement() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account Not Found.");
            return;
        }

        Account account = accounts.get(accNo);

        System.out.println("Account Number : " + account.getAccNo());
        System.out.println("Account Name   : " + account.getName());
        

        ArrayList<String> statement = account.getMiniStatement();

        for (String transaction : statement) {
            System.out.println(transaction);
        }

        System.out.println("Current Balance : " + account.getBalance());

    }
}


public class Banks1 {

    public static void main(String[] args) {

        Operations obj = new Operations();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdrawal");
            System.out.println("4. Check Balance");
            System.out.println("5. Mini Statement");
            System.out.println("6. Exit");
            

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

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
                    obj.miniStatement();
                    break;

                case 6:
                    System.out.println(
                            "\nThank You For Using Bank Management System."
                    );
                    break;

                default:
                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 6);
    }
}