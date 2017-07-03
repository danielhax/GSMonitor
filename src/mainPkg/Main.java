package mainPkg;

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

    private MainView root;
    public static short gridCount = 0;
    private ArrayList<ServersGridView> grids = new ArrayList<ServersGridView>();

    public static void main(String[] args) {
        launch(args);       //launch application as javfx application

        //create a new object: 1 ping 1 object
        Ping x = null;
        try {
            x = new Ping("sgp-2.valve.net");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //x.start();
    }

    //javafx main method
    @Override
    public void start(Stage primaryStage) throws IOException {

        this.root = new MainView();
        initialize();

            //STAGE/WINDOW PROPERTIES SECTION//
        primaryStage.setTitle("GSMonitor");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(this.root));

        primaryStage.show();
        ///////////////////////////////////////////

    }

    private void initialize(){
        /*
        Add initial grid
         */
        grids.add(new ServersGridView());
        root.getChildren().add(grids.get(gridCount));

        /*
        Set initial add box
         */
        grids.get(gridCount).getChildren().add(new AddBoxView());

    }

}
