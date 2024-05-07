public class Car extends Vehicle {
    private final int seats;
    private Double fuelLevel;
    private Double tankCapacity;

    public Car(String uniqueID, String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel,Double tankCapacity, int seats) {
        super( uniqueID,brand, model, km, color, weight, fuelType, fuelLevel, tankCapacity);
        this.seats = seats;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,current tank capacity = "+ String.format("%.1f", fuelLevel)+" l ,tank capacity = "+tankCapacity+" ,seats = " + seats ;
    }

    public int getSeats() {
        return seats;
    }
}
