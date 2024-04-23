public class Vehicle {
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

    public static  int getCount(){
        return count;
    }

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

    public void addFuel(double amount){
        if (fuelLevel != null){
            fuelLevel +=amount;
        }
    }

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
