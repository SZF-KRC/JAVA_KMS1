import java.util.Scanner;

public class KMS1_05_LE_02_03_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        double sideA, sideB, high;

        System.out.print("Enter the side A in cm, which is common for the rectangle, triangle, and also serves as the radius of the circle: ");
        sideA = entry.nextInt();
        System.out.print("Enter the side B in cm, for the rectangle: ");
        sideB = entry.nextInt();
        System.out.print("Enter the height in cm, which is common for the cuboid, prism, and cylinder: ");
        high = entry.nextInt();

        double rectangle = sideA * sideB;
        double cuboid = 2 * (rectangle + (sideA * high) + (sideB * high));
        double triangle = (Math.sqrt(3)/4) * Math.pow(sideA, 2);
        double prism = triangle * high;
        double circle = Math.PI * Math.pow(high,2);
        double cylinder = circle * high;

        System.out.println("Area of the rectangle: " + rectangle + " cm2\nArea of the cuboid: " + cuboid + " cm2\nArea of the triangle: " + triangle + " cm2\nArea of the Prism: " + prism + " cm2\nArea of the circle: " + circle + " cm2\nArea of the cylinder: " + cylinder + " cm2");
    }
}
