package manager.function;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Function {
    private final Scanner entry = new Scanner(System.in);
    private static final HashSet<String> uniqueID = new HashSet<>();


    // get only two option of answer
    public String getAnswer() {
        boolean exit = false;
        String answer="";
        while (!exit) {
            answer = readLine().toLowerCase();
            switch (answer) {
                case "yes", "no":exit=true;break;
                default: System.out.println("Enter only [yes] or [no].");
            }
        }
        return answer;
    }

    // universal control if user input is integer number
    public int intEntry(){
        int number;
        while (true){
            try {
                number = entry.nextInt();
                entry.nextLine();
                break;
            }catch (InputMismatchException e){
                System.out.print("--- Enter only integer number: ");
                entry.nextLine();
            }
        }
        return number;
    }

    // read manual with different language mode
    public void readFile(String language){
        try {
            File manual = new File("docs/"+language+".txt");
            Scanner myReader = new Scanner(manual);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("Manual not exist !");
        }
    }

    public void closeScanner(){
        entry.close();
    }
    public String readLine() {
        boolean exit= false;
        String line="";
        while (!exit){
            line = entry.nextLine();
            if (line.isEmpty()){
                System.out.print("--- Input cannot be empty. Please try again --- :");
            } else if (!line.matches("[a-zA-Z ]+")) {
                System.out.print("--- Invalid input. Write only alphabets --- :");
            } else {
                exit=true;
            }
        }
        return line;
    }

    // generate random unique ID
    public String generateUniqueID() {
        int lengthUniqueID = 10;
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        Random random = new Random();
        String uniID;
        do {
            StringBuilder result = new StringBuilder();
            while (result.length() < lengthUniqueID) {
                int index = random.nextInt(characterSet.length());
                result.append(characterSet.charAt(index));
            }
            uniID = result.toString();
        } while (uniqueID.contains(uniID));
        uniqueID.add(uniID);
        return uniID;
    }

}
