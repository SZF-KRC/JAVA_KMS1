import java.util.Scanner;

public class KMS1_05_LE_03_01_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        String name;
        int pieces ;
        double price ;

        System.out.println("Enter name: ");
        name = entry.nextLine();

        while (true) {
            System.out.println("Enter pieces: ");
            if (entry.hasNextInt()) {
                pieces = entry.nextInt();
                break;
            } else {
                System.out.println("Write only integer number");
                entry.nextLine();
            }
        }

        while (true) {
            System.out.println("Enter price: ");
            if (entry.hasNextDouble()) {
                price = entry.nextDouble();
                break;
            } else {
                System.out.println("Write only number");
                entry.nextLine();
            }
        }

        System.out.println(STR."Name of product: \{name}\nPieces: \{pieces}\nPrice: \{price}");
    }
}
