package testing;

import org.joml.Vector4f;
import static org.lwjgl.opengl.GL15.*;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.batch.Rect;
import niles.lwjgl.batch.Vao;
import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Shader;

public class Batch_testing extends Game{

	public static void main(String[] args) {
		new Batch_testing();
	}
	Shader shader;
	Vao batch;
	
	Rect[] rects;
	
	float[] vertices;
	
	Batch objects;
	
	@Override
	public void setup() {
		shader = new Shader("batch_shader");
		
		objects = new Batch(100000);
		
		for(int i = 0; i<10; i++) {
			objects.addRect((float)(Math.random())-0.5f, (float)(Math.random())-0.5f, 0.05f, 0.05f, new Vector4f(0, 1, 1, 1));
		}
		
		
		getWindow().setVSync(false);
	}
	
	@Override
	public void update() {
		shader.bind();
		
		
		objects.setY(0, objects.getY(0)+0.001f);
		objects.setX(0, objects.getX(0)+0.001f);
		
		objects.updateAllValues();
		objects.render();
		
		
		setFpsCap(120);
		//System.out.println(getWindow().getFps());
		
	}

}
