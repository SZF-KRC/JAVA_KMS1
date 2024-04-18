import java.util.Scanner;

public class KMS1_05_LE_02_02_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int num1, num2, option;
        System.out.print("Welcome in Calculator\nEnter your first Number: ");
        num1 = entry.nextInt();
        System.out.print("Enter your second Number: ");
        num2 = entry.nextInt();
        System.out.print("Menu: \n1 : Plus\n2 : Minus\n3 : Devide\n4 : Multiply\nEnter index of your choose: ");
        option = entry.nextInt();

        switch (option){
            case 1:
                System.out.println((num1 + num2));
                break;
            case 2:
                System.out.println((num1 - num2));
                break;
            case 3:
                System.out.println(((float)num1 / num2));
                break;
            case 4:
                System.out.println((num1 * num2));
                break;
            default:
                System.out.println("Please entry correct index of your option");
        }
    }
}
