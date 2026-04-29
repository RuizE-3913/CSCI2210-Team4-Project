
import java.util.ArrayList;

/**
 * Manages collection of all Tickets. Provides getAllTickets() for Passenger.viewTickets()
 */
public class TicketManager {

    private ArrayList<Ticket> tickets;

    /**
     * Constructs a new TicketManager with an empty ticket list
     */
    public TicketManager() {
        tickets = new ArrayList<>();
    }

    /**
     * Creates a new ticket and adds it to the managed collection
     * @param p the passenger who owns the ticket
     * @param f the flight the ticket is for
     * @return the newly created Ticket
     */
    public Ticket createTicket(Passenger p, Flight f) {
        Ticket t = new Ticket("T" + System.currentTimeMillis(), f, null, "Pending", p);
        tickets.add(t);
        return t;
    }

    /**
     * Removes a ticket from the managed collection
     * @param t the ticket to remove
     */
    public void removeTicket(Ticket t) {
        tickets.remove(t);
    }

    /**
     * Finds a ticket by its ID
     * @param ticketID the ticket identifier
     * @return the Ticket if found, null otherwise
     */
    public Ticket findTicket(String ticketID) {
        for (Ticket t : tickets) {
            if (t.getTicketID().equals(ticketID)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Returns ALL tickets in the system
     * Required by Passenger.viewTickets(TicketManager)
     * @return copy of the full ticket list
     */
    public ArrayList<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }
}
