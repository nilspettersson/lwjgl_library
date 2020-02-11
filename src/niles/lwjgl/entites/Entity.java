package niles.lwjgl.entites;

public abstract class Entity {
	
	private Position position;
	private Material material;
	
	private static Geometry geometry;
	
	public Entity() {
		createGeometry();
		createPosition();
		createMaterial();
	}
	
	public abstract Geometry createGeometry();
	public abstract Position createPosition();
	public abstract Material createMaterial();
	

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

	public static Geometry getGeometry() {
		return geometry;
	}

	public static void setGeometry(Geometry geometry) {
		Entity.geometry = geometry;
	}
	
	
	
}
