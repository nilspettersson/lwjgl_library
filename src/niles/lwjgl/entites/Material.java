package niles.lwjgl.entites;

import org.joml.Vector4f;

import niles.lwjgl.util.Texture;

public class Material {
	
	private Vector4f diffuseColor;
	private float roughness;
	private float bump;
	private Texture diffuseTexture;
	private Texture bumpTexture;
	
	public Material(Texture diffuseTexture,float roughness, float bump) {
		this.diffuseTexture=diffuseTexture;
		this.roughness=roughness;
		this.bump=bump;
	}
	
	public Material(Vector4f diffuseColor,float roughness, float bump) {
		this.roughness=roughness;
		this.bump=bump;
		this.diffuseColor=diffuseColor;
	}
	
	
	public Vector4f getDiffuseColor() {
		return diffuseColor;
	}
	public void setDiffuseColor(Vector4f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}
	public float getRoughness() {
		return roughness;
	}
	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}
	public float getBump() {
		return bump;
	}
	public void setBump(float bump) {
		this.bump = bump;
	}
	public Texture getDiffuseTexture() {
		return diffuseTexture;
	}
	public void setDiffuseTexture(Texture diffuseTexture) {
		this.diffuseTexture = diffuseTexture;
	}

	public Texture getBumpTexture() {
		return bumpTexture;
	}

	public void setBumpTexture(Texture bumpTexture) {
		this.bumpTexture = bumpTexture;
	}
	
	
	

}
