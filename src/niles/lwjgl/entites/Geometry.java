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
	private Shader shader;
	private Texture texture;
	private Texture bumpTexture;
	
	private Vector4f color;
	
	//private Matrix4f position=new Matrix4f(); 
	
	public Geometry(float[] vertices, int indices[]) {
		float[] tex_coords=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		model=new Model(vertices, tex_coords, indices);
		
		shader=new Shader("shader");
		
		
		
		setColor(new Vector4f(0));
		
	}
	public void bindTexture() {
		texture.bind(0);
	}
	
	public void bindShader() {
		shader.bind();
	}
	
	public void render(Camera camera, Matrix4f position) {
		
		
		shader.setUniform("projection", camera.getProjection().mul(position));
		model.render();
	}
	
	public void init(Light lights) {
		shader.setUniform("lights", lights.getPositions());
		shader.setUniform("size", lights.getPositions().size());
		
	}
	public void init() {
		shader.setUniform("size", 0);
	}
	
	
	public void applyMaterial(Material material) {
		shader.setUniform("roughness", material.getRoughness()+1);
		shader.setUniform("color", material.getDiffuseColor());
		shader.setUniform("bump", material.getBump());
		
	}
	public void applyDiffuseTexture(Material material) {
		if(!material.getDiffuseTexture().equals(null)) {
			setTexture(material.getDiffuseTexture());
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler1"), 0);
			texture.bind(0);
		}
	}
	public void applyBumpTexture(Material material) {
		if(!material.getBumpTexture().equals(null)) {
			setBumpTexture(material.getBumpTexture());
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler2"), 1);
			bumpTexture.bind(1);
		}
	}
	
	
	
	public void addRoughness(float roughness) {
		shader.setUniform("roughness", roughness+1);
	}
	public void addBump(float bump) {
		shader.setUniform("bump", bump);
	}
	
	
	
	
	
	
	

	
	
	
	
	
	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Texture getBumpTexture() {
		return bumpTexture;
	}
	public void setBumpTexture(Texture bumpTexture) {
		this.bumpTexture = bumpTexture;
	}
	public Shader getShader() {
		return shader;
	}
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	

}
