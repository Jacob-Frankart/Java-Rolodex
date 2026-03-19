package pa4rolodexgroup1;

import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author Cole Brandon and Jacob Frankart
 */
public class PA4RolodexControllerGroup1
{
    //Private instance variables
    private PA4RolodexViewGroup1 view;
    private Rolodex model;
    private PA4RolodexDataEntry dataEntry;
    
    /**
     * Constructor to set the view and model classes, as well as initialize the
     * dataEntry class and begin the menu.
     * @param view the view class
     * @param model the model class
     * @throws ClassNotFoundException 
     */
    public PA4RolodexControllerGroup1(
            PA4RolodexViewGroup1 view, 
            Rolodex model) throws ClassNotFoundException
    {
        this.view = view;
        this.model = model;
        dataEntry = new PA4RolodexDataEntry();
        menuStartUp();
    }//End PA4RolodexControllerGroup1()

    /**
     * Calls the menuInputAndDisplay method and selects the menu option of 
     * Open, Display, Create, Delete, Help, About, and Quit based off of 
     * user input.
     * @throws ClassNotFoundException 
     */
    public void menuStartUp() throws ClassNotFoundException
    {
        String menuChoice;
        do
        {
            menuChoice = menuInputAndDisplay();
            
            switch(menuChoice)
            {
                case "O": openRolodex(); break;
                case "D": displayRolodex(); break;
                case "E": createNewContact(); break;
                case "L": deleteContact(); break;           
                case "H": help(); break;
                case "A": about(); break;     
                default : ;
            }//end switch   
        }
        while(!menuChoice.equalsIgnoreCase("Q"));
            
            saveAndQuitRolodex();
    }//End menuStartUp()
    
    /**
     * Displays the menu and receives user input 
     * based off of valid menu options.
     * @return String representation of user input
     */
    public String menuInputAndDisplay()
    {
        String validChoices = "OoDdEeLlHhAaQq";
        String keyboardInput;
        
        do
        {
            view.displayMenu(Prompt.Menu.getMessage());
            //view.getChoice(Prompt.Choice.getMessage());
            keyboardInput = dataEntry.getStringInput();
        }
        while(keyboardInput.indexOf(validChoices) == 0);
                
        return keyboardInput.toUpperCase();       
    }//End String menuInputAndDisplay()
    
    /**
     * Opens the Rolodex if the file exists, otherwise raises a 
     * ClassNotFoundException.
     * Rolodex must be opened every time after first use to ensure new contacts
     * are added.
     * @throws ClassNotFoundException 
     */
    public void openRolodex() throws ClassNotFoundException
    {
        if(PA4RolodexDiskIO.fileNotFound())
        {
            view.displayReadStatus("Error reading file " + 
                    PA4RolodexDiskIO.getFileName());
            view.displayReadStatus("Rolodex from the disk is null: true" );
        }
        model = PA4RolodexDiskIO.readRolodex();
        view.displayOpen(Prompt.MenuOpened.getMessage());
    }//End void openRolodex()
    
    /**
     * Displays the current Contacts of the Rolodex in ID order. 
     * Waits for user input to continue back to main menu.
     */
    public void displayRolodex()
    {
        view.displayOpen(model.rolodexStringLayout());
        view.displayContinue(Prompt.Continue.getMessage());
        String response = dataEntry.getStringInput();
    }//End void displayRolodex()

    /**
     * Creates a new contact using user inputs throughout for each field. 
     * Allows user to create multiple contacts at once. All contacts are then
     * added to the Rolodex. 
     * Waits for user input to continue back to main menu.
     */
    public void createNewContact()
    {
        if(!responseYes(Prompt.Contact.getMessage()))
            return;
        
        Integer ID = setIDNumber();
        String firstName = setFirstOrLastName(Prompt.FirstName);
        String lastName = setFirstOrLastName(Prompt.LastName);
        String organization = setOrganizationName();
        PhoneNumbers phoneNumbers = setPhoneNumbers();
        EmailAddresses emailAddresses = setEmailAddresses();
        
        Contact contact = new Contact(ID, firstName, lastName, phoneNumbers, 
                emailAddresses, organization);
        
        model.addContact(contact);
        
        if(responseYes(Prompt.AnotherContact.getMessage()))
        {
            createNewContact();
            return;
        }
        
        view.displayExitingData(Prompt.ExitingData.getMessage());
        view.displayContinue(Prompt.Continue.getMessage());
        String response = dataEntry.getStringInput();
    }//End void createNewContact()
    
    /**
     * Delete contact method is still under development. 
     * Currently displays development message.
     * Waits for user input to continue back to main menu.
     */
    public void deleteContact()
    {
        view.displayDeleteContacts(Prompt.DeleteContact.getMessage());
        view.displayContinue(Prompt.Continue.getMessage());
        String response = dataEntry.getStringInput();        
    }//End void deleteContact()
    
    /**
     * Displays a help message that describes the functionality of each menu
     * option. 
     * Waits for user input to continue back to main menu.
     */
    public void help()
    {
        view.displayHelp(Prompt.Help.getMessage());
        view.displayContinue(Prompt.Continue.getMessage());
        String response = dataEntry.getStringInput();         
    }//End void help()
    
    /**
     * Displays an about message detailing the project's creators and current
     * update history
     */
    public void about()
    {
        view.displayAbout(Prompt.About.getMessage());
        view.displayContinue(Prompt.Continue.getMessage());
        String response = dataEntry.getStringInput();          
    }//End void about()
    
    /**
     * Quits the Rolodex program and allows the option for changes made to the
     * Rolodex to be saved to the Disk.
     */
    public void saveAndQuitRolodex()
    {
        String response;
        String writeStatus;
        do
        {
            view.displaySave(Prompt.Save.getMessage());
            response = dataEntry.getStringInput();
        }
        while(!dataEntry.isValidYesOrNo(response));
        
        if(response.equalsIgnoreCase("Y"))
        {
            writeStatus = PA4RolodexDiskIO.writeRolodex(model);
            view.displayWriteStatus(writeStatus);
        }
        
        view.displayQuit(Prompt.ExitingRolodex.getMessage());
    }//End void saveAndQuitRolodex()  
    
    /**
     * Validation loop that displays the prompt for 
     * a stored user input of an ID number
     * @return Integer representation of the ID
     */
    public Integer setIDNumber()
    {
        Integer idNumber;
        do
        {
            view.displayEnterID(Prompt.ID.getMessage());
            idNumber = dataEntry.getIntegerInput();    
        }
        while(!dataEntry.isValidID(idNumber));

        return idNumber;
    }//End Integer setIDNumber()

    /**
     * Validation loop that displays the prompt for 
     * a stored user input of an String firstName or String lastName
     * Allows for users to confirm name as either correct or incorrect before
     * adding to Contact.
     * @param prompt the Prompt of either inputting a first or last name
     * @return String representation of first or last name depending on prompt
     */
    public String setFirstOrLastName(Prompt prompt)
    {
        String response;
        do
        {
            do
            {
            view.displayEnterFirstName(prompt.getMessage());
            response = dataEntry.getStringInput();   
            }
            while(!dataEntry.isValidName(response));
        }
        while(!responseYes("\nIs \"" + response + "\" correct? (Y/N): "));
        
        return response;
    }//End String setFirstOrLastName()
    
    /**
     * Validation loop that displays the prompt for 
     * a stored user input of an String organizationName.
     * Allows for users to confirm organization as either correct or incorrect
     * before adding to Contact.
     * @return String representation of the organizationName
     */
    public String setOrganizationName()
    {
        String response;
        do
        {
            view.displayEnterFirstName(Prompt.Organization.getMessage());
            response = dataEntry.getStringInput();   
        }
        while(!responseYes("\nIs \"" + response + "\" correct? (Y/N): "));
        
        return response;
    }//End String setOrganizationName()

    /**
     * Validation loop that displays the prompt for 
     * a stored user input of a PhoneNumbers phoneNumbersArray.
     * Allows for 1 to 3 phoneNumbers to be added into the array.
     * Formats numbers into (xxx) xxx-xxxx style.
     * @return PhoneNumbers representation of the phoneNumbersArray
     */
    public PhoneNumbers setPhoneNumbers()
    {
        PhoneNumbers phoneNumbersArray = new PhoneNumbers();
        String phoneString;
        
        do
        {
            view.displayEnterTelephone(Prompt.Telephone.getMessage());
            phoneString = dataEntry.getStringInput();
        }
        while(!dataEntry.isValidPhoneNumber(phoneString));
        phoneNumbersArray.addPhoneNumber(phoneString);
        
        while(phoneNumbersArray.getPhoneNumbersArraySize() <= 2)
        {
            if(!responseYes(Prompt.AnotherTelephone.getMessage()))
                break;
            do
            {
                view.displayEnterTelephone(Prompt.Telephone.getMessage());
                phoneString = dataEntry.getStringInput();
            }
            while(!dataEntry.isValidPhoneNumber(phoneString));
            phoneNumbersArray.addPhoneNumber(phoneString);  
        }  
        return phoneNumbersArray;
    }//End PhoneNumbers setPhoneNumbers()
    
    /**
     * Validation loop that displays the prompt for 
     * a stored user input of an EmailAddresses emailAddressesArray.
     * Allows for 1 to 3 emailAddresses to be added into the array.
     * @return EmailAddresses representation of the emailAddressesArray.
     */
    public EmailAddresses setEmailAddresses()
    {
        EmailAddresses emailAddressesArray = new EmailAddresses();
        String emailString;
        
        do
        {
            view.displayEnterEmailAddress(Prompt.Email.getMessage());
            emailString = dataEntry.getStringInput();
        }
        while(!dataEntry.isValidEmail(emailString)); 
        emailAddressesArray.addEmailAddress(emailString);
        
        while(emailAddressesArray.getEmailAddressesArraySize() <= 2)
        {
            if(!responseYes(Prompt.AnotherEmail.getMessage()))
                break;
            do
            {
                view.displayEnterEmailAddress(Prompt.Email.getMessage());
                emailString = dataEntry.getStringInput();
            }
            while(!dataEntry.isValidEmail(emailString)); 
            emailAddressesArray.addEmailAddress(emailString);
        }
        return emailAddressesArray;
    }//End EmailAddresses setEmailAddresses()
    
    /**
     * Validation loop that displays a prompt for
     * a stored user input to either yes or no.
     * @param prompt The yes or no prompt
     * @return true if user response is yes
     */
    public Boolean responseYes(String prompt)
    {
        String response = "N";

        do
        {
            view.displayStringValidation(prompt);
            response = dataEntry.getStringInput();
        }
        while(!dataEntry.isValidYesOrNo(response));
        
        return response.equalsIgnoreCase("Y");
    }//End Boolean responseYes()
    
    /**
     * Enum defined within the Controller Class to handle Prompts.
     */
    public enum Prompt
    {
        Menu("""
             
                              Rolodex Menu
                                                                                 
                \n(O)pen Rolodex file
                \n(D)isplay Rolodex
                \n(E)nter contacts
                \n(L)Delete a contact
                \n(H)elp
                \n(A)bout
                \n(Q)uit             
                """),
        Help("""
             
                                    Rolodex Help
             
             Menu Command           Explanation
             
             Open Rolodex File:     After the first use, always open the 
                                    existing Rolodex file when you begin the 
                                    program. Otherwise, new contacts will not 
                                    be appended to the file.
             
             Display Rolodex:       Displays the entire Rolodex sorted by 
                                    ID number.
             
             Enter Contacts:        Enter contact information, following the
                                    prompts.
             
             Delete Contact:        this is a delete-and-purge. No recover of
                                    the deleted record is possible.
             
             Help:                  Displays this document.
             
             About:                 Displays version and copyright information.
             
             Quit:                  Quits the program with an optional save.
             """
               ),
        About("""
              This Rolodex is a product of Jacob Frankart and Cole Brandon.
              Last Updated Dec. 12, 2024."""),
        MenuOpened("\nRolodex opened.\n"),
        DeleteContact("\nDelete conact under developement\n"),
        Choice("\nEnter choice: "),        
        Contact("\n\nEnter a contact? (Y/N): "),
        AnotherContact("\nEnter another contact? (Y/N): "),
        ID("\nEnter a 4-digit ID number 1001-9999: "),
        FirstName("\nEnter first name: "),
        LastName("\nEnter last name: "),
        Organization("\nEnter the organization name or \"Home\": "),
        Telephone("\nEnter a 10-digit telephone number in any format: "),
        AnotherTelephone("\nEnter another telephone number? (Y/N): "),
        Email("\nEnter an e-mail address in standard format: "),
        AnotherEmail("\nEnter another e-mail address? (Y/N): "),
        Continue("\n\nPress <Enter> to continue: "),
        Save("Save Rolodex before quitting? (Y/N): "),
        ExitingData("Exiting data entry . . . "),
        ExitingRolodex("\n\nExiting Rolodex program . . . \n");
        
        private final String message;

        /**
         * Constructor for the specific prompt called.
         * @param message prompt message
         */
        Prompt(String message)
        {
            this.message = message;
        }
        
        /**
         * Get method for the String message.
         * @return String representation of message
         */
        public String getMessage()
        {
            return message;
        }
    }//End enum Prompt
}//End PA4RolodexControllerGroup1 Class
