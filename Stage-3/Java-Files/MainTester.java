
/**
 * MAIN TESTER FOR SKYHIGH AIRLINES MANAGEMENT SYSTEM
 * 
 * This class serves as the primary entry point and interactive console interface
 * for the Airline Management System. It performs the following responsibilities:
 * 
 * 1. Creates all initial dummy objects (happy path)
 * 2. Displays a consolidated System Report on startup 
 * 3. Provides a menu interface with submenus for navigating 
 *    sections of system
 * 4. Allows re-running the full happy-path integration test via menu option 6 report generation
 *  
 * @author Eugene Ruiz
 * @author Joseph Heifner
 * @author Labeeb Md
 * @version 2.6.2
 * @date 04-12-2026
 */

import java.util.*;

    // ================================================================
    // MAIN TESTER
    // ================================================================
public class MainTester {

    // Static references for report and menu access
    private static Airline airline;
    private static Flight flightAA123;
    private static Staff captain;
    private static Staff attendant;
    private static Booking booking1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== AIRLINE MANAGEMENT SYSTEM TESTER. DUMMY OBJECTS (Happy Path) ===");
        System.out.println("Testing full integration of classes\n");

        // Initial dummy creation
        runHappyPathTest();

        // ====================================================================
        // NEW: Demonstrates report generation
        // ====================================================================
        displaySystemReport();

        // ====================================================================
        // Interactive Menu System
        // ====================================================================
        runInteractiveMenu();
    }

    /**
     * Runs the complete happy-path integration test
     * Called once at startup and again via menu option 6
     */
    private static void runHappyPathTest() {
        // 1. Lite Coordinator (Airline)
        airline = new Airline("SkyHigh Airlines");
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

        flightAA123 = new Flight("AA123", phx, lax, t4, t5, schDep, schArr, "ON_TIME", a320);

        // 3b. Create FlightAssignment (HEIFNER + RUIZ integration)
        FlightAssignment fa = new FlightAssignment("FL-ASSIGN-AA123", flightAA123, a320);
        flightAA123.setFlightAssignment(fa);   // Links back to the Flight

        System.out.println("FlightAssignment created and linked: " + fa.getAssignmentID());

        // 4. HEIFNER – Personnel
        captain = new Staff("Captain Elena Ruiz", "elena.ruiz@skyhigh.com", "S-001", "Pilot");
        attendant = new Staff("Maria Lopez", "maria.lopez@skyhigh.com", "S-002", "Flight Attendant");

        Passenger john = new Passenger("John Heffner", "john.h@email.com", "P-1001", "US123456789");
        Passenger eduardo = new Passenger("Eduardo Ceh-Varela", "eduardo.v@email.com", "P-1002", "CN987654321");

        staffManager.addStaff(captain);
        staffManager.addStaff(attendant);

        // 5. Assignments (HEIFNER + RUIZ integration)
        staffManager.assignStaffToFlight(captain, flightAA123);
        staffManager.assignStaffToFlight(attendant, flightAA123);

        // 5b. HEIFNER – Test Staff.viewSchedule()
        System.out.println("\n--- STAFF VIEW SCHEDULE (HEIFNER CRC) ---");
        captain.viewSchedule(staffManager);   
        System.out.println("Captain Elena Ruiz Schedule:");
        Schedule captainSchedule = captain.viewSchedule(staffManager);
        System.out.println(captainSchedule.getScheduleInfo());
        attendant.viewSchedule(staffManager); 
        System.out.println("\nMaria Lopez Schedule:");
        Schedule attendantSchedule = attendant.viewSchedule(staffManager);
        System.out.println(attendantSchedule.getScheduleInfo());

        // 6. RUIZ – Booking & Ticket (core is HEIFNER with RUIZ bridge)
        booking1 = bookingManager.createBooking(john);
        Ticket ticket1 = ticketManager.createTicket(john, flightAA123);
        Seat seat1A = a320.getSeats().get(0);
        ticket1.assignSeat(seat1A);
        booking1.addTicket(ticket1);

        Ticket ticket2 = ticketManager.createTicket(eduardo, flightAA123);
        Seat seat2 = a320.getSeats().get(1);
        ticket2.assignSeat(seat2);
        booking1.addTicket(ticket2);

        // 7. HEIFNER – View methods (CRC responsibilities)
        System.out.println("\n--- PASSENGER VIEW TICKETS (HEIFNER CRC) ---");
        for (Ticket t : john.viewTickets(ticketManager)) {
            System.out.println(t.getTicketDetails());
        }
        for (Ticket t : eduardo.viewTickets(ticketManager)) {
            System.out.println(t.getTicketDetails());
        }

        System.out.println("\n--- STAFF VIEW SCHEDULE (HEIFNER CRC) ---");
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

    /**
     * Displays a consolidated SYSTEM REPORT using existing objects
     */
    private static void displaySystemReport() {
        System.out.println("\n=== SKYHIGH AIRLINES SYSTEM REPORT ===");
        System.out.println("Customers/Passengers created: John Heffner, Eduardo Ceh-Varela");
        System.out.println("Transactions/Payments processed: $449.98 total revenue");
        System.out.println("Active Flights: " + flightAA123.getFlightNumber() + " (" + flightAA123.getStatus() + ")");
        System.out.println("Available Seats on AA123: " + flightAA123.getAvailableSeats());
        System.out.println("Staff Assigned: " + captain.getName() + " (Pilot), " + attendant.getName() + " (Attendant)");
        System.out.println("Bookings Created: " + booking1.getBookingID() + " (CONFIRMED)");
        System.out.println("=== REPORT COMPLETE ===\n");
    }

    private static void runInteractiveMenu() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getValidIntInput(0, 6);
            switch (choice) {
                case 1 -> showInfrastructureSubmenu();
                case 2 -> showPersonnelSubmenu();
                case 3 -> showFlightOperationsSubmenu();
                case 4 -> showBookingSubmenu();
                case 5 -> showPaymentsSubmenu();
                case 6 -> {
                    System.out.println("\n=== RE-RUNNING FULL HAPPY PATH INTEGRATION TEST ===");
                    runHappyPathTest();
                }
                case 0 -> {
                    running = false;
                    System.out.println("\nThank you for using SkyHigh Airlines Management System. Goodbye!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== MAIN MENU (SkyHigh Airlines) ===");
        System.out.println("1. Infrastructure Management (Airports / Terminals / Aircraft)");
        System.out.println("2. Personnel & Assignments (Staff / Passengers)");
        System.out.println("3. Flight Operations");
        System.out.println("4. Bookings & Tickets");
        System.out.println("5. Payments & Reports");
        System.out.println("6. Re-run Full Happy-Path Integration Test (original test output)");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
    }

    // ──────────────────────────────────────────────────────────────
    // SUBMENU 1: Infrastructure
    // ──────────────────────────────────────────────────────────────
    private static void showInfrastructureSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- INFRASTRUCTURE MANAGEMENT ---");
            System.out.println("1. List All Airports");
            System.out.println("2. View Aircraft Details");
            System.out.println("3. Show Available Seats on Current Flight");
            System.out.println("0. Back to Main Menu");
            int ch = getValidIntInput(0, 3);

            if (ch == 1) {
                System.out.println("Airports in system:");
                System.out.println(" - PHX: Phoenix Sky Harbor");
                System.out.println(" - LAX: Los Angeles International");
            } else if (ch == 2) {
                System.out.println("Aircraft Inventory:");
                System.out.println(" - A320-001 | Airbus A320 | Capacity 180");
                System.out.println(" - B737-002 | Boeing 737 | Capacity 160");
            } else if (ch == 3) {
                System.out.println("Available seats on AA123: " + 
                    (flightAA123 != null ? flightAA123.getAvailableSeats() : 178));
            } else if (ch == 0) {
                back = true;
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // SUBMENU 2: Personnel
    // ──────────────────────────────────────────────────────────────
    private static void showPersonnelSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- PERSONNEL & ASSIGNMENTS ---");
            System.out.println("1. View Staff Schedule");
            System.out.println("2. List All Passengers");
            System.out.println("3. Show Crew for Flight AA123");
            System.out.println("0. Back to Main Menu");
            int ch = getValidIntInput(0, 3);

            if (ch == 1) {
                System.out.println("Captain Elena Ruiz Schedule:");
                System.out.println(captain != null ? captain.viewSchedule(airline.getStaffManager()).getScheduleInfo() : "No schedule");
                System.out.println("\nMaria Lopez Schedule:");
                System.out.println(attendant != null ? attendant.viewSchedule(airline.getStaffManager()).getScheduleInfo() : "No schedule");
            } else if (ch == 2) {
                System.out.println("Passengers in system:");
                System.out.println(" - P-1001: John Heffner");
                System.out.println(" - P-1002: Eduardo Ceh-Varela");
            } else if (ch == 3) {
                System.out.println("Crew assigned to AA123: 2 members");
                System.out.println(" - Captain Elena Ruiz (Pilot)");
                System.out.println(" - Maria Lopez (Flight Attendant)");
            } else if (ch == 0) {
                back = true;
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // SUBMENU 3: Flight Operations
    // ──────────────────────────────────────────────────────────────
    private static void showFlightOperationsSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- FLIGHT OPERATIONS ---");
            System.out.println("1. View Current Flight Details");
            System.out.println("2. Show Flight Status");
            System.out.println("3. Check Aircraft Assignment");
            System.out.println("0. Back to Main Menu");
            int ch = getValidIntInput(0, 3);

            if (ch == 1) {
                System.out.println(flightAA123 != null ? flightAA123.getFlightDetails() : "Flight data not loaded");
            } else if (ch == 2) {
                System.out.println("Flight AA123 Status: " + (flightAA123 != null ? flightAA123.getStatus() : "ON_TIME"));
            } else if (ch == 3) {
                System.out.println("Aircraft assigned to AA123: A320-001 (Airbus A320)");
            } else if (ch == 0) {
                back = true;
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // SUBMENU 4: Bookings & Tickets (1 option only - uses real objects)
    // ──────────────────────────────────────────────────────────────
    private static void showBookingSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- BOOKINGS & TICKETS ---");
            System.out.println("1. Show All Booking Status for Tickets");
            System.out.println("0. Back to Main Menu");
            int ch = getValidIntInput(0, 1);

            if (ch == 1) {
                System.out.println("\n=== ALL BOOKINGS & TICKETS STATUS ===");

                TicketManager tm = airline.getTicketManager();
                ArrayList<Ticket> allTickets = tm.getAllTickets();

                for (Ticket t : allTickets) {
                    System.out.println(t.getTicketDetails());   // Real object call
                }

                System.out.println("\nTotal Tickets: " + allTickets.size());
                System.out.println("Booking ID: " + (booking1 != null ? booking1.getBookingID() : "B-...") + " | Status: CONFIRMED");
                System.out.println("=== END OF BOOKING/TICKET STATUS ===");
            } 
            else if (ch == 0) {
                back = true;
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // SUBMENU 5: Payments & Reports
    // ──────────────────────────────────────────────────────────────
    private static void showPaymentsSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- PAYMENTS & REPORTS ---");
            System.out.println("1. Show System Report (Summary)");
            System.out.println("2. View Payment Details");
            System.out.println("0. Back to Main Menu");
            int ch = getValidIntInput(0, 2);

            if (ch == 1) {
                displaySystemReport();
            } else if (ch == 2) {
                System.out.println("Payment for " + (booking1 != null ? booking1.getBookingID() : "B-...") + " | Amount: $299.99 | Status: PENDING");
                System.out.println("Payment for " + (booking1 != null ? booking1.getBookingID() : "B-...") + " | Amount: $149.99 | Status: PENDING");
            } else if (ch == 0) {
                back = true;
            }
        }
    }

    /**
     * Robust input helper for clearing promping and helping invalid outputs
     */
    private static int getValidIntInput(int min, int max) {
        while (true) {
            System.out.print("Enter choice (" + min + "-" + max + "): ");
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (Exception ignored) {}
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
    }
}
