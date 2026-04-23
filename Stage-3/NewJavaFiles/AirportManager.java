
import java.util.*;

/**
 * Manages the collection of airports and coordinates flight-terminal assignments.
 *
 * Responsibilities (SRP):
 *   - Add / remove / search airports
 *   - Assign or remove a flight from a specific terminal at a given airport
 *   - Query which flights are currently at a given airport
 *
 * NOTE: This class references Flight and Terminal from other groups.
 *       Wherever Flight is used below, it expects the Flight class defined
 *       by the flight-management group (Group 2 / Joseph).
 *       If Flight is not yet available, replace with a placeholder stub.
 *
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class AirportManager {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private ArrayList<Airport> airports;

    // Internal map: tracks which flights are assigned to which terminal+airport.
    // Key pattern: airportCode + "::" + terminalId  →  list of flights
    // Using a simple parallel list approach so we don't need java.util.HashMap
    // (swap to HashMap<String, ArrayList<Flight>> if your project imports allow it).
    private ArrayList<String>            flightKeys;    // "airportCode::terminalId"
    private ArrayList<ArrayList<Flight>> flightLists;   // parallel list of flight collections

    // ─── Constructor ──────────────────────────────────────────────────────────

    public AirportManager() {
        airports    = new ArrayList<>();
        flightKeys  = new ArrayList<>();
        flightLists = new ArrayList<>();
    }

    // ─── Airport Collection Methods ───────────────────────────────────────────

    /**
     * Adds an airport to the managed collection.
     */
    public void addAirport(Airport a) {
        if (a != null && findAirport(a.getAirportCode()) == null) {
            airports.add(a);
        }
    }

    /**
     * Removes an airport by its IATA/ICAO code.
     */
    public boolean removeAirport(String airportCode) {
        Iterator<Airport> iterator = airports.iterator();
        
        while (iterator.hasNext()) { 
            Airport a = iterator.next();
            
            if (a.getAirportCode().equalsIgnoreCase(airportCode)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns an airport by code. Returns null if not found.
     */
    public Airport findAirport(String airportCode) {
        for (Airport a : airports) {
            if (a.getAirportCode().equals(airportCode)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Returns the full list of managed airports.
     */
    public ArrayList<Airport> getAirports() {
        return airports;
    }

    // ─── Flight–Terminal Coordination Methods ─────────────────────────────────

    /**
     * Assigns a flight to a terminal at a given airport.
     * Creates a new slot if this terminal has not been seen before.
     *
     * @param f flight to assign
     * @param t terminal at the airport
     * @param a airport that owns the terminal
     */
    public void assignFlightToTerminal(Flight f, Terminal t, Airport a) {
        if (f == null || t == null || a == null) return;

        String key   = buildKey(a.getAirportCode(), t.getTerminalId());
        int    index = flightKeys.indexOf(key);

        if (index == -1) {
            // First flight at this terminal – create new slot
            flightKeys.add(key);
            ArrayList<Flight> list = new ArrayList<>();
            list.add(f);
            flightLists.add(list);
        } else {
            ArrayList<Flight> list = flightLists.get(index);
            if (!list.contains(f)) {
                list.add(f);
            }
        }
    }

    /**
     * Removes a flight from all terminals at the given airport.
     *
     * @param f flight to remove
     * @param a airport to search within
     */
    public void removeFlightFromAirport(Flight f, Airport a) {
        if (f == null || a == null) return;

        String prefix = a.getAirportCode() + "::";
        for (int i = 0; i < flightKeys.size(); i++) {
            if (flightKeys.get(i).startsWith(prefix)) {
                flightLists.get(i).remove(f);
            }
        }
    }

    /**
     * Returns all flights currently assigned to any terminal at a given airport.
     *
     * @param a the airport to query
     * @return list of flights (empty if none assigned)
     */
    public List<Flight> getFlightsAtAirport(Airport a) {
        List<Flight> result = new ArrayList<>();
        if (a == null) return result;

        String prefix = a.getAirportCode() + "::";
        for (int i = 0; i < flightKeys.size(); i++) {
            if (flightKeys.get(i).startsWith(prefix)) {
                result.addAll(flightLists.get(i));
            }
        }
        return result;
    }

    /**
     * Returns flights assigned to a specific terminal at a specific airport.
     *
     * @param a the airport
     * @param t the terminal within that airport
     * @return list of flights at that terminal
     */
    public List<Flight> getFlightsAtTerminal(Airport a, Terminal t) {
        List<Flight> result = new ArrayList<>();
        if (a == null || t == null) return result;

        String key   = buildKey(a.getAirportCode(), t.getTerminalId());
        int    index = flightKeys.indexOf(key);
        if (index != -1) {
            result.addAll(flightLists.get(index));
        }
        return result;
    }

    // ─── Helper ───────────────────────────────────────────────────────────────

    private String buildKey(String airportCode, String terminalId) {
        return airportCode + "::" + terminalId;
    }

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "AirportManager managing " + airports.size() + " airport(s).";
    }
}
