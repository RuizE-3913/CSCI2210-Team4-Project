

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all Flight objects in the system.
 * Acts as the central controller for flight-related operations.
 * This class ensures that flight numbers remain unique and provides access to info
 * to the internal flight data.
 * 
 * @author Joseph Heifner
 */
public class FlightManager {

    private ArrayList<Flight> flights = new ArrayList<>();

    /**
     * Adds a new flight to the system if it doesn't already exist.
     *
     * @param f the Flight object to be added
     */
    public void addFlight(Flight f) {
        if (f != null && findFlight(f.getFlightNumber()) == null) {
            flights.add(f);
        }
    }

    /**
     * Finds and returns a flight by its flight number.
     * Not case sensitive.
     *
     * @param flightNumber the flight number to search for
     * @return the Flight object if found, or null if not
     */
    public Flight findFlight(String flightNumber) {
        for (Flight f : flights) {
            if (f.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Returns a copy of all flights stored in the system.
     * This prevents external modification of the list.
     *
     * @return a new List containing all Flight objects
     */
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }

    /**
     * Updates the status of a specific flight.
     * If the flight exists, its status is changed to the new value.
     *
     * @param flightNumber the flight to update
     * @param status the new status ("ON_TIME", "DELAYED", "CANCELLED")
     * @return true if the flight was found and updated, false otherwise
     */
    public boolean updateStatus(String flightNumber, String status) {
        Flight f = findFlight(flightNumber);
        if (f != null) {
            f.setStatus(status);
            return true;
        }
        return false;
    }

    /**
     * Removes a flight from the system based on its flight number.
     * Not case sensitive
     *
     * @param flightNumber the flight number of the flight to remove
     * @return true if the flight was successfully removed, false if not found
     */
    public boolean removeFlight(String flightNumber) {
        return flights.removeIf(f -> f.getFlightNumber().equalsIgnoreCase(flightNumber));
    }
}
