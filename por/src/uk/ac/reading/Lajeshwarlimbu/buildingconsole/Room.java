package uk.ac.reading.Lajeshwarlimbu.buildingconsole;

import java.awt.Point;
import java.util.Random;
/**
 * @author rx013337
 * class for handling a room via its String of coordinates
 */

public class Room {

	private int x1, y1, x2, y2, xd, yd, ds; //room coordinates, ds->door size
	/**
	 * Constructor that is used to split the string of coorinates into integers and
	 * associate them with  x1, y1, x2, y2, xd, yd, ds accordingly.
	 * @param S - string of coordinates
	 */
	Room(String S) {

		StringSplitter splitter = new StringSplitter(S, " "); //splits the string

		x1 = splitter.getNthInt(0, 5); //associate x1 with the 0th value that was split.
		y1 = splitter.getNthInt(1, 5); //associate y1 with the 1st value that  was split.
		x2 = splitter.getNthInt(2, 5); //associate x2 with the 2nd value that was split.
		y2 = splitter.getNthInt(3, 5); //associate y2 with the 3rd value that was split.
		xd = splitter.getNthInt(4, 5); //associate xd with the 4th value that was split.
		yd = splitter.getNthInt(5, 5); //associate yd with the 5th value that was split.
		ds = splitter.getNthInt(6, 1); //default size of the door,ds is 1 else associate 6th value that was split.

	}
	
	/**
	 * A boolean method that is used to check if a point is within the room.
	 * @param p - point,(x,y) specified in integer precision.
	 */
	public boolean isInRoom(Point p) {

		return (p.getX() > x1 && p.getY() > y1 && p.getX() < x2 && p.getY() < y2);
	}
	
	/**
	 * returns the room coordinates as a string format.
	 */
	public String toString() {

		return x1 + "," + y1 + " to " + x2 + "," + y2 + " door at " + xd + ","
				+ yd /* + " " + ds */;

	}
	/**
	 * A point method that is used to get a random x and y coordinate within the room.
	 * The method returns a new point where x and y have been randomised.
	 * @param  random - generates a stream of pseudorandom numbers. 
	 */
	public Point get_random(Random random) {

		return new Point(x1 + 1 + random.nextInt(x2 - x1 - 2), y1 + 1 + random.nextInt(y2 - y1 - 2));//formula to randomise

	}
	
	/**
	 * A void method that is used show the room
	 * The method uses the method from buildingInterface,showWall
	 * to map the coordinates of the room
	 * @param  bi- instance of class BuildingInterface
	 */
	public void showRoom(BuildingInterface bi) {

		bi.showWall(x1, y1, x2, y2);

	}
	/**
	 * A void method that is used show the room
	 * The method uses the method from buildingInterface,showWall
	 * to get the coordinates of the room
	 * @param  bi- instance of class BuildingInterface
	 */
	public void showitDoor(BuildingInterface bi) {

		bi.showDoor(xd, yd);

	}
	/**
	 * A point method that is used to determine if 
	 * a person is just inside the door or outside the door
	 * -1 for just inside +1 for outside
	 * @param  ins- inside/outside
	 */
	public Point getDoor(int ins) {
		
		
		
		
		int x = xd; //xdoor pos
		int y = yd; //ydoor pos

		if (yd == y2) { 
			y =yd+ ins; //if yd door at y2 add to the value ins, set as outside

		} else if (yd == y1) {
			y =yd- ins;//if yd door at y1 subtract from value ins, set as inside

		} else if (xd == x1) {
			x =xd-ins; //if door at x1 subtract from value ins,set as inside

		}else {
			x =xd+ins; //add to x to set as outside
		}
		
		
		return new Point(x, y); //return position as outside or inside.

	}
	
	
	/**
	 * main method that is used to test out the room class
	 * @param args - contains the supplied command-line arguments as an array of String objects
	 */
	public static void main(String args[]) {

		Room r = new Room("0 0 5 5 0 2");
		System.out.println(r.toString());
		Point point = new Point(1, 3);

		if (r.isInRoom(point) == false) {
			System.out.println("Coordinate: " + point.x + "," + point.y + " is not in the room");
		} else {
			System.out.println("Coordinate: " + point.x + "," + point.y +" is in the room");
		}
        System.out.println(r.getDoor(-1));
        
        System.out.print(r.ds);
        
	}

}
