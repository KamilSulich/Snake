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
/** domy�lny  klasa do gry w Snake*/
public class Snake implements ActionListener, KeyListener
{
	/** klasa snake */
	public static Snake snake;
	/** obiekt typu Jframe do rysowania w oknie */
	public JFrame jframe;
	/** obiekt typu RenderPanel, do rysowania w oknie */
	public RenderPanel renderPanel;
	/**Obiekt typu timer, uruchamiajacy co okreslon� ilo�� milisekund wydarzenie */
	public Timer timer = new Timer(20, this);
	/** lista punkt�w o wsp�rz�dnych x,y na kt�rych jest ogon w�a */
	public ArrayList<Point> OgonWeza = new ArrayList<Point>();
	/** maksymalna wsp�rz�dna x po przekroczeniu kt�rej w�� walnie g�ow� w �cian� */
	public int max_x=80;
	/** maksymalna wsp�rz�dna y po przekroczeniu kt�rej w�� walnie g�ow� w �cian� */
	public int max_y=67;
	/** zmienna definiuj�ca kierunek */
	public static final int Gora = 0;
	/** zmienna definiuj�ca kierunek */
	public static final int	dol = 1; 
	/** zmienna definiuj�ca kierunek */
	public static final int	lewo = 2;
	/** zmienna definiuj�ca kierunek */
	public static final int	prawo = 3;
	/** Skala gry, im wi�ksza tym wi�skze ,,piksele" */
	public static final int	skala = 10;
	/** kierunek poruszania si� w�a */
	public int kierunek = dol;
	/** ile punkt�w zdoby� gracz */
	public int punkty;
	
	public int	Dlugosc_ogonu;
	public int zmiana_dlugosci=1;
	public Point glowa, Jablko;
	public Random random;
	public boolean koniec, Pauza=false,czy_pierwsze_odpalenie=true;
	public Dimension Dimension;
/** domy�lny konstruktor klasy Snake, ustawiaj�cy wy�wietlane okno i uruchamiaj�cy gr�*/
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
	/** Metoda uruchamiaj�ca gr�*/
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
	/** Metoda uruchamiaj�ca si� co takt zegara timer. G��wny mechanizm gry.*/
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
	
	/** Metoda sprawdzaj�ca czy pod punktem o wsp�rz�dnych x,y jest ogon w�a. Je�li jest, zwraca false, w przeciwnym wypadku zwraca true
	 *@return False je�eli w punkcie jest ogon, true je�eli go nie ma
	 *@param x wsp�rz�dna x sprawdzanego punktu
	 *@param y wsp�rz�dna y sprawdzanego punktu
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
	/** Metoda Main, uruchamiaj�ca program 
	 *@param args argumenty uruchomienia programu
	 * */
	public static void main(String[] args)
	{
		snake = new Snake();
	}

	/** Metoda sprawdzaj�ca jaki klawisz jest wci�ni�ty */
	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && kierunek != prawo)
		{
			kierunek = lewo;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && kierunek != lewo)
		{
			kierunek = prawo;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && kierunek != dol)
		{
			kierunek = Gora;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && kierunek != Gora)
		{
			kierunek = dol;
		}

		if (i == KeyEvent.VK_SPACE)
		{
			if (koniec)
			{
				UruchomGre();
			}
			else
			{
				Pauza = !Pauza;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)//ta metoda musi zosta�, by nie wy�wietla�y si� wyj�tki po kompilacji
	{
	}

	@Override
	public void keyTyped(KeyEvent e)//ta metoda musi zosta�, by nie wy�wietla�y si� wyj�tki po kompilacji
	{
	}

}
