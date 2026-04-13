


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a staff assignment to a specific flight.
 * Keeps track of the crew members assigned to the flight.
 * Also maintains a static list of all assignments for easy reference
 * @author Joseph Heifner
 */
public class StaffAssignment {

    private String assignmentID;
    private Flight flight;
    private ArrayList<Staff> crewMembers;

    // Static list to store ALL assignments
    private static ArrayList<StaffAssignment> allAssignments = new ArrayList<>();
    
    
    /**
     * Constructs a new StaffAssignment for a given flight.
     * Automatically registers this assignment in the static list of all assignments.
     * @param assignmentID a unique identifier for this assignment
     * @param flight the flight that this assignment applies to
     */
    public StaffAssignment(String assignmentID, Flight flight) {
        this.assignmentID = assignmentID;
        this.flight = flight;
        this.crewMembers = new ArrayList<>();

        allAssignments.add(this); //register this specific assignment
    }

    /**
     * Assigns a staff member to this flight assignment.
     * 
     * @param member the staff member to add to the crew
     */
    public void assignCrewMember(Staff member) {
        crewMembers.add(member);
    }

    /**
     * Removes a staff member from this flight assignment.
     * 
     * @param member the staff member to remove from the crew
     */
    public void removeCrewMember(Staff member) {
        crewMembers.remove(member);
    }

    /**
     * Returns a list of all crew members assigned to this flight.
     * 
     * @return a list of Staff objects
     */
    public List<Staff> getCrewMembers() {
        return crewMembers;
    }
    
    /**
     * Returns the flight associated with this assignment.
     * 
     * @return the Flight object
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Returns a list of all staff assignments in the system.
     * Useful for StaffManager or Staff to retrieve schedules.
     * 
     * @return a list of all StaffAssignment objects
     */
    public static List<StaffAssignment> getAllAssignments() {
        return allAssignments;
    }
}