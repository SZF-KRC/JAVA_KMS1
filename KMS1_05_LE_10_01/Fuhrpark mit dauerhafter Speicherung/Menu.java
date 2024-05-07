public class Menu {
    private final Function function;

    public Menu(Function function){
        this.function = function;
    }

    public void mainMenu(){
        boolean exit =false;
        while (!exit){
            System.out.print("\nTotal Vehicles in database: "+function.getCountVehicle()+"\n[1] Car manager\t\t\t\t(cars in database: "+function.getCountCar()+")\n[2] Truck manager\t\t\t(trucks in database: "+function.getCountTruck()+")\n[3] Motorcycle manager\t\t(motorcycles in database: "+function.getCountMotorcycle()+")\n[4] Bicycle manager\t\t\t(bicycles in database: "+function.getCountBicycle()+")\n[5] Employee manager\t\t(employees in database: "+function.getCountEmployee()+")\n[6] help(manual)\n[0] Exit program\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0: exit = true;function.closeScanner();break;// Exit program
                case 1: System.out.println("\n*- Car manager -*");vehicleMenu("Car");break; // calling universal Vehicle Menu and send string "Car" which we use for another function
                case 2: System.out.println("\n*- Truck manager -*");vehicleMenu("Truck");break;
                case 3: System.out.println("\n*- Motorcycle manager -*");vehicleMenu("Motorcycle");break;
                case 4: System.out.println("\n*- Bicycle manager -*");vehicleMenu("Bicycle");break;
                case 5: System.out.println("\n*- Employee manager -*");employeeMenu();break; // calling employee Menu
                case 6: System.out.println("\n*- help(manual) -*");manualMenu();break; // calling manual menu
                default: System.out.println("\n*** Enter only index from 0 - 6 ! ***\n");break;
            }
        }
    }
    // method employee menu gives the possibility to manage employees
    private void employeeMenu() {
        boolean exit = false;
        while (!exit){
            System.out.print("\nEmployee manager\n[1] add Employee\n[2] delete Employee\n[3] edit Employee\n[4] show Employees\n[0] back to main menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0:exit=true;break;
                case 1: System.out.println("\n*- add Employee -*");addEmployee();break;
                case 2: System.out.println("\n*- delete Employee -*");deleteEmployee();break;
                case 3: System.out.println("\n*- edit Employee -*");editEmployee();break;
                case 4: System.out.println("\n*- show Employees -*");function.printEmployeesWithIndex();break;
                default: System.out.println("\n*** Enter only index from 0 - 4 ! ***\n");break;
            }
        }
    }

    // vehicle Menu is universal menu for all vehicles to manage vehicles
    private void vehicleMenu(String vehicle){
        boolean exit = false;
        while (!exit){
            System.out.print("\n[1] add " + vehicle +"\n[2] delete " + vehicle +"\n[3] add Fuel\n[4] add Km\n[5] show " + vehicle +"s\n[0] back to main Menu\nEnter index of your choice: ");
            switch (function.intEntry()) {
                case 1: System.out.println("\n*- add " + vehicle+" -*");addVehicle(vehicle);break;
                case 2: System.out.println("\n*- delete " + vehicle+" -*");removeVehicle(vehicle);break;
                case 3: System.out.println("\n*- add Fuel -*");addFuelToVehicleOption(vehicle);break;
                case 4: System.out.println("\n*- add Km -*");addKmToVehicleOption(vehicle);break;
                case 5: System.out.println("\n*- show " + vehicle + "s -*");function.printVehicleWithIndex(vehicle);break;
                case 0: exit = true;break;
                default: System.out.println("*** Enter only index from 0 - 5 ! ***");
            }
        }
    }
    // manual menu gives option for manual in two languages
    private void manualMenu(){
        boolean exit = false;
        while (!exit){
            System.out.print("\n[1] Deutsch manual\n[2] English manual\n[0] back to main menu\nEnter index of your choice: ");
            switch (function.intEntry()){
                case 0: exit = true;break;
                case 1: function.readFile("deutsch");break;
                case 2: function.readFile("english");break;
                default: System.out.println("*** Wrong entry! Enter only index 1 or 2 ***");break;
            }
        }
    }

    // universal function to add data to vehicles, but we must first check if all data are correct before send to classes
    private void addVehicle(String vehicle){
        String brand,model,color,fuelType, input;
        int km, seats, load_capacity, power, gears;
        double weight;
        Double tankCapacity =null; // if we not add fuelLevel than we give empty variable
        // these inputs are same for all vehicles
        System.out.print("Enter brand: ");
        input= function.readLine().toLowerCase();
        brand = input.substring(0,1).toUpperCase() + input.substring(1);// Capitalize first letter
        System.out.print("Enter model: ");
        model = function.readLine();
        System.out.print("Enter Km: ");
        km = function.intEntry(); // checking if user input is really integer
        System.out.print("Enter color: ");
        color = function.readLine().toLowerCase();
        System.out.print("Enter weight(kg): ");
        weight = function.doubleEntry(); // checking if user input is really double


        // every vehicle have also another input data
        switch (vehicle){
            case "Car":
                fuelType = function.getFuelType("petrol, diesel, electric");
                if (fuelType.equals("electric")){
                    System.out.print("Enter battery capacity(kw): ");
                }else {
                    System.out.print("Enter tank capacity(l): ");
                }
                tankCapacity = function.doubleEntry();
                System.out.print("Enter seats: ");
                seats = function.intEntry();
                function.addCar(function.generateUniqueID(),brand,model,km, color,weight, fuelType,tankCapacity,tankCapacity,seats); // all data sending to method addCar
                break;
            case "Truck":
                fuelType = function.getFuelType("petrol or diesel");
                System.out.print("Enter tank capacity(l): ");
                tankCapacity = function.doubleEntry();
                System.out.print("Enter load capacity(kg): ");
                load_capacity = function.intEntry();
                function.addTruck(function.generateUniqueID(),brand,model,km,color,weight,fuelType, tankCapacity,tankCapacity,load_capacity);
                break;
            case "Motorcycle":
                fuelType = function.getFuelType("petrol or electric");
                System.out.print("Enter tank capacity(l): ");
                tankCapacity = function.doubleEntry();
                System.out.print("Enter power(kw): ");
                power = function.intEntry();
                function.addMotorcycle(function.generateUniqueID(),brand,model,km,color,weight,fuelType, tankCapacity,tankCapacity,power);
                break;
            case "Bicycle":
                fuelType = function.getFuelType("electric or manual");
                if (fuelType.equals("electric")) {
                    System.out.print("Enter kw capacity : ");
                    tankCapacity = function.doubleEntry();
                }
                System.out.print("Enter gears(pieces): ");
                gears = function.intEntry();
                function.addBicycle(function.generateUniqueID(),brand,model,km, color,weight,fuelType, tankCapacity, tankCapacity, gears);
                break;
        }
    }

    // this is help function to separate vehicle and send concrete List to removeVehicleFromList
    private void removeVehicle( String vehicle){
        switch (vehicle){
            case "Car": function.removeVehicleFromList(function.getCars(), vehicle);break;
            case "Truck": function.removeVehicleFromList(function.getTrucks(), vehicle);break;
            case "Motorcycle": function.removeVehicleFromList(function.getMotorcycles(), vehicle);break;
            case "Bicycle": function.removeVehicleFromList(function.getBicycles(), vehicle);break;
        }
    }

    // this is help function to separate option for refuel and sending List, concrete vehicle and fuel consumption
    private void addFuelToVehicleOption(String vehicle){
        switch (vehicle){
            case "Car": function.enterKmRefuelVehicleByIndex(function.getCars(),vehicle,"refuel", 7.0);break;
            case "Truck": function.enterKmRefuelVehicleByIndex(function.getTrucks(), vehicle,"refuel", 22.5);break;
            case "Motorcycle": function.enterKmRefuelVehicleByIndex(function.getMotorcycles(), vehicle,"refuel", 3.2);break;
            case "Bicycle": function.enterKmRefuelVehicleByIndex(function.getBicycles(), vehicle,"refuel",25.9);break;
        }
    }

    // this is help function to separate option for add new km and sending List, conrete vehicle and option
    private void addKmToVehicleOption(String vehicle){
        switch (vehicle){
            case "Car": function.enterKmRefuelVehicleByIndex(function.getCars(),vehicle,"addKm",7.0);break;
            case "Truck": function.enterKmRefuelVehicleByIndex(function.getTrucks(), vehicle, "addKm",22.5);break;
            case "Motorcycle": function.enterKmRefuelVehicleByIndex(function.getMotorcycles(), vehicle, "addKm",3.2);break;
            case "Bicycle": function.enterKmRefuelVehicleByIndex(function.getBicycles(), vehicle, "addKm",25.9);break;
        }
    }
    // this is help function for adding employee, user give data and send them to method addEmployee
     private void addEmployee(){
        String name, surname, uniqueID;
        System.out.print("Enter name: ");
        name = function.readLine();
        System.out.print("Enter surname: ");
        surname = function.readLine();
        uniqueID = function.generateUniqueID();
        function.addEmployee(uniqueID,name,surname);
     }
     // this is help function for delete concrete employee, take index from user, find ID from index and send unique ID to method removeEmployee
     private void deleteEmployee(){
        if (function.isEmployees()){
            function.printEmployeesWithIndex();
            System.out.print("Enter index of your Employee for delete: ");
            int index = function.intEntry();
            function.removeEmployee(function.getUniqueIDFromIndex(index));
        }else {
            System.out.println("--- No data for Employees ---");
        }

     }
    // this is help function for editing concrete employee, take index from user,take new name and surname, find ID from index and send unique ID and new name and surname to method changeEmployee
     private void editEmployee(){
        if (function.isEmployees()){
            String name, surname;
            function.printEmployeesWithIndex();
            System.out.print("Enter index of your Employee for edit: ");
            int index = function.intEntry();
            System.out.print("Enter new name: ");
            name = function.readLine();
            System.out.print("Enter new surname: ");
            surname = function.readLine();
            function.changeEmployeeName(function.getUniqueIDFromIndex(index),name,surname );
        }else {
            System.out.println("--- No data for Employees ---");
        }

     }
}
