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

    // LinkedHashMap used only for creating accounts
    LinkedHashMap<Integer, Account> accounts = new LinkedHashMap<>();

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
}

public class Banks1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Operations obj = new Operations();

        System.out.println("\n------ CREATE ACCOUNT ------");

        obj.createAccount();
    }
}