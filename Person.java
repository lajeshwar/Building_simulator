package por_J2;

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
	public  ArrayList<Point> targets;
	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;
	private boolean isMoving;
	int i = 0;
	
	
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
	 * @param j 
	 * @param i 
	 * 
	 * @param bi - object of building interface
	 */
	public void showPerson(int i, int j, BuildingInterface bi) {
		// call the showIt method in bi

		bi.showPersons(i, j, 15,15); // +1 to have 'â—�', person avoid being inside the wall
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
	 * clears the arraylist of targets
	 * 
	 */
	public void clearPath(){
		
		targets.clear();
		
		
	}
	/**
	 * set person as being isMoving or not
	 * @param isStopped
	 */
	public void setStopped(boolean isStopped) {
		isMoving = isStopped;
	}
	/**
	 * Is person isMoving
	 * @return if so
	 */
	public boolean getStopped() {
		return isMoving;
	}
	
	/**
	 * moves the person to the current target
	 * @param point 
	 * 
	 * @param ct- current path target
	 */
	public void movePerson(Point point)
	{
	
        
		int dx = 0;			// amount by which it will move in x .. and y, set to -1, 0 or 1
		int dy = 0;
		if (position.getX() < point.getX()) dx = 1; else if (position.getX() > point.getX()) dx = -1;
		if (position.getY() < point.getY()) dy = 1; else if (position.getY() > point.getY()) dy = -1;
		position.translate(dx, dy);	// now move
					position.translate(x, y);//Translates this point, at location (x,y),
					
					
	
		
	}
	
	private boolean equalXY(Point point) {
		return ( (int)point.getX() == (int) position.getX() ) && ( (int)point.getY() == (int) position.getY() );
	}
	public void update() {

		if (isMoving) {} 						// do nowt
		else if (equalXY(targets.get(i))){		// if at next point on path
			i++;					// as one point in this version, say isMoving
		}
		else if(!equalXY(targets.get(i)))
				movePerson(targets.get(i));
				else isMoving = true;	
		
		}// move closer to next destination
	
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
