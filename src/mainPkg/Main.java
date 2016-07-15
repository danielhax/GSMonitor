package mainPkg;

import mainPkg.gridPkg.boxPkg.AddBox;
import mainPkg.gridPkg.ServerGrid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application{

//javafx reference codes
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }

    private MainController root;
    public static short gridCount = 0;
    private ArrayList<ServerGrid> gridList = new ArrayList<ServerGrid>();

    public static void main(String[] args) {
        launch(args);       //launch application as javfx application

        //create a new object: 1 ping 1 object
        //Ping x = new Ping("sgp-2.valve.net");
        //x.start();
    }

    //javafx main method
    @Override
    public void start(Stage primaryStage) throws IOException {

        root = new MainController();
        initialize();

            //STAGE/WINDOW PROPERTIES SECTION//
        primaryStage.setTitle("GSMonitor");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
        ///////////////////////////////////////////

    }

    private void initialize(){
        /*
        Add initial grid
         */
        gridList.add(gridCount, new ServerGrid());
        root.getChildren().add(gridList.get(gridCount));

        /*
        Set initial add box
         */
        gridList.get(gridCount).getChildren().add(new AddBox());

    }

}
