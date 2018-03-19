package core;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.Window.WindowMode;

@SuppressWarnings("serial")
public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
	private static boolean[] keyboardState = new boolean[525];
	//private static boolean[] mouseState = new boolean[3];
	
	public Canvas() {
		// Create blank cursor
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		this.setCursor(blankCursor); // Set blank cursor
		this.setFocusable(true);
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
	public abstract void onKeyReleased(KeyEvent e);
	public abstract void onMouseReleased(MouseEvent e);
	
	public abstract void Draw(Graphics2D g2d);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Draw((Graphics2D)g);
	}
	
	public static boolean keyDown(int key) {
		return keyboardState[key];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboardState[e.getKeyCode()] = false;
		onKeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public Dimension getPreferredSize() {
		return (Window.mode == WindowMode.WINDOWED ? Config.windowedSize : super.getPreferredSize());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
    public void mouseReleased(MouseEvent e) {
		onMouseReleased(e);
	}
	
    @Override
    public void mouseClicked(MouseEvent e) {
    }
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
