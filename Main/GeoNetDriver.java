package GeoNet.Main;


import java.util.Scanner;

/**
 * Main entry point for GeoNet
 */
public class GeoNetDriver
{
    public static void main(String[] args)
    {
        GeoNet geoNet = new GeoNet(); //load existing user profiles

        Scanner scan = new Scanner(System.in);
        String name;
        boolean nameIsTaken;

        do {
            System.out.println("To create a profile, please enter your first and last name, " +
                    "seperated by a space. Otherwise, type EXIT");
            nameIsTaken = false;
            name = scan.nextLine();
            if (name.toLowerCase().contains("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            if (geoNet.nameTaken(name)) {
                System.out.println("There already exists a user with this name! Please enter a different name.");
                nameIsTaken = true;
            }
        } while (!name.matches("[a-zA-Z]+[ ]+[a-zA-Z]+") || nameIsTaken);

        String phoneNumber;
        do {
            System.out.println("Please enter your 10 digit phone number, beginning with the" +
                    " area code and containing no spaces or hyphens. (ex 1234567890)");
            phoneNumber = scan.nextLine();
            if (phoneNumber.contains("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        } while (!phoneNumber.matches("[0-9]{10}"));

        boolean validResidence = false;
        String residence;

        do {
            System.out.println("Please enter your city of residence in Ventura County.");
            residence = scan.nextLine();
            for (int i = 0; i < VenturaCountyMap.CITIES_OF_VENTURA_COUNTY.length; i++) {
                if (residence.toLowerCase().equalsIgnoreCase(VenturaCountyMap.CITIES_OF_VENTURA_COUNTY[i]))
                    validResidence = true;
            }
            if (!validResidence) {
                System.out.println("Invalid city of residence. Valid cities of residence are:");
                for (int i = 0; i < VenturaCountyMap.CITIES_OF_VENTURA_COUNTY.length; i++) {
                    if (i < VenturaCountyMap.CITIES_OF_VENTURA_COUNTY.length - 1)
                        System.out.print(VenturaCountyMap.CITIES_OF_VENTURA_COUNTY[i] + ", ");
                    else
                        System.out.println(VenturaCountyMap.CITIES_OF_VENTURA_COUNTY[i]);
                }
            }
        } while (!validResidence);

        Profile newUser = new Profile(name, phoneNumber, residence); //create new user profile
        geoNet.addUser(newUser); //add new user profile to the social network
    }
}
