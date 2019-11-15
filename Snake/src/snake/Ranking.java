package snake;
import javax.swing.JFrame;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Ranking extends JFrame
{
	JTable tabela_wynikow;
	String[] naglowki= {"Gracz","wynik"};
	String[][] wyniki= {{"Kamil","100"},{"Micha³","47"}};


	public Ranking() 
	{
		tabela_wynikow=new JTable(wyniki,naglowki);
		this.add(tabela_wynikow);
		setSize(300,200);
		setTitle("Statystyki"); 
	}
		
}
