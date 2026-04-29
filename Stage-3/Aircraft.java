
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

        String[] seatLetters = {"A","B","C","D","E","F"};

        int seatsPerRow = 6;
        int rows = capacity; // now capacity = ROWS, not seats

        for (int row = 1; row <= rows; row++) {

            for (String letter : seatLetters) {

                String seatNumber = row + letter;

                String seatClass = (row <= 2) ? "First Class" : "Economy";

                seats.add(new Seat(seatNumber, seatClass));
            }
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
    
 /**
 * Prints a seat map (row + seats grouped).
 */
public void printSeatMap() {

    System.out.println("\n=== SEAT MAP: " + aircraftID + " ===");

    String[] seatLetters = {"A", "B", "C", "D", "E", "F"};

    int rows = (int) Math.ceil(capacity / (double) 6.0);
    
    for (int row = 1; row <= rows; row++) {

        System.out.print("Row " + row + " : ");

        for (String letter : seatLetters) {

            String seatNum = row + letter;
            Seat s = findSeat(seatNum);

            if (s == null) {
                System.out.print("[   ] ");
            } 
            else if (s.isSeatAvailable()) {
                System.out.print("[" + seatNum + "] ");
            } 
            else {
                System.out.print("[ X ] ");
            }
        }

        System.out.println();
    }

    System.out.println("=========================\n");
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
