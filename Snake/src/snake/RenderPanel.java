package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel
{

	public static final Color GREEN = new Color(1666073);

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Snake snake = Snake.snake;

		g.setColor(Color.black);
		
		g.fillRect(0, 0, 800, 700);

		g.setColor(Color.GREEN);

		for (Point point : snake.CialoWeza)
		{
			g.fillRect(point.x * Snake.skala, point.y * Snake.skala, Snake.skala, Snake.skala);
		}
		
		g.fillRect(snake.head.x * Snake.skala, snake.head.y * Snake.skala, Snake.skala, Snake.skala);
		
		g.setColor(Color.RED);
		
		g.fillRect(snake.cherry.x * Snake.skala, snake.cherry.y * Snake.skala, Snake.skala, Snake.skala);
		
		String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 20;
		
		g.setColor(Color.white);
		
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Koniec Gry! Wciœnij spacje, by zagraæ jeszcze raz";

		if (snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}

		string = "Gra zapauzowana, wciœnij ponownie spacje, by graæ dalej";

		if (snake.paused && !snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}
	}
}
