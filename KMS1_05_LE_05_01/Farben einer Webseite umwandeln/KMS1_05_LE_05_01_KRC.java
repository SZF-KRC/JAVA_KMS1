import java.util.Scanner;

public class KMS1_05_LE_05_01_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int red , green , blue ;
        String hexNumber;
        boolean error;

        do {
            error = false;
            try {
                System.out.print("Write hex number (example: #FFFFFF): #");
                hexNumber = entry.nextLine();
                if (hexNumber.length() == 6){
                    red = Integer.parseInt(hexNumber.substring(0,2),16);
                    green = Integer.parseInt(hexNumber.substring(2,4),16);
                    blue = Integer.parseInt(hexNumber.substring(4,6),16);
                    if (red < 0 || green < 0 || blue < 0){
                        System.out.println("Write only positive number !");
                        error = true;
                    }else {
                        System.out.println("rgb(" + red + "," + green + "," + blue + ")");
                    }
                }else {
                    System.out.println("Invalid color code. Please enter a 6 hexadecimal value example: #FFFFFF");
                    error = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Hex number are 0-9,A-F !");
                error = true;
            }
        }while (error);
    }
}
