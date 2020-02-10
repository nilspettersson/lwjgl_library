package niles.lwjgl.entites;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Position {
	
	private Matrix4f position;
	
	
	
	public Position(float x,float y, float scale) {
		position=new Matrix4f();
		translate(new Vector3f(x,y,0), scale, scale);
	}
	
	
	
	
	
	public void translate(Vector3f vec,float width,float height ) {
		position=new Matrix4f();
		position.translate(vec).scale(width, height, 0);
	}

	public Matrix4f getPosition() {
		return position;
	}

	public void setPosition(Matrix4f position) {
		this.position = position;
	}
	
	public float getX() {
		return position.m30;
	}
	public float getY() {
		return position.m31;
	}public float getWidth() {
		return position.m00;
	}
	public float getHeight() {
		return position.m11;
	}
	
	public void move(float x,float y) {
		position.translate(new Vector3f(x, y, 0));
	}
	public void moveTo(float x,float y) {
		position=new Matrix4f();
		position.translate(new Vector3f(x, y, 0));
	}
	
	

}
