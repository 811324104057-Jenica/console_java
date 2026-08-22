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
}

class Operations {

    // TreeMap used for storing accounts
    TreeMap<Integer, Account> accounts = new TreeMap<>();

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

        // Store account in TreeMap
        accounts.put(accNo, account);

        System.out.println("================================");
        System.out.println("   ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("================================");
        System.out.println("Account Number : " + accNo);
        System.out.println("Account Holder : " + name);
        System.out.println("Initial Balance: Rs." + balance);
    }
}

public class Banks1 {

    public static void main(String[] args) {

        Operations obj = new Operations();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("      BANK MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. Exit");
            System.out.println("================================");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    obj.createAccount();
                    break;

                case 2:
                    System.out.println(
                            "\nThank You For Using Bank Management System."
                    );
                    break;

                default:
                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 2);

        sc.close();
    }
}