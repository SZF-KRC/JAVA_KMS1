import java.util.Objects;

public class Vehicle {

    // variables for this class
    private String uniqueID;
    private String brand;
    private String model;
    private int km;
    private String color;
    private double weight;
    private String fuelType;
    private Double fuelLevel;
    private Double tankCapacity;
    static int count = 0;

    public Vehicle(String uniqueID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, Double tankCapacity) {
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.color = color;
        this.weight = weight;
        this.fuelType = fuelType;
        this.fuelLevel = fuelLevel;
        this.uniqueID = uniqueID;
        this.tankCapacity = tankCapacity;
        count++;
    }
    // get info about how many vehicles we have together in system
    public static int getCount(){
        return count;
    }

    // Metóda pre znižovanie počtu pri vymazávaní vozidla, ak je to potrebné
    public static void decreaseCount() {
        count--;
    }

    // get brand
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getKm() {
        return km;
    }
    public String getColor() {
        return color;
    }
    public double getWeight() {
        return weight;
    }
    public String getFuelType() {
        return fuelType;
    }
    public Double getFuelLevel() {
        return fuelLevel;
    }
    public Double getTankCapacity() {
        return tankCapacity;
    }
    public String getUniqueID() {
        return uniqueID;
    }

    public boolean driveKm(int km_, String vehicle, double fuel_consumption){
        if (vehicle.equals("Bicycle")){
            addKm(km_);
            return true;
        }else {
            double required_fuel = km_* (fuel_consumption /100);
            if (required_fuel <= getFuelLevel()){
                addKm(km_);
                fuelLevel -= required_fuel;
                return true;
            }else {
                double max_km = fuelLevel/(fuel_consumption/100);
                System.out.printf("Not enough tank capacity for %d km journey. You can drive max: %.2f km\n",km_,max_km);
            }
        }
        return false;
    }

    public Double refuelToFull(){
        if (tankCapacity != null){
            if (!Objects.equals(fuelLevel, tankCapacity)){
                System.out.println("Tank is refueled");
                return fuelLevel = tankCapacity;
            }else {
                System.out.println("*** Your current tank capacity is still full ***");
            }
        }else {
            System.out.println("\n*** Your Bicycle has manual power, no possible to add fuel. You're not you when you're hungry, so grab a Snickers. :-) ***\n");
            return null;
        }
        return fuelLevel;
    }

    // adding new km to vehicle
    public void addKm(int amount){
        km += amount;
    }

    @Override
    public String toString() {
        return "brand = " + brand +
                ", model = " + model +
                ", km = " + km +
                ", color = " + color +
                ", weight = " + weight +
                " kg, fuel Type = " + fuelType;
    }


}
