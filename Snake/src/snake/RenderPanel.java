package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{

	//public static final Color GREEN = new Color(1666073);

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
		
		String string = "punkty: " + snake.punkty + ", D³ugoœæ wê¿a: " + snake.Dlugosc_weza;
		
		g.setColor(Color.white);
		
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Koniec Gry! Wciœnij spacje, by zagraæ jeszcze raz";

		if (snake.koniec)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.Dimension.getHeight() / 4);
		}

		string = "Gra zapauzowana, wciœnij ponownie spacje, by graæ dalej";

		if (snake.Pauza && !snake.koniec)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.Dimension.getHeight() / 4);
		}
	}
}
