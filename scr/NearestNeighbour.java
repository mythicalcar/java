package scr;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbour {

    // Helper class to represent a data point
    private static class DataPoint {
        double x;
        double y;

        public DataPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Method to calculate the Euclidean distance between two data points
    private static double calculateDistance(DataPoint p1, DataPoint p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Method to find the nearest neighbour
    private static DataPoint findNearestPoint(DataPoint queryPoint, List<DataPoint> dataPoints) {
        DataPoint nearestNeighbour = null;
        double minDistance = Double.MAX_VALUE;

        for (DataPoint point : dataPoints) {
            double distance = calculateDistance(queryPoint, point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestNeighbour = point;
            }
        }

        return nearestNeighbour;
    }

    public static void main(String[] args) {
        // Create a list of data points
        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint(1.0, 2.0));
        dataPoints.add(new DataPoint(3.0, 4.0));
        dataPoints.add(new DataPoint(5.0, 6.0));

        // Create a query point
        DataPoint queryPoint = new DataPoint(2.5, 3.5);

        // Find the nearest neighbour
        DataPoint nearestNeighbour = findNearestPoint(queryPoint, dataPoints);

        // Print the result
        System.out.println("Dichstbijzijnde punt: (" + nearestNeighbour.x + ", " + nearestNeighbour.y + ")");
    }
}