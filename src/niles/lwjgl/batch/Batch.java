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
	
	public float getHeight(int index) {
		return vertices.get((index * Vertex.size * 4 + 1) + Vertex.size * 2) - vertices.get(index * Vertex.size * 4 + 1);
	}
	
	private Vector2f getVertexPosition(int index, int vertex) {
		int i = index * Vertex.size * 4;
		
		Vector2f vert = new Vector2f();
		vert.x = vertices.get(i + vertex * Vertex.size + 0);
		vert.y = vertices.get(i + vertex * Vertex.size + 1);
		
		return vert;
	}
	
	private Vector2f getAngle(Vector2f vertPosition, Vector2f point) {
		
		double xdif = (point.x - vertPosition.x);
		double ydif = (point.y - vertPosition.y);
		double dis = Math.sqrt((xdif * xdif) + (ydif * ydif));
		double angle = Math.asin((ydif) / dis);
		
		if(point.x < vertPosition.x) {
			angle = (float) (angle + Math.PI);
		}
		else {
			angle = (float) (Math.PI - angle + Math.PI);
		}
		
		return new Vector2f((float)angle, (float)dis);
		
	}
	
	public float[] getShadowFromPoint(int index, Vector2f point, float shadowLength) {
		
		int i = index * Vertex.size * 4;
		
		Vector2f vert1 = getVertexPosition(index, 0);
		Vector2f angle1 = getAngle(vert1, point);
		
		Vector2f vert2 = getVertexPosition(index, 1);
		Vector2f angle2 = getAngle(vert2, point);
		
		Vector2f vert3 = getVertexPosition(index, 2);
		Vector2f angle3 = getAngle(vert3, point);
		
		Vector2f vert4 = getVertexPosition(index, 3);
		Vector2f angle4 = getAngle(vert4, point);
		
		
		
		
		float[] shadow =new float[3 * 4 * 4];
		
		
		shadow[0] = vert1.x;
		shadow[1] = vert1.y;
		shadow[2] = 0;
		
		shadow[3] = vert2.x;
		shadow[4] = vert2.y;
		shadow[5] = 0;
		
		shadow[6] = (float) (Math.cos(angle1.x) * -angle1.y * shadowLength) + vert1.x;
		shadow[7] = (float) (Math.sin(angle1.x) * angle1.y * shadowLength) + vert1.y;
		shadow[8] = 0;
		
		shadow[9] = (float) (Math.cos(angle2.x) * -angle2.y * shadowLength) + vert2.x;
		shadow[10] = (float) (Math.sin(angle2.x) * angle2.y * shadowLength) + vert2.y;
		shadow[11] = 0;
		
		
		
		shadow[12] = vert2.x;
		shadow[13] = vert2.y;
		shadow[14] = 0;
		
		shadow[15] = vert3.x;
		shadow[16] = vert3.y;
		shadow[17] = 0;
		
		shadow[18] = (float) (Math.cos(angle2.x) * -angle2.y * shadowLength) + vert2.x;
		shadow[19] = (float) (Math.sin(angle2.x) * angle2.y * shadowLength) + vert2.y;
		shadow[20] = 0;
		
		shadow[21] = (float) (Math.cos(angle3.x) * -angle3.y * shadowLength) + vert3.x;
		shadow[22] = (float) (Math.sin(angle3.x) * angle3.y * shadowLength) + vert3.y;
		shadow[23] = 0;
		
		
		shadow[24] = vert3.x;
		shadow[25] = vert3.y;
		shadow[26] = 0;
		
		shadow[27] = vert4.x;
		shadow[28] = vert4.y;
		shadow[29] = 0;
		
		shadow[30] = (float) (Math.cos(angle3.x) * -angle3.y * shadowLength) + vert3.x;
		shadow[31] = (float) (Math.sin(angle3.x) * angle3.y * shadowLength) + vert3.y;
		shadow[32] = 0;
		
		shadow[33] = (float) (Math.cos(angle4.x) * -angle4.y * shadowLength) + vert4.x;
		shadow[34] = (float) (Math.sin(angle4.x) * angle4.y * shadowLength) + vert4.y;
		shadow[35] = 0;
		
		
		shadow[36] = vert1.x;
		shadow[37] = vert1.y;
		shadow[38] = 0;
		
		shadow[39] = vert4.x;
		shadow[40] = vert4.y;
		shadow[41] = 0;
		
		shadow[42] = (float) (Math.cos(angle1.x) * -angle1.y * shadowLength) + vert1.x;
		shadow[43] = (float) (Math.sin(angle1.x) * angle1.y * shadowLength) + vert1.y;
		shadow[44] = 0;
		
		shadow[45] = (float) (Math.cos(angle4.x) * -angle4.y * shadowLength) + vert4.x;
		shadow[46] = (float) (Math.sin(angle4.x) * angle4.y * shadowLength) + vert4.y;
		shadow[47] = 0;
		
		
		
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
