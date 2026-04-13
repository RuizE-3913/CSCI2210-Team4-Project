import java.util.ArrayList;

/**
 * Represents an aircraft owned by an airline.
 * Holds seat inventory; assignment logic lives in FlightAssignment.
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class Aircraft {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private String          aircraftID;
    private String          model;
    private int             capacity;
    private String          status;         // e.g., "Active", "Maintenance", "Grounded"
    private ArrayList<Seat> seats;

    // ─── Constructor ──────────────────────────────────────────────────────────

    /**
     * Creates an aircraft. Status defaults to "Active".
     * Seat list starts empty; use generateSeats() or addSeat() to populate.
     *
     * @param aircraftID unique identifier (e.g., "AC-001")
     * @param model      model name (e.g., "Boeing 737")
     * @param capacity   total seat count
     */
    public Aircraft(String aircraftID, String model, int capacity) {
        this.aircraftID = aircraftID;
        this.model      = model;
        this.capacity   = capacity;
        this.status     = "Active";
        this.seats      = new ArrayList<>();
    }

    // ─── Core Methods ─────────────────────────────────────────────────────────

    /**
     * Convenience method: auto-generates Economy seats numbered 1–capacity.
     * Call once after construction if you don't need custom seat layouts.
     */
    public void generateSeats() {
        seats.clear();
        for (int i = 1; i <= capacity; i++) {
            seats.add(new Seat(String.valueOf(i), "Economy"));
        }
    }

    /**
     * Manually adds a single seat (for custom layouts).
     */
    public void addSeat(Seat seat) {
        if (seat != null) {
            seats.add(seat);
        }
    }

    /**
     * Updates the operational status of this aircraft.
     *
     * @param newStatus e.g., "Active", "Maintenance", "Grounded"
     */
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns a formatted summary of this aircraft's information.
     */
    public String getAircraftInfo() {
        return "Aircraft[" + aircraftID + "] Model: " + model
                + " | Capacity: " + capacity
                + " | Status: " + status
                + " | Seats loaded: " + seats.size();
    }

    /**
     * Counts and returns the number of available seats.
     */
    public int countAvailableSeats() {
        int count = 0;
        for (Seat s : seats) {
            if (s.isSeatAvailable()) count++;
        }
        return count;
    }

    /**
     * Finds a seat by its seat number. Returns null if not found.
     */
    public Seat findSeat(String seatNumber) {
        for (Seat s : seats) {
            if (s.getSeatNumber().equals(seatNumber)) {
                return s;
            }
        }
        return null;
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public String getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(String aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    // status updated via updateStatus(); direct setter also provided
    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return getAircraftInfo();
    }
}
