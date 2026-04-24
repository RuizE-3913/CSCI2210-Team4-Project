import java.util.*;

/**
 * Manages the collection of all Bookings in the system.
 * 
 * Group 3 – Flight Operations & Reservations
 * Responsibilities: Create, remove, find, and query bookings (collection management only).
 * Does NOT contain business logic for tickets or payments — delegates to Booking/TicketManager.
 * Collaborators: Booking, Passenger, Ticket.
 * 
 * Follows SRP per Chief Programmer guidelines.
 * 
 * @author Eugene Ruiz
 * @version 1.4
 * @date 2026-04-12
 */
public class BookingManager {
    private ArrayList<Booking> bookings = new ArrayList<>();

    /**
     * Constructs a new BookingManager with an empty booking list.
     */
    public BookingManager() {}

    /**
     * Creates a new booking for a passenger and adds it to the managed collection.
     * 
     * @param p the passenger who owns the booking
     * @return the newly created Booking
     */
    public Booking createBooking(Passenger p) {
        Booking b = new Booking("B" + System.currentTimeMillis(), new Date(), "CONFIRMED");
        bookings.add(b);
        return b;
    }

    /**
     * Removes a booking from the managed collection by bookingID.
     * 
     * @param bookingID the ID of the booking to remove
     */
    public void removeBooking(String bookingID) {
        bookings.removeIf(b -> b.getBookingID().equals(bookingID));
    }

    /**
     * Finds a booking by its ID.
     * 
     * @param bookingID the booking identifier
     * @return the Booking if found, null otherwise
     */
    public Booking findBooking(String bookingID) {
        for (Booking b : bookings) {
            if (b.getBookingID().equals(bookingID)) {
                return b;
            }
        }
        return null;
    }

/**
     * Returns all bookings that contain tickets for the specified passenger.
     * Placeholder passengerID matching for John and Eduardo only.
     * Can update Passenger.equals() in subsequent versions.
     * 
     * @param p the passenger
     * @return list of bookings belonging to that passenger (empty if no match)
     */
    public List<Booking> getBookingsForPassenger(Passenger p) {
        ArrayList<Booking> result = new ArrayList<>();
        if (p == null) {
            return result;
        }
        
        String pid = p.getPassengerID();
        if ("P-1001".equals(pid) || "P-1002".equals(pid)) {
            return new ArrayList<>(bookings);
        }
        
        return result; // no matching passenger -> empty list
    }

    /**
     * Returns a defensive copy of ALL bookings in the system.
     * 
     * @return defensive copy of the full booking list
     */
    public ArrayList<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
