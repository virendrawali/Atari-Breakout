package application;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball {

    public double ballCenterX;

    public double ballCenterY;

    public int radius;

    private Circle circle;

    private boolean directionX;
    private boolean directionY;

    public Ball(int ballCenterX, int ballCenterY, int radius) {
        this.ballCenterX = ballCenterX;
        this.ballCenterY = ballCenterY;
        this.radius = radius;
        this.directionX = true;
        this.directionY = true;
        circle = new Circle(radius, Color.DARKBLUE);
        circle.relocate(ballCenterX, ballCenterY);
    }

    public void moveBall(double xOffset, double yOffset) {
        if (directionX) {
            ballCenterX = ballCenterX + xOffset;
            
        } else {
            ballCenterX = ballCenterX - xOffset;
            
        }
        if (directionY) {
        	ballCenterY = ballCenterY + yOffset;
        }
        else {
        	ballCenterY = ballCenterY - yOffset;
        }
        getCircle().setLayoutX(ballCenterX);
        getCircle().setLayoutY(ballCenterY);
    }

    public boolean isLeftRightCollide(double minX, double maxX) {
        if(ballCenterX <= (minX + radius) || ballCenterX >= (maxX - radius)) {
        	System.out.println("left");	
        }
    	
    	return ballCenterX <= (minX + radius) || ballCenterX >= (maxX - radius);
        
    }

    public boolean isUpDownCollide(double minY, double maxY) {
    	if (ballCenterY <= (minY + radius) || ballCenterY >= (maxY - radius))
    	{
    		System.out.println("right");
    		return true;
    	}
    	return false;
    }

    public void changeDirection(Scene scene) {

    	
        if (isLeftRightCollide(0,scene.getWidth())) {
        	
            directionX = !directionX;
        }
        if (isUpDownCollide(0,scene.getHeight()-60)) {
        	directionY = !directionY;
        }
    }

    public void isSliderCollide(Slider slider, Ball ball)
    {
        if(slider.isCollide(ball))
        {
            directionX = !directionX;
        }

    }
	public void handleBrickCollision(Brick brick,Ball ball) {
    	Bounds brickBounds = brick.getRectangle().getBoundsInLocal();
    	if(brick.isLeftCollision(ball, brickBounds)||brick.isRightCollision(ball, brickBounds)) {
    		directionX = !directionX;
    	}
    	else if(brick.isTopCollision(ball, brickBounds)||brick.isBottomCollision(ball, brickBounds)) {
    		directionY = !directionY;
    	}
    }

	public Circle getCircle() {
		return circle;
	}
    
}
