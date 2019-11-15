package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{

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
		
		String Statystyki = "punkty: " + snake.punkty + ", D³ugoœæ wê¿a: " + snake.Dlugosc_weza;		
		g.setColor(Color.white);		
		g.drawString(Statystyki, (int) (getWidth() / 2 - Statystyki.length() * 2.5), 10);

		String stan_gry;
		if (snake.koniec)
		{
			stan_gry = "Koniec Gry! Wciœnij spacje, by zagraæ jeszcze raz";
			g.drawString(stan_gry, (int) (getWidth() / 2 - stan_gry.length() * 2.5), (int) snake.Dimension.getHeight() / 4);
		}


		if (snake.Pauza && !snake.koniec)
		{
			stan_gry = "Gra zapauzowana, wciœnij ponownie spacje, by graæ dalej";
			g.drawString(stan_gry, (int) (getWidth() / 2 - stan_gry.length() * 2.5), (int) snake.Dimension.getHeight() / 4);
		}
	}
}
