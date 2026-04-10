
import java.util.*;

    // ================================================================
    // MAIN TESTER
    // ================================================================
public class MainTester {
    public static void main(String[] args) {
        System.out.println("=== AIRLINE MANAGEMENT SYSTEM TESTER. DUMMY OBJECTS (Happy Path) ===");
        System.out.println("Testing full integration of classes\n");

        // 1. Lite Coordinator (Airline)
        Airline airline = new Airline("SkyHigh Airlines");
        AirportManager airportManager = airline.getAirportManager();
        StaffManager staffManager = airline.getStaffManager();
        BookingManager bookingManager = airline.getBookingManager();
        TicketManager ticketManager = airline.getTicketManager();

        // 2. LABEEB – Infrastructure
        Airport phx = new Airport("PHX", "Phoenix Sky Harbor", "Phoenix, AZ, USA");
        Airport lax = new Airport("LAX", "Los Angeles International", "Los Angeles, CA, USA");
        airportManager.addAirport(phx);
        airportManager.addAirport(lax);

        Terminal t4 = new Terminal("T4", "Terminal 4");
        Terminal t5 = new Terminal("T5", "Terminal 5");

        Aircraft a320 = new Aircraft("A320-001", "Airbus A320", 180);
        a320.generateSeats();          // NEW
        Aircraft b737 = new Aircraft("B737-002", "Boeing 737", 160);
        b737.generateSeats();          // NEW
        

        // 3. RUIZ – Schedule & Flight
        Schedule schDep = new Schedule("SCH-001", "2026-04-15 08:00", "2026-04-15 10:30");
        Schedule schArr = new Schedule("SCH-002", "2026-04-15 11:00", "2026-04-15 13:30");

        Flight flightAA123 = new Flight("AA123", phx, lax, t4, t5, schDep, schArr, "ON_TIME", a320);

        // 3b. Create FlightAssignment (HEIFNER + RUIZ integration)
        FlightAssignment fa = new FlightAssignment("FL-ASSIGN-AA123", flightAA123, a320);
        flightAA123.setFlightAssignment(fa);   // Links back to the Flight

        System.out.println("FlightAssignment created and linked: " + fa.getAssignmentID());

        // 4. HEIFNER – Personnel
        Staff captain = new Staff("Captain Elena Ruiz", "elena.ruiz@skyhigh.com", "S-001", "Pilot");
        Staff attendant = new Staff("Maria Lopez", "maria.lopez@skyhigh.com", "S-002", "Flight Attendant");

        Passenger john = new Passenger("John Heffner", "john.h@email.com", "P-1001", "US123456789");
        Passenger eduardo = new Passenger("Eduardo Ceh-Varela", "eduardo.v@email.com", "P-1002", "CN987654321");

        staffManager.addStaff(captain);
        staffManager.addStaff(attendant);

        // 5. Assignments (HEIFNER + RUIZ integration)
        staffManager.assignStaffToFlight(captain, flightAA123);
        staffManager.assignStaffToFlight(attendant, flightAA123);

        // 5b. HEIFNER – Test Staff.viewSchedule()
        System.out.println("\n--- STAFF VIEW SCHEDULE (HEIFNER CRC) ---");
        captain.viewSchedule(staffManager);   //
        System.out.println("Captain Elena Ruiz Schedule:");
        Schedule captainSchedule = captain.viewSchedule(staffManager);
        System.out.println(captainSchedule.getScheduleInfo());
        attendant.viewSchedule(staffManager); //
        System.out.println("\nMaria Lopez Schedule:");
        Schedule attendantSchedule = attendant.viewSchedule(staffManager);
        System.out.println(attendantSchedule.getScheduleInfo());

        // 6. RUIZ – Booking & Ticket (core is HEIFNER with RUIZ bridge)
        Booking booking1 = bookingManager.createBooking(john);
        Ticket ticket1 = ticketManager.createTicket(john, flightAA123);

        // Assigning a seat (happy path)
        Seat seat1A = a320.getSeats().get(0);
        ticket1.assignSeat(seat1A);
        booking1.addTicket(ticket1);

        // NEW: Ticket for Eduardo (second passenger)
        Ticket ticket2 = ticketManager.createTicket(eduardo, flightAA123);
        Seat seat2 = a320.getSeats().get(1);   // Seat "2" – different seat
        ticket2.assignSeat(seat2);
        booking1.addTicket(ticket2);

        // 7. HEIFNER – View methods (CRC responsibilities)
        System.out.println("\n--- PASSENGER VIEW TICKETS (HEIFNER CRC) ---");
        ArrayList<Ticket> johnTickets = john.viewTickets(ticketManager);
        for (Ticket t : johnTickets) {
            System.out.println(t.getTicketDetails());
        }

        // NEW: Show Eduardo's tickets (proves both passengers work)
        ArrayList<Ticket> eduardoTickets = eduardo.viewTickets(ticketManager);
        for (Ticket t : eduardoTickets) {
            System.out.println(t.getTicketDetails());
        }

        System.out.println("\n--- STAFF VIEW SCHEDULE (HEIFNER CRC) ---");
        // Note: viewSchedule() not fully implemented in current Staff.java, but manager is ready
        System.out.println("Staff assignments for Captain: " + staffManager.getStaffForFlight(flightAA123).size() + " crew member(s)");

        // 8. Flight & Schedule details (RUIZ CRC)
        System.out.println("\n--- FLIGHT DETAILS (RUIZ CRC) ---");
        System.out.println(flightAA123.getFlightDetails());
        System.out.println("Available seats: " + flightAA123.getAvailableSeats());

        // 9. Payment (RUIZ)
        Payment paymentJohn = new Payment(booking1, 299.99, "CREDIT_CARD");
        Payment paymentEduardo = new Payment(booking1, 149.99, "DEBIT_CARD");
        System.out.println("\n--- PAYMENT DETAILS ---");
        System.out.println(paymentJohn.getPaymentDetails());
        System.out.println(paymentEduardo.getPaymentDetails());

        System.out.println("\n=== TEST COMPLETE. All Dummy objects created and linked ===");
    }
}
