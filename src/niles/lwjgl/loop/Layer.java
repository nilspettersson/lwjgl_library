package niles.lwjgl.loop;

import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.util.Texture;

public class Layer {
	
	private Batch batch;
	
	public Layer(int entityMax) {
		batch = new Batch(entityMax);
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
	
	public void getX(int entity) {
		getBatch().getX(entity);
	}
	
	public void getY(int entity) {
		getBatch().getY(entity);
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
	
	
	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}
