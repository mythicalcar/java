package scr;

import okhttp3.Address;

import java.util.ArrayList;

public class Orders {
    private ArrayList<Adress> adresses;
    private final Adress warehouse = new Adress("Almere", "Hospitaaldreef", "5");

    public Orders() {
        this.adresses = new ArrayList<>();
        adresses.add(warehouse);
        adresses.add(new Adress("Amsterdam", "Prinsengracht", "14"));
        adresses.add(new Adress("Amsterdam", "Pampuslaan", "4"));
        adresses.add(new Adress("Beesd", "Stationsweg", "3"));
        adresses.add(new Adress("Millderburg", "Oosterkerkplein", "1"));
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

        return route;
    }
}
