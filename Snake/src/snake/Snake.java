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

public class Snake implements ActionListener, KeyListener
{

	public static Snake snake;

	public JFrame jframe;

	public RenderPanel renderPanel;

	public Timer timer = new Timer(20, this);

	public ArrayList<Point> CialoWeza = new ArrayList<Point>();

	public static final int Gora = 0, dol = 1, lewo = 2, prawo = 3, skala = 10;

	public int ile_tykniec_zegara = 0, direction = dol, score, tailLength, time;

	public Point head, cherry;

	public Random random;

	public boolean over = false, paused;

	public Dimension dim;

	public Snake()
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(810, 710);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}

	public void startGame()
	{
		over = false;
		paused = false;
		time = 0;
		score = 0;
		tailLength = 1;
		ile_tykniec_zegara = 0;
		direction = dol;
		head = new Point(0, 0);
		random = new Random();
		CialoWeza.clear();
		cherry = new Point(random.nextInt(79), random.nextInt(66));
		timer.setDelay(20);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		renderPanel.repaint();
		ile_tykniec_zegara++;

		if (ile_tykniec_zegara % 2 == 0 && !over && !paused)
		{
			time++;

			CialoWeza.add(new Point(head.x, head.y));

			if (direction == Gora)
			{
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
				{
					head = new Point(head.x, head.y - 1);
				}
				else
				{
					over = true;

				}
			}

			if (direction == dol)
			{
				if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1))
				{
					head = new Point(head.x, head.y + 1);
				}
				else
				{
					over = true;
				}
			}

			if (direction == lewo)
			{
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
				{
					head = new Point(head.x - 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction == prawo)
			{
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y))
				{
					head = new Point(head.x + 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			if (CialoWeza.size() > tailLength)
			{
				CialoWeza.remove(0);

			}

			if (cherry != null)
			{
				if (head.equals(cherry))
				{
					score += 10;
					tailLength++;
					cherry.setLocation(random.nextInt(79), random.nextInt(66));
					timer.setDelay(20/tailLength);
				}
			}
		}
	}

	public boolean noTailAt(int x, int y)
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

	public static void main(String[] args)
	{
		snake = new Snake();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != prawo)
		{
			direction = lewo;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != lewo)
		{
			direction = prawo;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != dol)
		{
			direction = Gora;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != Gora)
		{
			direction = dol;
		}

		if (i == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				startGame();
			}
			else
			{
				paused = !paused;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
