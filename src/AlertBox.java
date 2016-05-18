/**
 * File: AlertBox.java
 * @author Brandon Bires-Navel (brn5915@rit.edu)
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox creates a pop-up window and displays a given message
 */
public class AlertBox {
    /**
     * display displays the message in a pop-up window
     * @param message - the message that will be displayed on the AlertBox
     */
    public void display(String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button ok = new Button("Ok");

        ok.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, ok);
        layout.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(5, 0, 0, 0));

        Scene scene = new Scene(layout, 250, 100);
        window.setScene(scene);
        window.setTitle("Attention: ");
        window.setResizable(false);
        window.showAndWait();
    }
}
