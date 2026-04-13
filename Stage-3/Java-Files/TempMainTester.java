

import java.util.*;


// MAIN TESTER

public class TempMainTester {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

// initial information


        Airline airline = new Airline("SkyHigh Airlines");
        AirportManager airportManager = airline.getAirportManager();
        StaffManager staffManager = airline.getStaffManager();
        BookingManager bookingManager = airline.getBookingManager();
        TicketManager ticketManager = airline.getTicketManager();

        Airport phx = new Airport("PHX", "Phoenix Sky Harbor", "Phoenix, AZ, USA");
        Airport lax = new Airport("LAX", "Los Angeles International", "Los Angeles, CA, USA");
        airportManager.addAirport(phx);
        airportManager.addAirport(lax);

        Terminal t4 = new Terminal("T4", "Terminal 4");
        Terminal t5 = new Terminal("T5", "Terminal 5");

        Aircraft a320 = new Aircraft("A320-001", "Airbus A320", 180);
        a320.generateSeats();

        Schedule schDep = new Schedule("SCH-001", "2026-04-15 08:00", "2026-04-15 10:30");
        Schedule schArr = new Schedule("SCH-002", "2026-04-15 11:00", "2026-04-15 13:30");

        Flight flightAA123 = new Flight("AA123", phx, lax, t4, t5, schDep, schArr, "ON_TIME", a320);

        FlightAssignment fa = new FlightAssignment("FL-ASSIGN-AA123", flightAA123, a320);
        flightAA123.setFlightAssignment(fa);

        Staff captain = new Staff("Captain Elena Ruiz", "elena.ruiz@skyhigh.com", "S-001", "Pilot");
        Staff attendant = new Staff("Maria Lopez", "maria.lopez@skyhigh.com", "S-002", "Flight Attendant");

        Passenger john = new Passenger("John Heffner", "john.h@email.com", "P-1001", "US123456789");
        Passenger eduardo = new Passenger("Eduardo Ceh-Varela", "eduardo.v@email.com", "P-1002", "CN987654321");

        staffManager.addStaff(captain);
        staffManager.addStaff(attendant);

        staffManager.assignStaffToFlight(captain, flightAA123);
        staffManager.assignStaffToFlight(attendant, flightAA123);

        Booking booking1 = bookingManager.createBooking(john);

// MENU

        while (running) {
            System.out.println("\n=== AIRLINE MENU ===");
            System.out.println("1. View Flight Details");
            System.out.println("2. View Staff Schedule");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Passenger Tickets");
            System.out.println("5. Make Payment");
            System.out.println("6. Quit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.println("\n--- FLIGHT DETAILS ---");
                    System.out.println(flightAA123.getFlightDetails());
                    System.out.println("Available seats: " + flightAA123.getAvailableSeats());
                    break;

                case "2":
                    System.out.println("\n--- STAFF SCHEDULES ---");

                    System.out.println("Captain:");
                    Schedule cs = captain.viewSchedule(staffManager);
                    System.out.println(cs.getScheduleInfo());

                    System.out.println("\nAttendant:");
                    Schedule as = attendant.viewSchedule(staffManager);
                    System.out.println(as.getScheduleInfo());
                    break;

                case "3":
                    System.out.println("\n--- BOOK TICKET ---");
                    System.out.println("1. John");
                    System.out.println("2. Eduardo");
                    String pChoice = scanner.nextLine();

                    Passenger selectedPassenger = (pChoice.equals("2")) ? eduardo : john;

                    Ticket newTicket = ticketManager.createTicket(selectedPassenger, flightAA123);

// simple seat assignment
                    int nextSeatIndex = booking1.getTickets().size();
                    Seat seat = a320.getSeats().get(nextSeatIndex);
                    newTicket.assignSeat(seat);

                    booking1.addTicket(newTicket);

                    System.out.println("Ticket booked for " + selectedPassenger.getName());
                    break;

                case "4":
                    System.out.println("\n--- VIEW TICKETS ---");
                    System.out.println("1. John");
                    System.out.println("2. Eduardo");
                    String vChoice = scanner.nextLine();

                    Passenger viewPassenger = (vChoice.equals("2")) ? eduardo : john;

                    ArrayList<Ticket> tickets = viewPassenger.viewTickets(ticketManager);
                    for (Ticket t : tickets) {
                        System.out.println(t.getTicketDetails());
                    }
                    break;

                case "5":
                    System.out.println("\n--- PAYMENT ---");
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    Payment payment = new Payment(booking1, amount, "CREDIT_CARD");
                    System.out.println(payment.getPaymentDetails());
                    break;

                case "6":
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
