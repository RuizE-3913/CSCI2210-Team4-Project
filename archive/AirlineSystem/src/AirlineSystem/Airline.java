package AirlineSystem;

/**
 * Coordinator, holding references to all managers only
 */
public class Airline {
    private String airlineName;
    private AirportManager airportManager;
    private StaffManager staffManager;
    private BookingManager bookingManager;
    private TicketManager ticketManager;
    private FlightManager flightManager; //new addition
    private PassengerManager passengerManager; //new

    public Airline(String airlineName) {
        this.airlineName = airlineName;
        this.airportManager = new AirportManager();
        this.staffManager = new StaffManager();
        this.bookingManager = new BookingManager();
        this.ticketManager = new TicketManager();
        this.flightManager = new FlightManager(); //new addition
        this.passengerManager = new PassengerManager(); //new
    }

    public AirportManager getAirportManager() { return airportManager; }
    public StaffManager getStaffManager() { return staffManager; }
    public BookingManager getBookingManager() { return bookingManager; }
    public TicketManager getTicketManager() { return ticketManager; }
    public String getAirlineName() { return airlineName; }
    public FlightManager getFlightManager() { return flightManager; } //new method
    public PassengerManager getPassengerManager() { return passengerManager; } //new method
}
