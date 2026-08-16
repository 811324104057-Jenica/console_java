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

        if (accounts.containsKey(accNo)) {
            System.out.println("Account Already Exists.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        if (balance < 500) {
            System.out.println("Account Cannot Be Created.");
            System.out.println("Minimum Balance Required is Rs.500.");
            return;
        }

        Account account = new Account(accNo, name, balance);

        accounts.put(accNo, account);

        System.out.println("Account Created Successfully.");
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

        account.getMiniStatement().add("Deposit : +" + amount);

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

        account.getMiniStatement().add("Withdrawal : -" + amount);

        System.out.println("Withdrawal Successful.");
        System.out.println("Withdrawn Amount : " + amount);
        System.out.println("Current Balance  : " + account.getBalance());
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
            System.out.println("4. Exit");
            

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
                    System.out.println("\nThank You For Using Bank Management System.");
                    break;

                default:
                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 4);
    }
}