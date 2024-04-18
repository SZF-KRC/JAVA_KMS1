
import java.util.Scanner;

public class KMS1_05_LE_04_04_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int[] lotto = new int[5];
        int[] keys = new int[45];
        int rand, min = 1, max = 45, numTicket, totalNum = 0, overN = 10;
        double percent;

        while (true){
            System.out.print("Enter integer of how many times you want repeat your Lucky Numbers: ");
            if (entry.hasNextInt()){
                numTicket = entry.nextInt();
                if (numTicket > 0){
                    break;
                }else {
                    System.out.println("Write only positive number");
                }
            }else {
                System.out.println("Enter only a integer");
            }
        }
        for (int j = 0; j < numTicket; j++){
            for (int i = 0; i < lotto.length; i++){
                rand = (int)Math.floor(Math.random() * (max - min + 1)+ min);
                for (int num : lotto){
                    while (num == rand){
                        rand = (int)Math.floor(Math.random() * (max - min + 1)+ min);
                    }
                }
                lotto[i]= rand;
                keys[rand-1]++;
                totalNum++;
            }
        }
        // this part helped me CHat GPT **
    for (int i = 0; i < keys.length;i++){
        if (keys[i] > overN){
            percent = (double)keys[i] / totalNum * 100;
            System.out.printf("Number %d appeared %d times (%.2f%%)\n", i + 1, keys[i], percent);
        }// **
    }
    }
}
