import java.time.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KMS1_05_LE_07_01_KRC {
    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu(){
        Scanner entry = new Scanner(System.in);
        boolean error = true;
        while (error){
            try {
                System.out.println("[1] calculation of working hours\n[2] order calculation\nEnter index of your choice: ");
                switch (entry.nextInt()){
                    case 1:
                        printWorkHours();
                        error = false;
                        break;
                    case 2:
                        printOrderHours();
                        error = false;
                        break;
                    default:
                        System.out.println("\n*** Enter only a number 1 or 2 ! ***\n");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("\n*** Enter only integer number 1 or 2 ***\n");
                entry.nextLine();
            }
        }
    }

    static int calculationGeneraDateHour(String firstInput, String secondInput, int choice, String prompt) {
        if (choice == 1) { // Handling time
            // Parse the firstInput and secondInput strings into LocalTime objects
            LocalTime startTime = LocalTime.parse(firstInput);
            LocalTime endTime = LocalTime.parse(secondInput);
            // Check if the end time is not before the start time, allowing duration calculation
            if (!endTime.isBefore(startTime)) {
                // Calculate the duration between start and end times
                Duration duration = Duration.between(startTime, endTime);
                long hoursWorked = duration.toHours();// Extract the number of full hours from the duration
                long minutesWorked = duration.toMinutesPart();// Extract the remaining minutes part of the duration
                System.out.println("Hours worked: " + hoursWorked + ":" + minutesWorked);
            } else {
                // If end time is before start time, print the provided prompt and return 0
                System.out.println(prompt);
                return 0;
            }
        } else if (choice == 2) { // Handling dates
            // Parse the firstInput and secondInput strings into LocalDate objects
            LocalDate startDate = LocalDate.parse(firstInput);
            LocalDate endDate = LocalDate.parse(secondInput);
            if (!endDate.isBefore(startDate)) {
                // Calculate the period between the start and end dates
                Period period = Period.between(startDate, endDate);
                long daysDelivery = period.getDays();// Get the number of days from the period
                System.out.println("Delivery took " + daysDelivery + " days");
            } else {
                // If end date is before start date, print the provided prompt and return 0
                System.out.println(prompt);
                return 0;
            }
        }
        return 1;// Return 1 if all operations complete successfully
    }

    static void printWorkHours(){
        int error;
        Pattern regex = Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$"); // regex from ChatGPT
        String arrivalHour = checkEntry(getHoursNow(), "arrival", "hh:mm", regex);
        String departureHour = checkEntry(getHoursNow(), "departure", "hh:mm", regex);

        error = calculationGeneraDateHour(arrivalHour, departureHour, 1, "\n*** Departure time cannot be before arrival time. ***\n");
        while (error == 0){ // If end time is before start time then onw more time entry departureHour
            departureHour = checkEntry(getHoursNow(), "departure", "hh:mm", regex);
            error = calculationGeneraDateHour(arrivalHour, departureHour, 1, "\n*** Departure time cannot be before arrival time. ***\n");
        }
    }
    static void printOrderHours(){
        int error;
        Pattern regex = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$"); // regex from https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
        String orderDate = checkEntry(getDateNow(), "order", "yyyy-mm-dd", regex);
        String deliveryDate = checkEntry(getDateNow(), "delivery", "yyyy-mm-dd", regex);

        error = calculationGeneraDateHour(orderDate,deliveryDate, 2, "\n*** Delivery date cannot be before order day. ***\n");
        while (error == 0){// If end date is before start date then onw more time entry deliveryDate
            deliveryDate = checkEntry(getDateNow(), "delivery", "yyyy-mm-dd", regex);
            error = calculationGeneraDateHour(orderDate,deliveryDate, 2, "\n*** Delivery date cannot be before order day. ***\n");
        }
    }
    // return hour hh:mm
    static String getHoursNow() {
        return LocalTime.now().toString().substring(0, 5);
    }
    // return date yyyy-mm-dd
    static String getDateNow(){
        return LocalDate.now().toString().substring(0,10);
    }
    // Define a method to validate user input against a specified regex pattern
    static String checkEntry(String choiceEntry, String promt, String hour_date, Pattern regex){
        Scanner entry = new Scanner(System.in);
        String entryString;
        while (true){
            try {
                // Prompt the user with a custom message that includes the type of data expected (time or date)
                System.out.println("Entry time of " + promt + " (" + hour_date+ ") or (write: now): ");
                // Read the user's input, convert it to lower case to standardize it for comparison
                entryString = entry.nextLine().toLowerCase(Locale.ROOT);
                // Check if the user's input is "now", which is a special input case
                if (entryString.equals("now")){
                    entryString = choiceEntry;// Set the entryString to the predefined choiceEntry if "now" is entered
                    System.out.println(entryString);
                    return entryString; // Return the choiceEntry(Time or Date now) to the caller, effectively ending the input loop
                } else {
                    // If the input is not "now", use the provided regex to check the format
                    Matcher matcher = regex.matcher(entryString);
                    // Check if the input matches the expected format using the regex
                    if (matcher.find()){
                        return entryString;// Return the valid input to the caller, effectively ending the input loop
                    }else {
                        // If the input does not match, print an error message asking for the correct format
                        System.out.println("\n*** Invalid time format. Please enter time in " + hour_date +" format or 'now'. ***\n");
                    }
                }
            }catch (Exception e){
                System.out.println("\n*** Restart your program !!! ***\n"); // this exception should never be called
            }
        }
    }
}
