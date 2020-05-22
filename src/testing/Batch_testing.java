package testing;

import org.joml.Vector4f;
import static org.lwjgl.opengl.GL15.*;

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
	
	@Override
	public void setup() {
		shader = new Shader("batch_shader");
		batch = new Vao(100);
	}

	@Override
	public void update() {
		shader.bind();
		
		
		
		Rect[] rects = new Rect[2];
		rects[0] = new Rect(0.1f, 0f, 0.1f, 0.1f, new Vector4f(0, 1, 1, 1));
		rects[1] = new Rect(-0.5f, 0, 0.1f, 0.1f, new Vector4f(1, 0, 1, 1));
		
		
		float[] vertices = new float[7 * 4 * rects.length];
		
		int index = 0;
		for(int i = 0; i<rects.length; i++) {
			float[] temp = rects[i].toArray();
			for(int j = 0; j < temp.length; j++) {
				vertices[index + j] = temp[j];
				
			}
			index += (7 * 4);
		}
		
		glBindBuffer(GL_ARRAY_BUFFER, batch.getV_id());
		glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);
		
		
		batch.render();
		
		
		getWindow().update(120);
		System.out.println(getWindow().getFps());
		
	}

}
