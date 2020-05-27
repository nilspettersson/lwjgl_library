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

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.util.Texture;

public class Batch {
	
	private int index;
	
	private ArrayList<Texture>textures;
	
	private Vao vao;
	
	private FloatBuffer vertices;
	
	private int size;
	
	public Batch(int size) {
		ByteBuffer bb = ByteBuffer.allocateDirect(size * 4 * Vertex.size * 4);
	    bb.order(ByteOrder.nativeOrder());
	    vertices = bb.asFloatBuffer();
		
		
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
			vertices.put(index + i, rectVertices[i]);
		}
		index += Vertex.size * 4;
		size++;
	}
	
	
	
	public void setX(int index, float x) {
		int start = index * Vertex.size * 4;
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 1 || i == 2) {
				vertices.put(start + i * Vertex.size, x + offset);
			}
			else {
				vertices.put(start + i * Vertex.size, x);
			}
		}
	}
	
	public void setY(int index, float y) {
		int start = index * Vertex.size * 4 + 1;
		
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 2 || i == 3) {
				vertices.put(start + i * Vertex.size, y + offset);
			}
			else {
				vertices.put(start + i * Vertex.size, y);
			}
		}
	}
	
	public void setWidth(int index, float width) {
		int start = index * Vertex.size * 4 + 0;
		
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 1 || i == 2) {
				vertices.put(start + i * Vertex.size, vertices.get(start + 0 * Vertex.size) + width);
			}
		}
	}
	public void setHeight(int index, float height) {
		int start = index * Vertex.size * 4 + 1;
		
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			if(i == 2 || i == 3) {
				vertices.put(start + i * Vertex.size, vertices.get(start + 0 * Vertex.size) + height);
			}
		}
	}
	
	public void setColor(int index, Vector4f color) {
		int start = index * Vertex.size * 4 + 3;
		float offset = vertices.get(start + 2 * Vertex.size) - vertices.get(start);
		for(int i = 0; i < 4; i++) {
			
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
		return vertices.get(index * Vertex.size * 4);
	}
	public float getY(int index) {
		return vertices.get(index * Vertex.size * 4 + 1);
	}
	
	public float getWidth(int index) {
		return vertices.get((index * Vertex.size * 4) + Vertex.size * 2) - vertices.get(index * Vertex.size * 4);
	}
	
	public float[] getShadowFromPoint(int index, Vector2f point) {
		int i = index * Vertex.size * 4;
		
		Vector2f vert1 = new Vector2f();
		vert1.x = vertices.get(i + 0 * Vertex.size + 0);
		vert1.y = vertices.get(i + 0 * Vertex.size + 1);
		
		
		
		Vector2f vert2 = new Vector2f();
		vert2.x = vertices.get(i + 3 * Vertex.size + 0);
		vert2.y = vertices.get(i + 3 * Vertex.size + 1);
		System.out.println("vvv   "+vert2.x+"   "+ vert2.y);
		
		float dis = point.distance(vert1);
		float angle1 = (float) Math.asin((point.y - vert1.y) / dis);
		System.out.println("angle  " + angle1);
		
		float dis2 = point.distance(vert2);
		float angle2 = (float) Math.asin((point.y - vert2.y) / dis2);
		System.out.println("angle  " + angle2);
		
		float shadowDis = 2000;
		
		float[] shadow =new float[3 * 4];
		
		
		shadow[0] = vert1.x;
		shadow[1] = vert1.y;
		shadow[2] = 0;
		
		shadow[3] = vert2.x;
		shadow[4] = vert2.y;
		shadow[5] = 0;
		
		shadow[6] = (float) (Math.cos(angle1) * -shadowDis);
		shadow[7] = (float) (Math.sin(angle1) * -shadowDis);
		shadow[8] = 0;
		
		shadow[9] = (float) (Math.cos(angle2) * -shadowDis);
		shadow[10] = (float) (Math.sin(angle2) * -shadowDis);
		shadow[11] = 0;
		
		//float angle = point.angle(vert1);
		
		for(int ii = 0; ii < shadow.length; ii+=3) {
			System.out.println("");
			System.out.print(shadow[ii + 0] + "   ");
			System.out.print(shadow[ii + 1] + "   ");
			System.out.print(shadow[ii + 2] + "   ");
		}
		
		return shadow;
		
	}
	
	public void updateMax() {
		glBindBuffer(GL_ARRAY_BUFFER, vao.getV_id());
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
