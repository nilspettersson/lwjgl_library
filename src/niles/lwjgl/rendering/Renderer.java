package niles.lwjgl.rendering;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import niles.lwjgl.entites.Entity;
import niles.lwjgl.entites.Geometry;
import niles.lwjgl.entites.Light;
import niles.lwjgl.entites.Material;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Camera;
import testing.Rect;

public class Renderer {
	
	
	private Shader shader;
	
	public Renderer() {
		shader=new Shader("shader");
	}
	public void bindTexture(Texture texture) {
		texture.bind(0);
	}
	
	public void bindShader() {
		shader.bind();
	}
	
	public void render(Camera camera, Matrix4f position,Geometry geometry) {
		shader.setUniform("projection", camera.getProjection().mul(position));
		geometry.getModel().render();
	}
	
	public void render(Camera camera, Rect entity) {
		shader.setUniform("projection", camera.getProjection().mul(entity.getPosition().getPosition()));
		applyMaterialTexture(entity.getMaterial());
		entity.getGeometry().getModel().render();
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
	
	public void applyMaterialTexture(Material material) {
		shader.setUniform("roughness", material.getRoughness()+1);
		if(material.getDiffuseColor()!=null) {
			shader.setUniform("color", material.getDiffuseColor());
		}
		else {
			shader.setUniform("color", new Vector4f(1));
		}
		shader.setUniform("bump", material.getBump());
		if(!material.getDiffuseTexture().equals(null)) {
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler1"), 0);
			material.getDiffuseTexture().bind(0);
		}
		if(material.getBumpTexture()!=null) {
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler2"), 1);
			material.getBumpTexture().bind(1);
		}
	}
	
	
	public void applyDiffuseTexture(Material material) {
		if(!material.getDiffuseTexture().equals(null)) {
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler1"), 0);
			material.getDiffuseTexture().bind(0);
		}
	}
	public void applyBumpTexture(Material material) {
		if(!material.getBumpTexture().equals(null)) {
			glUniform1i(glGetUniformLocation(shader.getProgram(), "sampler2"), 1);
			material.getBumpTexture().bind(1);
		}
	}
	
	
	
	public void addRoughness(float roughness) {
		shader.setUniform("roughness", roughness+1);
	}
	public void addBump(float bump) {
		shader.setUniform("bump", bump);
	}
	
	
	
	public Shader getShader() {
		return shader;
	}
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	

}
