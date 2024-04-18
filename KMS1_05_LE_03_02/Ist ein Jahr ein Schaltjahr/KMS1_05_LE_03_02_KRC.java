import java.util.Scanner;

public class KMS1_05_LE_03_02_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int startYear , endYear ;

        while (true){
            System.out.print("Enter start Year: ");
            if (entry.hasNextInt()){
                startYear = entry.nextInt();
                break;
            }else{
                System.out.println("Enter only a integer number");
                entry.nextLine();
            }
        }
        while (true){
            System.out.print("Enter end Year: ");
            if (entry.hasNextInt()){
                endYear = entry.nextInt();
                break;
            }else{
                System.out.println("Enter only a integer number");
                entry.nextLine();
            }
        }

        for (int i = startYear ; i <= endYear; i++){
            if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)){
               System.out.println(i +" : is a leap year");
            } else  {
                System.out.println(i + " : is not a leap year");
            }
        }
    }
}
