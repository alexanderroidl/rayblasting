package core;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static enum WindowMode {
		WINDOWED,
		FULLSCREEN
	}
	
	public static WindowMode mode;
	
	private Window(WindowMode mode) {
		this.setTitle(Config.name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Window.mode = mode;
		
		// Configure window
		
		this.setContentPane(new Main());
		
		if(mode == WindowMode.FULLSCREEN) {
			this.setUndecorated(true);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		} else {
			this.setResizable(false);
			
		}

		// Show window
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private Window() {
		this(Config.defaultWindowMode);
	}
	
	public static void main(String[] args) {
		new Window();
	}
}
