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

    public void deposit(double amount) {
        this.balance += amount;
    }
}

class Operations {

    TreeMap<Integer, Account> accounts = new TreeMap<>();

    Scanner sc = new Scanner(System.in);

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

        if (name.trim().isEmpty()) {
            System.out.println("Name Cannot Be Empty.");
            return;
        }

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        if (balance < 500) {
            System.out.println("Account Cannot Be Created.");
            System.out.println("Minimum Balance Required is Rs.500.");
            return;
        }

        Account account = new Account(accNo, name, balance);

        accounts.put(accNo, account);

        System.out.println("================================");
        System.out.println("   ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("================================");
        System.out.println("Account Number : " + accNo);
        System.out.println("Account Holder : " + name);
        System.out.println("Initial Balance: Rs." + balance);
    }

    public void depositMoney() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        if (!accounts.containsKey(accNo)) {
            System.out.println("Account Does Not Exist.");
            return;
        }

        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid Amount. Deposit must be greater than 0.");
            return;
        }

        Account account = accounts.get(accNo);
        account.deposit(amount);

        System.out.println("================================");
        System.out.println("   DEPOSIT SUCCESSFUL");
        System.out.println("================================");
        System.out.println("Account Number : " + account.getAccNo());
        System.out.println("Account Holder : " + account.getName());
        System.out.println("Amount Deposited: Rs." + amount);
        System.out.println("Updated Balance : Rs." + account.getBalance());
    }
}

public class Banks1 {

    public static void main(String[] args) {

        Operations obj = new Operations();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("     BANK MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Exit");
            System.out.println("================================");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    obj.createAccount();
                    break;

                case 2:
                    obj.depositMoney();
                    break;

                case 3:
                    System.out.println(
                            "\nThank You For Using Bank Management System."
                    );
                    break;

                default:
                    System.out.println("\nInvalid Choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}