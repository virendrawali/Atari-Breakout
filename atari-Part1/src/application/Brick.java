package application;
import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	
	private Rectangle rectangle;
	private boolean isDead;
	public Brick() {
		
	}
	public Brick(int x, int y, int width, int height) {
		Rectangle rectangle = new Rectangle(x,y,width,height);
		rectangle.setFill(Color.GRAY);
		this.rectangle = rectangle;
		isDead = false;
	}
	
	public boolean isCollision(Ball ball) {
		Bounds brickBounds = this.getRectangle().getBoundsInLocal();
		if(this.isDead)
			return false;
		else if(isLeftCollision(ball,brickBounds) || isRightCollision(ball,brickBounds) || isTopCollision(ball,brickBounds) || isBottomCollision(ball,brickBounds))
        {
			killBrick();
			return true;
        }
		return false;
	}
	public boolean isLeftCollision(Ball ball,Bounds brickBounds) {
		if ((ball.getCircle().getLayoutX() + ball.getCircle().getRadius() >= brickBounds.getMinX() && ball.getCircle().getLayoutX() + ball.getCircle().getRadius() <= brickBounds.getMaxX() ) && ((ball.getCircle().getLayoutY() + ball.getCircle().getRadius() >= brickBounds.getMinY()) && (ball.getCircle().getLayoutY() - ball.getCircle().getRadius() <= brickBounds.getMaxY()))){
			System.out.println(ball.getCircle().getLayoutX()+",left");
			return true;
		}
		return false;
	}
	public boolean isRightCollision(Ball ball,Bounds brickBounds) {
		if ((ball.getCircle().getLayoutX() - ball.getCircle().getRadius() <= brickBounds.getMaxX() && ball.getCircle().getLayoutX() - ball.getCircle().getRadius() >= brickBounds.getMinX() )  && ((ball.getCircle().getLayoutY() + ball.getCircle().getRadius() >= brickBounds.getMinY()) && (ball.getCircle().getLayoutY() - ball.getCircle().getRadius() <= brickBounds.getMaxY()))){
			System.out.println(ball.getCircle().getLayoutX() - ball.getCircle().getRadius()+","+brickBounds.getMaxX()+",if1");
			return true;
		}
		return false;
	}
	public boolean isTopCollision(Ball ball,Bounds brickBounds) {
		if ((ball.getCircle().getLayoutY() + ball.getCircle().getRadius() >= brickBounds.getMinY() && ball.getCircle().getLayoutY() + ball.getCircle().getRadius() <= brickBounds.getMaxY() ) && ((ball.getCircle().getLayoutX() + ball.getCircle().getRadius() >= brickBounds.getMinX()) && (ball.getCircle().getLayoutX() - ball.getCircle().getRadius() <= brickBounds.getMaxX()))){
			System.out.println((ball.getCircle().getLayoutY() + ball.getCircle().getRadius())+","+ brickBounds.getMinY() +","+brickBounds.getMaxY() + " xtop "+(ball.getCircle().getLayoutX() + ball.getCircle().getRadius())+","+brickBounds.getMinX() + ","+ (ball.getCircle().getLayoutX() - ball.getCircle().getRadius()) +","+brickBounds.getMaxX());
			return true;
		}
		return false;
	}
	public boolean isBottomCollision(Ball ball,Bounds brickBounds) {
		if ((ball.getCircle().getLayoutY() - ball.getCircle().getRadius() <= brickBounds.getMaxY() && ball.getCircle().getLayoutY() - ball.getCircle().getRadius() >= brickBounds.getMinY() ) && ((ball.getCircle().getLayoutX() + ball.getCircle().getRadius() >= brickBounds.getMinX()) && (ball.getCircle().getLayoutX() - ball.getCircle().getRadius() <= brickBounds.getMaxX()))){
			System.out.println(ball.getCircle().getLayoutY()+",bottom");
			return true;
		}
		return false;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void killBrick() {
		isDead = true;
	}
	
	
}
