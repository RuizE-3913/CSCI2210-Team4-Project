/**
 * Core class representing a single flight in the airline system.
 * Holds location, timing, status, aircraft, and optional FlightAssignment data.
 * 
 * Responsibilities: Store flight details and provide basic status/seat queries.
 * Collaborators: Airport, Terminal, Schedule, Aircraft, FlightAssignment.
 * 
 * @author Eugene Ruiz
 * @version 2.1
 * @date 2026-04-12
 * CSCI 2210 Project Stage 3
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
    private FlightAssignment flightAssignment;

    /**
     * Constructs a new Flight with all required details.
     * 
     * @param flightNumber       unique flight identifier (e.g., "AA123")
     * @param origin             departure airport
     * @param destination        arrival airport
     * @param departureTerminal  terminal at origin airport
     * @param arrivalTerminal    terminal at destination airport
     * @param departureTime      departure schedule
     * @param arrivalTime        arrival schedule
     * @param status             initial flight status (e.g., "ON_TIME")
     * @param aircraft           aircraft assigned to this flight
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

    // ==================== Getters & Setters ====================

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
     * Returns the FlightAssignment linked to this flight.
     * @return the FlightAssignment or null if none assigned
     */
    public FlightAssignment getFlightAssignment() {
        return flightAssignment;
    }

    /**
     * Links (or updates) the FlightAssignment for this flight.
     * @param flightAssignment the assignment to link
     */
    public void setFlightAssignment(FlightAssignment flightAssignment) {
        this.flightAssignment = flightAssignment;
    }

    /**
     * Returns the number of available seats on this flight.
     * Delegates to the assigned Aircraft.
     * @return number of available seats, or 0 if no aircraft assigned
     */
    public int getAvailableSeats() {
        return (aircraft != null) ? 
            (int) aircraft.getSeats().stream().filter(Seat::isSeatAvailable).count() : 0;
    }

    /**
     * Updates the flight status (e.g., "ON_TIME", "DELAYED", "CANCELLED").
     * @param newStatus the new status value
     */
    public void updateStatus(String newStatus) { 
        this.status = newStatus; 
    }

    /**
     * Returns a formatted summary of key flight details.
     * Includes FlightAssignment info when present.
     * @return formatted flight details string
     */
    public String getFlightDetails() {
        String assignmentInfo = (flightAssignment != null) 
            ? " | Assignment: " + flightAssignment.getAssignmentID() 
            : " | No assignment yet";
        return "Flight: " + flightNumber + " | From: " + 
               (origin != null ? origin.getAirportCode() : "N/A") +
               " | To: " + (destination != null ? destination.getAirportCode() : "N/A") + 
               " | Status: " + status + assignmentInfo;
    }
}
