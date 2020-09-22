package niles.lwjgl.loop;

import org.joml.Vector2f;
import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.batch.ParticleSystem;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.util.Texture;

public class Layer {
	
	private Batch batch;
	private ShadowModel shadows;
	private ParticleSystem particleSystem;
	
	private boolean usingLights;
	
	public Layer(int entityMax, boolean usingLights) {
		batch = new Batch(entityMax);
		this.usingLights = usingLights;
	}
	
	public void CreateShadows(Vector2f point, float length) {
		if(shadows == null) {
			shadows = new ShadowModel(batch.size() * 4);
		}
		shadows.createShadowFromBatch(batch, point, length);
	}
	
	public void createParticleSystem(float x, float y, float initSpeed, float initAngle, float initRandVel, int particleAmount) {
		particleSystem = new ParticleSystem(x, y, initSpeed, initAngle, initRandVel, particleAmount);
	}
	
	public void updateBuffer() {
		getBatch().updateMax();
	}
	
	public void addEntity(float x, float y, float width, float height, Vector4f color, int textureId) {
		getBatch().addRect(x, y, width, height, color, textureId);
	}
	
	public void addTexture(Texture texture) {
		getBatch().addTexture(texture);
	}
	
	public void bindTextures() {
		getBatch().bindTextures();
	}
	
	
	public float getX(int entity) {
		return getBatch().getX(entity);
	}
	
	public float getY(int entity) {
		return getBatch().getY(entity);
	}
	
	public void setX(int entity, float x) {
		getBatch().setX(entity, x);
	}
	
	public void setY(int entity, float y) {
		getBatch().setY(entity, y);
	}
	
	
	public float getWidth(int entity) {
		return getBatch().getWidth(entity);
	}
	
	public void getHeight(int entity) {
		getBatch().getHeight(entity);
	}
	
	public void setWidth(int entity, float width) {
		getBatch().setWidth(entity, width);
	}
	
	public void setHeight(int entity, float height) {
		getBatch().setHeight(entity, height);
	}
	
	
	
	public boolean isUsingLights() {
		return usingLights;
	}

	public void setUsingLights(boolean usingLights) {
		this.usingLights = usingLights;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public ShadowModel getShadows() {
		return shadows;
	}

	public void setShadows(ShadowModel shadows) {
		this.shadows = shadows;
	}

	public ParticleSystem getParticleSystem() {
		return particleSystem;
	}

	public void setParticleSystem(ParticleSystem particleSystem) {
		this.particleSystem = particleSystem;
	}
	
	
	
}
