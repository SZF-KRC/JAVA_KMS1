public class Main {
    public static void main(String[] args) {

        Car auto = new Car("skoda", "octavia", 500, "red", 3500, "diesel", 90.0, 5);
        Truck truck = new Truck("Volvo", "S500", 15000, "white", 12000, "diesel", 250.0, 22000);
        Motorcycle motorcycle = new Motorcycle("Yamaha", "YBR", 1000, "Black", 80, "petrol", 12.0, 5);
        Bicycle bicycle = new Bicycle("BMX", "Hill", 0, "Black", 15, "Manual", null, 5);

        // car refuel + print
        auto.addFuel(50.0);
        System.out.println(auto);

        // truck refuel + print
        truck.addFuel(500);
        System.out.println(truck);

        // motorcycle refuel + print
        motorcycle.addFuel(5);
        System.out.println(motorcycle);

        // bicycle print + add km
        bicycle.addKm(50);
        bicycle.addFuel(50); // this refuel will be not happen , because we have initial null for this Bike
        System.out.println(bicycle);
    }
}
