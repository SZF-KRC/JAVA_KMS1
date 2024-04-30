public class Main {
    public static void main(String[] args) {
    BankSystem bank = new BankSystem();

    // first demo client
    bank.addClient("Christian","Kaiser", "1111");
    bank.addAccountToClient("1111","11111");
    // second demo cliente
    bank.addClient("Elizabeth", "Puntigam", "2222");
    bank.addAccountToClient("2222", "22222");

    bank.mainMenu();
    }
}
