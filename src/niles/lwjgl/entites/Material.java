package niles.lwjgl.entites;

import niles.lwjgl.util.Texture;

public class Material {
	
	private float diffuseColor;
	private float roughness;
	private float bump;
	private Texture diffuseTexture;
	private Texture bumpTexture;
	
	public Material(Texture diffuseTexture,float roughness, float bump) {
		this.diffuseTexture=diffuseTexture;
		this.roughness=roughness;
		this.bump=bump;
	}
	
	public float getDiffuseColor() {
		return diffuseColor;
	}
	public void setDiffuseColor(float diffuseColor) {
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
