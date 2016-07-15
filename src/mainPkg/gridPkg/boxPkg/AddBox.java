package mainPkg.gridPkg.boxPkg;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Created by DANIEL on 11/07/2016.
 */
public class AddBox extends BorderPane{
    @FXML
    private Button addButton;

    public AddBox(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBox.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AddDialog();
            }
        });
    }
}
