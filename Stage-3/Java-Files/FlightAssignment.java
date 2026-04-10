


/**
 * Represents the assignment of a specific aircraft to a flight.
 * Provides methods to view and change the assigned aircraft.
 * 
 * @author Joseph Heifner
 */
public class FlightAssignment {

    private String assignmentID;
    private Flight flight;
    private Aircraft aircraft;

    /**
     * Constructs a FlightAssignment linking a flight to an aircraft.
     * 
     * @param assignmentID a unique identifier for this assignment
     * @param flight the flight to assign
     * @param aircraft the aircraft assigned to the flight
     */
    public FlightAssignment(String assignmentID, Flight flight, Aircraft aircraft) {
        this.assignmentID = assignmentID;
        this.flight = flight;
        this.aircraft = aircraft;
    }

    /**
     * Returns the flight associated with this assignment.
     * 
     * @return the Flight object
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Returns the aircraft assigned to this flight.
     * 
     * @return the Aircraft object
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Returns the unique assignment identifier.
     * Required by Flight.getFlightDetails().
     */
    public String getAssignmentID() {
        return assignmentID;
    }

    /**
     * Changes the aircraft assigned to this flight.
     * 
     * @param newAircraft the new Aircraft to assign
     */
    public void changeAircraft(Aircraft newAircraft) {
        this.aircraft = newAircraft;
    }
}
