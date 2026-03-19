package pa4rolodexgroup1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.*;

/**
 *
 * @authors Cole Brandon and Jacob Frankart
 */
public class PhoneNumbers implements Serializable
{
    //*****Private Instance Variables
    ArrayList<String> PhoneNumbersArray;
    
    
    /**
     * Declares instance of the Phone Numbers Class
     */
    public PhoneNumbers()
    {
        PhoneNumbersArray = new ArrayList<String>();
    }//end public PhoneNumbers
    
    /**
     * Returns the complete array of Phone Numbers
     * @return 
     */
    public ArrayList<String> getPhoneNumbersArray()
    {
        return PhoneNumbersArray;
    }//End public getPhoneNumbersArray
    
    /**
     * Takes an input of a phone number in any format
     * Removes all non-digit characters
     * Formats the phone number in our preferred format
     * @param phoneNumber 
     */
    public void addPhoneNumber(String phoneNumber)
    {
        String tempPhoneNumber = phoneNumber;
        tempPhoneNumber = tempPhoneNumber.replaceAll("[^\\d]", "");
        tempPhoneNumber = tempPhoneNumber.replaceFirst("(\\d{0})(\\d{3})(\\d{3})(\\d{4})", "$1($2) $3-$4");
        PhoneNumbersArray.add(tempPhoneNumber);
    } //End public void addPhoneNumber
    
    /**
     * @return the size of the PhoneNumbersArray
     */
    public int getPhoneNumbersArraySize()
    {
        return PhoneNumbersArray.size();
    }//End public int getPhoneNumbersArraySize

    /**
     * Returns the entire Phone Numbers Array as a string
     * @return temp
     */
    @Override
    public String toString()
    {
        String temp = "";
        
        for (int i = PhoneNumbersArray.size() - 1; i >= 0; i--) 
        {
        String phoneNumber = PhoneNumbersArray.get(i);
        temp += phoneNumber + "\n";
        }
        
        return temp;
    }//End public String toString()
}//End public class phoneNumbers