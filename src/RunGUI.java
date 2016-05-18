import javafx.application.Application;

/**
 * Created by brand on 5/14/2016.
 */
public class RunGUI implements Runnable {

    @Override
    public void run() {
        Application.launch(ForumGUI.class);
    }
}
