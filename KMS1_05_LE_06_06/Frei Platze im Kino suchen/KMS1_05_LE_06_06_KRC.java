public class KMS1_05_LE_06_06_KRC {
    public static void main(String[] args) {
        printResult();
    }
    // return random number 0 or 1
    static int randomNumber(){
        return Math.random() < 0.3 ? 1 : 0; // 30% chance for 1
    }
    // generally calculating percentages
    static double countPercent(int numerator, int denominator){
        return (double) numerator / denominator * 100;
    }
    // randomly filling a seat in the cinema
    static int[] randomSeats(){
        int[] seat = new int[200]; // 200 free spaces in Cinema
        for (int i = 0; i < seat.length; i++){
            seat[i] = randomNumber(); // insert a random occupancy
        }
        return seat;
    }
    static void printResult(){
        int[] seat = randomSeats();
        int index, countO=0, countX = 0;

        for (int i = 65; i < 75 ;i++){
            System.out.print((char)i + " row\t");
            for (int j = 0; j < 20; j++){
                index = (i-65) * 20 + j; //calculates the index for each seat within each row, where i represents the row and j the column in the row.
                if (seat[index] == 0){
                    System.out.print("."); // free seat
                    countO++;
                }else {
                    System.out.print("X"); // seat taken
                    countX++;

                }
            }
            System.out.printf(" %.0f%% = %d free places", countPercent(countO,20), countO);
            countO = 0; // every row start from 0
            System.out.println();
        }
        System.out.printf("\nThe occupancy of the cinema is: %.1f%%\nFree seats: %d\n", countPercent(countX, seat.length), (seat.length - countX));
    }
}
