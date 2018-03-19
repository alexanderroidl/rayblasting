package core;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Game;

@SuppressWarnings("serial")
public class Main extends Canvas {
	private long lastFpsTime;
	int fps;
	
	public static enum GameState {
		STARTING,
		LOADING,
		MAIN_MENU,
		GAME_LOADING,
		PLAYING,
		GAME_OVER
	}
	
	public static GameState gameState;
	
	public static Dimension screenSize;
	
	private Game game;
	
	public Main() {
		gameState = GameState.STARTING;
		
		game = new Game(Config.firstLevel);
		
        Thread gameThread = new Thread() {
            @Override
            public void run() {
                GameLoop();
            }
        };
        gameThread.start();
	}
	
	private void GameLoop() {
	   long lastLoopTime = System.nanoTime();
	   final long OPTIMAL_TIME = 1000000000 / Config.targetFPS;   
		   
		while(true) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			//double delta = updateLength / (double) OPTIMAL_TIME;
			
			// update the frame counter
			lastFpsTime += updateLength;
			fps++;
			  
			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
			     //System.out.println("(FPS: "+fps+")");
			     lastFpsTime = 0;
			     fps = 0;
			}

			switch(gameState) {
				case STARTING:
					// Still starting
					if(this.getWidth() < Config.windowedSize.width ||
						this.getHeight() < Config.windowedSize.height) break;
					
					// Ready
					gameState = GameState.LOADING;
					break;
				case LOADING:
					// Create renderer using screen size
					game.createRenderer(new Dimension(this.getWidth(), this.getHeight()));
					
					gameState = GameState.MAIN_MENU;
					break;
				case MAIN_MENU:
					gameState = GameState.GAME_LOADING;
					break;
				case GAME_LOADING:
					gameState = GameState.PLAYING;
					break;
				case PLAYING:
					game.update();
					break;
				case GAME_OVER:
					break;
				default:
					break;
			}
			repaint();
			
		    try { Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000); }
		    catch (InterruptedException ex) { }
		}
	}

	public void Draw(Graphics2D g2d) {
		if(gameState == GameState.PLAYING)
			game.draw(g2d);
	}

	public void onKeyReleased(KeyEvent e) {
	}

	public void onMouseReleased(MouseEvent e) {
	}
}
