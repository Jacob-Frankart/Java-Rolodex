package pa4rolodexgroup1;
        
import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Scanner;

/**
 *
 * @author Cole Brandon and Jacob Frankart
 */
public class Rolodex implements Serializable 
{
    //*****Private Instance Variables
    private TreeMap<Integer, Contact> rolodexTreeMap;
    private Contact sampleOne;
    private Contact sampleTwo;
    
    /**
     * Declares instance of the Rolodex Class
     */
    public Rolodex()
    {
        rolodexTreeMap = new TreeMap<Integer, Contact>();
        PA4RolodexDataEntry dataEntry = new PA4RolodexDataEntry();
    }//End public Rolodex
    
    /**
     * Returns the ID field for each contact
     * @return 
     */
    public Collection<Integer> getContactIDs()
    {
        return rolodexTreeMap.keySet();
    }//end public getContactIDs
    
    /**
     * Takes the contact ID as a parameter and returns the 
     * contact linked to that ID
     * @param contactID
     * @return 
     */
    public Contact getContact(Integer contactID)
    {
        return rolodexTreeMap.get(contactID);
    }//End public Ccontact getContact
    
    /**
     * Returns the entire treeMap that stores our contacts
     * @return 
     */
    public TreeMap<Integer, Contact> getContactTreeMap()
    {
        return rolodexTreeMap;
    }//End public TreeMap getContactTreeMap
    
    
    /**
     * Adds a contact to the Tree Map
     * @param contact 
     */
    public void addContact(Contact contact)
    {
        rolodexTreeMap.put(contact.getPrimaryKey(), contact);
    }//End public void addCcontact
    
    
    /**
     * Constructs the complete formatted string for each contact
     * Also handles moving onto a new line
     * in the case of multiple emails or phone numbers
     * @return 
     */
    public String rolodexStringLayout()
    {
        StringBuilder result = new StringBuilder();
        StringBuilder tempPNumberBuilder;
        StringBuilder tempEMailBuilder;
        Integer tempPKey;
        String tempName;
        String tempPNumber;
        String tempEMail;
        String tempOrg;
        Object[] phoneNumberArray;
        Object[] emailAddressArray;
        Integer maxRows;
        Collection<Integer> contactIDs = getContactIDs();
        
        result.append(String.format("%-25s %-25s %-25s %-30s %-25s", "ID", 
                "Name", "TELEPHONE", "E-MAIL", "ORGANIZATION"));
        result.append("\n");
        
        for(Integer contactID : contactIDs)
        {
            tempPKey = getContact(contactID).getPrimaryKey();
            tempName = getContact(contactID).getLastName() + ", " 
                    + getContact(contactID).getFirstName();
            tempOrg = getContact(contactID).getOrganization();
            
            phoneNumberArray = new String[getContact(contactID).
                    getPhoneNumbers().getPhoneNumbersArraySize()];
            phoneNumberArray = getContact(contactID).getPhoneNumbers()
                    .getPhoneNumbersArray().toArray();
            emailAddressArray = new String[getContact(contactID)
                    .getEmailAddresses().getEmailAddressesArraySize()];
            emailAddressArray = getContact(contactID).getEmailAddresses()
                    .getEmailAddressesArray().toArray();
            
            maxRows = Math.max(phoneNumberArray.length, 
                    emailAddressArray.length);
            for (int i = 0; i < maxRows; i++)
            {
                tempPNumberBuilder = new StringBuilder();
                if (i >= phoneNumberArray.length)
                {
                    tempPNumberBuilder.append("[" + (i + 1) + "]" + "[empty]");
                } else
                {
                    tempPNumberBuilder.append("[" + (i + 1) + "]" 
                            + phoneNumberArray[i]);
                }
                tempPNumber = tempPNumberBuilder.toString();
                
                tempEMailBuilder = new StringBuilder();
                if (i >= emailAddressArray.length)
                {
                    tempEMailBuilder.append("[" + (i + 1) + "]" + "[empty]");
                } else
                {
                    tempEMailBuilder.append("[" + (i + 1) + "]" 
                            + emailAddressArray[i]);
                }
                tempEMail = tempEMailBuilder.toString();
                
                if (i == 0)
                {
                    result.append(
                            String.format("%-25s %-25s %-25s %-30s %-25s", 
                            tempPKey, tempName, tempPNumber, 
                            tempEMail, tempOrg));
                    result.append("\n");
                } else
                {
                    result.append(
                            String.format("%-25s %-25s %-25s %-30s %-25s", 
                            "", "", tempPNumber, tempEMail, ""));
                    result.append("\n");
                }
                
                if (i == maxRows - 1)
                {
                    result.append("\n");
                }
            }
            
        }   
        
        
        String output = result.toString();
        return output;
    }//End public String rolodexStringLayout
}//End public class rolodex