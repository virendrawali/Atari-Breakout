package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void stop() throws Exception {
        System.out.println("Stage is closing");
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

    	final double  SCENE_HEIGHT = 700;
    	final double SCENE_WIDTH = 1500;
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        Breakout breakout = new Breakout();
        breakout.startBreakout(scene, pane);
    	primaryStage.setScene(scene);
        primaryStage.setTitle("Breakout");
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent arg0) {
                primaryStage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
