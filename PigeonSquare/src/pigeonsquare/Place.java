package pigeonsquare;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public class Place {

	public ArrayList<Food> foods = new ArrayList<Food>();
	public ArrayList<Pigeon> pigeons = new ArrayList<Pigeon>();
	
	
	
	
	public synchronized void addPigeon(Pigeon pig) {
		pigeons.add(pig);
	}

	public synchronized Food addFood(MouseEvent t) {
    	Food f=new Food(t.getX(),t.getY());
		System.out.println(t.getX()+","+t.getY());
		foods.add(f);
		for (Pigeon p : pigeons) {
			p.foods=this.foods;
		}
		return f;
    }
    public synchronized void eatFood() {
    	/*for (Pigeon p : pigeons) {
    		if(p.getC().getCenterX()==p.getTriggerBy().getX() && p.getC().getCenterY()==p.getTriggerBy().getY()) {
    			removeFood(p.getTriggerBy());
    		}
		}*/
    	
    	for (Pigeon p : pigeons) {
    		for (Food f : foods) {
    			if(p.getC().getCenterX()==f.getX() && p.getC().getCenterY()==f.getY()) {
        			removeFood(f);
        		}
    		}
    		
		}
   
    }
    
    public synchronized void removeFood(Food f) {
    	foods.remove(f);
		for (Pigeon p : pigeons) {
			p.foods=this.foods;
		}
    }
    

}
