/**
 * Represents a single seat inside an Aircraft.
 * Belongs to Aircraft via composition (Aircraft o--> Seat).
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class Seat {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private String  seatNumber;
    private String  classType;     // e.g., "Economy", "Business", "First"
    private boolean isAvailable;

    // ─── Constructor ──────────────────────────────────────────────────────────

    /**
     * Creates a seat. Availability defaults to true (open seat).
     *
     * @param seatNumber seat label such as "12A"
     * @param classType  cabin class, e.g. "Economy"
     */
    public Seat(String seatNumber, String classType) {
        this.seatNumber  = seatNumber;
        this.classType   = classType;
        this.isAvailable = true;       // new seats start as available
    }

    // ─── Core Methods ─────────────────────────────────────────────────────────

    /**
     * Returns whether this seat is currently available.
     */
    public boolean isSeatAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of this seat.
     *
     * @param available true = seat is open; false = seat is taken
     */
    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    // isAvailable getter/setter handled by isSeatAvailable() / setAvailability()

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Seat[" + seatNumber + "] Class: " + classType
                + " | Available: " + isAvailable;
    }
}
