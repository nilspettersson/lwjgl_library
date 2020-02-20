package testing;

import org.joml.Vector4f;

import niles.lwjgl.entites.Light;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Mouse;

public class test2 extends Game{

	public test2(int width, int height, boolean fullsceen, Vector4f backgroundColor, int fps) {
		super(width, height, fullsceen, backgroundColor, fps);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test2(1920, 1080, true, new Vector4f(0,0,0,1), 120);
	}
	Rect rect;
	Light lights;
	Mouse mouse;
	@Override
	public void setup() {
		rect=new Rect(new Position(0, 0, 1000),new Material(new Texture("res/castle_wall_slates_diff_8k.jpg"), 1, 2.1f));
		lights=new Light();
		lights.addLight(700, 400, 1, 200, new Vector4f(1f,0.4f,0.4f,1));
		lights.addLight(100, 400, 40, 100, new Vector4f(0.3f,1f,1f,1));
		lights.addLight(1500, 400, 40, 100, new Vector4f(0.6f,0.6f,1f,1));
		lights.addLight(1000, 100, 40, 100, new Vector4f(0.4f,1f,0.2f,1));
		lights.addLight(300, 900, 40, 100, new Vector4f(1f,0.3f,0.1f,1));
		
		mouse=new Mouse();
	}
	
	@Override
	public void update() {
		mouse.moveCamera(getWindow(), getCamera(), 40f);
		mouse.isVisible(getWindow(), false);
		
		getRenderer().bindShader();
		
		getRenderer().init(getCamera(),lights);
		getRenderer().render(getCamera(), rect);
		
		getWindow().update(120);
		
	}

}
