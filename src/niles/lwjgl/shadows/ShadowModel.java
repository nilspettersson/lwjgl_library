package niles.lwjgl.shadows;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL40.*;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
public class ShadowModel {
	
	private int draw_count;
	private int v_id;
	private int i_id;
	
	private float[] vertices;
	
	
	public ShadowModel(float[] vertices, int[] indices) {
		draw_count=indices.length;
		
		this.vertices=vertices;
		
		
		v_id=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, 3 * 3 *4 *1000, GL_DYNAMIC_DRAW);
		
		
		
		i_id=glGenBuffers();
		
		/*glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		IntBuffer buffer=BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();*/
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, 2* 2 * 4 * 1000, GL_DYNAMIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		init();
	}
	
	
	public void render() {
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		
		glDrawElements(GL_TRIANGLE_FAN, draw_count,GL_UNSIGNED_INT,0);
	}
	
	public static void init() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
	}
	
	private FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}


	public int getV_id() {
		return v_id;
	}


	public void setV_id(int v_id) {
		this.v_id = v_id;
	}


	public int getI_id() {
		return i_id;
	}


	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	
	
	
}
