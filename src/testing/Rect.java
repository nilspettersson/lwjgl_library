package testing;

import niles.lwjgl.entites.Entity;
import niles.lwjgl.entites.Geometry;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Texture;

public class Rect extends Entity {
	
	public Rect() {
		
	}
	

	@Override
	public Geometry createGeometry() {
		// TODO Auto-generated method stub
		return new Geometry(Model.RectVertices(), Model.RectIndices());
	}

	@Override
	public Position createPosition() {
		// TODO Auto-generated method stub
		return new Position(0, 0, 100);
	}

	@Override
	public Material createMaterial() {
		// TODO Auto-generated method stub
		return new Material(new Texture("res/floor.png"), 1, 1);
	}
	
	

}
