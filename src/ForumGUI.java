/**
 * File: ForumGUI.java
 * ForumGUI: TODO
 */

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * ForumGUI sets up the GUI to enter information for the text block
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */
public class ForumGUI extends Application implements Serializable {
    private final int textWidth = 200;
    private boolean loadFromFile;
    private MessageFields messages;

    private Stage window;
    private Scene scene;
    private BorderPane borderPane;
    private VBox mainLayout;
    private HBox buttons;
    public TextField nameBox, mapBox, teamPlayersBox, tierBox, tierLimitBox;
    private Label name, map, teamPlayers, tier, tierLimit;
    private Button save, load;

    public ForumGUI(){
        messages = new MessageFields();
    }

    @Override
    public void init() throws Exception {
        //Component init
        borderPane = new BorderPane();
        name = new Label("Tournament Name:");
        nameBox = new TextField();
        map = new Label("Map Name:");
        mapBox = new TextField();
        teamPlayers = new Label("Number of Players:");
        teamPlayersBox = new TextField();
        tier = new Label("Tier Limit:");
        tierBox = new TextField();
        tierLimit = new Label("Total Tier Points:");
        tierLimitBox = new TextField();
        save = new Button("Save");
        load = new Button("Load");

        //Layout init
        mainLayout = new VBox(5, name, nameBox, map, mapBox, teamPlayers, teamPlayersBox, tier, tierBox, tierLimit, tierLimitBox);
        buttons = new HBox(10, save, load);
        scene = new Scene(borderPane, 300, 500);

        //Setting TextFields
        setTextFields();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane.setCenter(mainLayout);
        borderPane.setBottom(buttons);
        borderPane.getStylesheets().add("style.css");
        borderPane.setId("bp");
        setAlignment();
        setSpacing();
        setButtonActions();
        window = primaryStage;
        window.setTitle("Tournament Creator");
        window.setOnCloseRequest(event -> onClose());
        window.setScene(scene);
        window.show();
    }

    /**
     * setTextFields TODO
     */
    private void setTextFields(){
        nameBox.setText(messages.name);
        mapBox.setText(messages.map);
        teamPlayersBox.setText(messages.teamPlayers);
        tierBox.setText(messages.tier);
        tierLimitBox.setText(messages.tierLimit);
    }

    /**
     * setAlignment TODO
     */
    private void setAlignment(){
        mainLayout.setAlignment(Pos.TOP_CENTER);
        buttons.setAlignment(Pos.CENTER);
    }

    /**
     * setSpacing
     */
    private void setSpacing(){
        borderPane.setPadding(new Insets(0, 10, 5, 10));
        mainLayout.setPadding(new Insets(5, 5, 5, 5));
        nameBox.setMaxWidth(textWidth);
        mapBox.setMaxWidth(textWidth);
        teamPlayersBox.setMaxWidth(textWidth);
        tierBox.setMaxWidth(textWidth);
        tierLimitBox.setMaxWidth(textWidth);

        //Buttons
        save.setPrefWidth(100);
        load.setPrefWidth(100);
    }

    /**
     * setButtonActions TODO
     */
    private void setButtonActions(){
        save.setOnAction(event -> onSave());
        load.setOnAction(event -> onLoad());
    }

    /**
     * onSave TODO
     */
    private void onSave(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("test.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(messages);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * onLoad specifies what to do when 'Load' is pressed
     */
    private void onLoad(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Safe");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serialized Files", "*.ser"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile == null){
            return;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(selectedFile);
            ois = new ObjectInputStream(fis);
            this.messages = (MessageFields) ois.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            AlertBox ab = new AlertBox();
            ab.display("Incorrect file format");
            cnfe.printStackTrace();
            return;
        }finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * onClose specifies what to do when the window is closed
     */
    private void onClose(){
        window.close();
    }
}
