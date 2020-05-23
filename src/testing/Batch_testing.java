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
	
	Rect[] rects;
	
	float[] vertices;
	
	@Override
	public void setup() {
		shader = new Shader("batch_shader");
		batch = new Vao(1000000);
		
		rects = new Rect[100000];
		for(int i = 0; i<rects.length; i++) {
			rects[i] = new Rect((float)(Math.random())-0.5f, (float)(Math.random())-0.5f, 0.005f, 0.005f, new Vector4f(0, 1, 1, 1));
		}
		
		
		vertices = new float[7 * 4 * rects.length];
		
		int index = 0;
		for(int i = 0; i<rects.length; i++) {
			float[] temp = rects[i].toArray();
			System.arraycopy(temp, 0, vertices, index, temp.length);
			index += (7 * 4);
		}
		
		glBindBuffer(GL_ARRAY_BUFFER, batch.getV_id());
		glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);
		
		getWindow().setVSync(false);
	}

	@Override
	public void update() {
		shader.bind();
		
		
		
		float[] send = new float[7*4*100000];
		for(int i = 0; i<send.length; i++) {
			vertices[i]+=0.001f;
			send[i]=vertices[i];
		}
		
		
		
		
		
		
		glBindBuffer(GL_ARRAY_BUFFER, batch.getV_id());
		//glBufferSubData(GL_ARRAY_BUFFER, 0, send);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
		
		batch.render();
		
		setFpsCap(120);
		System.out.println(getWindow().getFps());
		
	}

}
