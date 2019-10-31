import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Monster {
	BufferedWriter writer = null;
	int hp;
	String name;
	public Monster()
	{		
		hp = 1;
		name = "Default";
	}
	
	public Monster(int input, String namae)
	{
		hp = input;
		name = namae;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHp()
	{
		return hp;	
	}
	
	public void setName(String namae)
	{
		name = namae;
	}
	
	public void setHp(int input)
	{
		hp = input;
	}
	
	public void display()
	{
		System.out.println(name+", Health:: "+hp);
	}
	
	public void printToFile() throws IOException
	{
		//append to a text file, must be premade(monsters.txt)
		
		writer = new BufferedWriter(new FileWriter("monsters.txt",true));
//		writer = new BufferedWriter(new FileWriter("monsters.txt"));
		writer.write(this.getName()+" has "+this.getHp()+" HP");
		writer.newLine();
		writer.close();	
		
	}
	

}
