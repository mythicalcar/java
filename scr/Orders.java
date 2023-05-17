package scr;

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
    }

    public ArrayList nearestNeigbhour(){
        ArrayList route = new ArrayList();
        for(int i = 0; i < adresses.size() - 1; i++){
            Adress address1 = adresses.get(i);
            Adress closestAddress;
            for(int i2 = 0; i2 < adresses.size() - 1; i2++){
                Adress address2 = adresses.get(i2);


            }

        }
        return null;
    }
}
