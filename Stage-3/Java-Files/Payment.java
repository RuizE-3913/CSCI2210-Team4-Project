
/** 
 * @author Eugene Ruiz
 * @version 1.6.0
 * @date 2026-04-10
*/

import java.util.*;

/**
 * Handles payment details for a Booking. Processes transactions and tracks status.
 */
public class Payment {
    private String paymentId;
    private Booking booking;
    private double amount;
    private Date paymentDate;
    private String method;
    private String status;

    /**
     * Constructs a Payment linked to a Booking
     * Automatically generates a unique paymentId and sets current date/status
     * @param booking the Booking this payment is for
     * @param amount  the payment amount
     * @param method  payment method (e.g., "CREDIT_CARD")
     */
    public Payment(Booking booking, double amount, String method) {
        this.paymentId = "PAY-" + System.currentTimeMillis();  // Always set
        this.booking = booking;
        this.amount = amount;
        this.method = method;
        this.paymentDate = new Date();
        this.status = "PENDING";
    }

    /**
     * Returns the unique payment identifier.
     * @return paymentId
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment identifier (rarely used after construction).
     * @param paymentId new payment ID
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean processPayment() { /* transaction logic stub */ return true; }
    public void updateStatus(String newStatus) { this.status = newStatus; }
    public boolean isCompleted() { return "COMPLETED".equals(status); }

    public String getPaymentDetails() {
        return "Payment ID: " + (paymentId != null ? paymentId : "N/A") +
               " | Amount: $" + amount +
               " | Status: " + status +
               " | Booking: " + (booking != null ? booking.getBookingID() : "N/A");
    }
}
