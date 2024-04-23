public class Motorcycle extends Vehicle {
    private int power;

    public Motorcycle(String make, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int power) {
        super(make, model, km, color, weight, fuelType, fuelLevel);
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

