package niles.lwjgl.loop;

import org.joml.Vector4f;

import niles.lwjgl.rendering.Renderer;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;
import testing.test2;

public abstract class Game {
	
	private Window window;
	private Camera camera;
	private Renderer renderer;
	
	private Vector4f backgroundColor;
	private int fpsCap;
	
	public Game(int width,int height,boolean fullsceen,Vector4f backgroundColor,int fpsCap) {
		window=new Window(width, height, fullsceen);
		camera=new Camera(width, height);
		
		this.backgroundColor=backgroundColor;
		this.fpsCap=fpsCap;
		
		loop();
	}
	
	public Game() {
		window=new Window(1920, 1080, true);
		camera=new Camera(1920, 1080);
		
		this.backgroundColor=new Vector4f(0,0,0,1);
		this.fpsCap=120;
		
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
			
			window.clean();
			window.update(fpsCap);
			
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



	public int getFpsCap() {
		return fpsCap;
	}



	public void setFpsCap(int fps) {
		this.fpsCap = fps;
	}


	public Renderer getRenderer() {
		return renderer;
	}


	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	
	
	
	
	

}
