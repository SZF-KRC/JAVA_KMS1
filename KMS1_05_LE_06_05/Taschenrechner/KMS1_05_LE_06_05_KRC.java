import java.util.Scanner;

public class KMS1_05_LE_06_05_KRC {
    public static void main(String[] args) {
        // Main entry point of the program. Starts the user interaction for operation choice.
        choiceMenu();
    }
    // Adds two integers and returns the result
    static int plus(int firstNum, int secondNum){return firstNum + secondNum;}
    // Adds two doubles and returns the result
    static double plus(double firstNum, double secondNum){return firstNum + secondNum;}
    // Subtracts the second integer from the first and returns the result
    static int minus(int firstNum, int secondNum){return firstNum - secondNum;}
    // Subtracts the second double from the first and returns the result
    static double minus(double firstNum, double secondNum){return firstNum - secondNum;}
    // Multiplies two integers and returns the result
    static int multiply(int firstNum, int secondNum){return firstNum * secondNum;}
    // Multiplies two doubles and returns the result
    static double multiply(double firstNum, double secondNum){return firstNum * secondNum;}
    // Divides the first double by the second and returns the result
    static double divide(double firstNum, double secondNum){return firstNum / secondNum;}

    static void printMenu(){// Displays the menu of operations to the user
        System.out.println("\n[1] plus\n[2] minus\n[3] multiply\n[4] divide\n[5] exit Program\nEnter [index] of your choice: ");
    }

    static void choiceMenu(){// Manages the user choices from the menu and error handle
        Scanner entry = new Scanner(System.in);
        boolean error = false;
        // Continue displaying the menu until a valid choice is made or the user exits
        while (!error){
            try {
                printMenu();
                switch (entry.nextInt()){// Reads the user choice from input
                    case 1:// Plus operation
                        calculation(1);
                        error = true;// Exit the loop after successful operation
                        break;
                    case 2:// Minus operation
                        calculation(2);
                        error = true;
                        break;
                    case 3:// Multiply operation
                        calculation(3);
                        error=true;
                        break;
                    case 4:// Divide operation
                        calculation(4);
                        error=true;
                        break;
                    case 5:// Exit the program
                        return;
                    default:// Handle invalid menu options
                        System.out.println("*** Invalid option! Enter only numbers from 1 - 5 ***");
                }
            }catch (Exception e){// Catch invalid inputs that are not integers
                System.out.println("*** Write only a number for index ! ***");
                entry.nextLine();// Consume the invalid input to prevent an infinite loop
            }
        }
    }
    // Performs the calculation based on the user's operation choice
    static void calculation(int choice){
        int firstNumInt, secondNumInt;
        double firstNumDouble, secondNumDouble;
        firstNumDouble = checkNumber("first");
        secondNumDouble = checkNumber("second");

        if (choice == 1){
            // Decide whether to use integer or double operations based on the presence of a decimal part
            if (firstNumDouble % 1 == 0 && secondNumDouble % 1==0) {
                firstNumInt = (int) firstNumDouble;
                secondNumInt = (int) secondNumDouble;
                System.out.println("Result: " + plus(firstNumInt,secondNumInt));
            }else {
                System.out.println("Result: " + plus(firstNumDouble,secondNumDouble));
            }
        } else if (choice == 2) {
            if (firstNumDouble % 1 == 0 && secondNumDouble % 1==0) {
                firstNumInt = (int) firstNumDouble;
                secondNumInt = (int) secondNumDouble;
                System.out.println("Result: " + minus(firstNumInt,secondNumInt));
            }else {
                System.out.println("Result: " + minus(firstNumDouble,secondNumDouble));
            }
        }else if (choice == 3) {
            if (firstNumDouble % 1 == 0 && secondNumDouble % 1==0) {
                firstNumInt = (int) firstNumDouble;
                secondNumInt = (int) secondNumDouble;
                System.out.println("Result: " + multiply(firstNumInt,secondNumInt));
            }else {
                System.out.println("Result: " + multiply(firstNumDouble,secondNumDouble));
            }
        }else if (choice == 4) {
            while (true){
                if (secondNumDouble == 0){// Special case for division to handle division by zero
                    System.out.println("You can not divide with 0 !");
                    secondNumDouble = checkNumber("second");
                }else {
                    break;
                }
            }
            System.out.printf("Result: %.2f", divide(firstNumDouble,secondNumDouble));
        }

    }
    // Validates and reads a number from the user
    static double checkNumber(String message){
        Scanner entry = new Scanner(System.in);
        double numberDouble;
        int numberInteger;
        String temporaryInput;
        while (true){
            try {
                System.out.println("Enter the " + message + " number please");
                temporaryInput = entry.nextLine();

                if(temporaryInput.contains(".")){
                    try {
                        numberDouble = Double.parseDouble(temporaryInput);
                        return numberDouble;
                    }catch (NumberFormatException e){
                        System.out.println("*** Enter a valid floating number");
                    }
                }else {
                    try {
                        numberInteger = Integer.parseInt(temporaryInput);
                        return numberInteger;
                    }catch (NumberFormatException e){
                        System.out.println("*** Enter a valid integer number ***" );
                    }
                }
            }catch (Exception e){
                System.out.println("*** Enter only a number please! ***");
                entry.nextLine();
            }
        }
    }
}
