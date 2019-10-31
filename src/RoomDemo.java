import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.ArrayList;
import java.util.Scanner;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

//main class of the file holding everything, RUN method is in RoomRunner.java
//holds a run method, constructs GUI and listeners,
// has graphics class for victory settings
public class RoomDemo extends JFrame implements ActionListener{
	public static final int WIDTH = 800;
	public static final int HEIGHT= 600;
	public static final int LINES = 30;
	
	public Player Hero;
	public Slime monster1;
	public Slime monster2;
	public int turns = 0;
	public boolean mustMove = false;
	public boolean mustAct = false;
	
	public ArrayList<String> monList;
	
	Player King= new Player();
	
	public RoomList gameRooms = new RoomList();
	public RoomList.RoomIterator i;
	private JTextArea dialogDisplay;
	
	public JPanel actionsPanel,infoPanel;
	public JMenu moveMenu;
	public boolean endFlag = false;
	
//	public int lineCount=0;
	//moved main method to separate class
	//	public static void main(String[] args)
	//	{
	//		RoomDemo window = new RoomDemo();
	//		window.setVisible(true);	
	//		
	//		
	//	}
	
	public void run()
	{
	
		i = gameRooms.iterator();
		
		
		while(!endFlag)
		{
			dialogDisplay.append("Where will you move?\n");
			dialogDisplay.append("Use the menu and move.\n");
			
			//  && !(i.returnCurrentPosition().hasMonster()) copypasta relation
			boolean temp= false;
			
			while(!mustMove)
			{
			
				moveMenu.setPopupMenuVisible(true);	
				String random = i.returnCurrentPosition().hasMonster()+""; // needed to run correctly


			}

			temp = i.returnCurrentPosition().hasMonster();
			
			while(temp)
			{	
				moveMenu.setPopupMenuVisible(false);

				if( (turns/2) == 0 && i.returnCurrentPosition().hasMonster() )
				{
					
					if(!mustAct)
					{ mustAct=true;
					dialogDisplay.append("There's a monster in the room!\n what will you do?\n");
					}
					actionsPanel.setVisible(true);
				
				}
				if( (turns%2 )==1 && i.returnCurrentPosition().hasMonster())
				{						
					if(i.returnCurrentPosition().getMonster().getHp() > 0)
					{
					mustAct = true;
				
					dialogDisplay.append("The slime attacks you for 4 damage!\n"
							+ "the slime has "+ i.returnCurrentPosition().getMonster().getHp()+"HP\n");					
					King.takeDamage(i.returnCurrentPosition().getMonster().getAttackDamage());
					dialogDisplay.append(King+"\n");
					if(King.getHp() < 0)
					{
						dialogDisplay.append("You died! You lost!\n");
						endFlag =true;
						DeathPanel d = new DeathPanel();
						add(d);
						break;
					}
					}
					else
					{	
						dialogDisplay.append("The slime is slain!");
						mustAct = false;
						mustMove= false;
						break;
					}
					
					turns++;
				}				
//				System.out.println(i.returnCurrentPosition().toString()); testing line
				if(i.returnCurrentPosition().isEnd())
				{
					endFlag=true;
					dialogDisplay.append("You have found the final room! Congratulations! You win!\n");					
					actionsPanel.setVisible(false);
					infoPanel.setVisible(false);					
					PaintPanel p = new PaintPanel();
					add(p);
					break;
				}
				
			}			

		}
			
	}	
	
	public RoomDemo()
	{
		super("RPG Room Game!");		
		
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel textPanel = new JPanel();
		textPanel.setBackground(new Color(186,225,255));
		//creating dialogue area
		dialogDisplay = new JTextArea(LINES, 50);
		dialogDisplay.setBackground(Color.WHITE);
		
		JScrollPane scrolledText = new JScrollPane(dialogDisplay);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textPanel.add(scrolledText);
		
		add(textPanel, BorderLayout.CENTER);
		
		actionsPanel = new JPanel();
		actionsPanel.setBackground(new Color(255,255,186));
		
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new attackListener());
		actionsPanel.add(attackButton);
		
		JButton magicButton = new JButton("Magic");
		magicButton.addActionListener(new magicListener());
		actionsPanel.add(magicButton);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new runListener());
		actionsPanel.add(runButton);
		add(actionsPanel,BorderLayout.SOUTH);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2,1));
		JButton roomButton = new JButton("Map Info");
		roomButton.addActionListener(new readListener());
		infoPanel.add(roomButton);
		
		JButton monsterButton = new JButton("Monster Info");
		monsterButton.addActionListener(new monsterListener());		
		infoPanel.add(monsterButton);
		add(infoPanel,BorderLayout.EAST);
				
		
		moveMenu = new JMenu("Move options");
		JMenuItem upChoice = new JMenuItem("Up");
		upChoice.addActionListener(new upListener());
		moveMenu.add(upChoice);
		JMenuItem leftChoice = new JMenuItem("Left");
		leftChoice.addActionListener(new leftListener());
		moveMenu.add(leftChoice);
		JMenuItem rightChoice = new JMenuItem("Right");
		rightChoice.addActionListener(new rightListener());
		moveMenu.add(rightChoice);
		JMenuItem downChoice = new JMenuItem("Down");
		downChoice.addActionListener(new downListener());
		moveMenu.add(downChoice);
		JMenuBar bar = new JMenuBar();
		bar.add(moveMenu);
		setJMenuBar(bar);
		
		actionsPanel.setVisible(false);
		
		
		//begin game 
		generateMap(gameRooms);
		
					
	}
	
	
	//filler action performed for now
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Filler");
		
	}
	
	private class attackListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			mustAct = true;
			System.out.println("Attacc'd");
			dialogDisplay.append("Just attacked!\n");
			i.returnCurrentPosition().getMonster().takeDamage(6);
			turns++;
			
		}
	}
	/**
	 * 
	 * it all works right now, just making magic actions and run actions! attack worked!! success!
	 * 
	 *
	 */
	
	private class magicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mustAct = true;			
			System.out.println("magicc'd");
			dialogDisplay.append("Cast fireball!!\n");
			i.returnCurrentPosition().getMonster().takeDamage(12);
			turns++;
		}
	}
	
	private class runListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			mustAct=true;
			dialogDisplay.append("You attempt to escape!\n");	
			int val = King.getRoll(6); // recursive roll 6 times
			System.out.println(val);
			if(val > 15)
			{
				dialogDisplay.append("You got away!\n");
				i.returnCurrentPosition().getMonster().takeDamage(20);
			}
			System.out.println("Run'd");
			turns++;
		}
	}
	
	private class upListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			if( (i.returnCurrentPosition().getUp())!=null )
			{	
				
				i.move("U", gameRooms.returnCurrentRoom());
				
				dialogDisplay.append("You just moved to the upwards room!\n");
				i.check();	    
				mustMove=true;
			}
			else
			{
				System.out.println("up was done");
				dialogDisplay.append("A wall blocks your path..\n");
			}
				
		}
	}
	
	private class leftListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( (i.returnCurrentPosition().getLeft())!=null )
			{
				mustMove=true;
			i.move("L", gameRooms.returnCurrentRoom());
			
			dialogDisplay.append("You just moved to the left room!\n");
			i.check();
			}
			else
			{
				System.out.println("left was done");
				dialogDisplay.append("A hedge blocks your path..\n");
			}
			
		}
	}
	
	private class rightListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( (i.returnCurrentPosition().getRight())!=null )
			{
				mustMove=true;
			i.move("R", gameRooms.returnCurrentRoom());
			dialogDisplay.append("You just moved to the right room!\n");
			i.check();			
			}
			else
			{
				System.out.println("right was down");
				dialogDisplay.append("You can't go that way\n");
			}
		}
	}
	
	private class downListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if((i.returnCurrentPosition().getDown())!=null)
			{
				mustMove=true;
			i.move("D", gameRooms.returnCurrentRoom());
			dialogDisplay.append("You just moved to the downwards room!\n");
			i.check();
			}
			else
			{
				System.out.println("down'd");
				dialogDisplay.append("You're blocked by some unnatural force\n");
			}
		}
	}
	
	private class readListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			 try
			 {
			 ObjectInputStream inputStream =
			 new ObjectInputStream(new FileInputStream("rooms.bin"));
			 System.out.println("Reading the file rooms.bin.");
			 String room1 = inputStream.readUTF();
			 String room2 = inputStream.readUTF();
			 String room3 = inputStream.readUTF();
			 String room4 = inputStream.readUTF();
			 System.out.println("rooms read from file:");
			 System.out.println(room1);
			 System.out.println(room2);
			 System.out.println(room3);
			 System.out.println(room4);
			 dialogDisplay.append("The names of the rooms are the following \n");
			 dialogDisplay.append(room1+"\n"+room2+"\n"+room3+"\n"+room4+"\n");
			 inputStream.close();
			 }
			 catch(FileNotFoundException r)
			 {
			 System.out.println("Cannot find rooms.bin");
			}
			 catch(IOException r)
			 {
			 System.out.println("input for rooms.bin messed up");
			 }
			 
		}
	}
	//arrayList method here
	private class monsterListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)		
		{
			monList = new ArrayList<String>(3);
			try {
			File file = new File("monsters.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
			{
				String tempo= scan.nextLine();
				System.out.print(tempo+"\n");
				monList.add(tempo);
			}
			for(int i =0 ; i < 3 ; i ++)
			{
				dialogDisplay.append(monList.get(i)+"\n");
			}
			scan.close();
		}
		catch(FileNotFoundException r)
		{
			System.out.println("monsters.txt is missing");
		}
		}
	}
	
	//END OF LISTENER CLASSES
	//
	//
	public void generateMap(RoomList input) 
	{
		// FLUSHING THE monsters.txt file
		
		Room head = input.returnCurrentRoom();
		
		Room current;
		
		Room holder1,holder2,holder3,holder4;
	
		
		current = head;
		holder1 = head;
	
		current = current.setUp(new Room("It's only up from here, kinda",true));
		holder2 = current;
		monster1 = current.getMonster();
		

	
		current = current.setUp(new Room("This rooms really weird.",true));
		holder3 = current;
		monster2 = current.getMonster();

		current = current.setRight(new Room("This might be it!"));
		holder4 = current;
		current.setEnd();
		try {
			monster2.monFlush();
			}
			catch(IOException r)
			{
				System.out.println("monFlush method failed to find file");
			}
		try {
			King.printToFile();
			monster1.printToFile();
			monster2.printToFile();
		}
			catch(IOException e){
				System.out.println("error");
			}
		
		// begin room generation binary file
		try
		  {
		  ObjectOutputStream outputStream =
		  new ObjectOutputStream(
		 new FileOutputStream("rooms.bin"));
		  
		  outputStream.writeUTF(holder1+" 1");
		  outputStream.writeUTF(holder2+" 2");
		  outputStream.writeUTF(holder3+" 4");
		  outputStream.writeUTF(holder4+" 4");
		  
		  outputStream.close();
		  }
		 
		 catch (IOException e)
		  {
		  System.out.println("Problem with file output.");
		  }
		
	}	
	
//	class for GRAPHICS
	
	private class PaintPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			setBackground(new Color(186, 255, 201));
			
			g.drawString("You win!!!", 200, 200);
			g.drawString("You win!!!", 210, 210);
			g.drawString("You win!!!", 235, 235);
			g.drawString("You win!!!", 260, 260);
			g.drawString("You win!!!", 260, 210);
		}
	}
	
	private class DeathPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			setBackground(new Color(255,179,186));
			
			g.drawString("You died! Game over.", 250, 250);
		}
	}

}
