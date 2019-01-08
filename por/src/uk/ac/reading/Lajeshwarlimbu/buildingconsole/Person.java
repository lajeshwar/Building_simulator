package uk.ac.reading.Lajeshwarlimbu.buildingconsole;

import java.awt.Point;
import java.util.ArrayList;
/**
 * @author rx013337 
 * 
 * class for handling a person with moving to the target, set, get its position,
 * if the person is still moving to the target, clearing the path of the person
 */

public class Person {

	private Point position;
	public ArrayList<Point> targets;
	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;

	
	
	
	/**
	 * Initilise the position of the person
	 * Initilise the ArrayList of Points
	 * @param point - position of the Person
	 */
	
	public Person(Point point) {
		position = new Point(point);
		targets = new ArrayList<Point>();
	}
	/**
	 * 
	 * Get the X position				
	 * 
	 */
	public int getX() {

		return (int) position.getX();
	}
	/**
	 * 
	 * Get the Y position				
	 * 
	 */
	public int getY() {

		return (int) position.getY();
	}
	
	/**
	 * 
	 * Get the X and Y position				
	 * 
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * 
	 * Set the default position for x
	 * 
	 * @param x - default position x			
	 * 
	 */
	public void setDefaultPosX(int x) {

		this.x = x;

	}
	/**
	 * 
	 * Set the default position for y
	 * 
	 * @param y - default position y			
	 * 
	 */
	public void setDefaultPosY(int y) {

		this.y = y;

	}
	/**
	 * 
	 * Set the position 
	 * 
	 * @param position - point,position			
	 * 
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	
	/**
	 * show person within the building interface
	 * 
	 * @param bi - object of building interface
	 */
	public void showPerson(double d, double e, BuildingInterface bi) {
		// call the showIt method in bi

		bi.showIt(e+1, d+1, '●'); // +1 to have '●', person avoid being inside the wall
	}
	
	/**
	 * setPath used to add points to the arraylist
	 * 
	 * @param p - point that is going to be stored in the arraylist.
	 */
	public void setPath(Point p) {
		
		targets.add(new Point(p));
		
	}
	/**
	 * returns the current target
	 * 
	 * @param ct - current path target
	 */
	public Point getTarget(int ct) {
		return targets.get(ct);
	}
	/**
	 * clears the arraylist of targets
	 * 
	 */
	public void clearPath(){
		
		targets.clear();
		
		
	}
	/**
	 * determines if position is equal to the target
	 * 
	 * @param ct- current path target
	 */
	public boolean isTarget(int ct){
		
		
		return position.equals(targets.get(ct));

	}
	
	/**
	 * moves the person to the current target
	 * 
	 * @param ct- current path target
	 */
	public void movePerson(int ct)
	{
	
        
		int x; //the change in x
		int y;// the change in y
		
			
				 
					if (position.getX()<targets.get(ct).getX()) { //if the persons x position is less than the target position, move x foward by 1
						x = 1;
					}else if(position.getX()>targets.get(ct).getX()){//if the persons x position is greater than the target position, move x backward by 1
						x = -1;
					}else{
						x= 0; // if the x postition is correct to the target position than x stays the same;
						
					}
					
					if (position.getY()<targets.get(ct).getY()) {//if the persons y position is less than the target position, move y foward by 1
						y = 1;
					}else if(position.getY()>targets.get(ct).getY()){//if the persons y position is greater than the target position, move y backward by 1
						y = -1;
					}else{
						y= 0; // if the x postition is correct to the target position than x stays the same;
						
					}
					position.translate(x, y);//Translates this point, at location (x,y),
					
					
	
		
	}
	/**
	 * main method used for testing
	 *
	 */
	public static void main(String[] args) {
		Point sm = new Point(0, 2);
		Person person = new Person(sm);

		System.out.print(person.getPosition());
		System.out.print((int) person.getX());
		System.out.print((int) person.getY());

	}

}
