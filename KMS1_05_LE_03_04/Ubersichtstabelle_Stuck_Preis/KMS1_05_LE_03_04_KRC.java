import java.util.Scanner;

public class KMS1_05_LE_03_04_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        double firstPrice, endPrice, price;
        while (true){
            System.out.print("Enter first Price: ");
            if (entry.hasNextDouble()){
                firstPrice = entry.nextDouble();
                break;
            }else {
                System.out.println("Enter only a number");
                entry.nextLine();
            }
        }
        while (true){
            System.out.print("Enter end Price: ");
            if (entry.hasNextDouble()){
                endPrice = entry.nextDouble();
                if (endPrice > firstPrice){
                    break;
                }else {
                    System.out.println("End Price have to be bigger than first Price");
                    entry.nextLine();
                }
            }else {
                System.out.println("Write only a number");
                entry.nextLine();
            }
        }

        // ChatGPT logic
        // We calculate the increment for the price per unit
        double priceIncrement = Math.round(((endPrice-firstPrice)/10) * 2)/2.0;

        // We print an empty column for the place where the piece values will be
        System.out.print("          "); // Blank space for the first column

        // We will print a header for prices
        for ( price = firstPrice; price <= endPrice; price += priceIncrement) {
            System.out.printf("%8.2f ", price);
        }
        System.out.println(); // A new line after the header

        // Create and calculate table values
        for (int pieces = 10; pieces <= 100; pieces += 10) {
            // Print the number of pieces at the beginning of each line
            System.out.printf("%8d ", pieces);

            // For each number of pieces, we create a row in the table
            for ( price = firstPrice; price <= endPrice; price += priceIncrement) {
                double totalPrice = price * pieces;
                System.out.printf("%8.0f ", totalPrice);
            }
            System.out.println(); // New line after each line
        }
    }
}
