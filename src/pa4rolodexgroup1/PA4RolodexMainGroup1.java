package pa4rolodexgroup1;

public class PA4RolodexMainGroup1 {

    /**
     * Authors Cole Brandon and Jacob Frankart
     * Main function
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException 
    {
        Rolodex model = new Rolodex();
        PA4RolodexViewGroup1 view =  new PA4RolodexViewGroup1();
        PA4RolodexControllerGroup1 controller;
        controller = new PA4RolodexControllerGroup1(view, model);
    }//End public static void main()
}//End class PA4RolodexMainGroup1
