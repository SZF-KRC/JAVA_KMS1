import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Function {

    // 4 List
    private List<Car> cars;
    private List<Truck>trucks;
    private List<Motorcycle>motorcycles;
    private List<Bicycle>bicycles;
    public Function() {
        this.cars = new ArrayList<>();
        this.trucks = new ArrayList<>();
        this.motorcycles = new ArrayList<>();
        this.bicycles = new ArrayList<>();
    }
    public void addCar(Car car){// add new car to List
        cars.add(car);
    }
    public void addTruck(Truck truck){// add new truck to List
        trucks.add(truck);
    }
    public void addMotorcycle(Motorcycle motorcycle){// add new motorcycle to List
        motorcycles.add(motorcycle);
    }
    public void addBicycle(Bicycle bicycle){// add new bicycle to List
        bicycles.add(bicycle);
    }

    public void mainMenu(){
        System.out.print("\n[0] help\n[1] Car manager\n[2] Truck manager\n[3] Motorcycle manager\n[4] Bicycle manager\n[5] Exit program\nEnter index of your choice: ");
    }
    // vehicle Menu is universal menu for all vehicles
    public void vehicleMenu(String vehicle){
        Scanner entry = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            try {
                System.out.println("[1] add " + vehicle +"\n[2] delete " + vehicle +"\n[3] add Fuel\n[4] add Km\n[5] show " + vehicle +"s\n[0] back to main Menu\nEnter index of your choice: ");
                switch (entry.nextInt()){
                    case 1:
                        System.out.println("add " + vehicle);
                        inputVehicle(vehicle);
                        break;
                    case 2:
                        System.out.println("delete "+ vehicle);
                        removeVehicle(vehicle);
                        break;
                    case 3:
                        System.out.println("add Fuel");
                        addFuelToVehicleOption(vehicle);
                        break;
                    case 4:
                        System.out.println("add Km");
                        addKmToVehicleOption(vehicle);
                        break;
                    case 5:
                        System.out.println("show "+ vehicle + "s");
                        printVehicleWithIndex(vehicle);
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("*** Enter only index from 0 - 5 ! ***");
                }
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number for index ! ***");
                entry.nextLine();
            }
        }

    }

    // this function adding fuel or adding km , depends on what option want a User
    public void addFuelKMToVehicleList(List<?> vehicleList, String vehicle, String option){
        if (vehicleList.isEmpty()){// check first if List which user want is empty or not
            System.out.println("--- no data for ---" + vehicle);
        }else {
            printVehicleWithIndex(vehicle); // print vehicles with index
            System.out.print("Enter index of your " + vehicle + " for "+option+": ");
            int index = intEntry(); // user give index number but is also checked if is really integer number
            if (index >= 0 && index < vehicleList.size()){// checking if index number is in range 0 - how many indexes are in the List actual
                if (option.equals("refuel")){ // checking with option gave User if he want refuel or add km
                    if (vehicle.equals("Bicycle")){ // if have a bicycle than we asking to top up KW
                        System.out.println("Enter the number of kw you want to top up:");
                    }else {
                        System.out.print("Enter the number of liters you want to top up: ");
                    }
                } else if (option.equals("addKm")) {
                    System.out.print("Enter km: ");
                }
                double amount = doubleEntry(); // user add amount which is check if is really double number
                switch (vehicle){ // first switch detecting which vehicle we have
                    case "Car":
                        switch (option){ // second switch detecting which option user want
                            case "refuel":
                                cars.get(index).addFuel(amount); // add fuel to concrete car which added user and with amount which gave user
                                break;
                            case "addKm":
                                cars.get(index).addKm((int)amount); // also giving new km to specific car, but because we have declare integer for km than must first retype to int
                                break;
                        }
                        break;
                    case "Truck":
                        switch (option) {
                            case "refuel":
                                trucks.get(index).addFuel(amount);
                                break;
                            case "addKm":
                                trucks.get(index).addKm((int) amount);
                                break;
                        }
                        break;
                    case "Motorcycle":
                        switch (option) {
                            case "refuel":
                                motorcycles.get(index).addFuel(amount);
                                break;
                            case "addKm":
                                motorcycles.get(index).addKm((int) amount);
                                break;
                        }
                        break;
                    case "Bicycle":
                        switch (option) {
                            case "refuel":
                                bicycles.get(index).addFuel(amount);
                                break;
                            case "addKm":
                                bicycles.get(index).addKm((int) amount);
                                break;
                        }
                        break;
                }
            }else { // if user gave wrong index then print message
                System.out.println("\n*** Enter index between 0 - " + (vehicleList.size()-1) + " for "+ vehicle + " ***\n");
            }
        }
    }

    // this function separate user option and sending List, conrete vehicle and option
    public void addKmToVehicleOption(String vehicle){
        switch (vehicle){
            case "Car":
                addFuelKMToVehicleList(cars,vehicle,"addKm");
                break;
            case "Truck":
                addFuelKMToVehicleList(trucks, vehicle, "addKm");
                break;
            case "Motorcycle":
                addFuelKMToVehicleList(motorcycles, vehicle, "addKm");
                break;
            case "Bicycle":
                addFuelKMToVehicleList(bicycles, vehicle, "addKm");
                break;
        }
    }
    // this function separate user option and sending List, conrete vehicle and option
    public void addFuelToVehicleOption(String vehicle){
        switch (vehicle){
            case "Car":
                addFuelKMToVehicleList(cars,vehicle,"refuel");
                break;
            case "Truck":
                addFuelKMToVehicleList(trucks, vehicle,"refuel");
                break;
            case "Motorcycle":
                addFuelKMToVehicleList(motorcycles, vehicle,"refuel");
                break;
            case "Bicycle":
                addFuelKMToVehicleList(bicycles, vehicle,"refuel");
                break;
        }
    }
    // remove concrete vehicle from List
    private void removeVehicleFromList(List<?> vehicleList, String vehicle){
        if (vehicleList.isEmpty()){
            System.out.println("--- no data for ---" + vehicle);
        }else {
            printVehicleWithIndex(vehicle);
            int index = intEntry();
            if (index >= 0 && index < vehicleList.size()){
                System.out.println(vehicle +" "+ vehicleList.get(index) + " is deleted.\n");
                vehicleList.remove(index);
            }else {
                System.out.println("\n*** Enter index between 0 - " + (vehicleList.size()-1) + " for "+ vehicle + " ***\n");
            }
        }
    }
    // this is help function to separate vehicle and send concrete List to removeVehicleFromList
    public void removeVehicle( String vehicle){
        switch (vehicle){
            case "Car":
                removeVehicleFromList(cars, vehicle);
                break;
            case "Truck":
              removeVehicleFromList(trucks, vehicle);
              break;
            case "Motorcycle":
                removeVehicleFromList(motorcycles, vehicle);
                break;
            case "Bicycle":
               removeVehicleFromList(bicycles, vehicle);
               break;
        }
    }


    // add car to class Car and to the list cars and print new vehicle
    public void addCar(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int seats){
        Car car = new Car(brand,model,km, color,weight,fuelType,fuelLevel,seats);
        addCar(car);
        System.out.println(car);
    }
    // add truck to class Truck and to the list truck and print new vehicle
    public void addTruck(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int load_capacity){
        Truck truck = new Truck(brand,model,km, color,weight,fuelType,fuelLevel,load_capacity);
        addTruck(truck);
        System.out.println(truck);
    }
    // add motorcycle to class Motorcycle and to the list motorcycles and print new vehicle
    public void addMotorcycle(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int power){
        Motorcycle motorcycle = new Motorcycle(brand,model,km, color,weight,fuelType,fuelLevel, power);
        addMotorcycle(motorcycle);
        System.out.println(motorcycle);
    }
    // add bicycle to class Bicycle and to the list bicycles and print new vehicle
    public void addBicycle(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int gears){
        Bicycle bicycle = new Bicycle(brand,model,km, color,weight,fuelType,fuelLevel,gears);
        addBicycle(bicycle);
        System.out.println(bicycle);
    }

    // universal control if user input is integer number
    public int intEntry(){
        Scanner entry = new Scanner(System.in);
        int number;
        while (true){
            try {
                number = entry.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only integer number ***");
                entry.nextLine();
            }
        }
        return number;
    }
    // universal control if user input is double number
    public double doubleEntry(){
        Scanner entry = new Scanner(System.in);
        double number;
        while (true){
            try {
                number = entry.nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only double number ***");
                entry.nextLine();
            }
        }
        return number;
    }
    // universal function to add data to vehicles, but we must first check if all data are correct before send to classes
    public void inputVehicle(String vehicle){
        Scanner entry = new Scanner(System.in);
        String brand,model,color,fuelType;
        int km, seats, load_capacity, power, gears;
        double weight;
        Double fuelLevel=null; // if we not add fuelLevel than we give empty variable

        System.out.print("Enter brand: ");
        brand = entry.nextLine();
        System.out.print("Enter model: ");
        model = entry.nextLine();
        System.out.print("Enter Km: ");
        km = intEntry(); // checking if user input is really integer
        System.out.print("Enter color: ");
        color = entry.nextLine();
        System.out.print("Enter weight: ");
        weight = doubleEntry(); // checking if user input is really double
        System.out.print("Enter fuel type (petrol, diesel, electric, manual): ");
        fuelType = entry.nextLine();

        // every vehicle have another input data
        switch (vehicle){
            case "Car":
                System.out.print("Enter fuel level: ");
                fuelLevel= doubleEntry();
                System.out.print("Enter seats: ");
                seats = intEntry();
                addCar(brand,model,km, color,weight, fuelType,fuelLevel,seats);
                break;
            case "Truck":
                System.out.print("Enter fuel level: ");
                fuelLevel= doubleEntry();
                System.out.print("Enter load capacity: ");
                load_capacity = intEntry();
                addTruck(brand,model,km,color,weight,fuelType,fuelLevel,load_capacity);
                break;
            case "Motorcycle":
                System.out.print("Enter fuel level: ");
                fuelLevel= doubleEntry();
                System.out.print("Enter power: ");
                power = intEntry();
                addMotorcycle(brand,model,km,color,weight,fuelType,fuelLevel,power);
                break;
            case "Bicycle":
                if (fuelType.equals("electric")) {
                    System.out.print("Enter kw : ");
                    fuelLevel= doubleEntry();
                }
                System.out.print("Enter gears: ");
                gears = intEntry();
                addBicycle(brand,model,km, color,weight,fuelType, fuelLevel, gears);
                break;
        }
    }



    public List<Car> getCars() {
        return cars;
    }

    public List<Truck> getTrucks(){
        return trucks;
    }
    public List<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

    public void printVehicleWithIndex(String vehicle){
        switch (vehicle){
            case "Car":
                List<Car> cars = getCars();
                if (cars.isEmpty()){
                    System.out.println("--- No data for " + vehicle + " ---");
                }else {
                    for (int i = 0; i < cars.size(); i++){
                        System.out.println("Index: "+ i + ", Car: " + cars.get(i));
                    }
                }
                break;

            case "Truck":
                List<Truck> trucks = getTrucks();
                if (trucks.isEmpty()){
                    System.out.println("--- No data for " + vehicle + " ---");
                }else {
                    for (int i = 0; i < trucks.size(); i++){
                        System.out.println("Index: "+ i + ", Truck: " + trucks.get(i));
                    }
                }

                break;
            case "Motorcycle":
                List<Motorcycle> motorcycles = getMotorcycles();
                if (motorcycles.isEmpty()){
                    System.out.println("--- No data for " + vehicle + " ---");
                }else {
                    for (int i = 0; i < motorcycles.size(); i++){
                        System.out.println("Index: "+ i + ", Motorcycle: " + motorcycles.get(i));
                    }
                }
                break;
            case "Bicycle":
                List<Bicycle> bicycles = getBicycles();
                if (bicycles.isEmpty()){
                    System.out.println("--- No data for " + vehicle + " ---");
                }else {
                    for (int i = 0; i < bicycles.size(); i++){
                        System.out.println("Index: "+ i + ", Bicycle: " + bicycles.get(i));
                    }
                }
                break;
        }
    }
    // read manual with different language mode
    public void readFile(String language){
        try {
            File manual = new File(language+".txt");
            Scanner myReader = new Scanner(manual);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("Manual not exist !");
        }
    }
}
