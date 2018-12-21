package por_J2;

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

		StringSplitter splitter = new StringSplitter(S, " ");

		x1 = splitter.getNthInt(0, 5)*30;
		y1 = splitter.getNthInt(1, 5)*30;
		x2 = splitter.getNthInt(2, 5)*30;
		y2 = splitter.getNthInt(3, 5)*30;
		xd = splitter.getNthInt(4, 5)*30;
		yd = splitter.getNthInt(5, 5)*30;
		ds = splitter.getNthInt(6, 1)*30; //default size of the door is 1

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

		return new Point(x1 + 15 + random.nextInt(x2 - x1 - 30), y1 + 15 + random.nextInt(y2 - y1 - 30));//formula to randomise

	}
	
	/**
	 * A void method that is used show the room
	 * The method uses the method from buildingInterface,showWall
	 * to map the coordinates of the room
	 * @param  bi- instance of class BuildingInterface
	 */
	private void doWall (BuildingInterface bi, int xa, int ya, int xb, int yb) {
		if ( (xa == xb) && (xd == xa) ) {			// if vert wall, check if door in it
			bi.showWall(xa, ya, xb, yd-15);
			bi.showWall(xa, yd+ds, xb, yb);
		}
		else if ( (ya == yb) && (yd == ya) ) {		// similar for horiz wall
			bi.showWall(xa, ya, xd-15, yd);
			bi.showWall(xd+ds, yd, xb, yb);
		}
		else bi.showWall(xa, ya, xb, yb);
	}
	
	public void showRoom(BuildingInterface bi) {

		
		doWall(bi, x1, y1, x1, y2);			// show each of its walls
		doWall(bi, x2, y1, x2, y2);
		doWall(bi, x1, y2, x2, y2);
		doWall(bi, x1, y1, x2, y1);
	
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
		
		int x = xd;					// x,y position calculated
		int y = yd;
					// need to find which wall door is on, and act accordingly
		if (yd == y2) { y = yd + ins; x = xd + ds / 2; }
		else if (yd == y1) { y = yd - ins; x = xd + ds / 2; }
		else if (xd == x1) { x = xd - ins; y = yd + ds / 2; }
		else { x = xd + ins; y = yd + ds / 2; }
		return new Point(x,y);

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
