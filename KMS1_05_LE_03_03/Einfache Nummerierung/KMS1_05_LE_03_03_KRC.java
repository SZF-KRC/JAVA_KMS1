import java.util.Scanner;

public class KMS1_05_LE_03_03_KRC {
    public static void main(String[] args) {
        int firstNumber, endNumber, nextHop;
        Scanner entry = new Scanner(System.in);

        while (true){
            System.out.print("Enter first number of List: ");
            if (entry.hasNextInt()){
                firstNumber = entry.nextInt();
                break;
            }else {
                System.out.println("Enter only a integer number");
                entry.nextLine();
            }
        }
        while (true){
            System.out.print("Enter end number of List: ");
            if (entry.hasNextInt()){
                endNumber = entry.nextInt();
                if (endNumber > firstNumber){
                    break;
                }else {
                    System.out.println(STR."End number have to be bigger than\{firstNumber}");
                    entry.nextLine();
                }
            }else {
                System.out.println("Enter only a integer number");
                entry.nextLine();
            }
        }
        while (true){
            System.out.print("Enter number of next hop: ");
            if (entry.hasNextInt()){
                nextHop = entry.nextInt();
                if (nextHop > 0){
                    break;
                }else {
                    System.out.println("Write only positive number");
                    entry.nextLine();
                }
            }else {
                System.out.println("Enter only a integer number");
                entry.nextLine();
            }
        }

        for (int i = firstNumber; i <= endNumber; i +=nextHop){
            System.out.println(i);
        }
    }
}
