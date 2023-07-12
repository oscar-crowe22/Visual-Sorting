import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
public class Main{

    private static JButton insertButton;
    private static InsertionSort insertionSort;
    public static void main(String[] args) {

        // Main Frame
        JFrame mainFrame = new JFrame("Sorting Algorithm Visualiser");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating Menus
        JMenuBar mainMenu = new JMenuBar();

        // Test Option For Menu
        insertButton = new JButton("Insertion Sort");
        mainMenu.add(insertButton);

        mainFrame.setJMenuBar(mainMenu);
        // Panel For Display
        insertionSort = new InsertionSort(insertButton);

        // Frame Admin
        mainFrame.getContentPane().add(insertionSort);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}