package snake;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class Ranking extends JFrame
{
	JTable tabela_wynikow;
	String[] naglowki= {"Gracz","wynik"};
	String[][] wyniki= {{"Kamil","100"},{"Micha³","47"}};

	public Ranking() 
	{
		
		File WynikiWeza = new File("C:\\wynikiWeza.txt");
		boolean czy_istnieja_statystyki = WynikiWeza.exists();
		if (!czy_istnieja_statystyki)
		{
			File plik = new File("C:\\wynikiWeza.txt");
		}
			
		snake.CzytajTablice czytnik= new snake.CzytajTablice();
		try {
			czytnik.CzytajTabliceZpliku();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wyniki=CzytajTablice.Wyniki;
		
		Arrays.sort(wyniki, new Comparator<String[]>(){

		    @Override
		    public int compare(final String[] first, final String[] second){
		        // here you should usually check that first and second
		        // a) are not null and b) have at least two items
		        // updated after comments: comparing Double, not Strings
		        // makes more sense, thanks Bart Kiers
		        return Double.valueOf(second[1]).compareTo(
		            Double.valueOf(first[1])
		        );
		    }
		});
		
		tabela_wynikow=new JTable(wyniki,naglowki);
		this.add(tabela_wynikow);
		setSize(500,500);
		setTitle("Statystyki"); 
	}
		
}
