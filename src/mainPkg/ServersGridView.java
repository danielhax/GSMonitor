package mainPkg;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by DANIEL on 09/07/2016.
 */
public class ServersGridView extends GridPane{

    public ServersGridView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/serverGrid.fxml"));

        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}