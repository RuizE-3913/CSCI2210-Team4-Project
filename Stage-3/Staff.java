
import java.util.List;
/**
 * Represents a staff member in the system.
 * Extends Person with staff-specific attributes and behaviors.
 * @author Joseph Heifner
 */
public class Staff extends Person {
    
    private String staffID;
    private String role;

    /**
     * Constructs a Staff member.
     * 
     * @param name the staff member's name
     * @param contactInfo the contact information
     * @param staffID the staff ID
     * @param role the staff role
     */
    public Staff(String name, String contactInfo, String staffID, String role) {
        super(name, contactInfo);
        this.staffID = staffID;
        this.role = role;
    }

    /**
     * Returns the staff member's schedule.
     * 
     * @param manager the StaffManager used to retrieve all staff assignments to search for
     * @return the schedule
     */
    public Schedule viewSchedule(StaffManager manager) {
        Schedule mySchedule = new Schedule("SCHEDULE-" + this.getStaffID(), "DEPARTURE TIME", "ARRIVAL TIME");
        
        List<StaffAssignment> assignments = manager.getAllAssignments();
        
        for (StaffAssignment a : assignments) {
            if (a.getCrewMembers().contains(this)) {  // check if staff member is on the flight
                mySchedule.addFlight(a.getFlight());   // add flight from StaffAssignment to schedule
            }
        }
        return mySchedule;
    }

    /**
     * Returns the staff ID.
     * 
     * @return the staff ID
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Returns the staff role.
     * 
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the staff role.
     * 
     * @param role the staff member's role
     */
    public void setRole(String role) {
        this.role = role;
    }
    
}
