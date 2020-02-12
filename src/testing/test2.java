package testing;

import org.joml.Vector4f;

import niles.lwjgl.entites.Geometry;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.Renderer;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Texture;

public class test2 extends Game{

	public test2(int width, int height, boolean fullsceen, Vector4f backgroundColor, int fps) {
		super(width, height, fullsceen, backgroundColor, fps);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test2(1920, 1080, true, new Vector4f(0,0,0,1), 120);
	}

	Renderer renderer;
	Rect rect;
	
	Material material;
	Position pos;
	Geometry gem;
	
	@Override
	public void setup() {
		renderer=new Renderer();
		
		material=new Material(null, 1, 1);
		material.setDiffuseColor(new Vector4f(1,1,1,1));
		pos=new Position(0, 0, 100);
		gem=new Geometry(Model.RectVertices(), Model.RectIndices());
		
		rect=new Rect();
		
	}

	@Override
	public void update() {
		
		renderer.bindShader();
		renderer.render(getCamera(), rect);
		
	}

}
