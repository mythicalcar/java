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
    private final String apiKey = "AIzaSyDLqeDXpQB6vLjO5sh7oJrp0twa8qyJXIE";



    public double calculateDistance(String sourcePlace, String destinationPlace) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        try {
            DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context)
                    .origins(sourcePlace)
                    .destinations(destinationPlace);

            DistanceMatrix matrix = request.await();

            DistanceMatrixRow row = matrix.rows[0];
            DistanceMatrixElement element = row.elements[0];

            if (element.status == DistanceMatrixElementStatus.OK) {
                int distanceInMeters = (int) element.distance.inMeters;
                double distanceInKilometers = distanceInMeters / 1000.0;

                return distanceInKilometers;
            } else {
                System.out.println("Unable to calculate distance. Status: " + element.status);
                return -1; // Return a negative value to indicate an error
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate an error
        }
    }

    public void printDistance(String sourcePlace, String destinationPlace){
        double distance = calculateDistance(sourcePlace, destinationPlace);
        if (distance >= 0) {
            System.out.println("Distance: " + distance + " km");
        } else {
            System.out.println("An error occurred while calculating the distance.");
        }
    }
}
