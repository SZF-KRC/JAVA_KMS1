public class Bicycle extends Vehicle {
    private int gears;
    private Double fuelLevel;
    private Double tankCapacity;

    public Bicycle(String uniqueID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int gears) {
        super(uniqueID,brand, model, km, color, weight, fuelType, fuelLevel,tankCapacity);
        this.gears = gears;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }
    public int getGears() {
        return gears;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,battery capacity = "+ String.format("%.1f", fuelLevel)+" kw ,current battery capacity = "+tankCapacity+" kw ,gears = " + gears;
    }
}
