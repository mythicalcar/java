package scr;

import okhttp3.Address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Orders {
    private ArrayList<Adress> adresses;
    private final Adress warehouse = new Adress("Almere", "Hospitaaldreef", "5", "1315RC");

    public Orders() {
        this.adresses = new ArrayList<>();
    }

    public void addAdress(Adress adress) {
        adresses.add(adress);
    }

    public ArrayList<Adress> sortAddressesByPostcode() {
        ArrayList<Adress> addresses = this.adresses;
        // Use a custom comparator to sort the addresses based on the postcode
        Comparator<Adress> postcodeComparator = Comparator.comparing(Adress::getPostcode);

        // Sort the addresses using the comparator
        Collections.sort(addresses, postcodeComparator);

        // Return the sorted addresses
        return addresses;
    }


    public ArrayList<Adress> nearestNeighbor() {
        ArrayList<Adress> route = new ArrayList<>();
        Adress currentAddress = warehouse;
        route.add(currentAddress);

        ArrayList<Adress> unvisitedAddresses = new ArrayList<>(adresses);
        unvisitedAddresses.remove(currentAddress);

        while (!unvisitedAddresses.isEmpty()) {
            Adress nearestAddress = null;
            double smallestDistance = Double.MAX_VALUE;

            for (Adress address : unvisitedAddresses) {
                double distance = Distances.calculateDistance(currentAddress, address);
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    nearestAddress = address;
                }
            }

            route.add(nearestAddress);
            currentAddress = nearestAddress;
            unvisitedAddresses.remove(nearestAddress);
        }
        route.add(warehouse);
        return route;
    }



}
