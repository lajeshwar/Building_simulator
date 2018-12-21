package por_J2;


import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/**
 * @author rx013337 
 * @author shsmchlr
 * class for handling a room via its String of coordinates
 */

public class BuildingInterface extends Application{

	Scanner s; // scanner used for input from user
	char disp[][];//display the rooms
	public Building myBuilding; // building in which rooms are shown
	public BuildingInterface nBuildingInterface;
	  double xOffset = 0;//move bp  
	  double yOffset = 0;//move bp
	 GraphicsContext g;
	 int bOpt = 2;
	 int width = 1000; 
	 int height =600; 
	 int bwidth; 
	 int bheight; 
	 AnimationTimer timer;
	/**
	 * return as String definition of bOpt'th building
	 * 
	 * @param bOpt
	 */
	public String buildingString() {
		if (bOpt == 1)
			return "11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6";

		else {
			return "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6 3";
		}
	}
	/**
	 * method used to display the building via length,width
	 */

	public void doDisplay() {
		bwidth = myBuilding.getsizeX()*30;
		bheight = myBuilding.getsizeY()*30; 
		g.setFill(Color.ALICEBLUE);
		g.fillRect(0,0,bwidth,bheight);
		myBuilding.showBuilding(this); 

		
	}



	/**
	 * specific what is shown at position x,y
	 * 
	 * @param d - y position
	 * @param e - x position
	 * @param ch - character to be placed at postion x,y
	 */
	
	public void showPersons(double x1, double y1,double x2, double y2 ) {
		g.setFill(Color.RED);
		g.fillOval(x1, y1, x2, y2);
	}
	/**
	 * shows walls from position x1,y1 to x1,y2
	 * 
	 * @param x1 - corner x position
	 * @param y1 - corner y position
	 * @param x2 - opposite corner x position
	 * @param y2 - opposite corner y position
	 */
	public void showWall(int x1, int y1, int x2, int y2) {
		 int x_1 =x1; int y_1=y1; int x_2=x2; int y_2=y2;
		 g.setStroke(Color.BLACK);	
		 g.setLineWidth(5);
		 g.strokeLine(x_1+15,y_1+15, x_2+15,y_2+15);	

		/*
		 * adding symbol on each of the corners of the room
		 
		showIt(y1 + 1, x1 + 1, '');
		showIt(y2 + 1, x1 + 1, 'Ã¢â€�â€�');
		showIt(y1 + 1, x2 + 1, 'Ã¢â€�ï¿½');
		showIt(y2 + 1, x2 + 1, 'Ã¢â€�Ëœ');
	*/
		
	}
	

	
	/**
	 * Method used to create the door
	 * @param x1 - door position x
	 * @param y2 - door position y
	 */
	public void showDoor(int x1, int y1) {

	//	showIt(y1 + 1, x1 + 1, ' '); //show door by using space

	}

	
		

		
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Building");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
	//	primaryStage.initStyle(StageStyle.UNDECORATED);
		BorderPane pane = new BorderPane();
		Group root = new Group();

		root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 xOffset = primaryStage.getX() - event.getScreenX();
                 yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
	    
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              
				primaryStage.setX(event.getScreenX() + xOffset);
              
				primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
		Canvas canvas = new Canvas(width,height);
		g = canvas.getGraphicsContext2D();
	
		
		 timer = new AnimationTimer() {
			
				@Override
				public void handle(long arg0) {
				

					doDisplay();
					myBuilding.animate();
				}
			};	timer.start();
	
		pane.autosize();
		root.getChildren().add(canvas);
		pane.setCenter(root);
		
		
		Scene scene = new Scene(pane,1200,500,Color.rgb(36, 36,36));
	
	
	
		primaryStage.setScene(scene);
		myBuilding = new Building(buildingString());
		primaryStage.show();

		

	}
	public static void main(String[] args) {
		Application.launch(args);

}
}
