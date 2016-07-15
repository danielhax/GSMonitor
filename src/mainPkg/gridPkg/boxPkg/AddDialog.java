package mainPkg.gridPkg.boxPkg;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by DANIEL on 16/07/2016.
 */
public class AddDialog extends TextInputDialog{
    public AddDialog(){
        setTitle("Add Server");
        setHeaderText("Enter server address");
        Optional<String> result = showAndWait();
        result.ifPresent(address -> System.out.println(address));
    }
}
