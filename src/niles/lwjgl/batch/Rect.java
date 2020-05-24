package niles.lwjgl.batch;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Rect {
	
	private Vertex[] vertices = new Vertex[4];
	
	public Rect(float x, float y, float width, float height, Vector4f color, float textureId) {
		
		vertices[0] = new Vertex(new Vector3f(x, y, 0), color, textureId, new Vector2f(0, 0));
		vertices[1] = new Vertex(new Vector3f(x + width, y, 0), color, textureId, new Vector2f(1, 0));
		vertices[2] = new Vertex(new Vector3f(x + width, y - height, 0), color, textureId, new Vector2f(1, 1));
		vertices[3] = new Vertex(new Vector3f(x, y - height, 0), color, textureId, new Vector2f(0, 1));
		
	}
	
	public float[] toArray() {
		float[] array = new float[4 * Vertex.size];
		int index = 0;
		for(int i = 0; i < 4; i++) {
			float[] temp = vertices[i].toArray();
			for(int j = 0; j < temp.length; j++) {
				array[index + j] = temp[j];
			}
			index += Vertex.size;
		}
		
		
		return array;
		
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

}
