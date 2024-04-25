public class Motorcycle extends Vehicle {
    private int power;

    public Motorcycle(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int power) {
        super(brand, model, km, color, weight, fuelType, fuelLevel);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + ", power = " + power + this.getModel() ;
    }
}

