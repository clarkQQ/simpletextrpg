import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
// has accessors, takeDamage, heal , printables
public class Slime extends Monster implements Printable {
	private String attack;
	private int attackDamage;
	BufferedWriter writer = null;
	public Slime()
	{
		super();
		attack = "Boop";
		name = "Slime";
		attackDamage = 4;
		hp = 20;
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
	
	public void heal()
	{
		hp+=2;
	}
	
	public String toString()
	{
		String display = "Slime, has "+this.getAttack()+" which deals "+this.getAttackDamage()+"damage";
		return display;
	}
	
	public void printToFile() throws IOException
	{
		//append to a text file, must be premade(monsters.txt)
		
			
			writer = new BufferedWriter(new FileWriter("monsters.txt",true));
			
			writer.write(this.getName()+" has "+this.getHp()+" HP and "
			+this.getAttack()+"s for "+this.getAttackDamage());
			writer.newLine();
			writer.close();
			
	}
	
	public void monFlush() throws IOException
	{
writer = new BufferedWriter(new FileWriter("monsters.txt"));
	}
	
	@Override
	public void display()
	{
		System.out.println(this.getName()+" HP:"+this.getHp());
	}
	
	
}
