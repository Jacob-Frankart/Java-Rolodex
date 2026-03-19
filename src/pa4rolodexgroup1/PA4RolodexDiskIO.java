/*
 * CPS 311
 * Fall 2024
 * Dr. Close
 * 
 * Copyright © 2024 Daryl Close 
 * This work is licensed under the Creative Commons 
 * Attribution-Noncommercial-NoDerivatives 4.0 International Public License. 
 * To view a copy of this license, visit 
 * https://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to 
 * Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 
 * 94105, USA.  
 */

package pa4rolodexgroup1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File name:     PA4RolodexDiskIO.java
 * Description:   Class for serializing and de-serializing a Rolodex object.
 * <p>
 * @author Daryl Close dclose@heidelberg.edu 
 * @version 21-Oct-2024
 */
public class PA4RolodexDiskIO implements Serializable 
{
    //-------static fields-------
    
    private static String fileName = "pa4-rolodex.ser";
    
    //-------static methods-------
    
    /**
     * Class method to write the Rolodex to the disk.
     * <p>
     * @param theRolodex the Rolodex
     * @return the status of the write method
     */
    public static String writeRolodex(Rolodex theRolodex) 
    {
        String writeStatus;
        try
        {
            ObjectOutputStream outFile 
                    = new ObjectOutputStream(new FileOutputStream(fileName));
            outFile.writeObject(theRolodex);
            outFile.close();
            writeStatus = "Serialized Map data have been saved to the"
                              + " disk in pa4-rolodex.ser";
            return writeStatus;
        }        
        catch(IOException e)
        {
            //e.printStackTrace(); // Not recommended for production code
            Logger.getLogger(PA4RolodexDiskIO.class
                                             .getName())
                                             .log(Level.SEVERE, null, e);
            writeStatus = "Error writing to file " + fileName;
            
            return writeStatus;
        }

    }//End public static void writeRolodex(Rolodex)
    
    /**
     * Class method to read the Rolodex from the disk.
     * <p>
     * @return the Rolodex
     */
    public static Rolodex readRolodex() throws ClassNotFoundException 
    {
        Rolodex theRolodex = null;
        
        try
        {
            ObjectInputStream inFile 
                    = new ObjectInputStream(new FileInputStream(fileName));
            theRolodex = (Rolodex)inFile.readObject(); 
            inFile.close();
        }        
        catch(IOException e)
        {
//            System.out.println("Error reading file " + fileName);        
//            System.out.println("Rolodex from disk is null: " 
//                               + theRolodex.equals(null));
//            e.printStackTrace(); // Not recommended for production code
            Logger.getLogger(PA4RolodexDiskIO.class
                                             .getName())
                                             .log(Level.SEVERE, null, e);
            System.exit(1);
        }
        
        return theRolodex;
        
    }//End  public static Rolodex readRolodex()
    
    /**
     * Class method to check for the Rolodex file on the disk.
     * <p>
     * @return true if Rolodex file is not found, otherwise false
     */
    public static boolean fileNotFound()
    {             
        try
        {
            ObjectInputStream inFile 
                    = new ObjectInputStream(new FileInputStream(fileName));
            return false;
        }
        
        catch(Exception e)
        {
            return true;
        }
        
    }//End public static boolean fileNotFound()
    
    /**
     * Getter for the fileName variable
     * @return the fileName
     */
    public static String getFileName()
    {
        return fileName;
    }
     
}//End public class PA4RolodexDiskIO




