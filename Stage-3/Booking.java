
/** 
 * @author Eugene Ruiz
 * @version 1.3
 * @date 2026-04-10
*/

import java.util.*;

/**
 * Represents a passenger reservation with one or more tickets
 */
public class Booking {
    private String bookingID;
    private Date bookingDate;
    private String status;
    private Passenger passenger;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Booking(String bookingID, Date bookingDate, Passenger passenger) {
        this.bookingID = bookingID;
        this.bookingDate = bookingDate;
        this.passenger = passenger;
        this.status = "PENDING"; 
    }

    public String getBookingID() { return bookingID; }
    public void setBookingID(String bookingID) { this.bookingID = bookingID; }
    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status; 
        
        //keep tickets in sync with booking label for status
        for (Ticket t : tickets){
            t.setStatus(status);
        }
    }
    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger){ this.passenger = passenger; }
    public ArrayList<Ticket> getTickets() { return new ArrayList<>(tickets); }

    public void addTicket(Ticket t) { tickets.add(t); }
    public void cancelBooking() { this.status = "CANCELLED"; }
    public String getBookingStatus() { return status; }
}
