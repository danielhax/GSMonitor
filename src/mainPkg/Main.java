package mainPkg;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

//javafx reference codes
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    public static void main(String[] args) {
        launch(args);       //launch application as javfx application

        //create a new object: 1 ping 1 object
        //Ping x = new Ping("sgp-2.valve.net");
        //x.start();
    }

    @FXML
    private HBox root;

    //javafx main method
    @Override
    public void start(Stage primaryStage) throws IOException {

        root = FXMLLoader.load(getClass().getResource("main.fxml"));

            //STAGE/WINDOW PROPERTIES SECTION//
        primaryStage.setTitle("GSMonitor");
        //primaryStage.setResizable(false);

                    //SCENE SECTION//
        Scene scene = new Scene(root);

        primaryStage.show();
        ///////////////////////////////////////////

    }
}
