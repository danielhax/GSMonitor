package pkg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{

//javafx reference codes
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    Button button;

    public static void main(String[] args) throws IOException {
        //launch application as javfx application
        //launch(args);

        //create a new object: 1 ping 1 object
        Ping x = new Ping("sgp-2.valve.net");
        x.start();
    }

    //javafx main method
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ping-a-Ping");

        button = new Button();
        button.setText("Start");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
