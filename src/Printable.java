//interface for monsters
import java.io.IOException;
public interface Printable {
	public String toString();
	public void display();
	public void printToFile() throws IOException ;
}
