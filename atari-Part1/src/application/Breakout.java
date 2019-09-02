package application;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import application.Brick;

public class Breakout {
	
	private ArrayList<Brick> createMultipleBlocks(int height,int width, int count) {
    	ArrayList<Brick> bricks = new ArrayList<Brick>();
    	double x = width - 50;
    	double y = 0;
    	int column  = 1;
    	for(int i = 0; i< count; i++) {
    		Brick brick = new Brick((int)x, (int)y, 50, 50);
    		bricks.add(brick);
    		y += 55;
    		if(y+50 > height) {
    			column++;
    			y = 0;
    			x = x - 55;
    		}
    	}
    	return bricks;
    }
    public HBox addHBox(Clock clock) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 12, 5, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.getChildren().addAll(clock);
        return hbox;
    }

    public void startBreakout(Scene scene, BorderPane pane)
    {
        Ball ball = new Ball(100,100,15);
        //Brick brick = new Brick(1000,100,50,50);
        ArrayList<Brick> bricks = createMultipleBlocks((int)scene.getHeight(), (int)scene.getWidth(), 100);
        Slider slider = new Slider(900, 0, 20, 200);
        Clock clock = new Clock();

        HBox hBox = addHBox(clock);
        clock.runClock();

        Pane gamePane = new Pane(slider.getRectangle());
        //gamePane.getChildren().add(brick.getRectangle());
        for(Brick brick : bricks) {
        	gamePane.getChildren().add(brick.getRectangle());
        }
        gamePane.getChildren().add(ball.getCircle());

        pane.setTop(hBox);
        pane.setCenter(gamePane);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>(){
            double ballMovementX = 7;
            double ballMovementY = 3;
            double sliderMovementY = 5;

            @Override
            public void handle(ActionEvent event) {
                ball.moveBall(ballMovementX, ballMovementY);
                ball.changeDirection(scene);
                slider.moveSlider(sliderMovementY);
                if(slider.isBoundary(scene)) {
                    System.out.println("Toggle");
                    slider.toggleSliderDirection(sliderMovementY);
                }
                ball.isSliderCollide(slider,ball);
                for(Brick brick : bricks) {
                	if(brick.isCollision(ball)) {
                    	brick.getRectangle().setFill(Color.WHITE);
                    	ball.handleBrickCollision(brick, ball);
                    }
                }
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.UP) {
                            slider.isUp = true;
                        }
                        else if (event.getCode() == KeyCode.DOWN) {
                            slider.isUp = false;
                        }
                    }
                });
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}