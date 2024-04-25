import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        boolean exit=false;

        Car auto1 = new Car("Skoda", "Octavia", 500, "red", 3500, "diesel", 90.0, 5);
        Car auto2 = new Car("Toyota", "Prius", 500, "red", 3500, "diesel", 90.0, 5);
        Car auto3 = new Car("Audi", "A5", 500, "red", 3500, "petrol", 90.0, 5);

        Truck lkw1 = new Truck("Volvo", "S500", 15000, "white", 12000, "diesel", 250.0, 22000);
        Motorcycle motorrad = new Motorcycle("Yamaha", "YBR", 1000, "Black", 80, "petrol", 12.0, 5);
        Bicycle fahrrad = new Bicycle("BMX", "Hill", 0, "Black", 15, "Manual", null, 5);

        Function function = new Function();

        function.addCar(auto1);
        function.addCar(auto2);
        function.addCar(auto3);
        function.addTruck(lkw1);
        function.addBicycle(fahrrad);
        function.addMotorcycle(motorrad);

        while (!exit){
            try {
                function.mainMenu(); // print of the main menu
                switch (entry.nextInt()){ // user enter integer number of index
                    case 0: // Manual
                        System.out.print("\n[1] Deutsch manual\n[2] English manual\nEnter index of your choice: ");
                        switch (entry.nextInt()){
                            case 1:
                                function.readFile("manual_deutsch");
                                break;
                            case 2:
                                function.readFile("manual_english");
                            default:
                                System.out.println("Wrong entry! Enter only index 1 or 2");
                        }

                        break;
                    case 1: // Car manager
                        System.out.println("Car manager");
                        function.vehicleMenu("Car"); // calling Vehicle Menu and send string which we use rename some parts and send to another function
                        break;
                    case 2: // Truck manager
                        System.out.println("Truck manager");
                        function.vehicleMenu("Truck");
                        break;
                    case 3: // Motorcycle manager
                        System.out.println("Motorcycle manager");
                        function.vehicleMenu("Motorcycle");
                        break;
                    case 4: // Bicycle manager
                        System.out.println("Bicycle manager");
                        function.vehicleMenu("Bicycle");
                        break;
                    case 5: // Exit program
                        System.out.println("Exit program");
                        exit = true;
                        break;
                    default:
                        System.out.println("Enter only index from 0 - 5 !");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number for index !");
                entry.nextLine();
            }
        }
    }
}
