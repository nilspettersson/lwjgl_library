package niles.lwjgl.batch;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.*;

import org.joml.Vector4f;

public class Batch {
	
	private float[] vertices;
	private int[] rectIds;
	private int index;
	
	private Vao vao;
	
	public Batch(int size) {
		vertices = new float[size * 4 * 7];
		rectIds = new int[size];
		
		vao = new Vao(size);
		
		index = 0;
	}
	
	public void addRect(float x, float y, float width, float height, Vector4f color) {
		Rect rect = new Rect(x, y, width, height, color);
		float[] rectVertices = rect.toArray();
		for(int i = 0; i< rectVertices.length; i++) {
			vertices[index + i] = rectVertices[i];
		}
		index += 7 * 4;
	}
	
	public void render() {
		vao.render();
	}
	
	
	public void updateMax() {
		glBindBuffer(GL_ARRAY_BUFFER, vao.getV_id());
		//glBufferSubData(GL_ARRAY_BUFFER, 0, send);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
	}
	
	public void updateAllValues() {
		glBindBuffer(GL_ARRAY_BUFFER, vao.getV_id());
		float[] subArray = new float[index];
		System.arraycopy(vertices, 0, subArray, 0, subArray.length);
		
		glBufferSubData(GL_ARRAY_BUFFER, 0, subArray);
		//glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
	}
	
	

}
