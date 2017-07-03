package mainPkg;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by DANIEL on 11/07/2016.
 */
public class MainView extends HBox{


    public MainView(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/main.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
