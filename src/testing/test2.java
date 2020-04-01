package testing;

import org.joml.Vector4f;

import niles.lwjgl.entites.Light;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.entites.Rect;
import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Mouse;

public class test2 extends Game{
	
	public test2() {
		
	}

	public static void main(String[] args) {
		new test2();
	}
	Rect rect;
	Light lights;
	Mouse mouse;
	@Override
	public void setup() {
		
		
		setBackgroundColor(new Vector4f(1));
		
		rect=new Rect(new Position(0, 0, 1000),new Material(new Texture("res/floor.png"), 1, 2.1f));
		lights=new Light();
		lights.addLight(700, 400, 1, 200, new Vector4f(1f,0.4f,0.4f,1));
		lights.addLight(100, 400, 40, 100, new Vector4f(0.3f,1f,1f,1));
		lights.addLight(1500, 400, 40, 100, new Vector4f(0.6f,0.6f,1f,1));
		lights.addLight(1000, 100, 40, 100, new Vector4f(0.4f,1f,0.2f,1));
		lights.addLight(300, 900, 40, 100, new Vector4f(1f,0.3f,0.1f,1));
		
		mouse=new Mouse();
		getWindow().setVSync(false);
	}
	
	@Override
	public void update() {
		mouse.moveCamera(getWindow(), getCamera(), 1f);
		mouse.isVisible(getWindow(), false);
		
		getRenderer().bindShader();
		
		getRenderer().init(getCamera(),lights);
		getRenderer().render(getCamera(), rect);
		
		System.out.println(getWindow().getFps());
		setFpsCap(240);
		
	}

}
