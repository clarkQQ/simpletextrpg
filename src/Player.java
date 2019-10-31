import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//is Printable , attacks,damage,takeDamage, lukRoll,
public class Player extends Monster implements Printable {
	private String attack;
	private int attackDamage;
	private int diceRollVal;
	BufferedWriter writer = null;
	
	public Player()
	{
		super();
		attack = "Smash";
		name = "Player";
		attackDamage = 6;
		hp = 50;
	}
	
	public String getAttack()
	{
		return attack;
	}
	
	public int getAttackDamage()
	{
		return attackDamage;
	}
	
	public void takeDamage(int input)
	{
		hp-= input;
	}
	//simulate DungeonsnDragons roll, 20 side dice.
	public void lukRoll(int take)
	{
		int diceRoll = (int)(Math.random()*20)+1;
		//println to make sure method is working
		System.out.println(diceRoll+"-> rolled");
		take--;
	
		if(take > 0)		
			lukRoll(take);
		
		else		
			diceRollVal=diceRoll;	
	}
	public int getRoll(int rolls)
	{
		lukRoll(rolls);
		return diceRollVal;
	}
	
	
	public String toString()
	{
		String display = "The Player, has "+this.getAttack()+" which deals "+this.getAttackDamage()+"damage and has "+this.getHp()+" HP";
		return display;
	}
	
	public void printToFile() throws IOException
	{
		//append to a text file, must be premade(monsters.txt)
			
//			writer = new BufferedWriter(new FileWriter("monsters.txt",true)); <- the true makes it append instead of rewrite
			writer = new BufferedWriter(new FileWriter("monsters.txt",true));
//			writer = new BufferedWriter(new FileWriter("monsters.txt"));
			writer.write(this.getName()+" has "+this.getHp()+" HP and "
			+this.getAttack()+"s for "+this.getAttackDamage());
			writer.newLine();
			writer.close();	
	}
	
	@Override
	public void display()
	{
		System.out.println(this.getName()+" HP:"+this.getHp());
	}

}
