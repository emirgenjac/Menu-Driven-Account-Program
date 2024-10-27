import java.io.*;
import java.util.*;

/*
Account Program - must use hash map
1.Create Account
2.Delete Account
3.View Account
4.View All Accounts
5.Save Account
6.Exit

Enter Your Choice:



 */
class Account {
    int id;
    String name;
    String balance;

    public Account(int id, String name, String balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + name + '\'' +
                ", accountNumber=" + id +
                ", balance=" + balance +
                '}';
    }
}

class AccountManager {
    private static HashMap<Integer, Account> accounts = new HashMap<>();
    private static int nextId = 1;

    public static void createNewAccount(String name, String balance) {
        Account newAccount = new Account(nextId, name, balance);
        accounts.put(nextId, newAccount);
        System.out.println("Account created: " + newAccount);
        nextId++;
        System.out.println(accounts);
    }
    public static Account getAccount(int id) {
        return accounts.get(id); // Return the account or null if not found
    }

    public static Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    public static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
        } else {
            System.out.println("Accounts:");
            for (Account account : accounts.values()) {
                System.out.println(account);
            }
        }
    }

    public static void deleteAccount(int id) {
        if (accounts.remove(id) != null) {
            System.out.println("Account deleted: " + accounts.get(id));
        } else {
            System.out.println("Account not found");
        }
    }
}

public class Main {
    public static void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String name = sc.nextLine();
        System.out.println("Enter account balance: ");
        String balance = sc.nextLine();
        AccountManager.createNewAccount(name, balance);
    }
    public static void saveToFile() throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\genja\\OneDrive\\Desktop\\Java\\Collection\\AccountProgramwithHashMap\\Accounts.txt"))) {
            for (Account account : AccountManager.getAllAccounts()) {
                writer.write(account.toString()); // Write the account's string representation
                writer.newLine(); // Add a new line after each account
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving accounts: " + e.getMessage());
        }
        System.out.println("Accounts saved successfully.");
    }
    public static void menu() throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome!\n1. Create Account\n2. Delete Account\n3. View Account\n4. View All Accounts\n5. Save Account\n6. Exit\nEnter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    System.out.println("Enter account ID to delete: ");
                    int idToDelete = sc.nextInt();
                    AccountManager.deleteAccount(idToDelete);
                    break;
                case 3:
                    System.out.println("Enter Account ID to view: ");
                    int idToView = sc.nextInt();
                    Account account = AccountManager.getAccount(idToView);
                    if (account != null) {
                        System.out.println("Account details: " + account);
                    } else {
                        System.out.println("Account not found");
                    }
                    break;
                case 4:
                    AccountManager.viewAllAccounts();
                    break;
                case 5:
                    System.out.println("Saving to external file...");
                    saveToFile();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }
    public static void main(String[] args) {
       try {
        menu();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}