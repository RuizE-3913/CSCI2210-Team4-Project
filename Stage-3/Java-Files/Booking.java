
/** 
 * @author Eugene Ruiz
 * @version 1.3
 * @date 2026-04-05
*/

/**
 * Represents a passenger reservation with one or more tickets
 */
public class Booking {
    private String bookingID;
    private Date bookingDate;
    private String status;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Booking(String bookingID, Date bookingDate, String status) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public String getBookingID() { return bookingID; }
    public void setBookingID(String bookingID) { this.bookingID = bookingID; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public ArrayList<Ticket> getTickets() { return new ArrayList<>(tickets); }

    public void addTicket(Ticket t) { tickets.add(t); }
    public void cancelBooking() { this.status = "CANCELLED"; }
    public String getBookingStatus() { return status; }
}
