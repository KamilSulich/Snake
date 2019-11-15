package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
/** Klasa do rysowania na ekranie */
@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{
	/** Metoda do rysowania na ekranie */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);	
		Snake snake = Snake.snake;
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.green);
		for (Point point : snake.CialoWeza)
		{
			g.fillRect(point.x * Snake.skala, point.y * Snake.skala, Snake.skala, Snake.skala);
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(snake.glowa.x * Snake.skala, snake.glowa.y * Snake.skala, Snake.skala, Snake.skala);
		
		g.setColor(Color.RED);		
		g.fillRect(snake.Jablko.x * Snake.skala, snake.Jablko.y * Snake.skala, Snake.skala, Snake.skala);
		
		String Statystyki = "punkty: " + snake.punkty + ", D�ugo�� w�a: " + snake.Dlugosc_weza;		
		g.setColor(Color.white);		
		g.drawString(Statystyki, (int) (getWidth() / 2 - Statystyki.length() * 2.5), 10);

		String stan_gry;
		if (snake.koniec)
		{
			stan_gry = "Koniec Gry! Wci�nij spacje, by zagra� jeszcze raz";
			g.drawString(stan_gry, (int) (getWidth() / 2 - stan_gry.length() * 2.5), (int) snake.Dimension.getHeight() / 4);
		}


		if (snake.Pauza && !snake.koniec)
		{
			int wysokosc_zero= (int) snake.Dimension.getHeight()/4;
			int zmiana_wysokosci=(int)20;
			stan_gry = "Gra zapauzowana, wci�nij spacje, by gra� dalej.";
			String s0,s1,s2,s3,s4,s5,s6;
			s0="Zbieraj czerwone jab�ka by by� coraz d�u�szym, i zdobywa� coraz wi�cej punkt�w. Nie uderz w sw�j ogon, ani kraniec mapy.";
			s1="sterowanie:";
			s2="a lub strza�ka w lewo-kieruje w�a w lewo";
			s3="s lub strza�ka w d�-kieruje w�a w d�";
			s4="d lub strza�ka w prawo-kieruje w�a w prawo";
			s5="w lub strza�ka w g�r�-kieruje w�a w g�r�";
			s6="spacja-pauza";

			
			g.drawString(stan_gry, (int) (getWidth() / 2 - stan_gry.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s0, (int) (getWidth() / 2 - s0.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci+100;
			g.drawString(s1, (int) (getWidth() / 2 - s1.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s2, (int) (getWidth() / 2 - s2.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s3, (int) (getWidth() / 2 - s3.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s4, (int) (getWidth() / 2 - s4.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s5, (int) (getWidth() / 2 - s5.length() * 2.5), wysokosc_zero);
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s6, (int) (getWidth() / 2 - s6.length() * 2.5), wysokosc_zero);
		}
	}
}
