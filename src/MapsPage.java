package src;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapsPage {
    public static void openGoogleMapsRoute(ArrayList<Adress> destinations) {
        if (destinations.size() < 2) {
            System.out.println("Je hebt minstens 2 adressen nodig!!!!");
            return;
        }

        try {
            StringBuilder urlBuilder = new StringBuilder("https://www.google.com/maps/dir/");
            for (int i = 0; i < destinations.size() - 1; i++) {
                String encodedDestination = URLEncoder.encode(String.valueOf(destinations.get(i)), "UTF-8");
                urlBuilder.append(encodedDestination).append("/");
            }

            String encodedDestination = URLEncoder.encode(String.valueOf(destinations.get(destinations.size() - 1)), "UTF-8");
            urlBuilder.append(encodedDestination);

            String url = urlBuilder.toString();
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}


