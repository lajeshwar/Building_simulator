package uk.ac.reading.Lajeshwarlimbu.buildingconsole;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * @author rx013337 
 * @author shsmchlr
 * class for handling a room via its String of coordinates
 */

public class BuildingInterface {

	Scanner s; // scanner used for input from user
	char disp[][];//display the rooms
	public Building myBuilding; // building in which rooms are shown
	String usersBuilding =""; //store users building
	String emptyString=""; //used to clear a string
	JFileChooser fc = new JFileChooser("D:\\work space\\por");
	File file = new File("");
	
	/**
	 * return as String definition of bOpt'th building
	 * 
	 * @param bOpt
	 */
	public String buildingString(int bOpt) {
		if (bOpt == 1)
			return "11 11;0 0 4 4 2 4 5;6 0 10 10 6 5 5;0 6 4 10 2 6 2";

		else {
			return "40 12;0 0 15 4 8 4;15 0 30 4 22 4;0 6 10 11 6 6";
		}
	}

	/**
	 * constructor for BuildingInterface sets up scanner used for input and the
	 * arena then has main loop allowing user to enter commands
	 */
	public BuildingInterface() {
		s = new Scanner(System.in); // set up scanner for user input
		int bno = 1; // initially building 1 selected
		
		myBuilding = new Building(buildingString(bno));// create building

		char ch = ' ';
		do {
			System.out.print(
					"(N)ew buidling (I)nfo, (D)isplay, (A)nimate, (C)reate new building, (S)ave, (L)oad, e(X)it > ");
			ch = s.next().charAt(0);
			s.nextLine();

			switch (ch) {
			case 'N':
			case 'n':
				bno = 3 - bno; // change 1 to 2 or 2 to 1
				myBuilding.setBuilding(buildingString(bno));
				myBuilding.setPerson();
				break;
			case 'I':
			case 'i':
				System.out.print(myBuilding.toString());//prints building information
				break;

			case 'D':
			case 'd':

				doDisplay(); //shows the building,room, person

				break;
			case 'C':

			case 'c':
				try {
					/* Takes inputs for building size and room details
					 * joptionpane used to show the messages and inputs within a message box
					 * inputs are stored within the usersBuilding and the building is created using the method setBuilding
					 * Within the building, person is also set within the room accordingly
					 * the try and catch is used to handle basic errors and clear the string caused by the input
					 */
					JOptionPane.showMessageDialog(null, "Specify the building in the following the format:\n|xSize ySize;x1 y1 x2 y2 xd yd|\nCurrent building|" + usersBuilding + "|");
				    usersBuilding += JOptionPane.showInputDialog("ENTER:");
				
					myBuilding.setBuilding(usersBuilding);
					myBuilding.setPerson();
					JOptionPane.showMessageDialog(null, "Building created!");
				} catch (Exception e) {
					usersBuilding=emptyString; //clears the string
					System.out.println("Error found!: " + e);
					System.out.println("Most likely error: missing ';' or space after '; '");
				}

				break;
			case 'S':
			case 's':
				
			
                 save();
				// save file

				break;
			case 'L':
			case 'l':

				// load building
				
				loadBuilding();
						
				break;
			case 'A':

			case 'a':
				myBuilding.animate(this);//animates the person move to a set location
				break;

			case 'x':

				ch = 'X'; // when X detected program ends
				break;

			}
		} while (ch != 'X'); // test if end

		s.close(); // close scanner
	}

	/**
	 * method used to display the building via length,width
	 */

	public void doDisplay() {

		int x = myBuilding.getsizeX() + 2; // add x by 2 for better layout of
											// the rooms
		int y = myBuilding.getsizeY() + 2; // add y by 2 for better layout of
											// the rooms

		disp = new char[y][x]; // make char array by to the size of y, x
		
		// fill the top row and bottom row
		for (int i = 0; i < x; i++) {
			disp[0][i] = '─';
			disp[y - 1][i] = '─';

		}

		// fill the left and right column
		for (int i = 0; i < y; i++) {
			disp[i][0] = '│';
			disp[i][x - 1] = '│';
			
		
		} 
		
		/*
		 * used to add corners within the building
		 * */
		showIt(0, 0, '┌');
		showIt(0, x-1, '┐');
		showIt(y-1, 0, '└');
		showIt(y-1, x-1, '┘');
		
		myBuilding.showBuilding(this); 

		printArray(disp); // shows the building
	}

	/**
	 * 
	 * static array to print out 2d array of character, d
	 */
	private static void printArray(char[][] d) {
		for (int i = 0; i < d.length; i++) { // i = y
			for (int j = 0; j < d[i].length; j++) { // j=x
			   
				System.out.print(d[i][j]);
            
			}
			 
			System.out.println(); // need println to print one line at a time
									// to get the correct format
		}
	}

	/**
	 * specific what is shown at position x,y
	 * 
	 * @param d - y position
	 * @param e - x position
	 * @param ch - character to be placed at postion x,y
	 */
	public void showIt(double d, double e, char ch) {
		disp[(int) d][(int) e] = ch;
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
		int difference = x2 - x1; // for y, column
		int difference2 = y2 - y1; // for x, row

		for (int j = 0; j < difference; j++) { // run through the coordinate by
												// the difference between y2-y1

			showIt(y1 + 1, x1 + j + 1, '─');
			showIt(y2 + 1, x1 + j + 1, '─');

		}

		for (int i = 0; i <= difference2; i++) { // run through the coordinate
													// by the difference between
													// y2-y1

			showIt(y1 + i + 1, x1 + 1, '│');
			showIt(y2 - i + 1, x2 + 1, '│');
		}

		/*
		 * adding symbol on each of the corners of the room
		 */
		showIt(y1 + 1, x1 + 1, '┌');
		showIt(y2 + 1, x1 + 1, '└');
		showIt(y1 + 1, x2 + 1, '┐');
		showIt(y2 + 1, x2 + 1, '┘');
	
		
	}
	/**
	 * Method used to save the users created building.
	 * Jfilechooser is used to suitably open the and save a file
	 * BufferedWriter is used to write to the file using file writer
	 */
	public void save() {

		//Intialise dialog
		fc.setApproveButtonText("Save");
		
		int rt = fc.showOpenDialog(null);//Pops up an "Save File" file chooser dialog. 
		if (rt == JFileChooser.APPROVE_OPTION){
		
		   File file = fc.getSelectedFile(); //gets file selected when clicked on save
		   
		    BufferedWriter writer = null;        
			try {
				writer = new BufferedWriter( //buffered writer used to        
				new FileWriter(file,false));  //write to the file, no append if false
				writer.write(usersBuilding); //within the selected file, writes the String for the building user has made.
				//writer.newLine();//new line inside the file
				System.out.println("File saved!");
				writer.close(); //close the file
			} catch (IOException e) {
				
				e.printStackTrace();//This method prints a stack trace for this Throwable object on the error output stream that is the value of the field System.
			}

		}
	}
	/**
	 * Method used to load the building from a file.
	 * Jfilechooser is used to suitably open the and load the file
	 * filereader and buffered reader is used to read from the file and set the building via String of line	
	 */
	public void loadBuilding() {
		//Intialise dialog
		fc.setApproveButtonText("Open");
		int rt = fc.showOpenDialog(null);//Pops up an "Open File" file chooser dialog. 
		if (rt == JFileChooser.APPROVE_OPTION){
		
		    File file = fc.getSelectedFile(); //gets file selected when clicked on open
		  //before loading check if file being loaded is empty or not
			if (file.isFile()) {
				long size = file.length();
				if (size == 0) {
					
					System.out.println("Empty file!");
				}else {
				
				
		    BufferedReader reader = null;        
			try {
				reader = new BufferedReader( //buffered reader used to        
				new FileReader(file));  //read to the file
				myBuilding.setBuilding(reader.readLine()); //within the selected file, load the String.
				myBuilding.setPerson(); //set person inside the loaded building
				System.out.println("File loaded!");
				doDisplay();// displays the loaded building
				reader.close(); //close the file
			} catch (IOException e) {
				
				e.printStackTrace();//This method prints a stack trace for this Throwable object on the error output stream that is the value of the field System.
			}}}}

		
	
	}
	/**
	 * Method used to create the door
	 * @param x1 - door position x
	 * @param y2 - door position y
	 */
	public void showDoor(int x1, int y1) {

		showIt(y1 + 1, x1 + 1, ' '); //show door by using space

	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		BuildingInterface b = new BuildingInterface();
		// just call the interface
		
       

		
	}
}
