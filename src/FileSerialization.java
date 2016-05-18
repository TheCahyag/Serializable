/**
 * File: FileSerialization.java
 */

import javafx.application.Application;

import java.io.*;

/**
 * FileSerialization TODO
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */
public class FileSerialization {
    private ForumGUI forumGUI;

    public FileSerialization(){
        //RunGUI runGUI = new RunGUI();
    }

    public void main() {

        try {
            //Write object to file
            //File file = new File(forumGUI.getNameBox() + ".txt");
            Application.launch(ForumGUI.class);
            FileOutputStream fos = new FileOutputStream("example.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(forumGUI.getNameBox());
            oos.close();

            //Read object from file
            //FileInputStream fis = new FileInputStream()
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
