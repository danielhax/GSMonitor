package pkg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

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

    Button button, button2;
    public Ping x = null;

    public static void main(String[] args) {
        launch(args);       //launch application as javfx application

        //create a new object: 1 ping 1 object
        //Ping x = new Ping("sgp-2.valve.net");
        //x.start();
    }

    //javafx main method
    @Override
    public void start(Stage primaryStage) throws Exception {

            //STAGE/WINDOW PROPERTIES SECTION//
        primaryStage.setTitle("GSMonitor");
        primaryStage.setResizable(false);
        ////////////////////////////////////////////

                    //COMPONENTS SECTION//
        button = new Button("Start");
        button2 = new Button("Stop");
        ////////////////////////////////////////////

                    //LAYOUT SECTION//
        FlowPane layout = new FlowPane();
        layout.setVgap(4);
        layout.setHgap(200);
        button.setPadding(new Insets(25, 25, 25, 25));
        ////////////////////////////////////////////

                    //ADDING SECTION//
        layout.getChildren().add(button);
        layout.getChildren().add(button2);
        ////////////////////////////////////////////

                    //SCENE SECTION//
        Scene scene = new Scene(layout, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
        ///////////////////////////////////////////

                    //ACTION SECTION//


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                //create a new object: 1 ping 1 object
                    try {
                        x = new Ping("sgp-2.valve.net");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    x.start();


            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(x.isAlive()){
                    x.interrupt();
                    System.out.println("Process interrupted");
                }
            }
        });
        ///////////////////////////////////////////

    }
}
