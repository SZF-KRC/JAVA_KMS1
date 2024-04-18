
public class KMS1_05_LE_06_02_KRC {
    public static void main(String[] args) {
        // Testing the sumNumber method with different numbers of inputs
        sumNumber(10,20); // Two arguments
        sumNumber(1,2,3,4,5); // Five arguments
        sumNumber(); // Zero arguments
    }
    // Method with varargs int...
    public static void sumNumber(int... numbers){
        int total = 0;
        for (int num : numbers){
            total += num; // count every single number in numbers
        }
        System.out.println("\nTotal sum: " + total + "\nTotal numbers: " + numbers.length);
    }
}
