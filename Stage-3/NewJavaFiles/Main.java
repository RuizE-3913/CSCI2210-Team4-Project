/**
 * MAIN PROGRAM FOR SKYHIGH AIRLINES MANAGEMENT SYSTEM
 * 
 * This class serves as the primary entry point and interactive console interface
 * for the Airline Management System.
 *  
 * @author Eugene Ruiz
 * @author Joseph Heifner
 * @author Labeeb Md
 * @date 04-22-2026
 */

import java.util.*;

public class Main {

    private static Airline airline;
    private static Scanner scanner = new Scanner(System.in);

    // session state (important for booking flow)
    private static Flight selectedFlight;
    private static Booking activeBooking;

    public static void main(String[] args) {

        airline = new Airline("SkyHigh Airlines");

        dummyData(); // optional starter data

        System.out.println("=== SKYHIGH AIRLINES SYSTEM ===");

        runMainMenu();
    }


    // DUMMY DATA

    private static void dummyData() {

    AirportManager am = airline.getAirportManager();
    FlightManager fm = airline.getFlightManager();
    StaffManager sm = airline.getStaffManager();
    TicketManager tm = airline.getTicketManager();
    BookingManager bm = airline.getBookingManager();


    // AIRPORTS
    
    Airport phx = new Airport("PHX", "Phoenix Sky Harbor", "Phoenix, AZ");
    Airport lax = new Airport("LAX", "Los Angeles International", "Los Angeles, CA");
    Airport las = new Airport("LAS", "Harry Reid International", "Las Vegas, NV");
    Airport dfw = new Airport("DFW", "Dallas Fort Worth International", "Dallas, TX");
    Airport ord = new Airport("ORD", "O'Hare International", "Chicago, IL");

    am.addAirport(phx);
    am.addAirport(lax);
    am.addAirport(las);
    am.addAirport(dfw);
    am.addAirport(ord);


    // AIRCRAFT

    Aircraft a1 = new Aircraft("AC-100", "Boeing 737", 10);
    a1.generateSeats();

    Aircraft a2 = new Aircraft("AC-200", "Airbus A320", 12);
    a2.generateSeats();

    Aircraft a3 = new Aircraft("AC-300", "Boeing 787 Dreamliner", 15);
    a3.generateSeats();


    // FLIGHTS

    Flight f1 = new Flight(
            "AA123",
            phx,
            lax,
            new Terminal("T1", "Terminal 1"),
            new Terminal("T2", "Terminal 2"),
            new Schedule("S1", "08:00", "10:00"),
            new Schedule("S2", "10:30", "12:30"),
            "ON_TIME",
            a1
    );

    Flight f2 = new Flight(
            "DL455",
            lax,
            las,
            new Terminal("T3", "Terminal 3"),
            new Terminal("T4", "Terminal 4"),
            new Schedule("S3", "11:00", "12:00"),
            new Schedule("S4", "12:30", "13:30"),
            "ON_TIME",
            a2
    );

    Flight f3 = new Flight(
            "UA789",
            dfw,
            ord,
            new Terminal("T5", "Terminal 5"),
            new Terminal("T6", "Terminal 6"),
            new Schedule("S5", "14:00", "16:30"),
            new Schedule("S6", "17:00", "19:30"),
            "DELAYED",
            a3
    );

    fm.addFlight(f1);
    fm.addFlight(f2);
    fm.addFlight(f3);


    // STAFF

    Staff s1 = new Staff("Alex Morgan", "alex@skyhigh.com", "S100", "Pilot");
    Staff s2 = new Staff("Jordan Lee", "jordan@skyhigh.com", "S101", "Co-Pilot");
    Staff s3 = new Staff("Taylor Smith", "taylor@skyhigh.com", "S102", "Flight Attendant");

    sm.addStaff(s1);
    sm.addStaff(s2);
    sm.addStaff(s3);

    sm.assignStaffToFlight(s1, f1);
    sm.assignStaffToFlight(s2, f1);
    sm.assignStaffToFlight(s3, f2);


    // PASSENGER BOOKING STUFF

    Passenger p = new Passenger("Chris Johnson", "chris@email.com", "P001", "PASS123");

    Booking b = bm.createBooking(p);

    Ticket t = tm.createTicket(p, f1);
    t.assignSeat(a1.findSeat("1"));

    b.addTicket(t);

    //pre-confirm one booking so system isn't empty
    bm.confirmBooking(b.getBookingID());
        
    }

    // =======================================
    // MAIN MENU
    // =======================================
    private static void runMainMenu() {

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. FLIGHT MANAGEMENT");
            System.out.println("2. BOOK A FLIGHT");
            System.out.println("3. VIEW BOOKINGS");
            System.out.println("4. AIRPORT MANAGEMENT");
            System.out.println("5. PAYMENT (CARD)");
            System.out.println("6. STAFF MANAGEMENT");
            System.out.println("7. SYSTEM REPORT");
            System.out.println("0. EXIT");

            int choice = inputInt();

            switch (choice) {

                case 1 -> flightMenu();
                case 2 -> bookingMenu();
                case 3 -> viewBookings();
                case 4 -> airportMenu();
                case 5 -> paymentMenu();
                case 6 -> staffMenu();
                case 7 -> systemReport();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    // =======================================
    // FLIGHT MENU
    // =======================================
    private static void flightMenu() {

        FlightManager fm = airline.getFlightManager();
        AirportManager am = airline.getAirportManager();

        System.out.println("\n--- FLIGHT MENU ---");
        System.out.println("1. View Flights");
        System.out.println("2. Add Flight");
        System.out.println("3. Remove Flight");
        System.out.println("4. Select Flight");

        int choice = inputInt();

        switch (choice) {

            case 1 -> {
                for (Flight f : fm.getAllFlights()) {
                    System.out.println(f.getFlightDetails());
                }
            }

            case 2 -> {

                System.out.print("Flight number: ");
                String fn = scanner.nextLine();

                System.out.print("Origin airport code: ");
                Airport origin = am.findAirport(scanner.nextLine());

                System.out.print("Destination airport code: ");
                Airport dest = am.findAirport(scanner.nextLine());

                if (origin == null || dest == null) {
                    System.out.println("Invalid airport(s).");
                    return;
                }

                Aircraft aircraft = new Aircraft("AC-" + fn, "Airbus A320", 10);
                aircraft.generateSeats();

                Flight f = new Flight(
                        fn,
                        origin,
                        dest,
                        new Terminal("T1", "T1"),
                        new Terminal("T2", "T2"),
                        new Schedule("SCH", "09:00", "11:00"),
                        new Schedule("SCH2", "11:30", "13:30"),
                        "ON_TIME",
                        aircraft
                );

                fm.addFlight(f);
                System.out.println("Flight added.");
            }

            case 3 -> {
                System.out.print("Flight number: ");
                boolean removed = fm.removeFlight(scanner.nextLine());
                System.out.println(removed ? "Removed." : "Not found.");
            }

            case 4 -> {
                System.out.print("Select flight number: ");
                selectedFlight = fm.findFlight(scanner.nextLine());
                System.out.println(selectedFlight != null ? "Selected!" : "Not found.");
            }
        }
    }

    // =======================================
    // BOOKING MENU
    // =======================================
    private static void bookingMenu() {

        if (selectedFlight == null) {
            System.out.println("Select a flight first.");
            return;
        }

        System.out.print("Passenger name: ");
        Passenger p = new Passenger(scanner.nextLine(), "email@test.com",
                "P-" + System.currentTimeMillis(), "PASS");

        BookingManager bm = airline.getBookingManager();
        TicketManager tm = airline.getTicketManager();

        activeBooking = bm.createBooking(p);

        System.out.println("Available seats: " + selectedFlight.getAvailableSeats());

        System.out.print("Enter seat number: ");
        String seatNum = scanner.nextLine();

        Seat seat = selectedFlight.getAircraft().findSeat(seatNum);

        if (seat == null || !seat.isSeatAvailable()) {
            System.out.println("Invalid seat.");
            return;
        }

        Ticket ticket = tm.createTicket(p, selectedFlight);
        ticket.assignSeat(seat);

        activeBooking.addTicket(ticket);

        System.out.println("Booking created. STATUS = PENDING");
        System.out.println("Proceed to PAYMENT.");
    }

    // =======================================
    // VIEW BOOKINGS
    // =======================================
    private static void viewBookings() {

        for (Booking b : airline.getBookingManager().getAllBookings()) {
            System.out.println("\nBooking: " + b.getBookingID() + " | " + b.getStatus());

            for (Ticket t : b.getTickets()) {
                System.out.println(" - " + t.getTicketDetails());
            }
        }
    }

    // =======================================
    // AIRPORT MENU
    // =======================================
    private static void airportMenu() {

        AirportManager am = airline.getAirportManager();

        System.out.println("\n--- AIRPORT MENU ---");
        System.out.println("1. View Airports");
        System.out.println("2. Add Airport");
        System.out.println("3. Remove Airport");

        int choice = inputInt();

        switch (choice) {

            case 1 -> {
                for (Airport a : am.getAirports()) {
                    System.out.println(a.getAirportInfo());
                }
            }

            case 2 -> {
                System.out.print("Code: ");
                String code = scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Location: ");
                String loc = scanner.nextLine();

                am.addAirport(new Airport(code, name, loc));
            }

            case 3 -> {
                System.out.print("Code: ");
                System.out.println(am.removeAirport(scanner.nextLine())
                        ? "Removed"
                        : "Not found");
            }
        }
    }

    // =======================================
    // PAYMENT (CARD ONLY)
    // =======================================
    private static void paymentMenu() {

        if (activeBooking == null) {
            System.out.println("No active booking.");
            return;
        }

        System.out.print("Enter card amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Payment payment = new Payment(activeBooking, amount, "CARD");

        boolean success = payment.processPayment();

        if (success) {
            payment.updateStatus("COMPLETED");

            airline.getBookingManager()
                    .confirmBooking(activeBooking.getBookingID());

            System.out.println("PAYMENT SUCCESSFUL → BOOKING CONFIRMED");
        } else {
            payment.updateStatus("FAILED");
            System.out.println("PAYMENT FAILED → BOOKING STILL PENDING");
        }

        System.out.println(payment.getPaymentDetails());
    }

    // =======================================
    // STAFF MENU
    // =======================================
    private static void staffMenu() {

        StaffManager sm = airline.getStaffManager();

        System.out.println("\n--- STAFF MENU ---");
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Assign Staff to Selected Flight");

        int choice = inputInt();

        switch (choice) {

            case 1 -> {
                for (Staff s : sm.getAllStaff()) {
                    System.out.println(s.getStaffID() + " | " + s.getName() + " | " + s.getRole());
                }
            }

            case 2 -> {
                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Contact: ");
                String contact = scanner.nextLine();

                System.out.print("ID: ");
                String id = scanner.nextLine();

                System.out.print("Role: ");
                String role = scanner.nextLine();

                sm.addStaff(new Staff(name, contact, id, role));
            }

            case 3 -> {
                System.out.print("Staff ID: ");
                boolean removed = sm.removeStaff(scanner.nextLine());
                System.out.println(removed ? "Removed" : "Not found");
            }

            case 4 -> {

                if (selectedFlight == null) {
                    System.out.println("Select a flight first.");
                    return;
                }

                System.out.print("Staff ID: ");
                Staff s = sm.findStaff(scanner.nextLine());

                if (s == null) {
                    System.out.println("Not found.");
                    return;
                }

                sm.assignStaffToFlight(s, selectedFlight);
                System.out.println("Assigned to flight.");
            }
        }
    }

    // =======================================
    // SYSTEM REPORT FOR EXISTING INFO AND DETAILS
    // =======================================
    private static void systemReport() {

        System.out.println("\n========== SYSTEM REPORT ==========");

        FlightManager fm = airline.getFlightManager();
        BookingManager bm = airline.getBookingManager();
        StaffManager sm = airline.getStaffManager();
        AirportManager am = airline.getAirportManager();

        System.out.println("\n--- FLIGHTS ---");
        for (Flight f : fm.getAllFlights()) {
            System.out.println(f.getFlightDetails());
        }

        System.out.println("\n--- BOOKINGS ---");
        for (Booking b : bm.getAllBookings()) {
            System.out.println(b.getBookingID() + " | " + b.getStatus());

            for (Ticket t : b.getTickets()) {
                System.out.println(" - " + t.getTicketDetails());
            }
        }

        System.out.println("\n--- STAFF ---");
        for (Staff s : sm.getAllStaff()) {
            System.out.println(s.getStaffID() + " | " + s.getName() + " | " + s.getRole());
        }

        System.out.println("\n--- AIRPORTS ---");
        for (Airport a : am.getAirports()) {
            System.out.println(a.getAirportInfo());
        }

        System.out.println("\n==================================");
    }

    // ─────────────────────────────────────────────
    private static int inputInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}