package snake;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;


@SuppressWarnings("serial")
public class Ranking extends JFrame
{
	JTable tabela_wynikow;
	String[] naglowki= {"Gracz","wynik"};
	String[][] wyniki= {{"Kamil","100"},{"Micha³","47"}};

	

	public Ranking() 
	{
		
		snake.CzytajTablice czytnik= new snake.CzytajTablice();
		try {
			czytnik.CzytajTabliceZpliku();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wyniki=CzytajTablice.Wyniki;
		tabela_wynikow=new JTable(wyniki,naglowki);
		this.add(tabela_wynikow);
		setSize(300,200);
		setTitle("Statystyki"); 
	}
		
}
