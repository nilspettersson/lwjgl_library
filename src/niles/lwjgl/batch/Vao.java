package niles.lwjgl.batch;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL40.*;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
public class Vao {
	
	private int draw_count;
	private int v_id;
	private int t_id;
	private int i_id;
	
	
	
	public static Vao CreateModel(boolean xyCenter) {
		float[] verticesTemp;
		if(xyCenter) {
			verticesTemp=new float[] {
			-1f,1f,0,
			1f,1f,0,
			1f,-1f,0,
			-1f,-1f,0
			};
		}
		else {
			verticesTemp=new float[] {
					0f,0f,0,
					1f,0f,0,
					1f,-1f,0,
					0f,-1f,0,
					
			};
		}
		
		
		float[] tex_coordsTemp=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		int[] indicesTemp=new int[] {
				0,1,2,
				2,3,0
		};
		
		return new Vao(verticesTemp, tex_coordsTemp, indicesTemp);
	}
	
	
	public static float[] RectVertices() {
		float[] verticesTemp;
		verticesTemp=new float[] {
			-1f,1f,0,
			1f,1f,0,
			1f,-1f,0,
			-1f,-1f,0
		};
		return verticesTemp;
	}
	
	public static int[] RectIndices() {
		int[] indicesTemp=new int[] {
				0,1,2,
				2,3,0
		};
		return indicesTemp;
	}
	
	
	
	
	
	
	public Vao(float[] vertices2, float[] tex_coords2, int[] indices2) {
		
		float[] vertices=new float[] {
				-1.5f, -0.5f, 0,
				-0.5f, -0.5f, 0,
				-0.5f, 0.5f, 0,
				-1.5f, 0.5f, 0,
				
				 0.5f, -0.5f, 0,
				 1.5f, -0.5f, 0,
				 1.5f, 0.5f, 0,
				 0.5f, 0.5f, 0,
				
		};
		
		
		float[] tex_coords=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		int[] indices=new int[] {
				0,1,2,
				2,3,0,
				
				4,5,6,
				6,7,4
		};
		
		draw_count=indices.length;
		
		
		v_id=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		
		
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
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER,t_id);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		
		
		glDrawElements(GL_TRIANGLES, draw_count,GL_UNSIGNED_INT,0);
		
		
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
	
}

