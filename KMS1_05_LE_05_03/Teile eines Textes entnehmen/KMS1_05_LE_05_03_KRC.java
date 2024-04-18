import java.util.Scanner;

public class KMS1_05_LE_05_03_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        int startItem, endItem, startCustomer, endCustomer;
        String inputText, item, customer;
        boolean error = false;

        while (!error) {
            try {
                System.out.println("Enter the text (theItem[Item]wasOrderedBy[Customer])");
                inputText = entry.nextLine();

                startItem = inputText.indexOf("[") + 1;
                endItem = inputText.indexOf("]");
                item = inputText.substring(startItem, endItem);

                startCustomer = inputText.lastIndexOf("[") + 1;
                endCustomer = inputText.lastIndexOf("]");
                customer = inputText.substring(startCustomer, endCustomer);

                System.out.println("Item: " + item + "\nCustomer: " + customer);
                error = true;
            } catch (Exception e) {
                System.out.println("Write correct text : theItem[name of your item]wasOrderedBy[Customer name]");
            }
        }
    }
}
