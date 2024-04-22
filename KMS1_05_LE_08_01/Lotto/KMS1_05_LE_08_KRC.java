import java.util.*; // Import all classes from the java.util package, which includes ArrayList, Scanner, Collections, etc.

public class KMS1_05_LE_08_KRC {
    public static void main(String[] args) {
        int numberLotto;// Declare an integer variable to store each number input by the user
        ArrayList<Integer> lotto = new ArrayList<>();// Create a new ArrayList to store the numbers entered by the user
        Scanner entry = new Scanner(System.in);
        for (int i = 1; i < 7; i++){// Start a loop that iterates 6 times, for each number entry
            boolean error = true;
            while (error){
                try {
                    System.out.println("Enter a "+ i +". number between 1 and 45:");
                    numberLotto=entry.nextInt();
                    if ((numberLotto >0) && (numberLotto<=45)){// Check if the number is within the valid range
                        if (lotto.contains(numberLotto)){// Check if the number has already been entered
                            System.out.println("This number has already been entered");
                        }else {
                            lotto.add(numberLotto);// Add the new number to the ArrayList
                            error = false;// Set the error flag to false to exit the while loop
                        }
                    }else {
                        // Throw an exception if number is out of range
                        throw new IllegalArgumentException("Number must be between 1 and 45");
                    }
                }catch (InputMismatchException e){// Catch block to handle the case where input is not an integer
                    System.out.println("Write only integer number");
                    entry.nextLine();// Clear the input buffer
                }catch (IllegalArgumentException e){// Catch block to handle the case where the number is out of range
                    System.out.println(e.getMessage());
                    entry.nextLine();
                }
            }
        }
        Collections.sort(lotto);// Sort the ArrayList in ascending order
        System.out.println("You have chosen these numbers: "+lotto);
    }
}
