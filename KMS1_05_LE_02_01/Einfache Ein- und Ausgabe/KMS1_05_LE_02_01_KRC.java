import java.util.Scanner;

public class KMS1_05_LE_02_01_KRC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String myName = input.nextLine();
        System.out.print("Enter your surname: ");
        String mySurname = input.nextLine();

        System.out.println("Your name is: " + myName + " and your surname is: " + mySurname);
    }
}
