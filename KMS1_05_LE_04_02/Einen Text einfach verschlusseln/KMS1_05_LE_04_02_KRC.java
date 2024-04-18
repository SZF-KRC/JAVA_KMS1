import java.util.Scanner;

public class KMS1_05_LE_04_02_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int caesar = 3;
        char oneChar, newAlpha, oneAlpha;
        String newUser= "" ;
        String user;
        char[] alphabet = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'
        };
        System.out.print("Enter your sentence: ");
        user = entry.nextLine().toLowerCase();
        for (int i = 0; i < user.length(); i++){
            oneChar = user.charAt(i);
            if (oneChar == ' '){
                newUser += " ";
            }else {
                for (int j = 0; j < alphabet.length; j++) {
                    oneAlpha = alphabet[j];
                    if (oneChar == oneAlpha) {
                        newAlpha = alphabet[(j + caesar) % alphabet.length];// *
                        newUser += newAlpha;
                    }
                }
            }
        }
        System.out.println(newUser);
    }
}
 // * chat gpt gave me this logic :
// char[] abc = {'a', 'b', 'c'};
//int index = (1 + 2) % abc.length; // (1 + 2) % 3 = 0
//char result = abc[index]; // return 'a'