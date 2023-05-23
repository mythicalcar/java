package scr;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;

public class Distances {
    private static final String apiKey = "AIzaSyDLqeDXpQB6vLjO5sh7oJrp0twa8qyJXIE";
    
    public static double calculateDistance(Adress adress1, Adress adress2) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        try {
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                    .origins(String.valueOf(adress1))
                    .destinations(String.valueOf(adress2));

            DistanceMatrix matrix = request.await();

            DistanceMatrixRow row = matrix.rows[0];
            DistanceMatrixElement element = row.elements[0];

            if (element.status == DistanceMatrixElementStatus.OK) {
                int distanceInMeters = (int) element.distance.inMeters;
                double distanceInKilometers = distanceInMeters / 1000.0;

                return distanceInKilometers;
            } else {
                System.out.println("Err Status: " + element.status);
                return -1; // Return a negative value to indicate an error
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate an error
        }
    }

    public void printDistance(Adress sourcePlace, Adress destinationPlace){
        double distance = calculateDistance(sourcePlace, destinationPlace);
        if (distance >= 0) {
            System.out.println("Afstand: " + distance + " km");
        } else {
            System.out.println("Er is een fout opgetreden bij het berekenen van je afstand.");
        }
    }
}
