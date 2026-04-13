


/**
 * Abstract base class representing a person. 
 * Contains shared attributes for Passengers and Staff.
 * @author Joseph Heifner
 */
public class Person {
    
    private String name;
    private String contactInfo;
    
    /**
     * Constructs a Person with a name and contact information.
     * 
     * @param name the person's name
     * @param contactInfo the person's contact information
     */
    
    public Person(String name, String contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    /**
     * Returns the person's name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the person's contact information.
     * 
     * @return the contact information
     */
    public String getContactInfo(){
        return contactInfo;
    }
    
    /**
     * Updates the person's contact information.
     * 
     * @param newInfo the new contact information
     */
    public void updateContactInfo(String newInfo) {
        this.contactInfo = newInfo;
    }
}
