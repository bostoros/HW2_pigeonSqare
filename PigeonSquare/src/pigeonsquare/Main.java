package pigeonsquare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {	
	static Place p;
	AnimationTimer gameLoop;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,700,700);
			primaryStage.setScene(scene);
			
			Circle c1 = new Circle(50.0f, 50.0f, 10.0f, Color.GREY);	
			
			
			System.out.println(p.pigeons.toString());
			for (Pigeon pig : p.pigeons) {
				root.getChildren().add(pig.getC());
			}
			
			EventHandler<ActionEvent> test = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					loopGame();
				}
			}; 
			
		
			
			
			
			
			
			root.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					System.out.println(t.getButton());
					Food f = p.addFood(t);
					root.getChildren().add(f.getC());
					

					FadeTransition t1 = f.disapear();
					//eatFood();
					
					t1.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {		
							System.out.println("detruit" + f.getX());
							p.removeFood(f);
						}
					});
					//SequentialTransition blinkThenFade = new SequentialTransition(f.getC(),f.disapear());
					//blinkThenFade.play();
					//
					
//					if(fade.getByValue()==0.5) {
//						removeFood(f);
//					}
					Duration durationToFood = Duration.millis(2500);
			        //Create new translate transition
//			        if(!foods.isEmpty()) {
//			        	
//			        	translation(durationToFood,c1,f);
//			        }
				}
			});
			
			root.getChildren().add(c1);
			primaryStage.show();
			startGame();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		p =new Place();
		Pigeon p1 = new Pigeon(p);
		Pigeon p2 = new Pigeon(p);
		Pigeon p3 = new Pigeon(p);
		Pigeon p4 = new Pigeon(p);
		launch(args);		
	}
	
	
	 private void startGame() {

	        // start game
	        gameLoop = new AnimationTimer() {

	            @Override
	            public void handle(long now) {
	            	loopGame();
	            }
	        };

	        gameLoop.start();

    }
	 
    public void loopGame() {

	    for (Pigeon pig : p.pigeons) {
	    		pig.run();
	    		p.eatFood();
	    
    	}

    }
}

