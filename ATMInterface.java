

import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    Scanner sc = new Scanner(System.in); 

    public void register() {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("Enter your Password: ");
        this.password = sc.nextLine();
        System.out.println("Enter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account.");
    }

    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.println("\nEnter your Username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(userName)) {
                while (!isLogin) {
                    System.out.println("Enter your Password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("\nLogin Successful!");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect Password. Try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Try again.");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        if (amount > 0 && balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("\nWithdrawal Successful.");
            transactionHistory += amount + " Rs Withdrawn\n";
        } else if (amount > balance) {
            System.out.println("\nInsufficient Balance.");
        } else {
            System.out.println("\nInvalid Amount. Try again.");
        }
    }

    public void deposit() {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        if (amount > 0 && amount <= 10000f) {
            transactions++;
            balance += amount;
            System.out.println("\nDeposit Successful.");
            transactionHistory += amount + " Rs Deposited\n";
        } else if (amount > 10000f) {
            System.out.println("\nSorry, the limit is 10000 Rs.");
        } else {
            System.out.println("\nInvalid Amount. Try again.");
        }
    }

    public void transfer() {
        System.out.println("\nEnter Recipient's Name: ");
        sc.nextLine(); // Consume leftover newline
        String recipient = sc.nextLine();
        System.out.println("Enter Amount to Transfer: ");
        float amount = sc.nextFloat();
        if (amount > 0 && balance >= amount && amount <= 50000f) {
            transactions++;
            balance -= amount;
            System.out.println("\nSuccessfully Transferred to " + recipient);
            transactionHistory += amount + " Rs Transferred to " + recipient + "\n";
        } else if (amount > 50000f) {
            System.out.println("\nSorry, the limit is 50000 Rs.");
        } else if (amount > balance) {
            System.out.println("\nInsufficient Balance.");
        } else {
            System.out.println("\nInvalid Amount. Try again.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions Found.");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }

    
}

public class ATMInterface {

    
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);

        while (!flag) {
            try {
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    flag = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit + ":");
                }
            } catch (Exception e) {
                System.out.println("Enter a valid integer:");
                sc.next(); // Clear invalid input
            }
        }
        return input;
        
    }

    public static void main(String[] args) {
        System.out.println("\n******************** WELCOME TO GOVARDHAN ATM INTERFACE ********************");
        System.out.println("\n1. Register\n2. Exit");
        System.out.println("Choose an option: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();

            while (true) {
                System.out.println("\n1. Login\n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2);

                if (ch == 1) {
                    if (account.login()) {
                        System.out.println("\n******************** WELCOME BACK, " + account.name + " ********************");
                        boolean isFinished = false;

                        while (!isFinished) {
                            System.out.println("\n1. Withdraw\n2. Deposit\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Exit");
                            System.out.println("Enter your choice: ");
                            int option = takeIntegerInput(6);

                            switch (option) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                                default:
                                    System.out.println("Invalid Option. Try again.");
                            }
                        }
                    }
                } else {
                    System.out.println("Thank you for using the ATM Interface. Goodbye!");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Thank you for using the ATM Interface. Goodbye!");
            System.exit(0);
        }
    }
}
