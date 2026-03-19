package pa4rolodexgroup1;
import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author Jacob Frankart
 */
public class PA4RolodexGUIView
{
    /**
     * 
     * No Arg constructor
     */
    public PA4RolodexGUIView()
    {
    }//End PA4RolodexGUIView

    /**
     * Displays to the console the menu layout
     * @param menuLayout
     */
    public void displayMenu(String menuLayout)
    {
        //Rolodex Main Menu Frame
        JFrame frame = new JFrame("Rolodex Main Menu");

        //Open Panel Option
        JPanel openFilePanel = new JPanel();
        openFilePanel.setBackground(Color.LIGHT_GRAY);
        openFilePanel.setLayout(new FlowLayout());
        Button b1 = new Button("O");
        b1.setBounds(50,50,10,10);
        openFilePanel.add(b1);

        //Display Panel Option
        JPanel displayFilePanel = new JPanel();
        displayFilePanel.setBackground(Color.LIGHT_GRAY);
        displayFilePanel.setLayout(new FlowLayout());
        Button b2 = new Button("b");
        b2.setBounds(50,50,10,10);
        openFilePanel.add(b2);

        frame.add(openFilePanel, BorderLayout.LINE_START);
        frame.add(displayFilePanel, BorderLayout.LINE_END);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//End displayMenu
}