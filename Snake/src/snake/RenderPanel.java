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
		/** zmienna typu snake */
		Snake snake = Snake.snake;
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.green);
		for (Point point : snake.OgonWeza)
		{
			g.fillRect(point.x * Snake.skala, point.y * Snake.skala, Snake.skala, Snake.skala);
		}
		
		g.setColor(Color.BLUE);
		g.fillRect(snake.glowa.x * Snake.skala, snake.glowa.y * Snake.skala, Snake.skala, Snake.skala);
		
		g.setColor(Color.RED);		
		g.fillRect(snake.Jablko.x * Snake.skala, snake.Jablko.y * Snake.skala, Snake.skala, Snake.skala);
		
		/** wyœwietla na bierz¹co statystyki gry */
		String Statystyki = "punkty: " + snake.punkty + ", D³ugoœæ ogonu: " + snake.Dlugosc_ogonu;		
		g.setColor(Color.white);		
		g.drawString(Statystyki, (int) (getWidth() / 2 - Statystyki.length() * 2.5), 10);

		/** zmienna okreslaj¹ca czy jest pauza lub koniec gry */
		String stan_gry;
		if (snake.koniec)
		{
			stan_gry = "Koniec Gry! Wciœnij spacje, by zagraæ jeszcze raz";
			g.drawString(stan_gry, (int) (getWidth() / 2 - stan_gry.length() * 2.5), (int) snake.Dimension.getHeight() / 4);
		}


		if (snake.Pauza && !snake.koniec)
		{
			/** okreœla wysokoœæ na jakiej ma siê pojawiæ pierwsza linia tekstu */
			int wysokosc_zero= (int) snake.Dimension.getHeight()/4;
			/** okreœla odstêp miêdzy liniami tekstu */
			int zmiana_wysokosci=(int)20;
			stan_gry = "Gra zapauzowana, wciœnij spacje, by graæ dalej.";
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s0;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s1;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s2;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s3;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s4;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s5;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s6;
			/** zmienna do przechowywania wyœwietlanego tekstu */
			String s7;
			s0="Zbieraj czerwone jab³ka by byæ coraz d³u¿szym, i zdobywaæ coraz wiêcej punktów. Nie uderz w swój ogon, ani kraniec mapy.";
			s1="sterowanie:";
			s2="a lub strza³ka w lewo-kieruje wê¿a w lewo";
			s3="s lub strza³ka w dó³-kieruje wê¿a w dó³";
			s4="d lub strza³ka w prawo-kieruje wê¿a w prawo";
			s5="w lub strza³ka w górê-kieruje wê¿a w górê";
			s6="spacja-pauza";
			s7="n-poka¿ statystyki najlepszych graczy";
			
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
			wysokosc_zero=wysokosc_zero+zmiana_wysokosci;
			g.drawString(s7, (int) (getWidth() / 2 - s7.length() * 2.5), wysokosc_zero);
		}
	}
}
