import java.util.Scanner;

public class KMS1_05_LE_04_03_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int howManyTest, perfect = 0, good = 0, notRecommended = 0, rand, min = 1, max = 3;
           double percPerfect, percGood, percNotRecom;

        while (true){
            System.out.print("Enter integer of how many tests to take place for your product: ");
            if (entry.hasNextInt()){
                howManyTest = entry.nextInt();
                if (howManyTest > 0){
                    break;
                }else {
                    System.out.println("Only positive number are acceptable");
                    entry.nextLine();
                }
            }else {
                System.out.println("Enter only a integer");
                entry.nextLine();
            }
        }
        for (int i = 0; i < howManyTest; i++){
            rand = (int) Math.floor(Math.random() * (max - min + 1) + min); // used formula from https://www.educative.io/answers/how-to-generate-random-numbers-in-java
            if (rand == 1){
                notRecommended++;
            } else if (rand == 2) {
                good++;
            } else if (rand == 3) {
                perfect++;
            }
        }

        percPerfect = ((double)perfect/howManyTest)*100;
        percGood = ((double)good/howManyTest)*100;
        percNotRecom = ((double)notRecommended/howManyTest)*100;

        System.out.printf("\nMarket research evaluated your product as follows:\nPerfect: " + perfect + " = " + "%.2f %%" , percPerfect);
        System.out.printf("\nGood: " + good + " = " + "%.2f %%", percGood);
        System.out.printf("\nNot recommended: " + notRecommended + " = " + "%.2f %%", percNotRecom);
        if (perfect > good && perfect > notRecommended){
            System.out.println("\nOverall, the product is perfect");
        } else if (good > perfect && good > notRecommended) {
            System.out.println("\nOverall, the product is good");
        } else if (notRecommended > perfect && notRecommended > good) {
            System.out.println("\nOverall, the product is not recommended");
        }
    }
}
