package pigeonsquare;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Pigeon implements Runnable{
	public Place p;
	private double speed;
	private Circle c;
	private Food triggerBy;
	public ArrayList<Food> foods = new ArrayList<Food>();
	

	public Pigeon(Place place) {
		this.p=place;
		this.c = new Circle((Math.random() * 700),(Math.random() * 700),10, Color.RED);
		this.speed = 0.3;
		new Thread(this,"pigeon"+Thread.currentThread().getName()).start();
		p.addPigeon(this);
	}
	

	//identifier la nouriture la plus proche
	public void getCloserFood(ArrayList<Food> foods) {
		Food food =foods.get(0);
		double distance=0,d;
		for (Food f : foods) {
			d=Math.sqrt(Math.pow((this.getX()-f.getX()),2)+Math.pow((this.getY()-f.getY()),2));
			//System.out.println("distance :"+d);
			if(distance==0) {
				distance=d;
				food =f;
			}
			if(d<distance) {
				distance=d;
				food=f;
			}
		}
		this.triggerBy=food;
	}
	
	
	//se déplacer vers la nourriture triggered
	public TranslateTransition moveToFood() {
		Food f =this.triggerBy;
		//double distance = Math.sqrt(Math.pow((this.getX()-f.getX()),2)+Math.pow((this.getY()-f.getY()),2));
		TranslateTransition	transition = new TranslateTransition(Duration.millis(100/this.speed),this.c);
        transition.setToX(f.getX()-this.c.getCenterX());    //decalage de la valeur initiale x de c1
        transition.setToY(f.getY()-this.c.getCenterY());
        transition.play();
        return transition;
	}
	
	
	@Override
	public void run() {
		
			System.out.println("pigeon :" +this.getX()+"/"+this.getY()+"----"+foods.size());
			if(!foods.isEmpty()) {
				getCloserFood(foods);
				moveToFood();
				
				
			
		}
	}
	
	public Circle getC() {
		return c;
	}

	public double getX() {
		return c.getCenterX();
	}

	public double getY() {
		return c.getCenterY();
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setC(Circle c) {
		this.c = c;
	}

	public Food getTriggerBy() {
		return triggerBy;
	}

	public void setTriggerBy(Food triggerBy) {
		this.triggerBy = triggerBy;
	}
	
}


