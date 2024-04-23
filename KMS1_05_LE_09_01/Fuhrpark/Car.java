public class Car extends Vehicle {
    private int seats;

    public Car(String make, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int seats) {
        super(make, model, km, color, weight, fuelType, fuelLevel);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,seats = " + seats ;
    }
}
