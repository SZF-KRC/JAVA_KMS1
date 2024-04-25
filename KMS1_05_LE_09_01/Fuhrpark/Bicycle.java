public class Bicycle extends Vehicle {
    private int gears;

    public Bicycle(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int gears) {
        super(brand, model, km, color, weight, fuelType, fuelLevel);
        this.gears = gears;
    }

    public int getGears() {
        return gears;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + ", gears = " + gears;
    }
}
