import java.util.Scanner;

public class KMS1_05_LE_05_02_KRC {
    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        String ipAddress;
        int octet, power;
        long number = 0;
        String[] arrOctet;
        String[] ipToOctal = new String[4];
        String[] ipToBinary = new String[4];
        String[] ipToHex = new String[4] ;
        boolean error = false;

        do{
            try {
                System.out.println("Enter IP Address example(192.168.1.1): ");
                ipAddress = entry.nextLine();
                arrOctet = ipAddress.split("\\.");
                if (arrOctet.length < 4){
                    throw new Exception("IP must have a correct format . example: 172.16.0.0");
                }

                for (int i = 0; i < arrOctet.length; i++){
                    octet = Integer.parseInt(arrOctet[i]);
                    if (octet < 0 || octet > 255){
                        throw new Exception("!!!");
                    }
                    ipToBinary[i]= String.format("%08d", Integer.parseInt(Integer.toBinaryString(octet)));
                }


                for (int i = 0; i < arrOctet.length; i++){
                    power = 3 - i;
                    number += ((Integer.parseInt(arrOctet[i]) % 256 * Math.pow(256, power)));// https://stackoverflow.com/questions/11548273/convert-an-ip-address-to-its-decimal-equivalent-in-java

                }

                for (int i = 0; i < arrOctet.length; i++){
                    octet = Integer.parseInt(arrOctet[i]);
                    ipToHex[i] = String.format("%02x", octet);
                }


                for (int i = 0; i < arrOctet.length; i++){
                    octet = Integer.parseInt(arrOctet[i]);
                    ipToOctal[i] = String.format("%04o", octet);
                }
                System.out.println("Convert your ip: " + ipAddress + " to:");
                System.out.println("Binary Format: " + String.join(" ", ipToBinary));
                System.out.println("Octal Format: " + String.join(".", ipToOctal));
                System.out.println("Hex Format: #" + String.join("",ipToHex));
                System.out.println("Decimal Format: " + number);
                error = false;
            }catch (Exception e){
                System.out.println("\n***\tWrong IP Address! example : 80.80.1.1\t***\n");
                error = true;
            }
        }while(error);
    }
}