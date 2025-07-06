import java.util.*;

class Account {
    protected String accountHolder;
    protected int accountNumber;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(String accountHolder, int accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: ₹" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("₹" + amount + " deposited.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("₹" + amount + " withdrawn.");
        } else {
            System.out.println(" Insufficient balance or invalid amount.");
        }
    }

    public void showBalance() {
        System.out.println(" Current Balance: ₹" + balance);
    }

    public void showTransactionHistory() {
        System.out.println("\n Transaction History:");
        for (String entry : transactionHistory) {
            System.out.println("• " + entry);
        }
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String name, int number, double balance, double interestRate) {
        super(name, number, balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest);  
        transactionHistory.add("Interest applied: ₹" + interest);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println(" Withdrawal from Savings Account");
        super.withdraw(amount); 
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        SavingsAccount myAccount = new SavingsAccount("Ravi Sharma", 1001, 5000, 5.0);

        int choice;
        do {
            System.out.println("\n Bank Menu");
            System.out.println("1. Deposit\n2. Withdraw\n3. Show Balance\n4. Apply Interest\n5. Show Transactions\n6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = scanner.nextDouble();
                    myAccount.deposit(dep);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ₹");
                    double wit = scanner.nextDouble();
                    myAccount.withdraw(wit);
                }
                case 3 -> myAccount.showBalance();
                case 4 -> myAccount.applyInterest();
                case 5 -> myAccount.showTransactionHistory();
                case 6 -> System.out.println(" Exiting...");
                default -> System.out.println(" Invalid option.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
