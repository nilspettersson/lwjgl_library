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
	
	
	public void moveTo(int index,float x,float y,float z) {
		positions.get(index).m00=x;
		positions.get(index).m01=y;
		positions.get(index).m02=z;
	}

	public void move(int index,float x,float y,float z) {
		positions.get(index).m00+=x;
		positions.get(index).m01+=y;
		positions.get(index).m02+=z;
	}
	
	

}
