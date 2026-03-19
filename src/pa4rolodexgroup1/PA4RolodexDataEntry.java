package pa4rolodexgroup1;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author Cole Brandon and Jacob Frankart
 */
public class PA4RolodexDataEntry
{
    /**
     * No-arg constructor
     */
    public PA4RolodexDataEntry()
    {
    }//End PA4RolodexDataEntry()
    
    /**
     * Batch method to fill TreeMap with Contacts. Used for development.
     * @return TreeMap to be sent to Rolodex class.
     */
//  public TreeMap<Integer, Contact> 
//        batchPopulation(TreeMap<Integer, Contact> treeMap)
//    {
//      EmailAddresses sampleOneEmails = new EmailAddresses();
//        
//      sampleOneEmails.addEmailAddress("jfranka3@heidelberg.edu");
//      sampleOneEmails.addEmailAddress("j.m.frankart@gmail.com");
//        
//      PhoneNumbers sampleOneNumbers = new PhoneNumbers();
//        
//      sampleOneNumbers.addPhoneNumber("4444444444");
//        
//      EmailAddresses sampleTwoEmails = new EmailAddresses();
//        
//      sampleTwoEmails.addEmailAddress("cbrando@heidelberg.edu");
//        
//      PhoneNumbers sampleTwoNumbers = new PhoneNumbers();
//        
//      sampleTwoNumbers.addPhoneNumber("3308881092");
//        
//      Contact sampleOne = new Contact(1001, "Frankart", "Jacob", 
//                                sampleOneNumbers, sampleOneEmails,  
//                                "Heidelberg");
//        
//      Contact sampleTwo = new Contact(1002, "Brandon", "Cole",
//                                        sampleTwoNumbers, sampleTwoEmails,
//                                        "Dennis LoConti");
//      
//      treeMap.put(sampleOne.getPrimaryKey(), sampleOne);
//      treeMap.put(sampleTwo.getPrimaryKey(), sampleTwo);
//        
//      return treeMap;     
//  }//End TreeMap<Integer, Contact> batchPopulation()
    
    /**
     * Scanner object to get String input.
     * @return String representation of input.
     */
    public String getStringInput()
    {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }//End String getStringInput()
    
    /**
     * Scanner object to get Integer Input. Invalid input stores input as 0
     * @return Integer representation of input
     * @throws InputMismatchException 
     */
    public Integer getIntegerInput() throws InputMismatchException
    {
        Scanner keyboard = new Scanner(System.in);
        try
        {
            return keyboard.nextInt();
        }
        catch(InputMismatchException  e)
        {
            return 0;
        }
    }//End Integer getIntegerInput()
    
    /**
     * Validates a String as an alphabetic only name. 
     * 
     * This regex was reproduced from the following source.
     * 
     * TutorialsPoint. 2024. "Java Regular expression to check if a string
     *      contains alphabet." December 6 2024. https://www.tutorialspoint.com
     *      /java-regular-expression-to-check-if-a-string-contains-alphabet.
     * 
     * @param name the String to validate.
     * @return true if string is a valid name.
     */
    public Boolean isValidName(String name)
    {
        String regex = "^[a-zA-Z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(name);
               
        return matcher.matches();
    }

    /**
     * Validates an Integer ID as being within the range of allowed IDs. This
     * range is between 1001 and 9999.
     * @param ID the Integer to validate.
     * @return true if ID is between 1001 and 9999.
     */
    public Boolean isValidID(Integer ID)
    {
        return ID >= 1001 && ID <= 9999;
    }
    
    /**
     * Validates a String as being a valid response to a Yes or No question. 
     * A valid yes is a string "y" and a valid no is a string "n". 
     * Not case sensitive.
     * @param response the string to validate
     * @return true for either a "y" or "n"
     */
    public Boolean isValidYesOrNo(String response)
    {
        return response.equalsIgnoreCase("N") 
                || response.equalsIgnoreCase("Y");
    }
    
    /**
    * Validates a string as a 10-digit NANP telephone number using regular
    * expression pattern matching. The leading digit of the numbering plan
    * area code ("area code") cannot begin with a 0 or 1, nor can the first two
    * digits of the central office code.
    * <p>
    * @author Daryl Close dclose@heidelberg.edu
    * @return true if string is a valid number, false otherwise.
    * @param phoneString string to be converted to a telephone number
    */
    public boolean isValidPhoneNumber(String phoneString)
    {
        String regex = "^\\(?" //Start and match optional left par.
                     + "([2-9]{1})" //Exclude 0 or 1 as first digit
                     + "([0-9]{2})\\)?" //Match 2 digits and optional par.
                     + "[-.\\s]?" //Match optional hyphen, dot, or space
                     + "([2-9]{2})" //Exclude 0 or 1 as first two digits
                     + "([0-9]{1})" //Match third digit
                     + "[-.\\s]?" //Match optional hyphen, dot, or space
                     + "([0-9]{4})$"; //Match 4 digits and stop
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(phoneString);
        
        return matcher.matches();
    }//End public static boolean isValidPhoneNumber(String)
    
    /**
    * Validates a string as an e-mail address using regular expression pattern
    * matching in basic compliance with the RFC 5322 Internet Message
    * Format (IMF) standard. Checks for a local-part, a commercial at sign
    * (@), a domain part. Rejects leading, trailing, and consecutive dots,
    * and limits top level domain length between 2 and 6.
    * <p>
    * Based on Gupta, Lokesh. 2022. "Java Email Validation Using Regex."
    * <a href="https://howtodoinjava.com/java/regex/java-regex-validate
    * -email-address/">https://howtodoinjava.com/java/regex/java-regex-validate
    * -email-address/</a>
    * <p>
    * @author Daryl Close dclose@heidelberg.edu
    * @return true if string is a valid number, false otherwise.
    * @param emailString string to be converted to an e-mail address
    */
    public boolean isValidEmail(String emailString)
    {
        //Basic--allows lots of false positives
        String regex1 = "^(.+)@(.+)$";
        
        //Better, but allows domain names of any length
        String regex2 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+" //Match local-part
                      + "@" //Match at-sign
                      + "[a-zA-Z0-9.-]+$"; //Match domain name
        
        //Best--rejects misplaced dots and TLD parts that are too long
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+"
                     + "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
                     + "@" //Match at-sign
                     + "(?!-)(?:[a-zA-Z0-9-]+\\.)+" //Match LLD chars
                     + "[a-zA-Z]{2,6}$"; //Match TLD 2-6 chars
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        matcher = pattern.matcher(emailString);
        return matcher.matches();
    }//End public static boolean isValidEmail(emailString)
}//End Class PA4RolodexDataEntry
