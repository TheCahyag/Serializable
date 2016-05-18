/**
 * File: ForumGUI.java
 * ForumGUI: TODO
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Date;

/**
 * ForumGUI sets up the GUI to enter information for the text block
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */
public class ForumGUI extends Application implements Serializable {
    private final int textWidth = 200;

    private Stage window;
    private Scene scene;
    private BorderPane borderPane;
    private VBox left, right;
    private HBox buttons;
    public TextField nameBox, mapBox, teamPlayersBox, tierBox, tierLimitBox;
    private Label name, map, teamPlayers, tier, tierLimit;
    private Button save, load, close;

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
        close = new Button("Close");

        //Layout init
        left = new VBox(5, name, nameBox, map, mapBox, teamPlayers, teamPlayersBox, tier, tierBox, tierLimit, tierLimitBox);
        right = new VBox(5);
        buttons = new HBox(10, save, load, close);
        scene = new Scene(borderPane, 500, 500);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane.setLeft(left);
        borderPane.setRight(right);
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

    private void setAlignment(){
        left.setAlignment(Pos.TOP_CENTER);
        right.setAlignment(Pos.TOP_CENTER);
        buttons.setAlignment(Pos.CENTER);
    }

    private void setSpacing(){
        borderPane.setPadding(new Insets(0, 10, 5, 10));
        left.setPadding(new Insets(5, 5, 5, 5));
        right.setPadding(new Insets(5, 5, 5, 5));
        nameBox.setPrefWidth(textWidth);
        mapBox.setPrefWidth(textWidth);
        teamPlayersBox.setPrefWidth(textWidth);
        tierBox.setPrefWidth(textWidth);
        tierLimitBox.setPrefWidth(textWidth);

        //Buttons
        save.setPrefWidth(100);
        load.setPrefWidth(100);
        close.setPrefWidth(100);
    }

    private void setButtonActions(){
        save.setOnAction(event -> {
            save();
        });
        load.setOnAction(event -> {

        });
        close.setOnAction(event -> window.close()); // FIXME: 5/14/2016
    }

    public void onClose(){
        window.close();
    }

    public String getNameBox() {
        return nameBox.getText();
    }
    public String getStuff(){
        return "Test Stuff";
    }

    private void save(){
        RunGUI gui = new RunGUI(this);
        Thread backGround = new Thread(gui);
        backGround.start();
        System.out.println("Got to save");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
