package pa4rolodexgroup1;

import java.io.Serializable;
/**
 *
 * @authors Cole Brandon and Jacob Frankart
 */
public class Contact implements 
        Comparable<Contact>, Serializable
{
    private String firstName, lastName, organization;
    private Integer primaryKey;
    private EmailAddresses emailAddresses;
    private PhoneNumbers phoneNumbers;

   /**
    * Sets up this contact with the specified information.
    *
    * @param firstName     a string representation of a first name
    * @param lastName      a string representation of a last name
    * @param phoneNumbers a string representation of a phone number
    * @param organization   a string representation of an organization name
    * @param emailAddresses
    * @param primaryKey      an integer representation of a primary key
    */
   public Contact (Integer primaryKey, String firstName, String lastName, 
           PhoneNumbers phoneNumbers, EmailAddresses emailAddresses, 
           String organization)
   {
       this.primaryKey = primaryKey;
       this.firstName = firstName;
       this.lastName = lastName;
       this.phoneNumbers = phoneNumbers;
       this.emailAddresses = emailAddresses;
       this.organization = organization;
   }//End public Contact()
   
   /**
    * Returns the primary key of the contact.
    * 
    * @return the primary key
    */
   public Integer getPrimaryKey()
   {
       return primaryKey;
   }//End Integer getPrimaryKey()
   
   /**
    * Returns the first name of the contact
    * 
    * @return the first name
    */
   public String getFirstName()
   {
       return firstName;
   }//end String getFirstName()
   
   /**
    * returns the last name of the contact
    * 
    * @return the last name
    */
   public String getLastName()
   {
       return lastName;
   }//end String getLastName()
   
   /**
    * returns the PhoneNumbers object
    * 
    * @return the PhoneNumbers object 
    */
   public PhoneNumbers getPhoneNumbers()
   {
       return phoneNumbers;
   }//end PhoneNumbers getPhoneNumbers()
   
   /**
    * returns the EmailAddresses object
    * 
    * @return the EmailAddresses object 
    */
   public EmailAddresses getEmailAddresses()
   {
       return emailAddresses;
   }//end EmailAddresses getEmailAddresses()
   

   /**
    * returns the organization of the contact
    * 
    * @return the organization
    */
   public String getOrganization()
   {
       return organization;
   }//end String getOrganization()
   
   /**
    * Returns a description of this contact as a string.
    *
    * @return a string representation of this contact
    */
   @Override
   public String toString()
   {
       return primaryKey + " " + lastName + ", " + firstName + "\t" + phoneNumbers 
               + " " + organization;
   }//End String toString()

   /**
    * Uses both last and first names to determine lexical ordering.
    *
    * @param other the contact to be compared to this contact
    * @return      the integer result of the comparison
    */
   @Override
   public int compareTo(Contact other)
   {
       int result;

       if (lastName.equals(other.lastName))
           result = firstName.compareTo(other.firstName);
       else
           result = lastName.compareTo(other.lastName);

       return result;
   }//End int compareTo()
}//End Class Contact 