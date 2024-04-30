import java.util.*;

public class BankSystem {
    private final List<Client> clients = new ArrayList<>();// Declare a list to store client objects.
    private HashSet<String> clientsID = new HashSet<>();// A set to hold unique client IDs.
    private HashSet<String> accounts = new HashSet<>();// A set to hold unique client account number.
    private Scanner entry = new Scanner(System.in);

    // Method to display the main menu and handle navigation.
    public void mainMenu(){
        boolean exit = false;// Control loop exit.
        while (!exit){// Continue looping until exit is true.
            System.out.println("\nBank Fohnsdorf\n[1] Staff section\n[2] Client section\n[0] exit program\nEnter index of your choice: ");// Print menu options
            switch (intEntry()){// Switch on the choice entered by the user.
                case 1: System.out.println("** Staff section **");staffMenu();break;// If user chooses 1, enter staff section and Call the staffMenu method.
                case 2: System.out.println("** Client section **\n* Identity verification *");checkClient();break;
                case 0: exit = true;closeScanner();break;
                default: System.out.println("Enter index only from 0 - 2");break;
            }
        }
    }
    // Method for handling the staff menu
    public void staffMenu(){
        boolean exit = false;
        while (!exit){
            System.out.println("\n[1] add new Client\n[2] delete Client\n[3] show account\n[4] show all accounts\n[0] back to main menu\nEnter index of your choice: ");
            switch (intEntry()){
                case 1: System.out.println("add new client");addNewClient();break;
                case 2: System.out.println("delete client");deleteExistingAccount();break;
                case 3: System.out.println("show account");showOneAccount();break;
                case 4: System.out.println("show all accounts");printAllAccounts();break;
                case 0: exit = true;break;
                default: System.out.println("Enter only index from 0 - 4");
            }
        }
    }

    public void clientMenu(String name,String surname ,String clientId, String accountNumber){
        boolean exit = false;
        double amount;
        while (!exit){
            System.out.println("\nAccount: "+name+" "+surname +"\n[1] deposit\n[2] withdraw\n[3] show account\n[0] back to main menu\nEnter index of your choice: ");
            switch (intEntry()){
                case 1: System.out.print("deposit\nEnter amount: ");amount = doubleEntry();deposit(clientId,accountNumber,amount);break;
                case 2: System.out.println("withdraw\nEnter amount: ");amount = doubleEntry();withdraw(clientId,accountNumber, amount);break;
                case 3: System.out.println("show account");getBalance(clientId,accountNumber);break;
                case 0: exit = true;break;
                default: System.out.println("Enter only index from 0 - 4");break;
            }
        }
    }

    // Method to add a new client to the bank system.
    private void addNewClient(){
        String name, surname, clientID, accountNumber;// Declare variables to store client's name, surname, ID, and account number.
        System.out.print("Enter name for new Client: ");
        name = entry.nextLine();
        System.out.print("Enter surname for new Client: ");
        surname = entry.nextLine();
        clientID = randomClientID();// Generate a unique client ID.
        addClient(name,surname,clientID);// Add the new client to the system.
        accountNumber = randomAccountNumber();// Generate a unique account number for the new client.
        addAccountToClient(clientID,accountNumber);// Link the new account number with the client's ID.
        System.out.println("\n"+name+ " "+surname+" added successfully.\n*-* account number: " + accountNumber+" *-*\n*-* client ID: " + clientID+" *-*");
    }
    // Method to delete an existing account from the bank system.
    private void deleteExistingAccount() {
        if (clients.isEmpty()){// Check if there are any clients in the system.
            System.out.println("*** No data ***");
        }else {
            printAllAccounts();// Display all accounts if clients exist.
            System.out.print("Enter the index of the account you want to delete: ");
            int index = intEntry();
            deleteAccountByIndex(index);// Delete the account at the specified index.
        }
    }
    // Method to display details of a single account.
    private void showOneAccount(){
        if (clients.isEmpty()){// Check if there are any clients in the system.
            System.out.println("*** No data ***");
        }else {
            printAllAccounts();// Display all accounts if clients exist.
            System.out.print("Enter the index of the account you want to show: ");
            int index = intEntry();
            printOneAccount(index);// Print details of the account at the specified index.
        }
    }
    // Method to check a client's credentials.
    private void checkClient(){
        boolean exit = false;
        String account, clientId;
        int wrongEntry= 0;// Counter for wrong entries.
        while (!exit){
            System.out.print("Enter your bank account: ");
            account = checkStringEntryIfInteger(); // Read and validate the account input as a string.
            System.out.print("Enter your clientID: ");
            clientId = checkStringEntryIfInteger();// Read and validate the client ID as a string
            Client client = findClientById(clientId);// Attempt to find the client by ID.
            if (client != null){// Check if client exists and account is linked to this client.
                if (findAccount(clientId,account) == null){
                    System.out.println("\n*** Wrong account or clientID ***\n");
                }else {
                    clientMenu(client.name(),client.surname(),clientId, account);// Enter client menu if both client ID and account number are correct.
                    exit = true;
                }
            }
            wrongEntry++;// Increment wrong entry count.
            if (wrongEntry == 2){// Allow only two attempts.
                exit = true;// Exit after two wrong entries.
                System.out.println("\n--- To many wrong Inputs ---\n");
            }
        }
    }


    // check if input is integer
    private int intEntry(){
        int number;
        while (true){
            try {
                number = entry.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number ***");
                entry.nextLine();
            }
        }
        return number;
    }
    // check if entry is double
    private double doubleEntry(){
        double number;
        while (true){
            try {
                number = entry.nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only double number ***");
                entry.nextLine();
            }
        };
        return number;
    }
    // check if input are numbers
    private String checkStringEntryIfInteger(){
        String input;
        int num;
        while (true){
            input = entry.nextLine();
            try {
                num = Integer.parseInt(input);
                break;
            }catch (NumberFormatException e){
                System.out.print(input + " is not integer ***\nEnter integer number: ");
            }
        }
        return input;
    }
    // Method for close Scanner use only one when program is exiting
    private void closeScanner(){
        entry.close();
    }
    // Method to find a client by their ID.
    private Client findClientById(String clientId){
        for (Client client : clients){// Iterate through the list of clients.
            if (client.clientId().equals(clientId)){// Check if the current client's ID matches the given ID.
                return client;// Return the client if found.
            }
        }
        return null;// Return null if no client is found with the given ID.
    }
    // Method to find an account by client ID and account number.
    private Account findAccount(String clientId, String accountNumber){
        Client client = findClientById(clientId);// Use findClientById to get the client associated with the given client ID.
        if (client != null){// Check if the client exists.
            for (Account account : client.accounts()){// Iterate through the accounts of the found client.
                if (account.accountNumber().equals(accountNumber)){ // Check if the account number matches the given account number.
                    return account;// Return the account if found.
                }
            }
        }
        return null;// Return null if no account is found or client does not exist.
    }

    // Method to add a new client to the bank
    public void addClient(String name, String surname, String clientId){
        Client newClient = new Client(name,surname,clientId);// Create a new Client object with the provided details.
        clients.add(newClient);// Add the new client to the list of clients.
    }

    // Method to add an account to an existing client
    public void addAccountToClient(String clientId, String accountNumber){
        Client client = findClientById(clientId);// Find the client by ID.
        if (client != null){// Check if the client exists.
            Account newAccount = new Account(accountNumber, 0.0);// Create a new Account object with the provided account number and initial balance of 0.0.
            client.accounts().add(newAccount);// Add the new account to the client's list of accounts.

        }else {
            System.out.println("Client not exist: " + clientId);
        }
    }
    // Method to deposit money into an account.
    private void deposit(String clientId, String accountNumber, double amount){
        Account account = findAccount(clientId, accountNumber);// Find the account using client ID and account number.
        if (account != null){// Check if the account exists.
            Account updatedAccount = account.deposit(amount);// Deposit the amount into the account and update the account balance.
            updateAccount(clientId, accountNumber, updatedAccount);// Update the account details in the system.
            System.out.println("Deposit: " + amount + " added to account: " + accountNumber);
        }else {
            System.out.println("Account or client does not exist");
        }
    }
    // Method to withdraw money from an account.
    private void withdraw(String clientId, String accountNumber, double amount){
        Account account = findAccount(clientId, accountNumber);// Find the account using client ID and account number.
        if (account != null && account.balance() > amount){
            Account updatedAccount = account.withdraw(amount);// Withdraw the amount from the account and update the account balance.
            updateAccount(clientId, accountNumber, updatedAccount);// Update the account details in the system.
            System.out.println("Withdraw: " + amount + " from account: " + accountNumber);
        }else {
            System.out.println("Insufficient funds");
        }
    }
    // Method to update an existing account with new details.
    private void updateAccount(String clientId, String accountNumber, Account updatedAccount) {
        Client client = findClientById(clientId);// Find the client by their ID.
        if (client != null) {// If client is found,
            List<Account> accounts = client.accounts();// Get the list of the client's accounts.
            for (int i = 0; i < accounts.size(); i++) {// Loop through the list of accounts to find the matching account.
                if (accounts.get(i).accountNumber().equals(accountNumber)) {
                    accounts.set(i, updatedAccount);  // If the account number matches, replace the old account with the new one
                    return;// Exit after update is successful.
                }
            }
        }
    }
    // Method to delete an account using a global index across all clients.
    private void deleteAccountByIndex(int globalIndex) {
        int currentIndex = 0;// Initialize a counter to track the index across all accounts.
        for (Client client : clients) {// Loop through each client.
            List<Account> accounts = client.accounts();// Get the client's accounts.
            for (int i = 0; i < accounts.size(); i++) {// Loop through the accounts.
                if (currentIndex == globalIndex) {// Check if the current index matches the global index.
                    accounts.remove(i);// Remove the account from the accounts
                    clients.remove(client);// Remove the client from the bank
                    System.out.println("Account deleted successfully.");
                    return;// Found account, end of search
                }
                currentIndex++;// Increment the current index.
            }
        }
        System.out.println("No account found with the given index.");
    }
    // Method to print details of one specific account using an index
    private void printOneAccount(int index){
        int currentIndex = 0;// Counter to maintain the index across all accounts
        for (Client client : clients){// Loop through all clients.
            List<Account> accountList = client.accounts();// Get the client's accounts.
            for (Account account : accountList) {// Loop through the accounts.
                if (currentIndex == index) { // Check if the current index matches the desired index and print the account details.
                    System.out.println("\nClient: " + client.name() + " " + client.surname() + "\nAccount Number: " + account.accountNumber() + "\nClient ID: " + client.clientId() + "\nBalance: " + account.balance());
                    return;  // Exit after printing.
                }
                currentIndex++;  // Increment the index.
            }
        }
        // Notify if the account with the given index is not found
        System.out.println("Account with the given index does not exist.");
    }
    // Method to print all accounts in the bank system.
    private void printAllAccounts() {
        int globalIndex = 0;
        for (Client client : clients) {// Loop through all clients.
            List<Account> accounts = client.accounts(); // Get the client's accounts.
            for (Account account : accounts) {// Print details for each account.
                System.out.println("Index: " + globalIndex + ", Client: " + client.name() + " " + client.surname() + ", Account Number: " + account.accountNumber() + ", Balance: " + account.balance());
                globalIndex++;
            }
        }
    }
    // Method to display the balance of a specified account.
    private void getBalance(String cliendId, String accountNumber){
        Account account = findAccount(cliendId,accountNumber);// Find the account using client ID and account number.
        if (account !=null){// If account exists,Print the balance
            System.out.println(account.balance());
        }else {
            System.out.println("Account or client not exist");
        }
    }
    // Method to generate a random client ID.
    private String randomClientID() {
        int min = 1000, max = 9999;// Define the range for client IDs.
        String random;// Variable to hold the random ID.
        do {
            int randomNumber = (int) (Math.floor(Math.random() * (max - min + 1) + min));// Generate a random number within the specified range.
            random = String.valueOf(randomNumber);
        } while (clientsID.contains(random));// Ensure the ID is unique.
        clientsID.add(random);// Add the new ID to the set of IDs.
        return random;// Return the unique client ID.
    }
    // Method to generate a random account number.
    private String randomAccountNumber(){
        int min= 10000, max = 99999;
        String random;
        do{
            int randomNumber = (int) (Math.floor(Math.random() * (max - min + 1) + min));
            random = String.valueOf(randomNumber);
        }while (accounts.contains(random));// Ensure the account number is unique.
        accounts.add(random);// Add the new number to the set of account numbers.
        return random;// Return the unique account number.
    }
}
