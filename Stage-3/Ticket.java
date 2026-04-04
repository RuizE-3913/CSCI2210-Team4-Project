
/** 
 * @author Eugene Ruiz
 * @version 2.1
 * @date 2026-04-04
 * CSCI 2210 Class Project
*/

/**
 * Ticket class - Core Entity:
 * - Stores ticket data and owning Passenger
 * - Simple behaviors only (assign seat, cancel, get details)
 * - NO collection (delegated to TicketManager)
 */
public class Ticket {
    
    private String ticketID;
    private Flight flight;
    private Seat seat;
    private String status;
    private Passenger passenger;   // Added per Group 2 Passenger CRC notes

    /**
     * Constructor, fields required
     * 
     * @param ticketID   unique identifier for this ticket
     * @param flight     the Flight this ticket is for
     * @param seat       the Seat reserved on the flight
     * @param status     initial status (e.g., "CONFIRMED", "PENDING")
     * @param passenger  the Passenger who owns this ticket (supports Group 2)
     */
    public Ticket(String ticketID, Flight flight, Seat seat, String status, Passenger passenger) {
        this.ticketID = ticketID;
        this.flight = flight;
        this.seat = seat;
        this.status = status;
        this.passenger = passenger;
    }

    // Getters & Setters
    /**
     * Returns the unique ticket identifier.
     * @return ticketID
     */
    public String getTicketID() {
        return ticketID;
    }

    /**
     * Sets the ticket identifier (rarely used after creation).
     * @param ticketID new ticket ID
     */
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * Returns the Flight this ticket is booked for.
     * @return associated Flight object
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Updates the flight associated with this ticket.
     * @param flight new Flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Returns the Seat reserved by this ticket.
     * @return associated Seat object
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Updates the seat for this ticket.
     * @param seat new Seat
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Returns the current status of the ticket.
     * @return status (e.g., "CONFIRMED", "CANCELLED")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the ticket status.
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the Passenger who owns ticket
     * Required by Passenger.viewTickets() for Joseph Heifner
     * @return owning Passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Updates the passenger who owns this ticket.
     * @param passenger new Passenger (rarely changed)
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Assigns a seat to this ticket and updates seat availability
     * @param s the new Seat to assign
     */
    public void assignSeat(Seat s) {
        if (this.seat != null && this.seat.isSeatAvailable()) {
            this.seat.setAvailability(true);   // free old seat
        }
        this.seat = s;
        if (s != null) {
            s.setAvailability(false);          // reserve new seat
        }
    }

    /**
     * Cancels the ticket (updates status; seat is freed by caller)
     */
    public void cancelTicket() {
        this.status = "CANCELLED";
        // Seat availability restored via SeatManager or Flight
    }

    /**
     * Returns formatted ticket details for display and logging
     * Uses getters for encapsulation
     * @return readable ticket summary
     */
    public String getTicketDetails() {
        return "Ticket ID: " + ticketID +
               " | Flight: " + (flight != null ? flight.getFlightNumber() : "N/A") +
               " | Seat: " + (seat != null ? seat.getSeatNumber() : "N/A") +
               " | Status: " + status +
               " | Passenger: " + (passenger != null ? passenger.getName() : "N/A");
    }
}
