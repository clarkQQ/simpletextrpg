import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.Serializable;

//node inspired, need to IO
public class Room implements Serializable {

	private Room Up;
	private Room Down;
	private Room Left;
	private Room Right;
	public boolean endRoom;
	private boolean hasMonster;
	
	private Slime currMon;
	
	private String roomName;
	
	public Room(String input)
	{
		roomName = input;
		
		Up = null;
		Right = null;
		Left = null;
		Down = null;
		hasMonster = false;
		
		endRoom = false;		
	}	
	//use boolean to TRUE or False to designate a monster to the room rather than using first constructor
	public Room(String input, boolean filler)
	{
		//TESTING LINE, systemprint
		System.out.println("calling Room with mon constructor");
		roomName = input;
		Up = null;
		Right = null;
		Left = null;
		Down = null;
		
		endRoom = false;
		currMon = new Slime();
		hasMonster = true;
		
	}
	// begin ACCESSORS
	public Slime getMonster()
	{
		return currMon;
	}
	public Room getUp()
	{
		return Up;
		
	}
	
	public Room getRight()
	{
		return Right;
	}
	
	public Room getLeft()
	{
		return Left;
	}
	
	public Room getDown()
	{
		return Down;
	}
	// BEGIN CHECKERS
	public boolean hasUp()
	{
		if(Up==null)
			return false;
		else
			return true;
	}
	
	public boolean hasDown()
	{
		if(Down== null)
			return false;
		else
			return true;
	}
	
	public boolean hasRight()
	{
		if(Right ==null)
			return false;
		else
			return true;
	}
	
	public boolean hasLeft()
	{
		if(Left == null)
			return false;
		else
			return true;
	}
	
	public boolean hasMonster()
	{
		return	hasMonster;
	}
	//END ACESSSSORS
	//
	//BEGIN SETTERS
	
	public Room setUp(Room given)
	{
		Up = given;
		if(!(given.hasDown()))
		{
			given.setDown(this);
		}
			return given;					
	}
	
	public Room setDown(Room given)
	{
		Down = given;
		if(!(given.hasUp()))
		{
			given.setUp(this);
		}
		return given;
	}
	
	public Room setRight(Room given)
	{
		Right = given;
		if(!(given.hasLeft()))
		{
			given.setLeft(this);
		}
		return given;
	}
	
	public Room setLeft(Room given)
	{
		Left = given;
		if(!(given.hasRight()))
		{
			given.setRight(this);
		}
		return given;
	}
	
	public void setEnd()
	{
		endRoom = true;
	}	
	
	//END OF SETTERS
	
	public boolean isEnd()
	{
		return endRoom;
	}
	
	@Override
	public String toString()
	{
		if(hasMonster)
		{
			return roomName+" has a slime";
		}
		else
			return roomName;
	}	

	
	
	
	
}
