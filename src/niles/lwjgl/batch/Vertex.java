package niles.lwjgl.batch;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Vertex {
	
	private Vector3f position;
	private Vector4f color;
	
	public Vertex(Vector3f position, Vector4f color) {
		this.position = position;
		this.color = color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
	
	

}
