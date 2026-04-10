import java.util.*;

/**
 * Manages collection of all Bookings
 */
public class BookingManager {
        private ArrayList<Booking> bookings = new ArrayList<>();

    /**
     * Constructs a new BookingManager with an empty booking list
     */
    public BookingManager() {}

    /**
     * Creates a new booking and adds it to the managed collection
     * @param p the passenger who owns the booking
     * @return the newly created Booking
     */
    public Booking createBooking(Passenger p) {
        Booking b = new Booking("B" + System.currentTimeMillis(), new Date(), "CONFIRMED");
        bookings.add(b);
        return b;
    }

    /**
     * Removes a booking from the managed collection by bookingID
     * @param bookingID the ID of the booking to remove
     */
    public void removeBooking(String bookingID) {
        bookings.removeIf(b -> b.getBookingID().equals(bookingID));
    }

    /**
     * Finds a booking by its ID
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
     * Returns all bookings for a specific passenger
     * Uses reference equality (matches current Passenger.viewTickets)
     * @param p the passenger
     * @return list of bookings belonging to that passenger
     */
    public List<Booking> getBookingsForPassenger(Passenger p) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            // Will use equals() once Passenger override is approved
            if (b.getTickets().stream().anyMatch(t -> t.getPassenger() == p)) {
                result.add(b);
            }
        }
        return result;
    }

    /**
     * Returns ALL bookings in the system
     * @return defensive copy of the full booking list
     */
    public ArrayList<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}

