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

	public static Snake snake;
	public JFrame jframe;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(20, this);
	public ArrayList<Point> CialoWeza = new ArrayList<Point>();
	/** maksymalna wspó³rzêdna po przekroczeniu której w¹¿ walnie g³ow¹ w œcianê */
	public int max_x=80;//maksymalna wspó³rzêdna po przekroczeniu której w¹¿ walnie g³ow¹ w œcianê 
	public int max_y=67;//maksymalna wspó³rzêdna po przekroczeniu której w¹¿ walnie g³ow¹ w œcianê 
	public static final int Gora = 0, dol = 1, lewo = 2, prawo = 3, skala = 10;
	public int kierunek = dol, punkty, Dlugosc_weza;
	public int zmiana_dlugosci=1;
	public Point glowa, Jablko;
	public Random random;
	public boolean koniec, Pauza=false,czy_pierwsze_odpalenie=true;
	public Dimension Dimension;
/** domyœlny konstruktor klasy Snake, ustawiaj¹cy wyœwietlane okno i uruchamiaj¹cy grê*/
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
		Dlugosc_weza = 0;
		kierunek = dol;
		glowa = new Point(0, 0);
		random = new Random();
		CialoWeza.clear();
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

			CialoWeza.add(new Point(glowa.x, glowa.y));

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

			if (CialoWeza.size() > Dlugosc_weza)
			{
				CialoWeza.remove(0);//bez tej lini waz bedzie rosnac w nieskonczonosc

			}

			
				if (glowa.equals(Jablko))
				{
					punkty ++;
					Dlugosc_weza=Dlugosc_weza+zmiana_dlugosci;
					Jablko.setLocation(random.nextInt(max_x), random.nextInt(max_y));//
					//timer.setDelay(20/Dlugosc_weza);
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
		for (Point point : CialoWeza)
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
	public void keyReleased(KeyEvent e)//ta metoda musi zostaæ, by nie wyœwietla³y siê wyj¹tki po kompilacji
	{
	}

	@Override
	public void keyTyped(KeyEvent e)//ta metoda musi zostaæ, by nie wyœwietla³y siê wyj¹tki po kompilacji
	{
	}

}
