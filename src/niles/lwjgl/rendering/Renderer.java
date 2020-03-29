package niles.lwjgl.rendering;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import niles.lwjgl.entites.Entity;
import niles.lwjgl.entites.Geometry;
import niles.lwjgl.entites.Light;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Rect;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Camera;

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
	
	
	public void init(Camera camera,Light lights) {
		//shader.setUniform("lights", lights.getPositions());
		
		ArrayList<Matrix4f>positionsRelative=new ArrayList<Matrix4f>();
		
		for(int i=0;i<lights.getPositions().size();i++) {
			positionsRelative.add(new Matrix4f(lights.getPositions().get(i).m00, lights.getPositions().get(i).m01, lights.getPositions().get(i).m02, lights.getPositions().get(i).m03, lights.getPositions().get(i).m10, lights.getPositions().get(i).m11, lights.getPositions().get(i).m12, lights.getPositions().get(i).m13, lights.getPositions().get(i).m20, lights.getPositions().get(i).m21, lights.getPositions().get(i).m22, lights.getPositions().get(i).m23, lights.getPositions().get(i).m30, lights.getPositions().get(i).m31, lights.getPositions().get(i).m32, lights.getPositions().get(i).m33));
			positionsRelative.get(i).m00+=camera.getProjection().m30*1920/2;
			positionsRelative.get(i).m01+=camera.getProjection().m31*1080/2;
		}
		
		shader.setUniform("lights", positionsRelative);
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
			shader.setUniform("color", new Vector4f(0));
		}
		shader.setUniform("bump", material.getBump());
		if(material.getDiffuseTexture()!=null) {
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
