package testing;

import org.joml.Vector2f;
import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.entites.Light;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.world.Mouse;

public class testing3 extends Game {

	public static void main(String[] args) {
		new testing3();
	}
	
	
	Light lights;
	
	ShadowModel shadow;
	
	
	BatchRenderer renderer;
	Batch batch;
	
	Batch bg;
	
	
	
	@Override
	public void setup() {
		setBackgroundColor(new Vector4f(0.7f, 0.7f, 0.7f, 1));
		
		renderer = new BatchRenderer();
		
		
		lights = new Light();
		lights.addLight(0, 0, 2, 40, new Vector4f(1, 1, 1, 1));
		
		
		batch = new Batch(100);
		for(int i = 0; i < 100; i++) {
			batch.addRect((float)(Math.random()*1200)-600, (float)(Math.random()*1200)-600, 10, 10, new Vector4f(1, 0.4f, 0.4f, 1), 0);
		}
		
		bg = new Batch(1);
		bg.addRect(0 - 1000, 0 + 1000, 2000, 2000, new Vector4f(0.6f, 0.6f, 1, 1), 0);

		
		shadow = new ShadowModel(100000);
		
		getWindow().setVSync(false);
	}

	@Override
	public void update() {
		renderer.useLights(getCamera(), lights);
		
		bg.updateMax();
		renderer.renderBatch(getCamera(), bg);
		
		lights.moveTo(0, Mouse.getMousePosition(getWindow(), 1).x, -Mouse.getMousePosition(getWindow(), 1).y, 10f);
		
		shadow.createShadowFromBatch(batch, new Vector2f(Mouse.getMousePosition(getWindow(), 1).x, -Mouse.getMousePosition(getWindow(), 1).y), 10000);
		renderer.renderShadow(getCamera(), shadow);
		
		
		
		batch.updateMax();
		renderer.renderBatch(getCamera(), batch);
		
		
		System.out.println(getWindow().getFps());
		setFpsCap(1200);

	}

}



