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

    ArrayList<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void createAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account a : accounts) {
            if (a.getAccNo() == accNo) {
                System.out.println("Account already exists.");
                return;
            }
        }

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        accounts.add(new Account(accNo, name, balance));

        System.out.println("Account Created Successfully.");
    }

    public void deposit() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account a : accounts) {

            if (a.getAccNo() == accNo) {

                System.out.print("Enter Amount: ");
                double amount = sc.nextDouble();

                a.setBalance(a.getBalance() + amount);

                System.out.println("Amount Deposited Successfully.");
                return;
            }
        }

        System.out.println("Account Not Found.");
    }

    public void withdraw() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account a : accounts) {

            if (a.getAccNo() == accNo) {

                System.out.print("Enter Amount: ");
                double amount = sc.nextDouble();

                if (amount <= a.getBalance()) {
                    a.setBalance(a.getBalance() - amount);
                    System.out.println("Amount Withdrawn Successfully.");
                } else {
                    System.out.println("Insufficient Balance.");
                }
                return;
            }
        }

        System.out.println("Account Not Found.");
    }

    public void checkBalance() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        for (Account a : accounts) {

            if (a.getAccNo() == accNo) {

                System.out.println("Account Number : " + a.getAccNo());
                System.out.println("Account Holder : " + a.getName());
                System.out.println("Balance : " + a.getBalance());
                return;
            }
        }

        System.out.println("Account Not Found.");
    }
}
public class Banks {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Operations obj = new Operations();

        while (true) {
            System.out.println("\n------Menu------");
            System.out.println("\n1.Create Account");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Check Balance");
            System.out.println("5.Exit");

            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
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

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}