import java.util.ArrayList;

/**
 * Represents an airport with terminals.
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class Airport {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private String airportCode;
    private String name;
    private String location;
    private ArrayList<Terminal> terminals;

    // ─── Constructor ──────────────────────────────────────────────────────────

    public Airport(String airportCode, String name, String location) {
        this.airportCode = airportCode;
        this.name        = name;
        this.location    = location;
        this.terminals   = new ArrayList<>();
    }

    // ─── Core Methods ─────────────────────────────────────────────────────────

    /**
     * Returns a formatted summary of this airport's information.
     */
    public String getAirportInfo() {
        return "Airport[" + airportCode + "] " + name + " | Location: " + location
                + " | Terminals: " + terminals.size();
    }

    /**
     * Adds a terminal to this airport.
     */
    public void addTerminal(Terminal t) {
        if (t != null && !terminals.contains(t)) {
            terminals.add(t);
        }
    }

    /**
     * Removes a terminal by its ID.
     */
    public void removeTerminal(String terminalId) {
        terminals.removeIf(t -> t.getTerminalId().equals(terminalId));
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(ArrayList<Terminal> terminals) {
        this.terminals = terminals;
    }

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return getAirportInfo();
    }
}
