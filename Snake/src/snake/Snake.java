package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
/** domyœlny  klasa do gry w Snake*/
public class Snake implements ActionListener, KeyListener
{
	/** klasa snake */
	public static Snake snake;
	/** obiekt typu Jframe do rysowania w oknie */
	public JFrame jframe;
	/** obiekt typu RenderPanel, do rysowania w oknie */
	public RenderPanel renderPanel;
	/**Obiekt typu timer, uruchamiajacy co okreslon¹ iloœæ milisekund wydarzenie */
	public Timer timer = new Timer(20, this);
	/** lista punktów o wspó³rzêdnych x,y na których jest ogon wê¿a */
	public ArrayList<Point> OgonWeza = new ArrayList<Point>();
	/** maksymalna wspó³rzêdna x po przekroczeniu której w¹¿ uderzy g³ow¹ w œcianê */
	public int max_x=80;
	/** maksymalna wspó³rzêdna y po przekroczeniu której w¹¿ uderzy g³ow¹ w œcianê */
	public int max_y=67;
	/** zmienna definiuj¹ca kierunek */
	public static final int Gora = 0;
	/** zmienna definiuj¹ca kierunek */
	public static final int	dol = 1; 
	/** zmienna definiuj¹ca kierunek */
	public static final int	lewo = 2;
	/** zmienna definiuj¹ca kierunek */
	public static final int	prawo = 3;
	/** Skala gry, im wiêksza tym wiêskze ,,piksele" */
	public static final int	skala = 10;
	/** kierunek poruszania siê wê¿a */
	public int kierunek = dol;
	/** ile punktów zdoby³ gracz */
	public int punkty;
	/**jak d³ugi jest ogon */
	public int	Dlugosc_ogonu;
	/**o ile ma wyd³u¿aæ siê ogon, gdy w¹¿ zje jab³ko */
	public int zmiana_dlugosci=1;
	/**Gdzie jest g³owa */
	public Point glowa;
	/**Gdzie jest Jablko */
	public Point Jablko;
	/**Zmienna do losowania wspó³rzêdnych punktów */
	public Random random;
	/** zmienna zwracaj¹ca true je¿eli gracz przegra³, a jeœli nie to false*/
	public boolean koniec;
	/** czy gra jest zapauzowana*/
	public boolean Pauza=false;
	/**Czy to pierwsza rozgrywka od uruchomienia programu */
	public boolean czy_pierwsze_odpalenie=true;
	/** rozmiary ekranu */
	public Dimension Dimension;
/** domyœlny konstruktor klasy Snake, ustawiaj¹cy wyœwietlane okno i uruchamiaj¹cy grê*/
	Ranking ranking=new Ranking();
	/** czy pokazaæ ranking */
	public boolean pokaz_ranking=false;

	public Snake()
	{
		Dimension = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Gra Snake");
		jframe.setVisible(true);
		jframe.setSize(815, 710);
		jframe.setResizable(false);
		jframe.setLocation(Dimension.width / 2 - jframe.getWidth() / 2, Dimension.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		UruchomGre();
	}
	/** Metoda uruchamiaj¹ca grê*/
	public void UruchomGre()
	{
		koniec = false;
		if (czy_pierwsze_odpalenie)
		{
		Pauza = true;
		}
		punkty = 0;
		Dlugosc_ogonu = 0;
		kierunek = dol;
		glowa = new Point(0, 0);
		random = new Random();
		OgonWeza.clear();
		Jablko = new Point(random.nextInt(max_x), random.nextInt(max_y));
		timer.setDelay(40);
		timer.start();
	}
	/** Metoda uruchamiaj¹ca siê co takt zegara timer. G³ówny mechanizm gry.*/
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		renderPanel.repaint();

		if (!koniec && !Pauza)
		{

			OgonWeza.add(new Point(glowa.x, glowa.y));

			if (kierunek == Gora)
			{
				if (glowa.y - 1 >= 0 && BrakOgona(glowa.x, glowa.y - 1))
				{
					glowa = new Point(glowa.x, glowa.y - 1);
				}
				else
				{
					koniec = true;
					czy_pierwsze_odpalenie=false;

				}
			}

			if (kierunek == dol)
			{
				if (glowa.y + 1 < max_y && BrakOgona(glowa.x, glowa.y + 1))
				{
					glowa = new Point(glowa.x, glowa.y + 1);
				}
				else
				{
					koniec = true;
					czy_pierwsze_odpalenie=false;

				}
			}

			if (kierunek == lewo)
			{
				if (glowa.x - 1 >= 0 && BrakOgona(glowa.x - 1, glowa.y))
				{
					glowa = new Point(glowa.x - 1, glowa.y);
				}
				else
				{
					koniec = true;
					czy_pierwsze_odpalenie=false;

				}
			}

			if (kierunek == prawo)
			{
				if (glowa.x + 1 < max_x && BrakOgona(glowa.x + 1, glowa.y))
				{
					glowa = new Point(glowa.x + 1, glowa.y);
				}
				else
				{
					koniec = true;
					czy_pierwsze_odpalenie=false;
				}
			}

			if (OgonWeza.size() > Dlugosc_ogonu)
			{
				OgonWeza.remove(0);//bez tej lini waz bedzie rosnac w nieskonczonosc

			}

			
				if (glowa.equals(Jablko))
				{
					punkty ++;
					Dlugosc_ogonu=Dlugosc_ogonu+zmiana_dlugosci;
					Jablko.setLocation(random.nextInt(max_x), random.nextInt(max_y));//
					//timer.setDelay(20/Dlugosc_ogonu);
				}
			
		}
	}
	
	/** Metoda sprawdzaj¹ca czy pod punktem o wspó³rzêdnych x,y jest ogon wê¿a. Jeœli jest, zwraca false, w przeciwnym wypadku zwraca true
	 *@return False je¿eli w punkcie jest ogon, true je¿eli go nie ma
	 *@param x wspó³rzêdna x sprawdzanego punktu
	 *@param y wspó³rzêdna y sprawdzanego punktu
	 */
	public boolean BrakOgona(int x, int y)
	{
		for (Point point : OgonWeza)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
	/** Metoda Main, uruchamiaj¹ca program 
	 *@param args argumenty uruchomienia programu
	 * */
	public static void main(String[] args)
	{
		snake = new Snake();
	}

	/** Metoda sprawdzaj¹ca jaki klawisz jest wciœniêty */
	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && kierunek != prawo)//je¿eli klikniêto przycisk a lub strza³kê w lewo,  jedonczeœnie w¹¿ nie porusza siê w prawo
		{
			kierunek = lewo;//zmieñ kierunek na lewo
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && kierunek != lewo)//analogicznie jak wy¿ej
		{
			kierunek = prawo;//analogicznie jak wy¿ej
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && kierunek != dol)//analogicznie jak wy¿ej
		{
			kierunek = Gora;//analogicznie jak wy¿ej
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && kierunek != Gora)//analogicznie jak wy¿ej
		{
			kierunek = dol;//analogicznie jak wy¿ej
		}
		if (i == KeyEvent.VK_N )
		{
			pokaz_ranking=!pokaz_ranking;
			if (pokaz_ranking) 
			{
				ranking.setVisible(true);
				Pauza=true;
				pokaz_ranking=!pokaz_ranking;
			}
			else
			{
				ranking.setVisible(false);				
				Pauza=false;
			}
		}
		

		if (i == KeyEvent.VK_SPACE)//jeœli wciœniêto spacje
		{
			if (koniec)
			{
				UruchomGre();
			}
			else
			{
				Pauza = !Pauza;//zmieñ stan pauzy na przeciwny
			}
		}
	}
	/** ta metoda musi zostaæ, by nie wyœwietla³y siê wyj¹tki po kompilacji */
	@Override
	public void keyReleased(KeyEvent e)
	{
	}
	/** ta metoda musi zostaæ, by nie wyœwietla³y siê wyj¹tki po kompilacji */
	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
