
import java.util.ArrayList;
import java.util.List;

public class PassengerManager {

    private ArrayList<Passenger> passengers = new ArrayList<>();


    // CREATE
    public void addPassenger(Passenger p) {
        if (p != null && findPassenger(p.getPassengerID()) == null) {
            passengers.add(p);
        }
    }


    // READ (by ID)
    public Passenger findPassenger(String passengerID) {
        for (Passenger p : passengers) {
            if (p.getPassengerID().equalsIgnoreCase(passengerID)) {
                return p;
            }
        }
        return null;
    }


    // READ (all)
    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }


    // UPDATE NAME
    public boolean updateName(String passengerID, String newName) {
        Passenger p = findPassenger(passengerID);
        if (p != null) {
            p.setName(newName);
            return true;
        }
        return false;
    }


    // UPDATE CONTACT
    public boolean updateContact(String passengerID, String newContact) {
        Passenger p = findPassenger(passengerID);
        if (p != null) {
            p.setContactInfo(newContact);
            return true;
        }
        return false;
    }


    // DELETE
    public boolean removePassenger(String passengerID) {
        return passengers.removeIf(p -> p.getPassengerID().equalsIgnoreCase(passengerID));
    }
}
