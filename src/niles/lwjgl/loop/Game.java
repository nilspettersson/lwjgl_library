package niles.lwjgl.loop;

import org.joml.Vector4f;

import niles.lwjgl.rendering.Renderer;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

public abstract class Game {
	
	private Window window;
	private Camera camera;
	private Renderer renderer;
	
	private Vector4f backgroundColor;
	private int fps;
	
	public Game(int width,int height,boolean fullsceen,Vector4f backgroundColor,int fps) {
		window=new Window(width, height, fullsceen);
		camera=new Camera(width, height);
		
		this.backgroundColor=backgroundColor;
		this.fps=fps;
		
		loop();
	}
	
	
	public abstract void setup();
	
	public abstract void update();
	
	public void loop() {
		renderer=new Renderer();
		setup();
		
		while(window.shouldUpdate()) {
			window.drawInit(backgroundColor);
			
			
			update();
			
			
			window.update(fps);
			window.clean();
			
		}
		
		
		
	}
	
	

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}



	public Vector4f getBackgroundColor() {
		return backgroundColor;
	}



	public void setBackgroundColor(Vector4f backgroundColor) {
		this.backgroundColor = backgroundColor;
	}



	public int getFps() {
		return fps;
	}



	public void setFps(int fps) {
		this.fps = fps;
	}


	public Renderer getRenderer() {
		return renderer;
	}


	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	
	
	
	
	

}
