package pa4rolodexgroup1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Cole Brandon and Jacob Frankart
 */
public class EmailAddresses implements Serializable 
{
    //*****Private Instance Variables
    private ArrayList<String> emailAddressesArray;
    
    /**
     * No-arg constructor
     */
    public EmailAddresses()
    {
        emailAddressesArray = new ArrayList<String>();
    }//End EmailAddresses()
    
    /**
     * Get method for the EmailAddressesArray variable
     * @return emailAddressesArray
     */
    public ArrayList<String> getEmailAddressesArray()
    {
        return emailAddressesArray;
    }//End ArrayList<String> getEmailAddressesArray()
    
    /**
     * Adds a formatted email address to the emailAddressesArray
     * @param emailAddress 
     */
    public void addEmailAddress(String emailAddress)
    {
        emailAddressesArray.add(emailAddress);
    }//End void addEmailAddress()
    
    /**
     * Method to get the current size of the emailAddressesArray
     * @return integer value of the emailAddressesArray
     */
    public int getEmailAddressesArraySize()
    {
        return emailAddressesArray.size();
    }//End int getEmailAddressesArraySize()
    
    /**
     * Formats the emailAddressesArray as a string
     * @return string representation of the emailAddressesArray
     */
    @Override
    public String toString()
    {
        String temp = "";
        
        for (int i = emailAddressesArray.size() - 1; i >= 0; i--) 
        {
        String emailAddress = emailAddressesArray.get(i);
        temp += emailAddress + "\n";
        }
        
        return temp;
    }//End String toString()  
}//End EmailAddresses Class
