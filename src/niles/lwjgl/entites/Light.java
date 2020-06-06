package niles.lwjgl.entites;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Light {
	
	private ArrayList<Matrix4f>positions;
	
	public Light() {
		positions=new ArrayList<Matrix4f>();
	}
	
	public void addLight(float x,float y,float z,float intensity,Vector4f color) {
		positions.add(new Matrix4f(x, y, z, intensity, color.x, color.y, color.z, color.w, 0, 0, 0, 0, 0, 0, 0, 0));
	}

	public ArrayList<Matrix4f> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Matrix4f> positions) {
		this.positions = positions;
	}
	
	public float getX(int index) {
		return positions.get(index).m00;
	}
	public float getY(int index) {
		return positions.get(index).m01;
	}
	
	public float getZ(int index) {
		return positions.get(index).m02;
	}
	
	public void setX(int index, float x) {
		positions.get(index).m00=x;
	}
	
	public void setY(int index, float y) {
		positions.get(index).m01=y;
	}
	
	public void setZ(int index, float z) {
		positions.get(index).m02=z;
	}
	

}
