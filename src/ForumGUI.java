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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * ForumGUI sets up the GUI to enter information for the text block
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */
public class ForumGUI extends Application implements Serializable {
    private final int textWidth = 200;
    private MessageFields messages;

    private Stage window;
    private Scene scene;
    private BorderPane borderPane;
    private VBox mainLayout;
    private HBox buttons;
    public TextField nameBox, mapBox, teamPlayersBox, tierBox, tierLimitBox, teamLinkBox;
    private TextArea generatedResult;
    private Label name, map, teamPlayers, tier, tierLimit, teamLink;
    private Button save, load, generate;

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
        teamLink = new Label("Team Link:");
        teamLinkBox = new TextField();
        generatedResult = new TextArea();
        generatedResult.setPrefSize(200, 100);
        save = new Button("Save");
        load = new Button("Load");
        generate = new Button("Generate");

        //Layout init
        mainLayout = new VBox(5, name, nameBox, map, mapBox, teamPlayers,
                teamPlayersBox, tier, tierBox, tierLimit, tierLimitBox, teamLink, teamLinkBox, generatedResult);
        buttons = new HBox(10, save, load, generate);
        scene = new Scene(borderPane, 300, 500);

        //Setting TextFields
        setTextFields();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane.setCenter(mainLayout);
        borderPane.setBottom(buttons);
        borderPane.getStylesheets().add("resources/style.css");
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
        nameBox.setText(this.messages.name);
        mapBox.setText(this.messages.map);
        teamPlayersBox.setText(this.messages.teamPlayers);
        tierBox.setText(this.messages.tier);
        tierLimitBox.setText(this.messages.tierLimit);
        teamLinkBox.setText(this.messages.teamLink);
        generatedResult.setText(this.messages.generatedResult);
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
        teamLinkBox.setMaxWidth(textWidth);
        generatedResult.setMaxWidth(300);

        //Buttons
        save.setPrefWidth(100);
        load.setPrefWidth(100);
        generate.setPrefWidth(100);
    }

    /**
     * setButtonActions TODO
     */
    private void setButtonActions(){
        save.setOnAction(event -> onSave());
        load.setOnAction(event -> onLoad());
        generate.setOnAction(event -> onGenerate());
    }

    /**
     * onGenerate TODO
     */
    private void onGenerate(){
        String result = "[B][U]" + nameBox.getText() + "[/U][/B]" +
                "\nENTER DATES HERE" +
                "\n" + mapBox.getText() + " " + teamPlayersBox.getText() + "v" + teamPlayersBox.getText() +
                " T" + tierBox.getText() + " (" + tierLimitBox.getText() + " max tier points)" +
                "\nTeam: [URL]" + teamLinkBox.getText() + "[/URL]"+
                "\nWoT Scout: [URL]...[/URL]";
        generatedResult.setText(result);
    }

    /**
     * onSave TODO
     */
    private void onSave(){
        this.messages.name = nameBox.getText();
        this.messages.map = mapBox.getText();
        this.messages.teamPlayers = teamPlayersBox.getText();
        this.messages.tier = tierBox.getText();
        this.messages.tierLimit = tierLimitBox.getText();
        this.messages.teamLink = teamLinkBox.getText();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Safe");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(window);
        if (selectedFile == null){
            return;
        }
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(selectedFile);
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
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
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
            setTextFields();
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

    public static void main(String[] args) {
        Application.launch(args);
    }
}
