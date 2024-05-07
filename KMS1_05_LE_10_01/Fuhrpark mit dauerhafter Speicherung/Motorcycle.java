public class Motorcycle extends Vehicle {
    private final int power;
    private Double fuelLevel;
    private Double tankCapacity;

    public Motorcycle(String uniqueID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int power) {
        super(uniqueID, brand, model, km, color, weight, fuelType, fuelLevel,tankCapacity);
        this.power = power;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,current tank capacity = "+ String.format("%.1f", fuelLevel)+" l ,tank capacity = "+tankCapacity+" l ,power = " + power+" kw";
    }

    public int getPower() {
        return power;
    }
}
