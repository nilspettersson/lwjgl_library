package niles.lwjgl.batch;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glBufferSubData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.joml.Vector4f;

import niles.lwjgl.util.Texture;

public class Batch {
	
	//private float[] vertices;
	private int[] rectIds;
	private int index;
	
	private ArrayList<Texture>textures;
	
	private Vao vao;
	
	private FloatBuffer vertices;
	
	private int size;
	
	public Batch(int size) {
		//vertices = new float[size * 4 * Vertex.size];
		
		ByteBuffer bb = ByteBuffer.allocateDirect(size * 4 * Vertex.size * 4);
	    bb.order(ByteOrder.nativeOrder());
	    vertices = bb.asFloatBuffer();
		
		
		
		rectIds = new int[size];
		
		vao = new Vao(size);
		textures = new ArrayList<Texture>();
		
		index = 0;
		
		size = 0;
	}
	
	public void addTexture(Texture texture) {
		textures.add(texture);
	}
	public void bindTextures() {
		for(int i = 0; i < textures.size(); i++) {
			textures.get(i).bind(i);
		}
	}
	
	
	public void addRect(float x, float y, float width, float height, Vector4f color, int textureId) {
		Rect rect = new Rect(x, y, width, height, color, textureId);
		float[] rectVertices = rect.toArray();
		for(int i = 0; i < rectVertices.length; i++) {
			//vertices[index + i] = rectVertices[i];
			//System.out.println(vertices);
			vertices.put(index + i, rectVertices[i]);
		}
		index += Vertex.size * 4;
		size++;
	}
	
	
	
	public void setX(int index, float x) {
		int start = index * Vertex.size * 4;
		//float offset = vertices[start + 2 * Vertex.size] - vertices[start];
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 1 || i == 2) {
				//vertices[start + i * Vertex.size] = x + offset;
				vertices.put(start + i * Vertex.size, x + offset);
			}
			else {
				//vertices[start + i * Vertex.size] = x;
				vertices.put(start + i * Vertex.size, x);
			}
		}
	}
	
	public void setY(int index, float y) {
		int start = index * Vertex.size * 4 + 1;
		//float offset = vertices[start + 2 * Vertex.size] - vertices[start];
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 2 || i == 3) {
				//vertices[start + i * Vertex.size] = y + offset;
				vertices.put(start + i * Vertex.size, y + offset);
			}
			else {
				//vertices[start + i * Vertex.size] = y;
				vertices.put(start + i * Vertex.size, y);
			}
		}
	}
	
	public void setColor(int index, Vector4f color) {
		int start = index * Vertex.size * 4 + 3;
		//float offset = vertices[start + 2 * Vertex.size] - vertices[start];
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			/*vertices[start + i * Vertex.size + 0] = color.x;
			vertices[start + i * Vertex.size + 1] = color.y;
			vertices[start + i * Vertex.size + 2] = color.z;
			vertices[start + i * Vertex.size + 3] = color.w;*/
			
			vertices.put(start + i * Vertex.size + 0, color.x);
			vertices.put(start + i * Vertex.size + 1, color.y);
			vertices.put(start + i * Vertex.size + 2, color.z);
			vertices.put(start + i * Vertex.size + 3, color.w);
		}
	}
	
	
	public int size() {
		return size;
	}
	
	
	public float getX(int index) {
		//return vertices[index * Vertex.size * 4];
		return vertices.get(index * Vertex.size * 4);
	}
	public float getY(int index) {
		//return vertices[index * Vertex.size * 4 + 1];
		return vertices.get(index * Vertex.size * 4 + 1);
	}
	
	
	public void updateMax() {
		glBindBuffer(GL_ARRAY_BUFFER, vao.getV_id());
		//glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
		
		
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
	}
	
	
	public void updateAllValues() {
		glBindBuffer(GL_ARRAY_BUFFER, vao.getV_id());
		float[] subArray = new float[index];
		System.arraycopy(vertices, 0, subArray, 0, subArray.length);
		
		glBufferSubData(GL_ARRAY_BUFFER, 0, subArray);
		
	}

	public Vao getVao() {
		return vao;
	}

	public void setVao(Vao vao) {
		this.vao = vao;
	}
	
	
	
	

}
