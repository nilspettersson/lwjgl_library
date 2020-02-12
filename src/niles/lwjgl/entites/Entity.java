package niles.lwjgl.entites;

public abstract class Entity {
	
	private Position position;
	private Material material;
	
	
	public Entity() {
		position=createPosition();
		material=createMaterial();
	}
	
	public abstract Position createPosition();
	public abstract Material createMaterial();
	
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
