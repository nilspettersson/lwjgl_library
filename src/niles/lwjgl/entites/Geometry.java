package niles.lwjgl.entites;



import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.util.Hitbox;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Camera;

public class Geometry {
	private Model model;
	
	public Geometry(float[] vertices, int indices[]) {
		float[] tex_coords=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		model=new Model(vertices, tex_coords, indices);
		
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	

}
