public class Truck extends Vehicle{
    private final double load_capacity;
    private Double fuelLevel;
    private Double tankCapacity;

    public Truck(String uniqueID,String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, double load_capacity) {
        super(uniqueID,brand, model, km, color, weight, fuelType, fuelLevel,tankCapacity);
        this.load_capacity = load_capacity;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,current tank capacity = "+ String.format("%.1f", fuelLevel)+" l ,tank capacity = "+tankCapacity+" l ,load_capacity = " + load_capacity +" kg";
    }

    public double getLoad_capacity() {
        return load_capacity;
    }
}
