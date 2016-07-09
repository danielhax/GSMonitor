package createPanePkg;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by DANIEL on 09/07/2016.
 */
public class SetGrid {

    public SetGrid(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("serverGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}