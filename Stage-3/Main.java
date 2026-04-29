
/**
 * MAIN PROGRAM FOR SKYHIGH AIRLINES MANAGEMENT SYSTEM
 * 
 * This class serves as the primary entry point and interactive console interface
 * for the Airline Management System.
 *  
 * @author Eugene Ruiz
 * @author Joseph Heifner
 * @author Labeeb Md
 * @date 04-29-2026
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

        Aircraft a1 = new Aircraft("AC-100", "Boeing 737", 60);
        a1.generateSeats();

        Aircraft a2 = new Aircraft("AC-200", "Airbus A320", 72);
        a2.generateSeats();

        Aircraft a3 = new Aircraft("AC-300", "Boeing 787 Dreamliner", 90);
        a3.generateSeats();

        Flight f1 = new Flight("AA123", phx, lax,
            new Terminal("T1","Terminal 1"), new Terminal("T2","Terminal 2"),
            new Schedule("S1","08:00","10:00"),
            new Schedule("S2","10:30","12:30"),
            "ON_TIME", a1);

        Flight f2 = new Flight("DL455", lax, las,
            new Terminal("T3","Terminal 3"), new Terminal("T4","Terminal 4"),
            new Schedule("S3","11:00","12:00"),
            new Schedule("S4","12:30","13:30"),
            "ON_TIME", a2);

        Flight f3 = new Flight("UA789", dfw, ord,
            new Terminal("T5","Terminal 5"), new Terminal("T6","Terminal 6"),
            new Schedule("S5","14:00","16:30"),
            new Schedule("S6","17:00","19:30"),
            "DELAYED", a3);

        // Prices
        f1.setPrice(199.99);
        f2.setPrice(149.99);
        f3.setPrice(299.99);

        fm.addFlight(f1);
        fm.addFlight(f2);
        fm.addFlight(f3);

        Staff s1 = new Staff("Alex Morgan","alex@skyhigh.com","S100","Pilot");
        Staff s2 = new Staff("Jordan Lee","jordan@skyhigh.com","S101","Co-Pilot");
        Staff s3 = new Staff("Taylor Smith","taylor@skyhigh.com","S102","Flight Attendant");

        sm.addStaff(s1);
        sm.addStaff(s2);
        sm.addStaff(s3);

        sm.assignStaffToFlight(s1,f1);
        sm.assignStaffToFlight(s2,f1);
        sm.assignStaffToFlight(s3,f2);

        Passenger p = new Passenger("Chris Johnson","chris@email.com","P001","PASS123");

        Booking b = bm.createBooking(p);

        Ticket t = tm.createTicket(p,f1);
        t.assignSeat(a1.findSeat("1A"));

        b.addTicket(t);

        bm.confirmBooking(b.getBookingID());
        
    }

    // =======================================
    // MAIN MENU
    // =======================================
    private static void runMainMenu() {

        while (true) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. FLIGHT MANAGEMENT");
            System.out.println("2. BOOKING MENU");
            System.out.println("3. AIRPORT MANAGEMENT");
            System.out.println("4. PAYMENT (CARD)");
            System.out.println("5. STAFF MANAGEMENT");
            System.out.println("6. SYSTEM REPORT");
            System.out.println("7. PASSENGER MANAGEMENT");
            System.out.println("0. EXIT");

            int choice = inputInt();

            switch (choice) {

                case 1 -> flightMenu();
                case 2 -> bookingMenu();
                case 3 -> airportMenu();
                case 4 -> paymentMenu();
                case 5 -> staffMenu();
                case 6 -> systemReport();
                case 7 -> passengerMenu();
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
        System.out.println("5. Update Flight");

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
            
            case 5 -> {
                System.out.print("Enter flight number to update: ");
                String fn = scanner.nextLine();

                Flight f = fm.findFlight(fn);

                if (f == null) {
                    System.out.println("Flight not found.");
                    return;
                }

                System.out.print("New origin airport code: ");
                Airport origin = am.findAirport(scanner.nextLine());

                System.out.print("New destination airport code: ");
                Airport dest = am.findAirport(scanner.nextLine());

                if (origin == null || dest == null) {
                    System.out.println("Invalid airport(s).");
                    return;
                }

                f.setOrigin(origin);
                f.setDestination(dest);

                System.out.println("Flight updated successfully.");
            }
        }
    }

    // =======================================
    // BOOKING MENU
    // =======================================
    private static void bookingMenu() {

    BookingManager bm = airline.getBookingManager();
    TicketManager tm = airline.getTicketManager();

    System.out.println("\n--- BOOKING MENU ---");
    System.out.println("1. View All Bookings");
    System.out.println("2. Create Booking");
    System.out.println("3. Update Booking");
    System.out.println("4. Cancel Booking");
    System.out.println("0. Back");

    int choice = inputInt();

    switch (choice) {

        // VIEW ALL BOOKINGS
        case 1 -> {

            for (Booking b : bm.getAllBookings()) {
                System.out.println("\nBooking ID: " + b.getBookingID());
                System.out.println("Status: " + b.getStatus());

                Passenger p = b.getPassenger();
                System.out.println("Passenger: " + p.getName());

                for (Ticket t : b.getTickets()) {
                    System.out.println(" - " + t.getTicketDetails());
                }
            }
        }

        // CREATE BOOKING
        case 2 -> {

            if (selectedFlight == null) {
                System.out.println("Select a flight first.");
                return;
            }

            System.out.print("Passenger name: ");
            Passenger p = new Passenger(scanner.nextLine(),
                    "email@test.com",
                    "P-" + System.currentTimeMillis(),
                    "PASS");

            activeBooking = bm.createBooking(p);

            System.out.println("Available seats:");
            selectedFlight.getAircraft().printSeatMap();

            System.out.print("Enter seat (e.g. 12A): ");
            Seat seat = selectedFlight.getAircraft().findSeat(scanner.nextLine());

            if (seat == null || !seat.isSeatAvailable()) {
                System.out.println("Invalid seat.");
                return;
            }

            Ticket ticket = tm.createTicket(p, selectedFlight);
            ticket.assignSeat(seat);

            activeBooking.addTicket(ticket);

            System.out.println("Booking created → PENDING PAYMENT");
        }

        // UPDATE BOOKING
        case 3 -> {

            System.out.print("Enter Booking ID: ");
            String id = scanner.nextLine().trim();
            Booking b = airline.getBookingManager().findBooking(id);

            if (b == null) {
                System.out.println("Booking not found.");
                return;
            }

            System.out.println("1. Update Passenger Name");
            System.out.println("2. Update Seat");
            System.out.println("3. Change Status");

            int updateChoice = inputInt();

            switch (updateChoice) {

                case 1 -> {
                    System.out.print("New passenger name: ");
                    b.getPassenger().setName(scanner.nextLine());
                }

                case 2 -> {
                    Ticket t = b.getTickets().get(0); // 1 ticket per book
                    Flight f = t.getFlight();
                    
                    System.out.print("New seat (e.g. 14B): ");
                    String seatNum = scanner.nextLine();
                    
                    Seat newSeat = f.getAircraft().findSeat(seatNum);

                    if (newSeat == null) {
                        System.out.println("Seat does not exist.");
                        return;
                    }

                    if (!newSeat.isSeatAvailable()) {
                        System.out.println("Seat already taken.");
                        return;
                    }
                    
                    t.assignSeat(newSeat);
                }

                case 3 -> {
                    System.out.print("New status: ");
                    b.setStatus(scanner.nextLine());
                }
            }

            System.out.println("Booking updated.");
        }

        // CANCEL BOOKING
        case 4 -> {

            System.out.print("Enter Booking ID: ");
            Booking b = bm.findBooking(scanner.nextLine());

            if (b == null) {
                System.out.println("Booking not found.");
                return;
            }

            b.cancelBooking();

            System.out.println("Booking cancelled.");
        }

        case 0 -> {
            System.out.println("Returning...");
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
        System.out.println("4. Update Airport");

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
            
            case 4 -> {
                System.out.print("Code: ");
                Airport a = am.findAirport(scanner.nextLine());

                if (a == null) {
                    System.out.println("Not found.");
                    return;
                }

                System.out.print("New name: ");
                a.setName(scanner.nextLine());

                System.out.print("New location: ");
                a.setLocation(scanner.nextLine());

                System.out.println("Airport Updated.");
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

        double total = 0;

        for (Ticket t : activeBooking.getTickets()) {
            total += t.getFlight().getPrice();
        }

        System.out.println("Total Due: $" + total);

        System.out.print("Enter card amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount != total) {
            System.out.println("Incorrect amount.");
            return;
        }

        Payment payment = new Payment(activeBooking, amount, "CARD");

        if (payment.processPayment()) {
            
            payment.updateStatus("COMPLETED");
            Booking b = activeBooking;
            
            b.setStatus("CONFIRMED");
            
            airline.getBookingManager().confirmBooking(b.getBookingID());
            System.out.println("Payment successful. Booking confirmed");
        }   
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
        System.out.println("4. Assign Staff");
        System.out.println("5. Update Staff");

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
            
            case 5 -> {
                System.out.print("Staff ID: ");
                Staff s = sm.findStaff(scanner.nextLine());

                if (s == null) {
                    System.out.println("Not found.");
                    return;
                }

                System.out.print("New name: ");
                s.setName(scanner.nextLine());

                System.out.print("New role: ");
                s.setRole(scanner.nextLine());

                System.out.println("Staff Updated.");
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
    
    // =======================================
    // PASSENGER MENU
    // =======================================
    private static void passengerMenu() {

        BookingManager bm = airline.getBookingManager();

        System.out.println("\n--- PASSENGER MENU ---");
        System.out.println("1. View Passenger Info (by Booking)");
        System.out.println("2. Update Passenger Name");
        System.out.println("3. Update Passenger Contact Info");
        System.out.println("0. Back");

        int choice = inputInt();

        switch (choice) {

            // VIEW PASSENGER INFO
            case 1 -> {

                System.out.print("Enter Booking ID: ");
                String id = scanner.nextLine().trim();
                Booking b = airline.getBookingManager().findBooking(id);

                if (b == null) {
                    System.out.println("Booking not found.");
                    return;
                }

                Passenger p = b.getPassenger();

                System.out.println("\nPassenger Info:");
                System.out.println("Name: " + p.getName());
                System.out.println("Contact: " + p.getContactInfo());
            }

            // UPDATE NAME
            case 2 -> {

                System.out.print("Enter Booking ID: ");
                String id = scanner.nextLine().trim();
                Booking b = airline.getBookingManager().findBooking(id);

                if (b == null) {
                    System.out.println("Booking not found.");
                    return;
                }

                Passenger p = b.getPassenger();

                System.out.print("New Name: ");
                p.setName(scanner.nextLine());

                System.out.println("Passenger name updated.");
            }

            // UPDATE CONTACT INFO
            case 3 -> {

                System.out.print("Enter Booking ID: ");
                String id = scanner.nextLine().trim();
                Booking b = airline.getBookingManager().findBooking(id);

                if (b == null) {
                    System.out.println("Booking not found.");
                    return;
                }

                Passenger p = b.getPassenger();

                System.out.print("New Contact Info (email/phone): ");
                p.setContactInfo(scanner.nextLine());

                System.out.println("Passenger contact updated.");
            }

            case 0 -> {
                System.out.println("Returning...");
            }
        }
    }

    // input method for the scanner
    private static int inputInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
