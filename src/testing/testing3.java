package testing;

import org.joml.Vector2f;
import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.world.Mouse;

public class testing3 extends Game {

	public static void main(String[] args) {
		new testing3();
	}
	
	ShadowModel shadow;
	
	
	BatchRenderer renderer;
	Batch batch;
	
	
	
	@Override
	public void setup() {
		setBackgroundColor(new Vector4f(0.7f, 0.7f, 0.7f, 1));
		
		renderer = new BatchRenderer();
		
		batch = new Batch(1);
		batch.addRect(-400, 300, 100, 100, new Vector4f(1, 0, 0, 1), 0);
		
		

		
		shadow = new ShadowModel(100);
		
		
		
		
	}

	@Override
	public void update() {
		
		shadow.createShadowFromBatch(batch, new Vector2f(Mouse.getMousePosition(getWindow(), 1).x, -Mouse.getMousePosition(getWindow(), 1).y), 10000);
		renderer.renderShadow(getCamera(), shadow);
		
		
		
		batch.updateMax();
		renderer.renderBatch(getCamera(), batch);
		

	}

}



