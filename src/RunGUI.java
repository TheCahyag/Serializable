import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by brand on 5/14/2016.
 */
public class RunGUI implements Runnable {
    ForumGUI gui;

    public RunGUI(ForumGUI gui){
        this.gui = gui;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("example.txt", "UTF-8");
            writer.println(new Date());
            System.out.println(gui.nameBox.getText());
            writer.println(gui.nameBox.getText());
            writer.println(gui.mapBox.getText());
            writer.println(gui.tierLimitBox.getText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.close();
        System.out.println("got to the end of run");
    }
}
