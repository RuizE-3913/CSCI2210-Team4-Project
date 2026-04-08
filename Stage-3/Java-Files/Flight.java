
/** 
 * @author Eugene Ruiz
 * @version 2.8
 * @date 2026-04-08
 * CSCI 2210 Project Stage 3
*/

/**
 * Core class representing a single flight in the airline system
 * Holds location, timing, status, and aircraft data
 */
public class Flight {
    private String flightNumber;
    private Airport origin;
    private Airport destination;
    private Terminal departureTerminal;
    private Terminal arrivalTerminal;
    private Schedule departureTime;
    private Schedule arrivalTime;
    private String status;
    private Aircraft aircraft;

    /**
     * Constructor, ensures Flight state
     */
    public Flight(String flightNumber, Airport origin, Airport destination,
                  Terminal departureTerminal, Terminal arrivalTerminal,
                  Schedule departureTime, Schedule arrivalTime,
                  String status, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTerminal = departureTerminal;
        this.arrivalTerminal = arrivalTerminal;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.aircraft = aircraft;
    }

    // Getters & Setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public Airport getOrigin() { return origin; }
    public void setOrigin(Airport origin) { this.origin = origin; }
    public Airport getDestination() { return destination; }
    public void setDestination(Airport destination) { this.destination = destination; }
    public Terminal getDepartureTerminal() { return departureTerminal; }
    public void setDepartureTerminal(Terminal departureTerminal) { this.departureTerminal = departureTerminal; }
    public Terminal getArrivalTerminal() { return arrivalTerminal; }
    public void setArrivalTerminal(Terminal arrivalTerminal) { this.arrivalTerminal = arrivalTerminal; }
    public Schedule getDepartureTime() { return departureTime; }
    public void setDepartureTime(Schedule departureTime) { this.departureTime = departureTime; }
    public Schedule getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Schedule arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    /**
     * Returns number of available seats on this flight
     */
    public int getAvailableSeats() {
        return (aircraft != null) ? 
            (int) aircraft.getSeats().stream().filter(Seat::isSeatAvailable).count() : 0;
    }

    /**
     * Updates the flight status
     */
    public void updateStatus(String newStatus) { this.status = newStatus; }

    /**
     * Returns a formatted summary of key flight details
     */
    public String getFlightDetails() {
        return "Flight: " + flightNumber + " | From: " + (origin != null ? origin.getAirportCode() : "N/A") +
               " | To: " + (destination != null ? destination.getAirportCode() : "N/A") + " | Status: " + status;
    }
}
