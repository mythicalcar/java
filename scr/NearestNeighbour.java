package scr;

public class GoogleMapsApiExample {

    public static void main(String[] args) {
        String address = "1600 Amphitheatre Parkway, Mountain View, CA";
        LatLng coordinates = getCoordinatesFromAddress(address);

        if (coordinates != null) {
            double latitude = coordinates.lat;
            double longitude = coordinates.lng;
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);
        } else {
            System.out.println("Failed to fetch coordinates.");
        }
    }

    public static LatLng getCoordinatesFromAddress(String address) {
        // Set up the API context with your API key
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("YOUR_API_KEY")
                .build();

        try {
            // Make the API call to geocode the address
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            // Extract the latitude and longitude from the result
            if (results.length > 0) {
                LatLng location = results[0].geometry.location;
                return location;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
