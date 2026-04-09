/**
 * Represents a single piece of passenger baggage.
 * Status can be updated as the bag moves through the airport system.
 * Group 1 – Infrastructure & Location Management
 * Assigned Programmer: Md Labeeb
 */
public class Baggage {

    // ─── Instance Variables ───────────────────────────────────────────────────

    private String baggageID;
    private double weight;      // in kilograms
    private String status;      // e.g., "Checked In", "Loaded", "In Transit", "Claimed", "Lost"

    // ─── Constructor ──────────────────────────────────────────────────────────

    /**
     * Creates a baggage item. Status defaults to "Checked In".
     *
     * @param baggageID unique identifier (e.g., "BAG-00123")
     * @param weight    weight in kilograms
     */
    public Baggage(String baggageID, double weight) {
        this.baggageID = baggageID;
        this.weight    = weight;
        this.status    = "Checked In";
    }

    // ─── Core Methods ─────────────────────────────────────────────────────────

    /**
     * Updates the tracking status of this baggage item.
     *
     * @param newStatus new status string
     */
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns a formatted summary of this baggage item.
     */
    public String getBaggageInfo() {
        return "Baggage[" + baggageID + "] Weight: " + weight + " kg | Status: " + status;
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public String getBaggageID() {
        return baggageID;
    }

    public void setBaggageID(String baggageID) {
        this.baggageID = baggageID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    // status updated via updateStatus(); direct setter also provided
    public void setStatus(String status) {
        this.status = status;
    }

    // ─── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return getBaggageInfo();
    }
}
