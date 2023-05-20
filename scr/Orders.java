package scr;

import java.util.ArrayList;
import java.util.Objects;

public class Orders {
    private ArrayList<Adress> adresses;
    private final Adress warehouse = new Adress("Almere", "Hospitaaldreef", "5");

    public Orders() {
        this.adresses = new ArrayList<>();
        adresses.add(warehouse);
        adresses.add(new Adress("Amsterdam", "Prinsengracht", "14"));
        adresses.add(new Adress("Amsterdam", "Pampuslaan", "4"));
        adresses.add(new Adress("Beesd", "Stationsweg", "3"));
        adresses.add(new Adress("Zaandam", "Prinsenstraat", "35"));
    }

    private Distances distancesobject = new Distances();

    public ArrayList nearestNeigbhour(){
        ArrayList route = new ArrayList();
        //there may be a better way of going about this vv
        //add the warehouse to the route as the first point
        route.add(warehouse);
        while(adresses.isEmpty() == false){
            //get the first address in the array
            Adress address1 = adresses.get(0);
            //remove the address from the array so distance cannot be compared between the same addresses
            adresses.remove(address1);
            try {
                //start looking for the closest address, starting at the beginning of the addresses array. throw an exception if none are left
                Adress closestAddress = adresses.get(0);
                for(int i2 = 0; i2 == adresses.size() - 1; i2++){
                    Adress address2 = adresses.get(i2);
                    if(distancesobject.calculateDistance(address1, address2) < distancesobject.calculateDistance(address1, closestAddress)){
                        closestAddress = address2;
                    }
                }
                System.out.println(closestAddress);
                route.add(closestAddress);
            } catch (IndexOutOfBoundsException exception) {
            }
        }
        //add the warehouse to the route as the last point
        route.add(warehouse);
        System.out.println(route);
        return route;
    }
}