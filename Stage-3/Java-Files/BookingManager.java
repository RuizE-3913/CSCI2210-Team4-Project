
/**
 * Manages collection of all Bookings
 */
public class BookingManager {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public BookingManager() {}

    public Booking createBooking(Passenger p) {
        Booking b = new Booking("B" + System.currentTimeMillis(), new Date(), "CONFIRMED");
        bookings.add(b);
        return b;
    }
    public void removeBooking(String bookingID) { /* implementation */ }
    public Booking findBooking(String bookingID) { /* implementation */ }
    public List<Booking> getBookingsForPassenger(Passenger p) { /* implementation */ }
}

