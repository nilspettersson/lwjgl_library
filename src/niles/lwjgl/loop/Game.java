package niles.lwjgl.loop;

import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.entites.Light;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

public abstract class Game {
	
	private Window window;
	private Camera camera;
	private BatchRenderer renderer;
	private LayerSystem layers;
	
	
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
		renderer=new BatchRenderer();
		layers = new LayerSystem();
		
		setup();
		
		while(window.shouldUpdate()) {
			window.drawInit(backgroundColor);
			
			update();
			
			window.clean();
			window.update(fpsCap);
			
		}
		
	}
	
	public void render(int layer, boolean updateBuffer) {
		if(updateBuffer) {
			layers.getLayers().get(layer).updateBuffer();
		}
		renderer.renderBatch(camera, layers.getLayers().get(layer).getBatch());
	}
	
	public void render(int layer, boolean updateBuffer, Light lights) {
		if(updateBuffer) {
			layers.getLayers().get(layer).updateBuffer();
		}
		renderer.useLights(camera, lights);
		renderer.renderBatch(camera, layers.getLayers().get(layer).getBatch());
	}
	
	public Layer getLayer(int layer) {
		return layers.getLayers().get(layer);
	}
	
	
	public void addLayer(int maxEntities) {
		layers.getLayers().add(new Layer(maxEntities));
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

	public int getFps() {
		return window.getFps();
	}

	public BatchRenderer getRenderer() {
		return renderer;
	}


	public void setRenderer(BatchRenderer renderer) {
		this.renderer = renderer;
	}
	
	
	
	
	
	

}
