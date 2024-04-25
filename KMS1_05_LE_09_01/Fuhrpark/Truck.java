public class Truck extends Vehicle{
    private final double load_capacity;

    public Truck(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, double load_capacity) {
        super(brand, model, km, color, weight, fuelType, fuelLevel);
        this.load_capacity = load_capacity;
    }

    public double getLoad_capacity() {
        return load_capacity;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo+ ", load_capacity = " + load_capacity ;
    }
}

