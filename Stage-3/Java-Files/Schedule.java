
/** 
 * @author Eugene Ruiz
 * @version 1.4
 * @date 2026-04-05
*/

/**
 * Represents a reusable timing template for flights.
 */
public class Schedule {
    private String scheduleID;
    private String departureTime;
    private String arrivalTime;
    private ArrayList<Flight> flights = new ArrayList<>();

    public Schedule(String scheduleID, String departureTime, String arrivalTime) {
        this.scheduleID = scheduleID;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getScheduleID() { return scheduleID; }
    public void setScheduleID(String scheduleID) { this.scheduleID = scheduleID; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public void addFlight(Flight f) { flights.add(f); }
    public void removeFlight(Flight f) { flights.remove(f); }
    public ArrayList<Flight> getFlights() { return new ArrayList<>(flights); }

    /**
     * Returns a description of the schedule
     */
    public String getScheduleInfo() {
        return "Schedule " + scheduleID + ": " + departureTime + " – " + arrivalTime;
    }
}
