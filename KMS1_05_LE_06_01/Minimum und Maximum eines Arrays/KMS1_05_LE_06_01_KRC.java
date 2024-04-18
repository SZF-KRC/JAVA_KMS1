import java.util.Scanner;

public class KMS1_05_LE_06_01_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int howManyNumbers;
        while (true){
            try {
                question();
                howManyNumbers = entry.nextInt();
                if (howManyNumbers <=0){
                    System.out.println("*** Enter a positive integer number ! ***");
                    continue;
                }
                int[] arrNumbers = randomNumber(howManyNumbers);
                printInLines(arrNumbers,howManyNumbers);
                minMaxPrint(arrNumbers);
                break;
            }catch (Exception e){
                System.out.println("*** Enter only a integer number ! ***");
                entry.nextLine();
            }
        }
    }
    static void question(){
        System.out.println("From how many numbers he wanted to create an array: ");
    }

    static int[] randomNumber(int repeat){
        int[] arrNumbers = new int[repeat];
        int rand, min = 1, max = 500;

        // entry random numbers to array arrNumbers
        for (int i = 0; i < repeat; i++){
            rand = (int) Math.floor(Math.random() * (max - min + 1) + min);
            arrNumbers[i] = rand;
        }
        return arrNumbers;
    }
    static void printInLines(int[] arrNumbers, int repeat){
        for (int i = 0; i < repeat; i+=10){
            for (int j = i; j < i + 10 && j< repeat; j++){ // this line helped me ChatGPT
                System.out.print(arrNumbers[j] + " ");
            }
            System.out.println(); // New line after every 10 numbers
        }
    }

    static void minMaxPrint(int[] arrNumbers){
       int minNumber = arrNumbers[0]; // we declare first number in list that is min and max.
       int maxNumber= arrNumbers[0];
        // searching for min and max number in list
        for (int num : arrNumbers){
            if (num < minNumber){ // if number in array arrNumbers is lower then write new minNumber
                minNumber = num;
            }
            if (num > maxNumber){// if number in array arrNumbers is bigger then write new maxNumber
                maxNumber = num;
            }
        }
        System.out.println("\nLower Number is: " + minNumber + "\nHighest Number is: " + maxNumber);
    }
}
