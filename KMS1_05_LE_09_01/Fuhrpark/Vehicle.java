
public class Vehicle {

    // variables for this class
    private String brand;
    private String model;
    private int km;
    private String color;
    private double weight;
    private String fuelType;
    private Double fuelLevel;

    static int count = 0;

    public Vehicle(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel) {
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.color = color;
        this.weight = weight;
        this.fuelType = fuelType;
        this.fuelLevel = fuelLevel;

        count++;
    }
    // get info about how many vehicles we have together in system
    public static  int getCount(){
        return count;
    }
    // get brand
    public String getMake() {
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
    // adding fuel if is not a bicycle with manual power
    public void addFuel(double amount){
        if (fuelLevel != null){
            fuelLevel +=amount;
        }else {
            System.out.println("\n*** Your Bicycle has manual power, no possible to add fuel. You're not you when you're hungry, so grab a Snickers. :-) ***\n");
        }
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
                ", fuel Type = " + fuelType +
                ", fuel Level = " + fuelLevel;
    }
}
