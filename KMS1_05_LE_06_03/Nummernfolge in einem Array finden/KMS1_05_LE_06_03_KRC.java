import java.util.Scanner;

public class KMS1_05_LE_06_03_KRC {
    public static void main(String[] args) {
        boolean check;

        int userArr[] = checkEntry() ; // check the user input and insert it into the array
        int arrList[] = randomArrList(); // take a random list of array
        check = checkNumber(arrList,userArr); // if entry from user is match with random list

        if (check){
            System.out.println(" Your numbers are in a sequence of consecutive numbers");
        }else {
            System.out.println(" Your numbers do not match");
        }


    }
    // Function for checking input and send correct input in array list
    static int[] checkEntry(){
        int[] userArr = new int[4];
        String userEntry; // entry from user go first to normal String because user use a commas
        String[] temporary; // this temporary array list is for take number from String to arrString without commas
        Scanner entry = new Scanner(System.in);

        while (true){
            try {
                System.out.println("Enter for integer number with comma to check sequence (1,20,15,10): ");
                userEntry = entry.nextLine();
                temporary = userEntry.split(",");// we take a commas from input and transfer to temporary array
                if (temporary.length == 4){ // check if is input only with 4 numbers
                    for (int i = 0; i< userArr.length;i++){
                        userArr[i] = Integer.parseInt(temporary[i]); // transfer from temporary String array to int array
                    }
                    break;
                }else {
                    System.out.println("Please only 4 integer numbers");
                }
            }catch (Exception e){
                System.out.println("Entry correct  4 integer numbers with comma ( 1,50,20,14 ) ");
            }
        }
        return userArr; // return correct integer array
    }

    // for create random array list
    static int[] randomArrList(){
        int[] arrList = new int[50];
        int min = 0, max= 99;

        for (int i = 0; i < arrList.length;i++){
            arrList[i] = (int)Math.floor(Math.random()* (max - min + 1)+ min); // Formula for radnom numbers between min and max
        }
        return arrList; // return random array list with 50 numbers
    }
    // the control for the user's 4 numbers are located in a random array, right after each other
    // this logic helped me ChatGPT
    static boolean checkNumber(int[] arr, int[] controlNumbers){
        for (int i = 0; i < arr.length;i++){
            if (arr[i] == controlNumbers[0]){
                boolean match = true;
                for (int j = 1; j< controlNumbers.length; j++){
                    if (arr[i+j] != controlNumbers[j]){
                        match = false;
                        break;
                    }
                }
                if (match){
                    return true;
                }
            }
        }
        return false;
    }
}
// my first attempt also worked, but I wanted to change the logic and not use index1,2,3,4
//        int countCheck = 0, index1 =0,index2=0,index3=0,index4=0;
//        int intArr[] = {5,44, 10, 15, 22, 99,95,31 };
//        int intArr1[] = { 10, 15, 22, 31 };

//        for (int i = 0; i< intArr.length; i++){
//            if (intArr1[0] == intArr[i]){
//                countCheck++;
//                index1 = i;
//            }
//            if (intArr1[1] == intArr[i]){
//                countCheck++;
//                index2 = i;
//            }
//            if (intArr1[2] == intArr[i]){
//                countCheck++;
//                index3 = i;
//            }
//            if (intArr1[3] == intArr[i]){
//                countCheck++;
//                index4 = i;
//            }
//        }
//
//        if (countCheck == 4){
//            if (index1 + 1 == index2 && index2 + 1 == index3 && index3 + 1 == index4){
//                System.out.println("zhoda");
//            }
//
//        }