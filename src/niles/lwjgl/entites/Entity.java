package niles.lwjgl.entites;

public abstract class Entity {
	
	private Position position;
	private Material material;
	
	
	public Entity(Position position,Material material) {
		this.position=position;
		this.material=material;
	}
	
	
	public static Geometry getGeometry() {
		return null;
	}
	

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	
	
	
}
