package testing;

import org.joml.Vector4f;
import static org.lwjgl.opengl.GL15.*;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.batch.Rect;
import niles.lwjgl.batch.Vao;
import niles.lwjgl.entites.Light;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Mouse;

public class Batch_testing extends Game{

	public static void main(String[] args) {
		new Batch_testing();
	}
	
	Batch objects;
	
	BatchRenderer renderer;
	
	Light lights;
	
	Texture t;
	
	@Override
	public void setup() {
		
		t = new Texture("res/floor.png");
		
		objects = new Batch(60000);
		
		for(int i = 0; i<200; i++) {
			objects.addRect((float)(Math.random()*800*1)-400*1, (float)(Math.random()*800*1)-400*1, 100f, 100f, new Vector4f(0, 0, 0, 1), 1);
		}
		
		renderer = new BatchRenderer();
		
		lights = new Light();
		lights.addLight(400, -300, 10, 100, new Vector4f(1f,0.4f,0.4f,1));
		lights.addLight(100, 200, 40, 90, new Vector4f(0.3f,1f,1f,1));
		lights.addLight(-400, 600, 40, 90, new Vector4f(0.6f,0.6f,1f,1));
		lights.addLight(100, -1000, 80, 100, new Vector4f(0.4f,1f,0.2f,1));
		lights.addLight(-200, 500, 40, 120, new Vector4f(1f,0.3f,0.1f,1));
		
		
		
		getWindow().setVSync(false);
	}
	
	@Override
	public void update() {
		Mouse.moveCamera(getWindow(), getCamera(), 1);
		Mouse.isVisible(getWindow(), false);
		
		renderer.bindShader();
		
		t.bind(0);
		
		objects.updateAllValues();
		
		renderer.useLights(getCamera(), lights);
		renderer.renderBatch(getCamera(), objects);
		
		
		setFpsCap(120);
		System.out.println(getWindow().getFps());
		
	}

}
