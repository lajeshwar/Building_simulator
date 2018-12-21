package por_J2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;



/**
 * @author rx013337 
 * class for creating the building
 * Attribute of a building contains the size of the building
 * room(s),Person inside the building
 *   
 */
public class Building {

	private int xSize = 10; // size of building in x
	private int ySize = 10; // and y
	private ArrayList<Room> allRooms;//string of rooms within an arraylist
	private Person oPerson;
	private Random random; 
	
	/**
	 * Constructor that is used initalise the building,person,rooms and randomiser
	 * @param rooms - string of room coordinates
	 */
	public Building(String rooms) {
		
		allRooms = new ArrayList<>();
		random = new Random();
		setBuilding(rooms);
		setPerson();
	}
	/**
	 * A void method that is used to add the rooms within the arraylist accordingly.
	 * The method clears the arraylist room for any pervious rooms if added.
	 * splits the String by ; and then spaces and then allocates the size of the building.
	 * Finally the string split by ; are stored within the array list.
	 * @param rooms - string of room coordinates
	 */
	void setBuilding(String rooms) {
		allRooms.clear();
		StringSplitter spl = new StringSplitter(rooms, ";");
		StringSplitter split_space = new StringSplitter(spl.getNth(0, "5 5"), " ");
		xSize = split_space.getNthInt(0, 5);
		ySize = split_space.getNthInt(1, 5);
        for (int i = 1; i < spl.numElement(); i++) {
	    allRooms.add(new Room(spl.getNth(i, "")));
        }
		

		
	    
	}

	/**
	 * Point method that is used to select a random room by using the Random utility
	 */
	public Point select_random_room() {

		int randRoom = random.nextInt(allRooms.size());
		return allRooms.get(randRoom).get_random(random);

	}	
	
	/**
	 * Initalise the person to a random room.
	 * Inital target/path set just inside of the room, by the door
	 *
	 */
	public void setPerson(){
		
		oPerson = new Person ( allRooms.get(randRoomNumber()).get_random(random));

		setPathNewRoom(oPerson);
		
	}
	
	/**
	 * Returns the room at which the oPerson/person is in
	 * @param p - person that is being located
	 */
	public int occupant_room(Person p) {
		 int room = -1; 
		
		for (int i = 0; i < allRooms.size(); i++) {

			if (allRooms.get(i).isInRoom(p.getPosition())) {
		
				room = i+1; // gets the room
			
			}

		}
		return (room);//if return -1 then the person is not inside any room.


	}
	/**
	 * Method to set a path to a new room
	 * 
	 */
	public void setPathNewRoom(Person Persons){
		/*
		 * Inorder for the person to correctly path to a new room, it will require set of targets in which it can path towards.
		 */
		int newRoom =randRoomNumber();
while(newRoom==roomNumber())
	newRoom = randRoomNumber();
		 Persons.setPath(allRooms.get(roomNumber()).getDoor(-15));// a -> inside start room door
		 Persons.setPath(allRooms.get(roomNumber()).getDoor(15));// b-> outside the room door
		 Persons.setPath(allRooms.get(newRoom).getDoor(15)); //c-> outside door random room target door
		 Persons.setPath(allRooms.get(newRoom).getDoor(-15));  //d-> inside door of random room target
		 Persons.setPath(allRooms.get(newRoom).get_random(random)); //E-> random position inside the random room
			
        
       
	
	}
	
	/**
	 * int method to set random room
	 * 
	 */
     private int randRoomNumber() {
    	 int randRoom = random.nextInt(allRooms.size());
    	
		return randRoom;
	}
     /**
 	 * int method to get room number;
 	 * 
 	 * if -1 then room does not exist
 	 * 
 	 */
	private int roomNumber(){
	
    	 int	room_no = -1;
    	 for (int i = 0; i < allRooms.size(); i++) { //iterate through array 
		
		
    		 if (allRooms.get(i).isInRoom(oPerson.getPosition())) { //get the room at the persons position
	
		
    			 room_no = i ; //set room_no to the room the person is in
    			
    		 }
		
	}
	return room_no; // return the room if found 
	
	
}

	


	/**
	 * boolean method used to move the person, returns true of false if the current path has been reached 
	 * @param bi - instance of BuildingInterface
	 * @param ct - stands for current target,path
	 */
     
	
	/**
	 * This method is used to animate the person moving to rooms via multiple paths.
	 * Each frame is redrawn by 250 miliseconds to show it moving to the target.
	 * @param bi - instance of BuildingInterface
	 */
	public void animate() {
		for (int i = 0; i < 10; i++) {
			
		
		if (oPerson.getStopped()||oPerson.i ==oPerson.targets.size()) setPathNewRoom(oPerson); else oPerson.update();
		}
}
    
	/**
	 * Methods used to show the building by calling the method showRoom, to show the room and showDoor, to show the door.
	 * @param bi - instance of BuildingInterface
	 */
	public void showBuilding(BuildingInterface bi) {
		
	for (int j = 0; j < allRooms.size(); j++) {
		
		allRooms.get(j).showRoom(bi);
		allRooms.get(j).showitDoor(bi);
	}
	
	        // call the showPerson method of the oPerson at random pos
			oPerson.showPerson(oPerson.getX(), oPerson.getY(), bi);
		
		
	}
	/**
	 * gets the building xSize
	 */
	public int getsizeX() {

		return xSize;

	}
	/**
	 * gets the building ySize
	 */
	public int getsizeY() {

		return ySize;

	}
	/**
	 * clears all the rooms within the array list
	 */
	public void removeBuilding() {

		allRooms.clear();

	}

	/**
	 * calls the method format which returns the information about the building in a single string
	 */
	public String toString() {
	
		return format();
	
	}
	/**
	 * method to format the information about the building into a single string
	 */
public String format(){

	
	String str = ""; //empty string used to store the information

	str += String.format( "Size %d,%d\n", getsizeX(), getsizeY()); //using stringformat, add the building sizes
	int i;
	for(  i = 0; i < allRooms.size(); i++ )
	{
		str += "Room "+(i+1)+" "+allRooms.get( i ).toString()+"\n"; //loop through the rooms and get the room number and room coordinates
		
	}

	str += String.format( "Person is at %d,%d inside the room %d\n", oPerson.getX(), oPerson.getY(),occupant_room(oPerson)); // get the persons x and y coordinate and which room the person is in.
	
	return str;
	
}

	public static void main(String[] args) {

		Building b = new Building("11 11;0 0 4 4 2 4;6 0 10 10 6 5;0 6 4 10 2 6"); // create
		System.out.println(b.toString());

	}

}
