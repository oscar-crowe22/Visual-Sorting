import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Class is a specific implementation for the insertion sort algorithm
 * 
 * Allows you to visually see the changes occuring as the algorithim conducts its
 * sorting procedure
 * 
 * @Author Oscar Crowe
 * 
 */
public class InsertionSort extends JPanel implements ActionListener{

    int[] arr;
    int defualtWidth = 500;
    int defualtHeight = 500;
    int scaleFactor = 4;
    boolean sortMode = false;
    JButton insertButton;
    boolean isSorting;
    int arrayWalker;
    int free;

    /**
     * Main constructor for class, sets the button listener values aswell as initializing the array and filling it
     * @param insertButton, button that sets of sorting proccess
     */
    public InsertionSort(JButton insertButton){
        this.insertButton = insertButton;
        insertButton.addActionListener(this);
        setPreferredSize(new Dimension(defualtWidth, defualtHeight));
        arr = new int[100];
        fill(arr);
    }

    /**
     * method that paints the updated array onto the panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        if(sortMode == false){
            int xPos = 0;
            int buffer = 2;
            int width = (defualtWidth / arr.length) - 2;
            for(int i = 0; i < arr.length; i++){
                g.fillRect(xPos, defualtHeight - (arr[i] * scaleFactor), width, (arr[i] * scaleFactor));
                xPos += buffer + width;
            }
            return;
        }
        if(sortMode == true){
            g.setColor(Color.GREEN);
            int xPos = 0;
            int buffer = 2;
            int width = (defualtWidth / arr.length) - 2;
            for(int i = 0; i < arr.length; i++){
                if(i > arrayWalker){
                    g.setColor(Color.RED);
                }else if(i == free){
                    g.setColor(Color.MAGENTA);
                }
                g.fillRect(xPos, defualtHeight - (arr[i] * scaleFactor), width, (arr[i] * scaleFactor));
                xPos += buffer + width;
                g.setColor(Color.GREEN);
            }
            return;
        }
    }

    /**
     * Sort method conducts the insertion sort algorithm 
     */
    public void sort(){
        sortMode = true;
        arrayWalker = 1;
        int temp;
        while(arrayWalker < arr.length){
            free = arrayWalker;
            temp = arr[arrayWalker];
            for(int i = arrayWalker - 1; i > -1; i--){
                if(arr[i] > temp){
                    arr[free] = arr[i];
                    free = i;

                }
            }
            arr[free] = temp;
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayWalker += 1; 
        }
    }

    /**
     * Fills the array with random values from 0 - 100
     * @param arr, array to be filled
     */
    private void fill(int[] arr) {
        Random rand = new Random();
        for(int i = 0; i < arr.length; i ++){
            arr[i] = rand.nextInt(100);
        }
    }

    /**
     * Once the button is pressed two seperate threads are ran to 
     * execute the repainting and sorting concurrently, this allows for 
     * live updates to occur
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            Thread sortingThread = new Thread(() -> {
            isSorting = true;
            sort();
            isSorting = false;
        });
        sortingThread.start();
        Thread repaintingThread = new Thread(() -> {
            while (isSorting == true) {
                repaint();
            }
        });
        repaintingThread.start();
        }
    }
}