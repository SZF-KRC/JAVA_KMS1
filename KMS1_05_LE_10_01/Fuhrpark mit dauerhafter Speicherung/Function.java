import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Function {

    private final Scanner entry = new Scanner(System.in);
    private static final HashSet<String> uniqueID = new HashSet<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Car> cars;
    private List<Truck>trucks;
    private List<Motorcycle>motorcycles;
    private List<Bicycle>bicycles;
    private final Database_ data = new Database_();
    public Function() {
        this.cars = new ArrayList<>();
        this.trucks = new ArrayList<>();
        this.motorcycles = new ArrayList<>();
        this.bicycles = new ArrayList<>();
    }

    public int getCountVehicle(){
        return Vehicle.getCount();
    }
    public int getCountCar(){
        return cars.size();
    }
    public int getCountTruck(){
        return  trucks.size();
    }
    public int getCountMotorcycle(){
        return motorcycles.size();
    }
    public int getCountBicycle(){
        return bicycles.size();
    }
    public int getCountEmployee(){
        return employees.size();
    }
    public String readLine() {
        return entry.nextLine();
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }
    public void setMotorcycles(List<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }
    public void setBicycles(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }
    public void setEmployees(List<Employee> employees){
        this.employees = employees;
    }
    public void closeScanner(){
        entry.close();
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

    // add car to class Car and to the list cars and to the database
    public void addCar(String uniID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int seats){
        Car car = new Car(uniID,brand,model,km, color,weight,fuelType,fuelLevel,tankCapacity,seats);
        cars.add(car);
        data.addCarToDatabase(car);
    }
    // add truck to class Truck and to the list truck and to the database
    public void addTruck(String uniID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int load_capacity){
        Truck truck = new Truck(uniID,brand,model,km, color,weight,fuelType,fuelLevel,tankCapacity,load_capacity);
        trucks.add(truck);
        data.addTruckToDatabase(truck);
    }
    // add motorcycle to class Motorcycle and to the list motorcycles and to the database
    public void addMotorcycle(String uniID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int power){
        Motorcycle motorcycle = new Motorcycle(uniID,brand,model,km, color,weight,fuelType,fuelLevel,tankCapacity, power);
        motorcycles.add(motorcycle);
        data.addMotorcycleToDatabase(motorcycle);
    }
    // add bicycle to class Bicycle and to the list bicycles and to the database
    public void addBicycle(String uniID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int gears){
        Bicycle bicycle = new Bicycle(uniID,brand,model,km, color,weight,fuelType,fuelLevel,tankCapacity,gears);
        bicycles.add(bicycle);
        data.addBicycleToDatabase(bicycle);
    }
    //add employee to record class Employee
    public void addEmployee(String uniID, String name, String surname){
        Employee employee = new Employee(uniID,name,surname);
        employees.add(employee);
        data.addEmployeeToDatabase(employee);
    }

    // this method adding new km to concrete vehicle found by index and sending data to class Vehicle and update info in Database
    public void addNewKM(List<? extends Vehicle> vehicleList, String vehicle, int index, double fuel_consumption) {
        boolean exit = false;
        if (index >= 0 && index < vehicleList.size()) {
            Vehicle vehicleToUpdate = vehicleList.get(index);
            while (!exit){
                System.out.print("Enter km: ");
                int newKm = intEntry();
                if(vehicleToUpdate.driveKm(newKm, vehicle, fuel_consumption)){
                    data.updateKmVehicleInDatabase(vehicleToUpdate.getUniqueID(), vehicle, newKm, vehicleToUpdate.getFuelLevel());
                    exit = true;
                }
            }
        } else {
            System.out.println("\n*** Enter an index between 0 and " + (vehicleList.size() - 1) + " for " + vehicle + " ***\n");
        }
    }

    public void enterKmRefuelVehicleByIndex(List<? extends Vehicle> vehicleList, String vehicle, String option, double fuel_consumption){
        if (vehicleList.isEmpty()) {// check first if List which user want is empty or not
            System.out.println("\n--- no data for " + vehicle +" ---\n");
        } else {
            printVehicleWithIndex(vehicle);
            System.out.print("Enter index of your " + vehicle + ": ");
            int index = intEntry(); // user give index number but is also checked if is really integer number
            switch (option){
                case "addKm": addNewKM(vehicleList,vehicle,index, fuel_consumption);break;
                case "refuel": refuel(vehicleList,vehicle, index);break;
            }
        }
    }

    public void refuel(List<? extends Vehicle> vehicleList, String vehicle, int index) {
        if (index >= 0 && index < vehicleList.size()) {
            Vehicle vehicleToUpdate = vehicleList.get(index);
            Double newFuelLevel = vehicleToUpdate.refuelToFull();
            data.updateFuelLevelVehicleInDatabase(vehicleToUpdate.getUniqueID(),vehicle, newFuelLevel);
        } else {
            System.out.println("\n*** Enter an index between 0 and " + (vehicleList.size() - 1) + " for " + vehicle + " ***\n");
        }
    }

    public void changeEmployeeName(String uniID, String newName, String newSurname){
        for (Employee employee : employees){
            if (employee.uniqueID().equals(uniID)){
                Employee updatedEmployee = new Employee(uniID, newName,newSurname);
                employees.set(employees.indexOf(employee), updatedEmployee);
                data.updateEmployeeInDatabase(uniID,newName,newSurname);
            }
        }
    }
    // remove Employee from list and from Database
    public void removeEmployee(String uniID){
        employees.removeIf(employee -> employee.uniqueID().equals(uniID));
        data.deleteEmployeeFromDatabase(uniID);
    }
    // universal method for deleting Vehicle from concrete list
    public void removeVehicleFromList(List<? extends Vehicle> vehicleList, String vehicleType) {
        if (vehicleList.isEmpty()) {
            System.out.println("\n--- no data for " + vehicleType +" ---\n");
        } else {
            printVehicleWithIndex(vehicleType);
            int index = intEntry();
            if (index >= 0 && index < vehicleList.size()) {
                Vehicle vehicle = vehicleList.get(index);
                String uniqueID = vehicle.getUniqueID();
                vehicleList.remove(index);
                data.deleteVehicleFromDatabase(uniqueID, vehicleType);
                Vehicle.decreaseCount();

            } else {
                System.out.println("\n*** Enter index between 0 - " + (vehicleList.size() - 1) + " for " + vehicleType + " ***\n");
            }
        }
    }

    // Gets the uniqueID of the employee at the specified index
    public String getUniqueIDFromIndex(int index) {
        if (index >= 0 && index < employees.size()) {
            return employees.get(index).uniqueID();
        }
        System.out.println("Invalid index.");
        return null;
    }

    // get true or false if are or not employees in list
    public boolean isEmployees(){
        return !employees.isEmpty();
    }
    // help method to get only right name of  fuel type
    public String getFuelType(String promt){
        while (true){
            System.out.print("Enter fuel type: "+promt +": ");
            String input = readLine().toLowerCase();
            switch (input){
                case "petrol": return "petrol";
                case "diesel": return "diesel";
                case "manual":return "manual";
                case "electric":return "electric";
                default: System.out.println("\n*** Enter only one of this option: "+promt + " ***");
            }
        }
    }
    public void printEmployeesWithIndex(){
        if (employees.isEmpty()){
            System.out.println("--- No data for Employees ---");
        }else {
            for (int i = 0; i < employees.size();i++){
                System.out.println("Index: " + i + ", Employee: " + employees.get(i));
            }
        }
    }
    // universal method for printing Vehicle
    public void printVehicleWithIndex(String vehicle){
        switch (vehicle){
            case "Car":
                if (cars.isEmpty()){
                    System.out.println("\n--- No data for " + vehicle + " ---\n");
                }else {
                    for (int i = 0; i < cars.size(); i++){
                        System.out.println("Index: "+ i + ", Car: " + cars.get(i));
                    }
                }
                break;

            case "Truck":
                if (trucks.isEmpty()){
                    System.out.println("\n--- No data for " + vehicle + " ---\n");
                }else {
                    for (int i = 0; i < trucks.size(); i++){
                        System.out.println("Index: "+ i + ", Truck: " + trucks.get(i));
                    }
                }

                break;
            case "Motorcycle":
                if (motorcycles.isEmpty()){
                    System.out.println("\n--- No data for " + vehicle + " ---\n");
                }else {
                    for (int i = 0; i < motorcycles.size(); i++){
                        System.out.println("Index: "+ i + ", Motorcycle: " + motorcycles.get(i));
                    }
                }
                break;
            case "Bicycle":
                if (bicycles.isEmpty()){
                    System.out.println("\n--- No data for " + vehicle + " ---\n");
                }else {
                    for (int i = 0; i < bicycles.size(); i++){
                        if (bicycles.get(i).getFuelType().equals("manual")){
                            System.out.println("Index: "+ i + ", Bicycle: " +"brand = "+ bicycles.get(i).getBrand()+", model = " + bicycles.get(i).getModel()+", km = " + bicycles.get(i).getKm()+", color = " + bicycles.get(i).getColor()+", weight = "+bicycles.get(i).getWeight()+", fuel type = "+bicycles.get(i).getFuelType()+", gears = "+bicycles.get(i).getGears());
                        }else {
                            System.out.println("Index: "+ i + ", Bicycle: " + bicycles.get(i));
                        }
                    }
                }
                break;
        }
    }

    // read manual from txt with different language mode
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

    // universal control if user input is integer number
    public int intEntry(){
        int number;
        while (true){
            try {
                number = entry.nextInt();
                entry.nextLine();
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
        double number;
        while (true){
            try {
                number = entry.nextDouble();
                entry.nextLine();
                break;
            }catch (InputMismatchException e){
                System.out.println("*** Enter only double number ***");
                entry.nextLine();
            }
        }
        return number;
    }
    // every Vehicle or Employee has unique ID from this method
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
