package application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Slider {
	private Rectangle rectangle;
	private double sliderTopY;
	boolean isUp;

	public Slider(int x, int y, int width, int height) {
		Rectangle rectangle = new Rectangle(x,y,width,height);
		rectangle.setFill(Color.GREEN);
		this.rectangle = rectangle;
		isUp = false;
		this.sliderTopY = y;
	}

	public void moveSlider(double ry) {
		if(isUp)
        {
			sliderTopY += -Math.abs(ry);
        }
        else 
        {
        	sliderTopY += Math.abs(ry);
        }
		this.rectangle.setLayoutY(sliderTopY);
	}
	
	public boolean isBoundary(Scene scene) {
		if(sliderTopY >= scene.getHeight()-rectangle.getHeight()-60 || sliderTopY <= 0)
        {
            return true;
        }
		return false;
	}
	public void toggleSliderDirection(double ry) {
		ry = -ry;
		isUp = !isUp;
	}

	public boolean isCollide(Ball ball)
	{
		if(ball.ballCenterX - ball.radius <= rectangle.getX()+rectangle.getWidth() && ball.ballCenterX+ball.radius >= rectangle.getX() && ball.ballCenterY-ball.radius <= rectangle.getLayoutY()+rectangle.getHeight() && ball.ballCenterY+ball.radius >= rectangle.getLayoutY())
		{
			return true;
		}
		return false;
	}

	
	public Rectangle getRectangle() {
		return rectangle;
	}
}