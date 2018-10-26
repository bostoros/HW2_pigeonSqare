package pigeonsquare;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import java.util.Date;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Food{

	private long durability = 5;
	private long start;
	public Circle c;
	
	public Food(double x, double y) {
		super();
		this.c = new Circle(x,y,10, Color.BLUE);
		this.start = System.currentTimeMillis();
	}
	
	public Circle getC() {
		return c;
	}

	public void setC(Circle c) {
		this.c = c;
	}

	public boolean decreaseDurability() {
		long tmp = System.currentTimeMillis(); 
		if(start+(durability*100)<tmp){
			return false;
		}
		return true;
	}
	
	public float getDurability() {
		return durability;
	}
	public void setDurability(long durability) {
		this.durability = durability;
	}
	public double getX() {
		return c.getCenterX();
	}

	public double getY() {
		return c.getCenterY();
	}

	public FadeTransition disapear() {
		FadeTransition fade = createFader();
		fade.play();
		return fade;
	}
	
    public FadeTransition createFader() {
        FadeTransition fade = new FadeTransition(Duration.seconds(this.getDurability()), this.getC());
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }
	
}
