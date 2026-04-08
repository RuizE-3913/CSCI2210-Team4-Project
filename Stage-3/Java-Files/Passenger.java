


import java.util.ArrayList;

/**
 * Represents a passenger in the system.
 * Extends Person with passenger-specific attributes and behaviors.
 * @author Joseph Heifner
 */
public class Passenger extends Person {
    
    private String passengerID;
    private String passportNumber;

    /**
     * Constructs a Passenger.
     * 
     * @param name the passenger's name
     * @param contactInfo the contact information
     * @param passengerID the passenger ID
     * @param passportNumber the passport number
     */
    public Passenger(String name, String contactInfo, String passengerID, String passportNumber) {
        super(name, contactInfo);
        this.passengerID = passengerID;
        this.passportNumber = passportNumber;
    }

    /**
     * Returns the passenger's tickets.
     * @return a list of tickets
     */
    public ArrayList<Ticket> viewTickets(TicketManager manager) {
        
        ArrayList<Ticket> myTickets = new ArrayList<>();
        for (Ticket t : manager.getAllTickets()){
            if (t.getPassenger().equals(this)){
                myTickets.add(t);
            }
        }
        return myTickets;
    }

    /**
     * Returns the passenger ID.
     * @return the passenger ID
     */
    public String getPassengerID() {
        return passengerID;
    }

    /**
     * Returns the passport number.
     * @return the passport number
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * Updates contact information (optional override).
     * @param newInfo the new contact information
     */
    @Override
    public void updateContactInfo(String newInfo) {
        super.updateContactInfo(newInfo);
    }
}