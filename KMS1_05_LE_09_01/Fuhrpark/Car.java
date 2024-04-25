
public class Car extends Vehicle {
    // special variable for this class
    private int seats;


    public Car(String brand, String model, int km, String color, double weight, String fuelType, Double fuelLevel, int seats) {
        super(brand, model, km, color, weight, fuelType, fuelLevel);
        this.seats = seats;
    }
    // get number of seats
    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        String vehicleInfo = super.toString();
        return vehicleInfo + " ,seats = " + seats ;
    }
}
