package niles.lwjgl.shadows;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL40.*;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.world.Mouse;
public class ShadowModel {
	
	private int draw_count;
	private int v_id;
	private int i_id;
	
	private int shadowQuadSize;
	
	public ShadowModel(int shadowQuadSize) {
		
		this.shadowQuadSize = shadowQuadSize;
				
		
		v_id=glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, v_id);
		glBufferData(GL_ARRAY_BUFFER, 3 * 3 *4 *shadowQuadSize, GL_DYNAMIC_DRAW);
		
		
		
		i_id=glGenBuffers();
		
		int[] indices=new int[shadowQuadSize * 6];
		int offset = 0;
		for(int i = 0; i < shadowQuadSize; i ++) {
			indices[i * 6  + 0] = 2 + offset;
			indices[i * 6  + 1] = 3 + offset;
			indices[i * 6  + 2] = 1 + offset;
			
			indices[i * 6  + 3] = 0 + offset;
			indices[i * 6  + 4] = 1 + offset;
			indices[i * 6  + 5] = 2 + offset;
			offset += 4;
		}
		
		/*int[] indices=new int[] {
				2, 3, 1,
				0, 1, 2,
				
				2 + 4, 3 + 4, 1 + 4,
				0 + 4, 1 + 4, 2 + 4,
				
		};*/
		
		draw_count = indices.length;
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		IntBuffer buffer=BufferUtils.createIntBuffer(indices.length);
		buffer.put(indices);
		buffer.flip();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		init();
	}
	
	
	public void render() {
		
		glBindBuffer(GL_ARRAY_BUFFER,v_id);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
		
		glDrawElements(GL_TRIANGLES, draw_count,GL_UNSIGNED_INT,0);
	}
	
	public void createShadowFromBatch(Batch batch, Vector2f point, float shadowLength) {
		int index = 0;
		
		float[] vertices = new float[shadowQuadSize];
		
		for(int i = 0; i < batch.size(); i++) {
			float[] temp = batch.getShadowFromPoint(i, point, shadowLength);
			vertices[index + 0] = temp[0];
			vertices[index + 1] = temp[1];
			vertices[index + 2] = temp[2];
			
			vertices[index + 3] = temp[3];
			vertices[index + 4] = temp[4];
			vertices[index + 5] = temp[5];
			
			vertices[index + 6] = temp[6];
			vertices[index + 7] = temp[7];
			vertices[index + 8] = temp[8];
			
			vertices[index + 9] = temp[9];
			vertices[index + 10] = temp[10];
			vertices[index + 11] = temp[11];
			
			
			
			vertices[index + 12] = temp[12];
			vertices[index + 13] = temp[13];
			vertices[index + 14] = temp[14];
			
			vertices[index + 15] = temp[15];
			vertices[index + 16] = temp[16];
			vertices[index + 17] = temp[17];
			
			vertices[index + 18] = temp[18];
			vertices[index + 19] = temp[19];
			vertices[index + 20] = temp[20];
			
			vertices[index + 21] = temp[21];
			vertices[index + 22] = temp[22];
			vertices[index + 23] = temp[23];
			
			
			
			
			vertices[index + 24] = temp[24];
			vertices[index + 25] = temp[25];
			vertices[index + 26] = temp[26];
			
			vertices[index + 27] = temp[27];
			vertices[index + 28] = temp[28];
			vertices[index + 29] = temp[29];
			
			vertices[index + 30] = temp[30];
			vertices[index + 31] = temp[31];
			vertices[index + 32] = temp[32];
			
			vertices[index + 33] = temp[33];
			vertices[index + 34] = temp[34];
			vertices[index + 35] = temp[35];
			
			
			
			vertices[index + 36] = temp[36];
			vertices[index + 37] = temp[37];
			vertices[index + 38] = temp[38];
			
			vertices[index + 39] = temp[39];
			vertices[index + 40] = temp[40];
			vertices[index + 41] = temp[41];
			
			vertices[index + 42] = temp[42];
			vertices[index + 43] = temp[43];
			vertices[index + 44] = temp[44];
			
			vertices[index + 45] = temp[45];
			vertices[index + 46] = temp[46];
			vertices[index + 47] = temp[47];
			
			
			
			index += temp.length;
		}
		
		glBindBuffer(GL_ARRAY_BUFFER, getV_id());
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
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
