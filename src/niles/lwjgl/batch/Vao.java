package niles.lwjgl.batch;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL40.*;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
public class Vao {
	
	private int draw_count;
	private int v_id;
	private int t_id;
	private int i_id;
	
	public Vao(int maxRectangles) {
		
		
		
		
		float[] tex_coords=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		/*int[] indices=new int[] {
				0,1,2,
				2,3,0,
				
				4,5,6,
				6,7,4,
				
		};*/
		
		int[] indices=new int[maxRectangles * 6];
		int offset = 0;
		for(int i = 0; i < maxRectangles; i ++) {
			indices[i * 6  + 0] = 0 + offset;
			indices[i * 6  + 1] = 1 + offset;
			indices[i * 6  + 2] = 2 + offset;
			
			indices[i * 6  + 3] = 2 + offset;
			indices[i * 6  + 4] = 3 + offset;
			indices[i * 6  + 5] = 0 + offset;
			offset += 4;
		}
		
		draw_count=indices.length;
		
		
		v_id=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, 224 * maxRectangles, GL_DYNAMIC_DRAW);
		
		
		t_id=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, t_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords), GL_STATIC_DRAW);
		
		
		i_id=glGenBuffers();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		IntBuffer buffer=BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		init();
	}
	
	
	public void render() {
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 7 * 4, 0);
		
		glVertexAttribPointer(1, 4, GL_FLOAT, false, 7 * 4, 12);
		
		
		glBindBuffer(GL_ARRAY_BUFFER,t_id);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		
		
		glDrawElements(GL_TRIANGLES, draw_count,GL_UNSIGNED_INT,0);
		
		
	}
	
	public static void init() {
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);
	}
	
	private FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}


	public int getV_id() {
		return v_id;
	}


	public void setV_id(int v_id) {
		this.v_id = v_id;
	}


	public int getT_id() {
		return t_id;
	}


	public void setT_id(int t_id) {
		this.t_id = t_id;
	}


	public int getI_id() {
		return i_id;
	}


	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	
	
	
	
}

