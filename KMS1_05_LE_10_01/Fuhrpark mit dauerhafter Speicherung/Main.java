import java.util.List;

public class Main {
    public static void main(String[] args) {
        Function function = new Function();
        Database_ data = new Database_();

        List<Car> cars = data.loadCarsFromDatabase();  // load Cars from MySQL
        List<Truck>trucks= data.loadTrucksFromDatabase();// load Trucks from MySQL
        List<Motorcycle>motorcycles=data.loadMotorcyclesFromDatabase();// load Motorcycles from MySQL
        List<Bicycle> bicycles = data.loadBicyclesFromDatabase();// load Bicycles from MySQL
        List<Employee> employees = data.loadEmployeesFromDatabase();// load Employees from MySQL
        function.setCars(cars);// Setting up cars in the Function list cars
        function.setTrucks(trucks);// Setting up trucks in the Function list trucks
        function.setMotorcycles(motorcycles);// Setting up motorcycles in the Function list motorcycles
        function.setBicycles(bicycles);// Setting up bicycles in the Function list bicycles
        function.setEmployees(employees);

        Menu menu = new Menu(function);
        menu.mainMenu();
    }
}
