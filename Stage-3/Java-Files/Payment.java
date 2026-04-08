
/** 
 * @author Eugene Ruiz
 * @version 1.
 * @date 2026-04-07
*/


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

    public Payment(Booking booking, double amount, String method) {
        this.booking = booking;
        this.amount = amount;
        this.method = method;
        this.paymentDate = new Date();
        this.status = "PENDING";
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
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
        return "Payment ID: " + paymentId + " | Amount: $" + amount + " | Status: " + status;
    }
}
