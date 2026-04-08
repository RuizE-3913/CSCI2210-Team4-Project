
/**
 * Manages collection of all Tickets. Provides getAllTickets() for Passenger.viewTickets()
 */
public class TicketManager {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public TicketManager() {}

    public Ticket createTicket(Passenger p, Flight f) {
        Ticket t = new Ticket("T" + System.currentTimeMillis(), f, null, "CONFIRMED", p);
        tickets.add(t);
        return t;
    }
    public void removeTicket(Ticket t) { tickets.remove(t); }
    public Ticket findTicket(String ticketID) { /* implementation */ }

    public ArrayList<Ticket> getAllTickets() { return new ArrayList<>(tickets); }
}
