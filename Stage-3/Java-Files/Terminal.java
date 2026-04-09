import java.util.ArrayList;

/**
 * Represents a terminal inside an airport, holding gate information.
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class Terminal {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private String terminalId;
    private String name;
    private ArrayList<String> gates;

    // NOTE: Per UML, Terminal --> Flight (uses). The Flight list is managed by
    // AirportManager (assignFlightToTerminal / getFlightsAtAirport).
    // Terminal only stores gate labels (Strings) to keep it lightweight.

    // ─── Constructor ──────────────────────────────────────────────────────────

    public Terminal(String terminalId, String name) {
        this.terminalId = terminalId;
        this.name       = name;
        this.gates      = new ArrayList<>();
    }

    // ─── Core Methods ─────────────────────────────────────────────────────────

    /**
     * Returns a formatted summary of this terminal's information.
     */
    public String getTerminalInfo() {
        return "Terminal[" + terminalId + "] " + name + " | Gates: " + gates;
    }

    /**
     * Adds a gate label to this terminal (e.g., "A1", "B12").
     */
    public void addGate(String gate) {
        if (gate != null && !gate.isBlank() && !gates.contains(gate)) {
            gates.add(gate);
        }
    }

    /**
     * Removes a gate label from this terminal.
     */
    public void removeGate(String gate) {
        gates.remove(gate);
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getGates() {
        return gates;
    }

    public void setGates(ArrayList<String> gates) {
        this.gates = gates;
    }

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return getTerminalInfo();
    }
}
