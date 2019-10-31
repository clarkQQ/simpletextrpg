
public class RoomList {
	private Room head;
	
	public RoomList()
	{
		head = new Room("The beginning.");
	}
	
	//inner class iterator
	//
	//begin
	public class RoomIterator{
		private Room position;
		private Room lastRoom;
		private Room toUp, toLeft, toRight, toDown;
		
		public RoomIterator()
		{
			position = head;
		}
		
		//restarting
		public void restart()
		{
			position = head;
		}			
		
		public Room returnCurrentPosition()
		{
			return position;
		}
		//move but based on a button....the menuButton will have a string returned and
		// and a reference to the current head/room
		public Room move(String input, Room currentRoom)
		{
			input = input.toUpperCase();
			if(input.equals("U") && position.hasUp())
			{
				lastRoom = position;
				position = position.getUp();
				
				if(position.getUp()!=null)
					toUp = position.getUp();
				if(position.getRight()!=null)
					toRight = position.getRight();
				if(position.getLeft()!=null)
					toLeft = position.getLeft();
				if(position.getDown()!=null)
					toDown = position.getDown();				
			}
			
			else if(input.equals("L") && position.hasLeft())
			{
				lastRoom = position;
				position = position.getLeft();
				
				if(position.getUp()!=null)
					toUp = position.getUp();
				if(position.getRight()!=null)
					toRight = position.getRight();
				if(position.getLeft()!=null)
					toLeft = position.getLeft();
				if(position.getDown()!=null)
					toDown = position.getDown();				
			}
			
			else if(input.equals("R") && position.hasRight())
			{
				lastRoom = position;
				position = position.getRight();
				
				if(position.getUp()!=null)
					toUp = position.getUp();
				if(position.getRight()!=null)
					toRight = position.getRight();
				if(position.getLeft()!=null)
					toLeft = position.getLeft();
				if(position.getDown()!=null)
					toDown = position.getDown();				
			}
			
			else if(input.equals("D") && position.hasDown())
			{
				lastRoom = position;
				position = position.getDown();
				
				if(position.getUp()!=null)
					toUp = position.getUp();
				if(position.getRight()!=null)
					toRight = position.getRight();
				if(position.getLeft()!=null)
					toLeft = position.getLeft();
				if(position.getDown()!=null)
					toDown = position.getDown();				
			}
			
			else
			{
				System.out.println("Something went wrong... "
						+ "check the inputs and/or RoomList class");				
			}
			
			return position;
		}// END OF MOVE METHOD
		
		//print the openings in the room, think about dialog
		public void check()
		{
			System.out.print("This room has openings  ");
			if( position.getUp()!=null)
			{
				System.out.print("upwards, ");
			}
			if(position.getRight()!=null)
			{
				System.out.print("to the right, ");
 			}			
			if(position.getLeft()!=null)
			{
				System.out.print("to the left, ");
 			}
			if(position.getDown()!=null)
			{
				System.out.print("downward, ");
 			}
			System.out.println(); 
			//line break
		}
		//this prints out for now, think about how to put into the text area.. with buttons,
		// if(roomCheck()) go to demo class and do setText();
		public boolean roomCheck()
		{
			if(position.endRoom)
			{
				System.out.println("You reached the end");
				return true;
			}
			else
			{
				System.out.println("There are still rooms!");
				return false;
			}
		}
	}//end of iterator class
	//
	//
	public RoomIterator iterator()
	{
		return new RoomIterator();		
	}
	
	public Room returnCurrentRoom()
	{
		return this.head;
	}
	
	
}
