public record Account(String accountNumber, double balance) {

    // A method for depositing money that returns a new account with an updated balance
    public Account deposit(double amount) {
        if (amount > 0) {
            return new Account(accountNumber, balance + amount);
        } else {
            return this;
        }
    }

    // A method for withdrawing money that returns a new account with an updated balance
    public Account withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            return new Account(accountNumber, balance - amount);
        } else {
            return this;
        }
    }
}
