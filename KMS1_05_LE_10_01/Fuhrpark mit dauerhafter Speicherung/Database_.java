import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_ {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11703481"; // URL of your database
        String user = "sql11703481"; // database username
        String password = "ga1rcr72j3"; // database password
        return DriverManager.getConnection(url, user, password);
    }
    public void addCarToDatabase(Car car) {
        String query = "INSERT INTO Cars (uniID, brand, model, km, color, weight, fuelType, fuelLevel, tankCapacity, seats) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,car.getUniqueID());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getKm());
            preparedStatement.setString(5, car.getColor());
            preparedStatement.setDouble(6, car.getWeight());
            preparedStatement.setString(7, car.getFuelType());
            preparedStatement.setDouble(8, car.getFuelLevel()); // Set double value if not null
            preparedStatement.setDouble(9, car.getTankCapacity());
            preparedStatement.setInt(10, car.getSeats());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Car was successfully added. °\n");
            } else {
                System.out.println("*** Error,car was not added !!! ***");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void addTruckToDatabase(Truck truck){
        String query = "INSERT INTO Trucks (uniID, brand, model, km, color, weight, fuelType, fuelLevel, tankCapacity, load_capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,truck.getUniqueID());
            preparedStatement.setString(2,truck.getBrand());
            preparedStatement.setString(3, truck.getModel());
            preparedStatement.setInt(4,truck.getKm());
            preparedStatement.setString(5,truck.getColor());
            preparedStatement.setDouble(6,truck.getWeight());
            preparedStatement.setString(7,truck.getFuelType());
            preparedStatement.setDouble(8, truck.getFuelLevel()); // Set double value if not null
            preparedStatement.setDouble(9,truck.getTankCapacity());
            preparedStatement.setDouble(10,truck.getLoad_capacity());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Truck was successfully added. °\n");
            } else {
                System.out.println("*** Error,truck was not added !!! ***");
            }
        }catch (SQLException e){
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void addMotorcycleToDatabase(Motorcycle motorcycle){
        String query = "INSERT INTO Motorcycles (uniID, brand, model, km, color, weight, fuelType, fuelLevel, tankCapacity, power) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,motorcycle.getUniqueID());
            preparedStatement.setString(2,motorcycle.getBrand());
            preparedStatement.setString(3, motorcycle.getModel());
            preparedStatement.setInt(4,motorcycle.getKm());
            preparedStatement.setString(5,motorcycle.getColor());
            preparedStatement.setDouble(6,motorcycle.getWeight());
            preparedStatement.setString(7,motorcycle.getFuelType());
            preparedStatement.setDouble(8, motorcycle.getFuelLevel()); // Set double value if not null
            preparedStatement.setDouble(9,motorcycle.getTankCapacity());
            preparedStatement.setInt(10,motorcycle.getPower());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Motorcycle was successfully added. °\n");
            } else {
                System.out.println("*** Error,motorcycle was not added !!! ***");
            }
        }catch (SQLException e){
            System.out.println("*** Error, problem with database !!! ***");
        }
    }


    public void addBicycleToDatabase(Bicycle bicycle){
        String query = "INSERT INTO Bicycles (uniID, brand, model, km, color, weight, fuelType, fuelLevel, tankCapacity, gears) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,bicycle.getUniqueID());
            preparedStatement.setString(2,bicycle.getBrand());
            preparedStatement.setString(3, bicycle.getModel());
            preparedStatement.setInt(4,bicycle.getKm());
            preparedStatement.setString(5,bicycle.getColor());
            preparedStatement.setDouble(6,bicycle.getWeight());
            preparedStatement.setString(7,bicycle.getFuelType());
            // Set fuelLevel, handling null values properly
            if (bicycle.getFuelLevel() == null) {
                preparedStatement.setNull(8, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(8, bicycle.getFuelLevel());
            }

            // Set tankCapacity, handling null values properly
            if (bicycle.getTankCapacity() == null) {
                preparedStatement.setNull(9, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(9, bicycle.getTankCapacity());
            }
            preparedStatement.setInt(10,bicycle.getGears());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n° Bicycle was successfully added. °\n");
            } else {
                System.out.println("*** Error,bicycle was not added !!! ***");
            }
        }catch (SQLException e){
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void addEmployeeToDatabase(Employee employee){
        String query = "INSERT INTO Employees (uniID, name, surname) VALUES (?, ?, ?)";
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.uniqueID());
            preparedStatement.setString(2,employee.name());
            preparedStatement.setString(3, employee.surname());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public List<Car> loadCarsFromDatabase() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Cars";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getString("uniID"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("km"),
                        resultSet.getString("color"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("fuelType"),
                        resultSet.getDouble("fuelLevel"),
                        resultSet.getDouble("tankCapacity"),
                        resultSet.getInt("seats")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with Car database !!! ***");
        }
        return cars;
    }

    public List<Truck> loadTrucksFromDatabase() {
        List<Truck> trucks = new ArrayList<>();
        String query = "SELECT * FROM Trucks";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Truck truck = new Truck(
                        resultSet.getString("uniID"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("km"),
                        resultSet.getString("color"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("fuelType"),
                        resultSet.getDouble("fuelLevel"),
                        resultSet.getDouble("tankCapacity"),
                        resultSet.getDouble("load_capacity")
                );
                trucks.add(truck);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with Truck database !!! ***");
        }
        return trucks;
    }
    public List<Motorcycle> loadMotorcyclesFromDatabase() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        String query = "SELECT * FROM Motorcycles";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Motorcycle motorcycle = new Motorcycle(
                        resultSet.getString("uniID"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("km"),
                        resultSet.getString("color"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("fuelType"),
                        resultSet.getDouble("fuelLevel"),
                        resultSet.getDouble("tankCapacity"),
                        resultSet.getInt("power")
                );
                motorcycles.add(motorcycle);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with Motorcycle database !!! ***");
        }
        return motorcycles;
    }
    public List<Bicycle> loadBicyclesFromDatabase() {
        List<Bicycle> bicycles = new ArrayList<>();
        String query = "SELECT * FROM Bicycles";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Bicycle bicycle = new Bicycle(
                        resultSet.getString("uniID"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("km"),
                        resultSet.getString("color"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("fuelType"),
                        resultSet.getDouble("fuelLevel"),
                        resultSet.getDouble("tankCapacity"),
                        resultSet.getInt("gears")
                );
                bicycles.add(bicycle);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with Bicycles database !!! ***");
        }
        return bicycles;
    }

    public List<Employee> loadEmployeesFromDatabase() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString("uniID"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
        return employees;
    }

    public void deleteVehicleFromDatabase(String uniID, String vehicle) {
        String query = "DELETE FROM "+vehicle+"s WHERE uniID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, uniID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(vehicle+" with uniID: " + uniID + " was successfully deleted.");
            } else {
                System.out.println("No "+vehicle+" found with uniID: " + uniID + ".");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void deleteEmployeeFromDatabase(String uniID){
        String query = "DELETE FROM Employees WHERE uniID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, uniID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Employee with uniID: " + uniID + " was successfully deleted.");
            } else {
                System.out.println("No Employee found with uniID: " + uniID + ".");
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database Employee !!! ***");
        }
    }

    public void updateKmVehicleInDatabase(String uniID, String vehicle, int newKm, Double updatedFuelLevel){
        String query = "UPDATE "+vehicle+"s SET km = km + ?, fuelLevel = ? WHERE uniID = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, newKm);
            if (updatedFuelLevel == null) {
                preparedStatement.setNull(2, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(2, updatedFuelLevel);
            }
            preparedStatement.setString(3, uniID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Kilometres updated successfully.");
            }else {
                System.out.println("No record found with ID: "+uniID);
            }
        }catch (SQLException e){
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void updateFuelLevelVehicleInDatabase(String uniID,String vehicle, Double newFuelLevel) {
        String query = "UPDATE "+vehicle+"s SET fuelLevel = ? WHERE uniID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (newFuelLevel == null) {
                preparedStatement.setNull(1, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(1, newFuelLevel);
            }
            preparedStatement.setString(2, uniID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Fuel level updated successfully.");
            } else {
                System.out.println("No record found with ID: " + uniID);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
    }

    public void updateEmployeeInDatabase(String uniID,String name, String surname) {
        String query = "UPDATE Employees SET name = ?, surname = ? WHERE uniID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, uniID);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Employees updated successfully.");
            } else {
                System.out.println("No record found with ID: " + uniID);
            }
        } catch (SQLException e) {
            System.out.println("*** Error, problem with database !!! ***");
        }
    }
}
