package niles.lwjgl.entites;

import niles.lwjgl.util.Model;
import niles.lwjgl.util.Texture;

public class Rect extends Entity{
	
	public static Geometry geometry=new Geometry(Model.RectVertices(), Model.RectIndices());
	
	public Rect(Position position ,Material material) {
		super(position, material);
	}
	
	public static Geometry getGeometry() {
		return geometry;
	}





	public static void setGeometry(Geometry geometry) {
		Rect.geometry = geometry;
	}
	
	
	

}
