import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class KMS1_05_LE_06_04_KRC {
    public static void main(String[] args) {
        String[] arr = callToCheck(choiceMenu()); // Get the user's choice and process the color input accordingly
        convertToCMY(arr); // Convert the RGB values to CMY and display the result
    }

    static void printMenu(){
        System.out.println("Program for transferring your RGB spectrum to CMY" +
                "\n[1] #rgb to CMY\n[2] #rgbc to CMY\n[3] #rrggbb to CMY\n[4] #rrggbbcc to CMY");
    }

    static String[] callToCheck(int choice) {
        // Return the result based on user choice without using a loop or try-catch block
        return switch (choice) {
            case 1 -> checkRGB(); // return checkRGB()
            case 2 -> checkRGBC();
            case 3 -> checkRRGGBB();
            case 4 -> checkRRGGBBCC();
            default -> {
                System.out.println("Unexpected choice. Please restart the program.");
                yield new String[0]; // Return an empty array or handle the error as required
            }
        };
    }


    static void convertToCMY(String[] arr){
        int red, green, blue;
        double c,m,y;
        int[] decArray = convertHexToDecimal(arr); // Convert hex values to decimal
        red = decArray[0];
        green = decArray[1];
        blue = decArray[2];

        // Calculate CMY percentages from RGB
        c = ((255 - (double) red) / 255) * 100;
        m = ((255 - (double) green) / 255) * 100;
        y = ((255 - (double) blue) / 255) * 100;

        // Print the CMY values formatted to one decimal place
        System.out.printf("C: %.1f%% , M: %.1f%% , Y: %.1f%%",c,m,y);

    }

    static int[] convertHexToDecimal(String[] hexArray){
        int[] decArray = new int[3];
        for (int i = 0; i < 3;i++){
            // Parse each hex value into decimal
            decArray[i] = Integer.parseInt(hexArray[i],16);
        }
        return decArray;
    }

    static int choiceMenu(){
        Scanner entry = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                printMenu(); // Display the menu and prompt the user for a choice
                System.out.println("Enter the index of your choice (1-4): ");
                choice = entry.nextInt();

                // Validate the input to be within the allowed range
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }else {
                    System.out.println("\n*** Invalid input. Please enter a number between 1 and 4. ***\n");
                    entry.nextLine();
                }
            }catch (InputMismatchException e){
                System.out.println("\n*** Write only integer number from 1 - 4 ***\n");
                entry.nextLine();
            }
        }
    }

    // *this part of the code helped me simplify and optimize ChatGPT, the origin code is at the end
    static String[] checkHexInput(String prompt, int expectedLength, boolean expandSingle) {
        Scanner entry = new Scanner(System.in);
        String input, cleanInput;
        String[] hexArray;

        while (true) {
            try {
                System.out.println(prompt);
                input = entry.nextLine();
                cleanInput = input.trim().replace("#", "");

                // Check the correct length of the input before proceeding
                if (expandSingle) {
                    // Check if the input length matches the expected length for single character inputs
                    if (cleanInput.length() != expectedLength) {
                        // If not, inform the user about the correct number of characters needed
                        System.out.println("\n*** Invalid length: Enter # with " + expectedLength +
                                " hex digits (example: " + getExample(expectedLength, true) + ") ***\n");
                        continue; // Prompt the user again by continuing the loop
                    }
                } else {
                    // Check if the input length matches the expected length for two character inputs
                    if (cleanInput.length() != expectedLength * 2) {
                        // If not, inform the user about the correct number of characters needed
                        System.out.println("\n*** Invalid length: Enter # with " + (expectedLength * 2) +
                                " hex digits (example: " + getExample(expectedLength, false) + ") ***\n");
                        continue; // Prompt the user again by continuing the loop
                    }
                }

                if (expandSingle) {
                    // Expand single character to double character for each color component
                    hexArray = new String[expectedLength];
                    for (int i = 0; i < expectedLength; i++) {
                        hexArray[i] = "" + cleanInput.charAt(i) + cleanInput.charAt(i);
                    }
                } else {
                    // Split the input into two-character blocks
                    hexArray = cleanInput.split("(?<=\\G..)");
                }
                return hexArray;
            } catch (Exception e) {
                System.out.println("\n*** Enter correct syntax. Red, Green, Blue each from 00 to FF (example: #FF5733) ***\n");
            }
        }
    }

    static String getExample(int length, boolean expandSingle) {
        String example = "#";
        for (int i = 0; i < (expandSingle ? length : length * 2); i++) {
            example += "F";
        }
        return example;
    }

    static String[] checkRGB() {
        return checkHexInput("Enter #rgb", 3, true);
    }

    static String[] checkRGBC() {
        String[] fullInput = checkHexInput("Enter #rgbc", 4, true);
        return Arrays.copyOf(fullInput, 3);  // Return only RGB, ignore C (Alpha)
    }

    static String[] checkRRGGBB() {
        return checkHexInput("Enter #rrggbb", 3, false);
    }

    static String[] checkRRGGBBCC() {
        String[] fullInput = checkHexInput("Enter #rrggbbcc", 4, false);
        return Arrays.copyOf(fullInput, 3);  // Return only RGB, ignore CC (Alpha)
    }
}

// * this is origin code that worked correctly, but some parts of the code were repeated unnecessarily
//static String[] checkRGBC(){
//        Scanner entry = new Scanner(System.in);
//        String rgbc, cleanRgb;
//        String[] rgbcArray;
//        String[] rgbOnly = new String[3];
//
//        while (true){
//            try {
//                System.out.println("Enter #rgbc");
//                rgbc = entry.nextLine();
//                cleanRgb = rgbc.replace("#","");
//                rgbcArray = cleanRgb.split("");
//                if (rgbcArray.length==4){
//                    System.arraycopy(rgbcArray,0,rgbOnly,0,3);
//                    return rgbOnly;
//                }else {
//                    System.out.println("\n*** Enter correct rgbc. enter # with 3 hex number + one transparent number");
//                }
//            }catch (Exception e){
//                System.out.println("\n*** Enter correct syntax #rgbc. Red, Green, Blue + Transparent from 0 until 15 (example: #7ff4) ***\n");
//            }
//        }
//    }
//
//    static String[] checkRRGGBB() {
//        Scanner entry = new Scanner(System.in);
//        String rrggbb, cleanRRGGBB;
//        String[] rrggbbArray;
//
//        while (true) {
//            try {
//                System.out.println("Enter #rrggbb");
//                rrggbb = entry.nextLine();
//                cleanRRGGBB = rrggbb.replace("#", "");
//                rrggbbArray = cleanRRGGBB.split("(?<=\\G..)"); // Rozdelenie každých dvoch znakov
//                if (rrggbbArray.length == 3) {
//                    return rrggbbArray;
//                } else {
//                    System.out.println("\n*** Enter correct rrggbb. Enter # with 6 hex digits (example: #FF5733) ***\n");
//                }
//            } catch (Exception e) {
//                System.out.println("\n*** Enter correct syntax #rrggbb. Red, Green, Blue each from 00 to FF (example: #FF5733) ***\n");
//            }
//        }
//    }
//
//
//    static String[] checkRRGGBBCC() {
//        Scanner entry = new Scanner(System.in);
//        String rrggbbcc, cleanRRGGBBCC;
//        String[] rrggbbccArray;
//        String[] rgbOnly= new String[3];
//
//        while (true) {
//            try {
//                System.out.println("Enter #rrggbbcc");
//                rrggbbcc = entry.nextLine();
//                cleanRRGGBBCC = rrggbbcc.replace("#", "");
//                rrggbbccArray = cleanRRGGBBCC.split("(?<=\\G..)"); // Rozdelenie každých dvoch znakov
//                if (rrggbbccArray.length == 4) {
//                    System.arraycopy(rrggbbccArray,0,rgbOnly,0,3);
//                    return rgbOnly;
//                } else {
//                    System.out.println("\n*** Enter correct rrggbbcc. Enter # with 8 hex digits (example: #FF5733CC) ***\n");
//                }
//            } catch (Exception e) {
//                System.out.println("\n*** Enter correct syntax #rrggbccc. Red, Green, Blue, Alpha each from 00 to FF (example: #FF5733CC) ***\n");
//            }
//        }
//    }
//
//
//    static String[] checkRGB(){
//        Scanner entry = new Scanner(System.in);
//        String rgb, cleanRgb;
//        String[] rgbArray;
//
//        while (true){
//            try {
//                System.out.println("Enter #rgb");
//                rgb = entry.nextLine();
//                cleanRgb = rgb.replace("#","");
//                rgbArray = cleanRgb.split("");
//                if (rgbArray.length==3){
//                    return rgbArray;
//                }else {
//                    System.out.println("\n*** Enter correct rgb. enter # with 3 hex number");
//                }
//            }catch (Exception e){
//                System.out.println("\n*** Enter correct syntax #rgb. Red, Green, Blue from 0 until 15 (example: #7ff) ***\n");
//            }
//        }
//
//
//    }