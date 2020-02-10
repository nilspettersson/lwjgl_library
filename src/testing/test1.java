package testing;


import java.util.ArrayList;

import org.joml.Vector4f;

import niles.lwjgl.entites.Geometry;
import niles.lwjgl.entites.Light;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.MouseCursor;

public class test1 extends Game  {
	
	public test1(int width, int height, boolean fullsceen, Vector4f backgroundColor, int fps) {
		super(width, height, fullsceen, backgroundColor, fps);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new test1(1920, 1080, true, new Vector4f(0,0,0,1), 120);
		
	}
	
	Geometry g;
	Material material;
	ArrayList<Position>positions;
	Light lights;
	
	MouseCursor mouse;
	
	@Override
	public void setup() {
		mouse=new MouseCursor();
		getWindow().setVSync(false);
		
		positions=new ArrayList<Position>();
		for(int i=0;i<1;i++) {
			positions.add(new Position(0,0,800));
		}
		
		g=new Geometry(Model.RectVertices(), Model.RectIndices());
		material=new Material(new Texture("res/castle_wall_slates_diff_8k.jpg"), 1, 0.4f);
		material.setBumpTexture(new Texture("res/castle_wall_slates_diff_8k.jpg"));
		
		
		
		
		//g.setTexture(new Texture("res/wood_planks_old_0087_01.jpg"));
		//g.setTexture(new Texture("res/castle_wall_slates_diff_8k.jpg"));
		
		
		lights=new Light();
		//lights.addLight(1000, 500, 800f,800f, new Vector4f(1,1,1,0));
		lights.addLight(900, 500, 0f,500f, new Vector4f(1,1f,1f,1));
		//lights.addLight(1500, 520, 100f,200f, new Vector4f(0,0.2f,1f,1));
		
		
		
	}
	
	
	
	float bump=0;
	
	@Override
	public void update() {
		
		lights.moveTo(0,mouse.getMouseMovement(getWindow(), 1).x+1920/2, -mouse.getMouseMovement(getWindow(), 1).y+1080/2,340f);
		
		g.bindShader();
		g.init(lights);
		g.applyMaterial(material);
		g.applyDiffuseTexture(material);
		g.applyBumpTexture(material);
		
		
		for(int i=0;i<positions.size();i++) {
			//g.addBump((float)Math.sin(bump)+1);
			//g.addBump(0.5f);
			g.render(getCamera(),positions.get(i).getPosition());
			//bump+=0.008;
		}
		
		
		
	}

	


}
