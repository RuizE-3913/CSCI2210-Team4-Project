


import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Manages staff members and their flight assignments.
 * Provides methods to add, remove, and find staff.
 * Also assigns staff to flights.
 * Works closely with StaffAssignment to track crew per flight.
 * 
 * @author Joseph Heifner
 */
public class StaffManager {

    private ArrayList<Staff> staffMembers;

    /**
     * Constructs a new StaffManager with an empty staff list.
     */
    public StaffManager() {
        staffMembers = new ArrayList<>();
    }

    /**
     * Adds a staff member to the system.
     * 
     * @param s the staff member to add
     */
    public void addStaff(Staff s) {
        staffMembers.add(s);
    }

    /**
     * Removes a staff member from the system by staffID.
     * 
     * @param staffID the ID of the staff member to remove
     */
    public void removeStaff(String staffID) {
        Iterator<Staff> iterator = staffMembers.iterator();
        while (iterator.hasNext()) {
            Staff s = iterator.next();
            if (s.getStaffID().equals(staffID)) {
                iterator.remove(); // safe removal
            }
        }
    }

    /**
     * Finds a staff member in the system by staffID.
     * 
     * @param staffID the ID to search for
     * @return the Staff object if found, null otherwise
     */
    public Staff findStaff(String staffID) {
        for (Staff s : staffMembers) {
            if (s.getStaffID().equals(staffID)) {
                return s;
            }
        }
        return null; //not found
    }

    /**
     * Assigns a staff member to a flight.
     * If an assignment for the flight exists, adds the staff member to it.
     * Otherwise, creates a new StaffAssignment for the flight.
     * 
     * @param s the staff member to assign
     * @param f the flight to assign to
     */
    public void assignStaffToFlight(Staff s, Flight f) {
        //find existing assignment for this flight
        for (StaffAssignment sa : StaffAssignment.getAllAssignments()) {
            if (sa.getFlight().equals(f)) {
                sa.assignCrewMember(s);
                return;
            }
        }
        //if no assignment exists, create one
        String newAssignmentID = "ASSIGN-" + f.getFlightNumber() + "-" + s.getStaffID();
        StaffAssignment newAssignment = new StaffAssignment(newAssignmentID, f);
        newAssignment.assignCrewMember(s);
    }

    /**
     * Retrieves the list of staff assigned to a specific flight.
     * 
     * @param f the flight to check
     * @return a list of Staff assigned to the flight, empty if none
     */
    public List<Staff> getStaffForFlight(Flight f) {
        for (StaffAssignment sa : StaffAssignment.getAllAssignments()) {
            if (sa.getFlight().equals(f)) {
                return sa.getCrewMembers();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Returns all staff assignments in the system.
     * Useful for Staff.viewSchedule() method.
     * 
     * @return a list of all StaffAssignment objects
     */
    public List<StaffAssignment> getAllAssignments() {
        return StaffAssignment.getAllAssignments();
    }
}
